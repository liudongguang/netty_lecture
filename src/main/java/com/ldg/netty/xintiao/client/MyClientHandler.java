package com.ldg.netty.xintiao.client;

import com.ldg.netty.xintiao.Constant;
import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        for (int i = 0; i < 10; i++) {
            String requestMessage="send from client";
            MessageProtocol messageProtocol =new MessageProtocol();
            messageProtocol.setType(Constant.client_msg);
            messageProtocol.setContent(requestMessage);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        byte type = msg.getType();
        int length=msg.getLength();
        String content=msg.getContent();
        System.out.println("客户端端接受到的数据：length:"+length+"  内容："+content+"  type:"+type);
        System.out.println("客户端接受到的消息数："+(++count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
