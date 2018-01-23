package com.ldg.netty.xintiao.codec.decode;

import com.ldg.netty.xintiao.Constant;
import com.ldg.netty.xintiao.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyPersonDecode extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyPersonDecode decode invoke!"+in);
        int length=in.readInt();
        byte type=in.readByte();
        byte[] content=new byte[length];
        in.readBytes(content);
        MessageProtocol messageProtocol =new MessageProtocol();
        messageProtocol.setContent(new String(content, Constant.UTF_8));
        messageProtocol.setLength(length);
        messageProtocol.setType(type);
        out.add(messageProtocol);
    }
}
