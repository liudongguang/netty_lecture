package com.ldg.netty.socket5_proto.server;

import com.ldg.netty.socket5_proto.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liudo on 2017/6/11.
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {
        System.out.println(msg.getName()+"     "+msg.getAge()+"    "+msg.getAddress());
    }
}
