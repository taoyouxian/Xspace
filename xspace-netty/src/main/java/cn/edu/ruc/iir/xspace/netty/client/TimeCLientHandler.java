package cn.edu.ruc.iir.xspace.netty.client;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.netty.client
 * @ClassName: TimeCLientHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-01-26 15:13
 **/
public class TimeCLientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMSG;

    public TimeCLientHandler() {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMSG = Unpooled.buffer(req.length);
        firstMSG.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client -> active");

        ctx.writeAndFlush(firstMSG);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client -> read");

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, "UTF-8");

        System.out.println("NOW is: " + body);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
