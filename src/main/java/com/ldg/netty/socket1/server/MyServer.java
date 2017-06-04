package com.ldg.netty.socket1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by liudo on 2017/5/20.
 * socket 编程
 */
public class MyServer {
    public static void main(String[] args) throws  Exception{
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            //handler方法主要是对bossGroup起作用，childHandler对workerGroup起作用
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyServerInitializer());
            ChannelFuture channelFuture=serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
