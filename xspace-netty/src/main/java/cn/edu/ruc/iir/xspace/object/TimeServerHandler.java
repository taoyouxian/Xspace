package cn.edu.ruc.iir.xspace.object;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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

        System.out.println("Server -> read, " + msg);
        if (msg instanceof ReqParams) {
            ReqParams params = (ReqParams) msg;
            String res;
            switch (params.getAction()) {
                case "getSchemas": {
                    res = "getSchemas";
                    break;
                }
                case "getTables": {
                    res = "getTables";
                    break;
                }
                case "getLayouts": {
                    res = "getLayouts";
                    break;
                }
                case "getColumns": {
                    res = "getColumns";
                    break;
                }
                default: {
                    res = "action default";
                    break;
                }
            }
            ResParams resParams = new ResParams(params.getAction());
            resParams.setResult(res);
//            ChannelFuture f = ctx.write(resParams);
//            f.addListener(ChannelFutureListener.CLOSE);
            ChannelFuture future = ctx.writeAndFlush(resParams);
            future.addListener((ChannelFutureListener) channelFuture -> ctx.close());
        } else {
            ResParams resParams = new ResParams("Default");
            resParams.setResult("DefaultRes");
//            ChannelFuture f = ctx.write(resParams);
//            f.addListener(ChannelFutureListener.CLOSE);
            ChannelFuture future = ctx.writeAndFlush(resParams);
//            future.addListener((ChannelFutureListener) channelFuture -> ctx.close());
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server -> read complete");

        //将发送缓冲区中数据全部写入SocketChannel
        ctx.flush();
//        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //释放资源
        ctx.close();
    }
}
