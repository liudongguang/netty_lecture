package com.ldg.netty.socket2.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyChatClientHandler extends SimpleChannelInboundHandler<String>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
             System.out.println("客户端输出："+msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
