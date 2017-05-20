package com.ldg.netty.firstexample;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created by liudo on 2017/5/14.
 * 初始化器
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();//管道中有很多个channelhandler
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());//
        pipeline.addLast("TestHttpServerHandler",new TestHttpServerHandler());
    }
}
