package com.ldg.netty.socket2.client;

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
        channelPipeline.addLast("LengthFieldBasedFrameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));//解码
        channelPipeline.addLast("LengthFieldPrepender",new LengthFieldPrepender(4));//编码
        channelPipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));//编码
        channelPipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));//解码
        channelPipeline.addLast(new MyClientHandler());//自己的处理器
    }
}
