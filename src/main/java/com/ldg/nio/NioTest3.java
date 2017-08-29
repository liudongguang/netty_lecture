package com.ldg.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by LiuDongguang on 2017/8/29.
 */
public class NioTest3 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fom=new FileOutputStream("Niotest3.txt");
        FileChannel fileChannel=fom.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
        byte[] message="hello world welcom,nihao".getBytes();
        for(int i=0;i<message.length;++i){
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fom.close();
    }
}
