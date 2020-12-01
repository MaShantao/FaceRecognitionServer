package xin.marico.facerecogition.initializer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import xin.marico.facerecogition.vo.ImageProto;
import xin.marico.facerecogition.handler.SocketHandler;

import java.util.concurrent.TimeUnit;

public class SocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        socketChannel.pipeline().addLast("decoder", new ProtobufDecoder(ImageProto.Image.getDefaultInstance()));
        socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        socketChannel.pipeline().addLast("encoder", new ProtobufEncoder());
        socketChannel.pipeline().addLast("heartJump", new IdleStateHandler(12, 12, 20, TimeUnit.SECONDS));
        socketChannel.pipeline().addLast("imageHadler", new SocketHandler());
    }
}
