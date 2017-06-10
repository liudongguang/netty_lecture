package com.ldg.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created by liudo on 2017/6/10.
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
       DataInfo.Student student= DataInfo.Student.newBuilder().setName("abc").setAge("18岁").setAddress("北京").build();
       byte[] student2ByteArray=student.toByteArray();//可以在网络上传输
        DataInfo.Student student2=DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2+"    "+student2.getName()+"    "+student2.getAge()+"     "+student2.getAddress());
    }
}
