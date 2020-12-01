package xin.marico.facerecogition.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import redis.clients.jedis.Jedis;
import xin.marico.facerecogition.utils.RequestUtils;
import xin.marico.facerecogition.utils.UUIDUtils;
import xin.marico.facerecogition.vo.Image;
import xin.marico.facerecogition.vo.Result;
import xin.marico.facerecogition.vo.ResultProto;

import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.net.URI;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_HEADERS;
import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_METHODS;
import static io.netty.handler.codec.http.HttpHeaders.Names.ACCESS_CONTROL_ALLOW_ORIGIN;

public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    private String STORE_PATH = "/FaceRecognition/unknown_pictures/";

    private final static String uploadFaceIdQueue = "upload_faceid_queue_";

    private final static String faceRecogintionQueue = "face_recogintion_queue_";

    private final static int uploadProcessCount = 1;

    private final static int recognitionProcessCount = 2;
    private AbstractDocument.Content content;

    //响应
    private void response(ChannelHandlerContext ctx, Result result) {
        // 1.设置响应
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(JSONObject.toJSONString(result), CharsetUtil.UTF_8));

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=UTF-8");
        ////设置跨域
        response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.headers().set(ACCESS_CONTROL_ALLOW_HEADERS, "Origin, X-Requested-With, Content-Type, Accept");
        response.headers().set(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT,DELETE");
        response.headers().set(CONTENT_LENGTH, response.content().readableBytes());
        System.out.println("服务器回复：" + JSONObject.toJSONString(result));
        // 2.发送
        // 注意必须在使用完之后，close channel
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("收到http连接");
        FullHttpRequest request = (FullHttpRequest) msg;
        URI uri = new URI(request.uri());
        System.out.println(uri);
        Result result = new Result(100, "123");
        // 对图标的请求不做任何处理
        if ("/favicon.ico".equals(uri.getPath())) {
            return;
        }
        if (!"/checkFace".equals(uri.getPath()) && !"/uploadFace".equals(uri.getPath())) {
            // 过滤非人脸识别和上传的api
            result.setStatus(404);
            result.setMessage("Not Found！");
            ctx.writeAndFlush(result);
            response(ctx, result);
            return;
        }
        Map<String, Object> stringStringMap = RequestUtils.parseParameter(request);
        Long startTime = (new Date()).getTime();
        Integer cols = Integer.parseInt((String) stringStringMap.get("cols"));
        Integer rows = Integer.parseInt((String) stringStringMap.get("rows"));
        String personName = (String) stringStringMap.get("personName");
        System.out.println("Face Id：" + personName);
        List<Integer> dataList = Arrays.stream(((String) stringStringMap.get("data")).
                split(",")).
                map(Integer::parseInt).
                collect(Collectors.toList());
        if (!stringStringMap.containsKey("data") || cols == null
                || rows == null || dataList.size() != cols * rows * 3
                || "/uploadFace".equals(uri.getPath())
                && (personName == null || "".equals(personName))) {
            result.setStatus(402);
            result.setMessage("数据出错，数据不全或者格式不对");
            response(ctx, result);
            return;
        }
        Jedis jedis = null;
        String imgKey = UUIDUtils.getCode();
        String resultKey = UUIDUtils.getCode();
        try {
            jedis = new Jedis("localhost", 6379);
            if (personName != null && "/uploadFace".equals(uri.getPath()))
                jedis.hset(imgKey, "personName", personName);
            jedis.hset(imgKey, "data", JSON.toJSONString(dataList));
            jedis.hset(imgKey, "cols", String.valueOf(cols));
            jedis.hset(imgKey, "rows", String.valueOf(rows));
            System.out.println("图片临时存储在redis的:" + imgKey);
            System.out.println("rows:" + String.valueOf(rows));
            System.out.println("cols:" + String.valueOf(cols));
            int randomId = 0;
            if ("/checkFace".equals(uri.getPath())) {
                // 执行人脸识别
                randomId = new Random().nextInt(recognitionProcessCount);
                System.out.println("人脸识别：" + faceRecogintionQueue + randomId);
                jedis.rpush(faceRecogintionQueue + randomId, imgKey + "_" + resultKey);
            } else if ("/uploadFace".equals(uri.getPath())) {
                System.out.println("Face Id：" + personName);
                // 执行人脸id上传
                randomId = new Random().nextInt(uploadProcessCount);
                System.out.println("人脸识别：" + uploadFaceIdQueue + randomId);
                jedis.rpush(uploadFaceIdQueue + randomId, imgKey + "_" + resultKey);
            }
            System.out.println("消息的key：" + imgKey + "_" + resultKey);
            int waitCount = 1;
            while (true) {
                Thread.sleep(1);
                if (waitCount == 10000) {
                    result = new Result(500, "发生未知错误导致程序执行超时");
                    break;
                }
                if (jedis.exists(resultKey)) {
                    String resultStr = jedis.get(resultKey);
                    result = new Result(Integer.parseInt(resultStr.split("_")[0]),
                            resultStr.split("_")[1]);
                    jedis.del(resultKey);
                    break;
                }
                waitCount++;
            }
            System.out.println("服务器回复:" + result.getStatus() + "---" + result.getMessage());
            System.out.println(((new Date()).getTime() - startTime) / 1000.0 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
            result = new Result(500, "程序执行出错");
        } finally {
            jedis.del(imgKey);
            jedis.del(resultKey);
            jedis.close();
            response(ctx, result);
        }
    }
}
