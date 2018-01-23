package com.ldg.netty.nianbaochaibao2.server;

import com.ldg.netty.nianbaochaibao2.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol>{
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length=msg.getLength();
        byte[] content=msg.getContent();
        System.out.println("服务端接受到的数据：length:"+length+"  内容："+new String(content,Charset.forName("utf-8")));
        System.out.println("server 消息数量"+(++this.count));
        String reaponseMessage=UUID.randomUUID().toString();
        PersonProtocol personProtocol=new PersonProtocol();
        personProtocol.setLength(reaponseMessage.getBytes("utf-8").length);
        personProtocol.setContent(reaponseMessage.getBytes("utf-8"));
        ctx.writeAndFlush(personProtocol);
    }

    /**
     * 异常情况
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
