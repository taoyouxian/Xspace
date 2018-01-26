package cn.edu.ruc.iir.xspace.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.netty.time
 * @ClassName: ChildChannelInitializer
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-01-26 15:09
 **/
public class ChildChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("timeServerHandler", new TimeServerHandler());
    }
}
