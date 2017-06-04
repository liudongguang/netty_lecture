package com.ldg.netty.socket2_chat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * Created by liudo on 2017/5/20.
 */
public class MyChatServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast("DelimiterBasedFrameDecoder",new DelimiterBasedFrameDecoder(4096,Delimiters.lineDelimiter()));//符号分隔符解码
        channelPipeline.addLast("StringDecoder",new StringDecoder(CharsetUtil.UTF_8));//编码
        channelPipeline.addLast("StringEncoder",new StringEncoder(CharsetUtil.UTF_8));//解码
        channelPipeline.addLast(new MyChatServerHandler());//自己的处理器
    }
}
