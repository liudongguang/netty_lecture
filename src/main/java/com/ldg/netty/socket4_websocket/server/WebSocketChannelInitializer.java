package com.ldg.netty.socket4_websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * Created by liudo on 2017/5/20.
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline=ch.pipeline();
        channelPipeline.addLast("HttpServerCodec",new HttpServerCodec());
        channelPipeline.addLast("ChunkedWriteHandler",new ChunkedWriteHandler());//“块写”处理器
        channelPipeline.addLast("HttpObjectAggregator",new HttpObjectAggregator(8192));//聚合
        channelPipeline.addLast("WebSocketServerProtocolHandler",new WebSocketServerProtocolHandler("/ws"));
        channelPipeline.addLast(new TextWebSocketFrameHandler());//自己的处理器
    }
}
