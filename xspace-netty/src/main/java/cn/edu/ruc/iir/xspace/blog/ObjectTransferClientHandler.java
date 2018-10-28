package cn.edu.ruc.iir.xspace.blog;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.blog
 * @ClassName: ObjectTransferClientHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-17 19:33
 **/

public class ObjectTransferClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(ObjectTransferClientHandler.class.getName());

    private final List<User> message;
    private final User user;

    /**
     * Creates a client-side handler.
     */
    public ObjectTransferClientHandler(int messageSize) {
        if (messageSize <= 0) {
            throw new IllegalArgumentException("firstMessageSize: "
                    + messageSize);
        }
        message = new ArrayList<User>(messageSize);
        for (int i = 0; i < messageSize; i++) {
            User user = new User();
            user.setId(i);
            user.setCardNo("420000" + i);
            user.setName("hu" + i);
            user.setDescription("你觉得这样好吗？？真的好吗" + i);
            message.add(user);
        }
        user = new User();
        user.setId(21);
        user.setCardNo("42000021");
        user.setName("hu21");
        user.setDescription("你觉得这样好吗？？真的好吗");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // Send the message to Server
        super.channelActive(ctx);
        ctx.writeAndFlush(user);
//        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // you can use the Object from Server here
        System.out.println(msg);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        logger.log(Level.WARNING, "Unexpected exception from downstream.",
                cause);
        ctx.close();
    }
}
