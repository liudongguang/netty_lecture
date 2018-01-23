package com.ldg.netty.xintiao.codec.encode;

import com.ldg.netty.xintiao.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encode invoke!");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
