package com.ldg.netty.nianbaochaibao.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf>{
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
       byte[] buffer=new byte[msg.readableBytes()];
       msg.readBytes(buffer);
       String message=new String(buffer, Charset.forName("utf-8"));
        System.out.println("server 接受到的内容："+message);
        System.out.println("server 消息数量"+(++this.count));
        ByteBuf reaponseByteBuf= Unpooled.copiedBuffer(UUID.randomUUID().toString(),Charset.forName("utf-8"));
        ctx.writeAndFlush(reaponseByteBuf);
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
