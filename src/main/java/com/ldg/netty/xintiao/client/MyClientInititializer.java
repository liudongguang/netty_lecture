package com.ldg.netty.xintiao.client;

import com.ldg.netty.xintiao.codec.decode.MyPersonDecode;
import com.ldg.netty.xintiao.codec.encode.MyPersonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientInititializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
      //  channelPipeline.addLast(new IdleStateHandler(5,2,5));
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new ClientHearHandler());
        channelPipeline.addLast(new MyPersonDecode());
        channelPipeline.addLast(new MyClientHandler());//自己的处理器
    }
}
