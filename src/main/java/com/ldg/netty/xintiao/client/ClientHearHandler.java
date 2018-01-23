package com.ldg.netty.xintiao.client;

import com.ldg.netty.xintiao.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHearHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        //System.out.println("type:" + byteBuf.getByte(4) + msg);
        switch (byteBuf.getByte(4)) {
            case 1:
                ctx.writeAndFlush(Constant.cbeat);
                System.out.println("收到服务端心跳！");
                break;
            default:
                ctx.fireChannelRead(msg);
        }

    }
}
