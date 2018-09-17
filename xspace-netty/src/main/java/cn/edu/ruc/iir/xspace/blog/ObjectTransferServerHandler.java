package cn.edu.ruc.iir.xspace.blog;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.blog
 * @ClassName: ObjectTransferServerHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-17 19:32
 **/

public class ObjectTransferServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(ObjectTransferServerHandler.class.getName());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println(msg);
        ctx.writeAndFlush(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws
            Exception {
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }

}
