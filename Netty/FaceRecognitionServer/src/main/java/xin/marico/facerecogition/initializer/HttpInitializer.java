package xin.marico.facerecogition.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import xin.marico.facerecogition.handler.HttpHandler;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("httpcodec", new HttpServerCodec());
        //对写大数据流的支持
        ch.pipeline().addLast(new ChunkedWriteHandler());
        //设置单次请求的文件大小上限
        ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024 * 10));
        //websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
        //ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        ch.pipeline().addLast("httpHandler", new HttpHandler());
    }
}
