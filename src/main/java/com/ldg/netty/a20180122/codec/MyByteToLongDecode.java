package com.ldg.netty.a20180122.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecode extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyByteToLongDecode decode invoke!");
        System.out.println(in.readableBytes());
        if(in.readableBytes()>=8){
            out.add(in.readLong());
        }
    }
}
