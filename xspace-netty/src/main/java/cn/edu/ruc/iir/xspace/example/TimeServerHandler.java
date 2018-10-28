package cn.edu.ruc.iir.xspace.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.http.client.utils.DateUtils;

import java.util.Date;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: TimeServerHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:24
 **/
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("Server -> read");

//        ByteBuf buf = (ByteBuf) msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
        String body = (String) msg;

//        String body = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());

        System.out.println("timeServer received order: " + body + "the counter is:" + (++counter));

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? DateUtils.formatDate(new Date()) : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");

        //response
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //异步发送应答消息给客户端: 这里并没有把消息直接写入SocketChannel,而是放入发送缓冲数组中
//        ctx.writeAndFlush(resp);

        ChannelFuture future = ctx.writeAndFlush(resp);
//        future.addListener((ChannelFutureListener) channelFuture -> ctx.close());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server -> read complete");

        //将发送缓冲区中数据全部写入SocketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        ctx.close();
    }
}
