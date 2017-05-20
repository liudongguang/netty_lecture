package com.ldg.netty.socket2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端地址："+ctx.channel().remoteAddress());
        System.out.println("服务器端发送过来的信息，客户端输出："+msg);
        ctx.writeAndFlush("from client:"+ LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.writeAndFlush("来自客户端的问候！");
    }
}
