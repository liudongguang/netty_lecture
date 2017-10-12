package com.ldg.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by LiuDongguang on 2017/9/4.
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);
        String fileName="D:\\狸窝转换\\00076_0.mp4";
        FileChannel fileChannel=new FileInputStream(fileName).getChannel();
        long startTime=System.currentTimeMillis();
        long transferCount=fileChannel.transferTo(0,fileChannel.size(),socketChannel);
        System.out.println("总字节数："+transferCount+"  耗时："+(System.currentTimeMillis()-startTime));
        fileChannel.close();
        socketChannel.close();
    }
}
