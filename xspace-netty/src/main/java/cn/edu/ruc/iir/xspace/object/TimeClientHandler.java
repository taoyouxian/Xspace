package cn.edu.ruc.iir.xspace.object;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.example
 * @ClassName: TimeClientHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-09-12 10:26
 **/
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private int counter;
//    private final ByteBuf request;

    private ReqParams params;

    private boolean complete;
    private final String token;
    private final Map<String, String> response;
    private StringBuilder builder = new StringBuilder();


    public TimeClientHandler(ReqParams params, String token, Map<String, String> response) {
        this.params = params;
        this.token = token;
        this.response = response;
//        byte[] req = params.toString().getBytes();
//        request = Unpooled.buffer(req.length);
//        request.writeBytes(req);
    }

    /**
     * 链路建立成功时发送100条消息到服务端, 每发送一条就刷新一次数据到SocketChannel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(token);
        super.channelActive(ctx);
        System.out.println("Active: " + ctx.isRemoved());
        ctx.writeAndFlush(params);
//        ChannelFuture future = ctx.writeAndFlush(this.params);
//        future.addListener((ChannelFutureListener) channelFuture -> System.out.println("params post complete"));
//        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client -> read, " + msg);
        if (msg instanceof Response) {
            Response params = (Response) msg;
            System.out.println(params.toString());

        } else {
            ctx.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete " + " the counter is:" + (counter++));
        ctx.flush();
//        response.put(token, builder.toString());
//        if (complete)
//        {
//            ctx.close();
//        } else
//        {
//            ctx.read();
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
