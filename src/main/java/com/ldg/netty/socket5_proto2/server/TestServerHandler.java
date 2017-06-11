package com.ldg.netty.socket5_proto2.server;

import com.ldg.netty.socket5_proto2.MyDataInfo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by liudo on 2017/6/11.
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo2.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo2.MyMessage msg) throws Exception {
        MyDataInfo2.MyMessage.DataType dataType = msg.getDataType();
        if (dataType == MyDataInfo2.MyMessage.DataType.PersonType) {
            MyDataInfo2.Person pserson = msg.getPerson();
            System.out.println(pserson.getName() + "     " + pserson.getAge() + "    " + pserson.getAddress());
        } else if (dataType == MyDataInfo2.MyMessage.DataType.DogType) {
            MyDataInfo2.Dog dog = msg.getDog();
            System.out.println(dog.getName() + "     " + dog.getAge());
        } else {
            MyDataInfo2.Cat cat = msg.getCat();
            System.out.println(cat.getName() + "     " + cat.getCity());
        }
    }
}
