package com.ldg.netty.a20180122.client;

import com.ldg.netty.a20180122.codec.MyByteToLongDecode;
import com.ldg.netty.a20180122.codec.MyByteToLongDecode2;
import com.ldg.netty.a20180122.codec.MyLongToByteEncode;
import com.ldg.netty.a20180122.codec.MyStringToLongEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyClientInititializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast(new MyByteToLongDecode2());
        //注意出站是从最下面网上走的！！！
        channelPipeline.addLast(new MyLongToByteEncode());
        channelPipeline.addLast(new MyStringToLongEncode());
        channelPipeline.addLast(new MyClientHandler());//自己的处理器
    }
}
