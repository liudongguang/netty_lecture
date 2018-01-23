package com.ldg.netty.nianbaochaibao.client;

import com.ldg.netty.a20180122.codec.MyByteToLongDecode2;
import com.ldg.netty.a20180122.codec.MyLongToByteEncode;
import com.ldg.netty.a20180122.codec.MyStringToLongEncode;
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
        channelPipeline.addLast(new MyClientHandler());//自己的处理器
    }
}
