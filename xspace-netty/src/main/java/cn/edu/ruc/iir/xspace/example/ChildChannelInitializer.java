package cn.edu.ruc.iir.xspace.example;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: ChildChannelInitializer
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:28
 **/
public class ChildChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        channel.pipeline().addLast(new StringDecoder());

        channel.pipeline().addLast("timeServerHandler", new TimeServerHandler());
    }
}
