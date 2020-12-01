package xin.marico.facerecogition.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import redis.clients.jedis.Jedis;
import xin.marico.facerecogition.vo.ImageProto;
import xin.marico.facerecogition.vo.ResultProto;
import xin.marico.facerecogition.utils.UUIDUtils;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SocketHandler extends SimpleChannelInboundHandler<ImageProto.Image> {

    private String STORE_PATH = "/FaceRecognition/unknown_pictures/";

    private final static String uploadFaceIdQueue = "upload_faceid_queue_";

    private final static String faceRecogintionQueue = "face_recogintion_queue_";

    private final static int uploadProcessCount = 1;

    private final static int recognitionProcessCount = 2;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        // 心跳包检测不到长连接无读写操作，就断开连接
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        System.out.println("发现异常，....");
        ResultProto.Result result = ResultProto.Result.newBuilder().setStatus(401).setMessage("数据格式错误").build();
        ctx.writeAndFlush(result);
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ImageProto.Image image) throws Exception {
        System.out.println("-------------------收到来自客户端的数据--------------------");
        ResultProto.Result result = null;
        Long startTime = (new Date()).getTime();
        String personName = image.getPersonName();
        String imgKey = UUIDUtils.getCode();
        String resultKey = UUIDUtils.getCode();
        Jedis jedis = null;
        try {
            List<Integer> dataList = image.getDataList();
            jedis = new Jedis("localhost", 6379);
            String imgData = JSON.toJSONString(dataList);
            jedis.hset(imgKey, "personName", personName);
            jedis.hset(imgKey, "data", imgData);
            jedis.hset(imgKey, "cols", String.valueOf(image.getCols()));
            jedis.hset(imgKey, "rows", String.valueOf(image.getRows()));
            System.out.println("图片临时存储在redis的:" + imgKey);
            System.out.println("rows:" + String.valueOf(image.getRows()));
            System.out.println("cols:" + String.valueOf(image.getCols()));
            int randomId = 0;
            if ("".equals(personName) || "null".equals(personName)) {
                // 执行人脸识别
                randomId = new Random().nextInt(recognitionProcessCount);
                System.out.println("人脸识别：" + faceRecogintionQueue + randomId);
                jedis.rpush(faceRecogintionQueue + randomId, imgKey + "_" + resultKey);
            } else {
                System.out.println("Face Id：" + personName);
                // 执行人脸id上传
                randomId = new Random().nextInt(uploadProcessCount);
                System.out.println("人脸上传：" + uploadFaceIdQueue + randomId);
                jedis.rpush(uploadFaceIdQueue + randomId, imgKey + "_" + resultKey);
            }
            int waitCount = 1;
            while (true) {
                Thread.sleep(1);
                if (waitCount == 10000) {
                    result = ResultProto.Result.newBuilder().
                            setStatus(500).
                            setMessage("发生未知错误导致程序执行超时").build();
                    break;
                }
                if (jedis.exists(resultKey)) {
                    String resultStr = jedis.get(resultKey);
                    result = ResultProto.Result.newBuilder().
                            setStatus(Integer.parseInt(resultStr.split("_")[0])).
                            setMessage(resultStr.split("_")[1]).build();
                    jedis.del(resultKey);
                    break;
                }
                waitCount++;
            }
            ///给客户端回复
            System.out.println("服务器回复:" + result.getStatus() + "---" + result.getMessage());
            System.out.println(((new Date()).getTime() - startTime) / 1000.0 + "秒");
        } catch (Exception e) {
            e.printStackTrace();
            result = ResultProto.Result.newBuilder().
                    setStatus(500).
                    setMessage("程序发生未知异常").build();
        } finally {
            jedis.del(imgKey);
            jedis.del(resultKey);
            jedis.close();
            channelHandlerContext.writeAndFlush(result);
        }
    }

}
