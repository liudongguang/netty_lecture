package com.ldg.netty.nianbaochaibao2.client;

import com.ldg.netty.nianbaochaibao2.codec.decode.MyPersonDecode;
import com.ldg.netty.nianbaochaibao2.codec.encode.MyPersonEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientInititializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast(new MyPersonDecode());
        channelPipeline.addLast(new MyPersonEncoder());
        channelPipeline.addLast(new MyClientHandler());//自己的处理器
    }
}
