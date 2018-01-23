package com.ldg.netty.xintiao.codec.encode;

import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encode invoke!");
        out.writeByte(msg.getType());
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
        System.out.println(msg+"++++++++++++++++++++++++");
    }
}
