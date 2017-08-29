package com.ldg.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by LiuDongguang on 2017/8/29.
 */
public class NioTest8 {
    public static void main(String[] args) throws Exception{
        FileInputStream fim=new FileInputStream("input2.txt");
        FileOutputStream fom=new FileOutputStream("output2.txt");
        FileChannel inputchannel=fim.getChannel();
        FileChannel outputchannel=fom.getChannel();
        ByteBuffer directbuffer=ByteBuffer.allocateDirect(512);
        while (true){
            directbuffer.clear();
            int read=inputchannel.read(directbuffer);
            if(-1==read){
                break;
            }
            directbuffer.flip();
            outputchannel.write(directbuffer);
        }
        fim.close();
        fom.close();
    }
}
