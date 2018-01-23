package com.ldg.netty.xintiao.server;

import com.ldg.netty.xintiao.Constant;
import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.UUID;

public class ServerHeartHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                System.out.println("读空闲.....");
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                System.out.println("写空闲....."+ctx);
                ctx.writeAndFlush(Constant.sbeat);
            }
        }
    }

    /**
     * 异常情况
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
