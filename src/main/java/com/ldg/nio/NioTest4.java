package com.ldg.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by LiuDongguang on 2017/8/29.
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fim=new FileInputStream("input.txt");
        FileOutputStream fom=new FileOutputStream("output.txt");
        FileChannel inputchannel=fim.getChannel();
        FileChannel outputchannel=fom.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(512);
        while (true){
            buffer.clear();//注释掉 下边read会输出0
            int read=inputchannel.read(buffer);
            System.out.println("read:"+read);//14
            if(-1==read){
                break;
            }
            buffer.flip();
            outputchannel.write(buffer);
        }
        fim.close();
        fom.close();
    }
}
