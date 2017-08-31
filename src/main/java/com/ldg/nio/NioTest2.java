package com.ldg.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by LiuDongguang on 2017/8/29.
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fim=new FileInputStream("NIOTest2.txt");
        FileChannel fileChannel=fim.getChannel();
        //读取文件
        ByteBuffer byteBuffer=ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.remaining()>0){
           byte b=byteBuffer.get();
            System.out.println("Character:"+(char)b);
        }
        fim.close();
    }
}
