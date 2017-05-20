package com.ldg.netty.socket2.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"         "+msg);//获取客户端的远程地址
        //返回给客户端
        ctx.channel().writeAndFlush("from server:"+ UUID.randomUUID().toString());
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
