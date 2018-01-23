package com.ldg.netty.xintiao.server;

import com.ldg.netty.xintiao.Constant;
import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by liudo on 2017/5/20.
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol>{
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int length=msg.getLength();
        String content=msg.getContent();
        System.out.println("服务端接受到的数据：length:"+length+"  内容："+content);
        System.out.println("server 消息数量"+(++this.count));
        String reaponseMessage=UUID.randomUUID().toString();
        MessageProtocol messageProtocol =new MessageProtocol();
        messageProtocol.setType(Constant.client_msg);
        messageProtocol.setLength(reaponseMessage.getBytes("utf-8").length);
        messageProtocol.setContent(reaponseMessage);
        ctx.writeAndFlush(messageProtocol);
    }




}
