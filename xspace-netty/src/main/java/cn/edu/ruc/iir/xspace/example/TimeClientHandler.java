package cn.edu.ruc.iir.xspace.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: TimeClientHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:26
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private byte[] req;

    private int counter;

    public TimeClientHandler() {
        this.req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
    }


    /**
     * 链路建立成功时发送100条消息到服务端, 每发送一条就刷新一次数据到SocketChannel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client -> active");
        ByteBuf message = null;

        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client -> read");

//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//
//        String body = new String(req, "UTF-8");
        String body = (String) msg;

        System.out.println("NOW is: " + body + " the counter is:" + (++counter));
//        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete " + " the counter is:" + (counter));
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
