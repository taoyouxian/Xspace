package cn.edu.ruc.iir.xspace.object;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: TimeServer
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:28
 **/
public class TimeServer {
    public void bind(int port) throws Exception {
        //配置服务端NIO 线程组
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();

        try {
            server.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)),
                                    new TimeServerHandler());
                        }
                    });

            //绑定端口, 同步等待成功
            ChannelFuture future = server.bind(port).sync();

            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } finally {
            //优雅关闭 线程组
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    /**
     * main 函数
     *
     * @param args
     */
    public static void main(String[] args) {
        TimeServer server = new TimeServer();
        try {
            server.bind(18888);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
