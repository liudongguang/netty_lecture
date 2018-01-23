package com.ldg.netty.xintiao.server;


import com.ldg.netty.xintiao.codec.decode.MyPersonDecode;
import com.ldg.netty.xintiao.codec.encode.MyPersonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
       // channelPipeline.addLast(new IdleStateHandler(3,4,5));
        channelPipeline.addLast(new MyHeartHandler());
        channelPipeline.addLast(new MyPersonDecode());
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new MyServerHandler());//自己的处理器
    }
}
