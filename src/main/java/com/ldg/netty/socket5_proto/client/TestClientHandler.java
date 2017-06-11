package com.ldg.netty.socket5_proto.client;

import com.ldg.netty.socket5_proto.MyDataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liudo on 2017/6/11.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端会像服务端发送请求
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder().setName("张三").setAge("20岁").setAddress("北京").build();
        ctx.channel().writeAndFlush(person);
    }
}
