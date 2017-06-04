package com.ldg.netty.socket4_websocket.chatServer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 泛型 I 表示处理的传递的类型
 */
public class TextWebSocketFrameChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//保存一个个的channel对象

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("收到消息：" + msg.text());
        Channel channel = ctx.channel();
        channels.forEach(ch -> {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            if (ch != channel) {
                ch.writeAndFlush(new TextWebSocketFrame("其他人发送的消息" + time + "：\n" + msg.text()));
            } else {
                ch.writeAndFlush(new TextWebSocketFrame("[自己]" + time + "：\n" + msg.text()));
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded...." + ctx.channel().id().asLongText());
        Channel channel = ctx.channel();
        ////广播
        channels.writeAndFlush(new TextWebSocketFrame("[服务器]-" + channel.remoteAddress() + "加入\n"));
        ////
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved...." + ctx.channel().id().asLongText());
        Channel channel = ctx.channel();
        channels.writeAndFlush(new TextWebSocketFrame("[服务器]-" + channel.remoteAddress() + "离开\n"));
    }

    /**
     * 通道激活
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush(new TextWebSocketFrame(channel.remoteAddress() + "上线"));
    }

    /**
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush(new TextWebSocketFrame(channel.remoteAddress() + "下线"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生！");
        ctx.close();
    }
}
