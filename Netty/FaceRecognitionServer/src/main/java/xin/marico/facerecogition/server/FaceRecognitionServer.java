package xin.marico.facerecogition.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import xin.marico.facerecogition.initializer.HttpInitializer;
import xin.marico.facerecogition.initializer.SocketInitializer;

public class FaceRecognitionServer {

    private static int SOCKET_POOR = 6666;

    private static int HTTP_PORT = 8888;

    public static void socketProcess() {
        System.out.println("启动监听socket连接----");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new SocketInitializer());
            Channel channel = bootstrap.bind(SOCKET_POOR).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void httpProcess() {
        System.out.println("启动监听http连接----");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new HttpInitializer());
            Channel channel = bootstrap.bind(HTTP_PORT).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        System.out.println("---------------启动AI服务器-----------------");
        Thread httpProcess = new Thread(() -> {
            httpProcess();
        });
        Thread socketProcess = new Thread(() -> {
            socketProcess();
        });
        httpProcess.start();
        socketProcess.start();
    }
}
