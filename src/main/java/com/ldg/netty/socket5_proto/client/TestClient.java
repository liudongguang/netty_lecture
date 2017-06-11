package com.ldg.netty.socket5_proto.client;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by liudo on 2017/6/11.
 */
public class TestClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            //handler方法主要是对bossGroup起作用，childHandler对workerGroup起作用
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInititializer());
            ChannelFuture channelFuture=bootstrap.connect("localhost",8888).sync();
            Channel channel=channelFuture.channel();
            channel.closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
