package cn.edu.ruc.iir.xspace.object;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.UUID;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: TimeClient
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:27
 **/
public class TimeClient {

    private final ReqParams params;
    private final String token;

    public TimeClient(ReqParams params, String token) {
        this.params = params;
        this.token = token;
    }

    public void connect(int port, String host) throws Exception {
        //配置客户端NIO 线程组
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();

        try {
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new ObjectEncoder());

                            channel.pipeline().addLast(new ObjectDecoder(100 * 1024 * 1024, ClassResolvers.cacheDisabled(null)));

                            channel.pipeline().addLast(new TimeClientHandler(params, token, null));

                        }
                    });

            //绑定端口, 异步连接操作
            ChannelFuture f = client.connect(host, port).sync();

//            Channel channel = f.channel();
//            ChannelFuture taskFuture = channel.writeAndFlush(params);
//            taskFuture.addListener((ChannelFutureListener) future -> System.out.println("params post complete"));
//            channel.closeFuture().sync();
            //等待客户端连接端口关闭
            f.channel().closeFuture().sync();
        } finally {
            //优雅关闭 线程组
            group.shutdownGracefully();
        }
    }

    /**
     * main 函数
     *
     * @param args
     */
    public static void main(String[] args) {
        String action = "getSchemas";
        ReqParams params = new ReqParams(action);
        params.setParam("tableName", "test");
        params.setParam("schemaName", "pixels");

        String token = UUID.randomUUID().toString();
        TimeClient client = new TimeClient(params, token);
        try {
            client.connect(18888, "127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
