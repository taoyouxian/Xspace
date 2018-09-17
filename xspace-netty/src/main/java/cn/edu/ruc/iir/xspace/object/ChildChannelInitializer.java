package cn.edu.ruc.iir.xspace.object;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

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
        channel.pipeline().addLast(new ObjectEncoder());

        channel.pipeline().addLast(new ObjectDecoder(100 * 1024 * 1024, ClassResolvers.cacheDisabled(null)));

        channel.pipeline().addLast("timeServerHandler", new TimeServerHandler());
    }
}
