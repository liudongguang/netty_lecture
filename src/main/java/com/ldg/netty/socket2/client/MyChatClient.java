package com.ldg.netty.socket2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyChatClient {
    public static void main(String[] args) throws  Exception{
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            //handler方法主要是对bossGroup起作用，childHandler对workerGroup起作用
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyChatClientInititializer());
            ChannelFuture channelFuture=bootstrap.connect("localhost",8899).sync();
            Channel channel=channelFuture.channel();
            ///
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                channel.writeAndFlush(br.readLine()+"\r\n");
            }
            ///
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
