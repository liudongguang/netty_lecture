package com.ldg.netty.socket2_chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by liudo on 2017/5/20.
 * 1.连接建立----客户端上线要通知给其他用户
 * 2.发送消息后，客户端都受到发送的信息，自己发的信息要在前面添加“自己”
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String>{
    private static ChannelGroup channels=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//保存一个个的channel对象

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server channelRead0:"+msg);
        Channel channel=ctx.channel();
        channels.forEach(ch->{
            if(ch!=channel){
                ch.writeAndFlush("发送的消息："+msg+"\n");
            }else{
                ch.writeAndFlush("[自己]："+msg+"\n");
            }
        });
    }

    /**
     * 连接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        ////广播
        channels.writeAndFlush("[服务器]-"+channel.remoteAddress()+"加入\n");
        ////
        channels.add(channel);
    }

    /**
     * 客户端连接断开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        channels.writeAndFlush("[服务器]-"+channel.remoteAddress()+"离开\n");
        //channels.remove(channel);  会自动的移除断掉的channel
    }

    /**
     * 通道激活
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        ////广播
        System.out.println(channel.remoteAddress()+"上线");
        ////
    }

    /**
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        ////广播
        System.out.println(channel.remoteAddress()+"下线");
        ////
    }

    /**
     * 异常情况
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
               ctx.close();
    }
}
