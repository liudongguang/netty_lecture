package com.ldg.netty.a20180122.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByteToLongDecode2  extends ReplayingDecoder<Void>{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //不需要检查字节够不够
        System.out.println("MyByteToLongDecode2 decode invoke!");
        out.add(in.readLong());
    }
}
