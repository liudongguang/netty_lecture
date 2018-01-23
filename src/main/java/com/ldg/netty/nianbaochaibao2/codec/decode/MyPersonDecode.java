package com.ldg.netty.nianbaochaibao2.codec.decode;

import com.ldg.netty.nianbaochaibao2.PersonProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyPersonDecode extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyPersonDecode decode invoke!");
        int length=in.readInt();
        byte[] content=new byte[length];
        in.readBytes(content);
        PersonProtocol personProtocol=new PersonProtocol();
        personProtocol.setContent(content);
        personProtocol.setLength(length);
        out.add(personProtocol);
    }
}
