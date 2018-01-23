package com.ldg.netty.a20180122.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;


public class MyStringToLongEncode extends MessageToMessageEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        System.out.println("MyStringToLongEncode encode....msg:"+msg);
        if(msg.lastIndexOf("L")!=-1||msg.lastIndexOf("l")!=-1){
            msg=msg.substring(0,msg.length()-1);
        }
        out.add(Long.valueOf(msg));
    }

}


