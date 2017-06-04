package com.ldg.netty.socket3_Idle.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by liudo on 2017/5/20.
 * 心跳 读检查，写检查，读写检查
 */
public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //handler方法主要是对bossGroup起作用，childHandler对workerGroup起作用,这里对bossGroup增加一个日志handler
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new MyServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
