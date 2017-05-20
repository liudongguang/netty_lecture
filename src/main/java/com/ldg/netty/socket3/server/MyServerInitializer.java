package com.ldg.netty.socket3.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast("IdleStateHandler",new IdleStateHandler(5,7,3, TimeUnit.SECONDS));//空闲检测处理器,在一个时间内没有读或写就触发
        channelPipeline.addLast(new MyServerHandler());//自己的处理器
    }
}
