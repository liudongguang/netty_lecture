package com.ldg.netty.socket5_proto2.client;

import com.ldg.netty.socket5_proto2.MyDataInfo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * Created by liudo on 2017/6/11.
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo2.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo2.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt= new Random().nextInt(3);
        MyDataInfo2.MyMessage message=null;
        if(0==randomInt){
            MyDataInfo2.Person person = MyDataInfo2.Person.newBuilder().setName("张三").setAge(23).setAddress("北京").build();
            message=MyDataInfo2.MyMessage.newBuilder().setDataType(MyDataInfo2.MyMessage.DataType.PersonType).setPerson(person).build();
        }else if(1==randomInt){
            MyDataInfo2.Dog dog = MyDataInfo2.Dog.newBuilder().setName("大黄").setAge(6).build();
            message=MyDataInfo2.MyMessage.newBuilder().setDataType(MyDataInfo2.MyMessage.DataType.DogType).setDog(dog).build();
        }else {
            MyDataInfo2.Cat cat = MyDataInfo2.Cat.newBuilder().setName("小花猫").setCity("济南").build();
            message=MyDataInfo2.MyMessage.newBuilder().setDataType(MyDataInfo2.MyMessage.DataType.CatType).setCat(cat).build();
        }
        ctx.channel().writeAndFlush(message);
    }
}
