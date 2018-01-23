package com.ldg.netty.xintiao.codec.encode;

import com.ldg.netty.xintiao.Constant;
import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyPersonEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder encode invoke!");
        String content=msg.getContent();
        byte[] strBytes = content.getBytes(Constant.UTF_8);
        out.writeInt(strBytes.length);
        out.writeByte(msg.getType());
        out.writeBytes(strBytes);
        //System.out.println(strBytes.length+"   "+msg.getType()+"   "+strBytes);
    }
}
