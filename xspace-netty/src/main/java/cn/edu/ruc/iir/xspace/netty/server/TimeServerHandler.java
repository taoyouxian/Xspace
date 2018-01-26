package cn.edu.ruc.iir.xspace.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.netty.time
 * @ClassName: TimeServerHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-01-26 15:11
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Server -> read");

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req, "UTF-8");

        System.out.println("timeServer received order: " + body);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? df.format(new Date()) : "BAD ORDER";

        //response
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //异步发送应答消息给客户端: 这里并没有把消息直接写入SocketChannel,而是放入发送缓冲数组中
        ctx.writeAndFlush(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server -> read complete");

        //将发送缓冲区中数据全部写入SocketChannel
        //ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        ctx.close();
    }
}
