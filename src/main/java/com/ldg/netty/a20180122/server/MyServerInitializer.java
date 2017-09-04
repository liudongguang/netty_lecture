package com.ldg.netty.a20180122.server;

import com.ldg.netty.a20180122.codec.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        //要在这里将字节转换程long类型，不然下面的入站处理器无法处理Long类型
        channelPipeline.addLast(new MyByteToLongDecode2());
        channelPipeline.addLast(new MyLongToStringDecoder());
        channelPipeline.addLast(new MyLongToByteEncode());
        channelPipeline.addLast(new MyServerHandler());//自己的处理器
    }
}
