package cn.edu.ruc.iir.xspace.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;


/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.xspace.netty
 * @ClassName: NettyClientHandler
 * @Description:
 * @author: taoyouxian
 * @date: Create in 2018-01-26 14:50
 **/
public class NettyClientHandler extends ChannelHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
        //发送信息
        ctx.writeAndFlush(getSendByteBuf("客户端-->服务端 你好"));
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO Auto-generated method stub
        ByteBuf buf = (ByteBuf) msg;// 获取服务端传来的Msg
        String recieved = getMessage(buf);
        System.out.println("------收到信息------" + recieved);
    }

     /*
     * 将字节UTF-8编码返回字符串
     */

    private String getMessage(ByteBuf buf) {
        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        return new String(con, CharsetUtil.UTF_8);
    }

    /*
     * 将Sting转化为UTF-8编码的字节
     */
    private ByteBuf getSendByteBuf(String message) throws UnsupportedEncodingException {
        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);
        return pingMessage;
    }
}
