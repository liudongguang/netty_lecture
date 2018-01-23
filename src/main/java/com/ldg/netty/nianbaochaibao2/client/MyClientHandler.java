package com.ldg.netty.nianbaochaibao2.client;

import com.ldg.netty.nianbaochaibao2.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {
    private int count;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        for (int i = 0; i < 10; i++) {
            String requestMessage="send from client";
            byte[] bytes = requestMessage.getBytes("utf-8");
            PersonProtocol personProtocol=new PersonProtocol();
            personProtocol.setLength(bytes.length);
            personProtocol.setContent(bytes);
            ctx.writeAndFlush(personProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length=msg.getLength();
        byte[] content=msg.getContent();
        System.out.println("客户端端接受到的数据：length:"+length+"  内容："+new String(content,Charset.forName("utf-8")));
        System.out.println("客户端接受到的消息数："+(++count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
