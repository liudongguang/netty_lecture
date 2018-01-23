package com.ldg.netty.xintiao.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class MyHeartHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
       // msg.retain();
        System.out.println(msg);
        ctx.fireChannelRead(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲.....");
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                //  System.out.println("写空闲.....");
            }else if (e.state() == IdleState.ALL_IDLE) {
                //  System.out.println("读写空闲.....");
            }
        }
    }
}
