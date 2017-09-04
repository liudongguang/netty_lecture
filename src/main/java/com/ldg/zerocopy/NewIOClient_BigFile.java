package com.ldg.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by LiuDongguang on 2017/9/4.
 */
public class NewIOClient_BigFile {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel=SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8899));
        socketChannel.configureBlocking(true);
        String fileName="D:\\狸窝转换\\00076_0.mp4";
        //String fileName="D:\\二院工作\\邦尼医生与山大二院HIS接口需求0709.doc";
        FileChannel fileChannel=new FileInputStream(fileName).getChannel();
        long startTime=System.currentTimeMillis();
        long splitSize=8388608;//8M
        long position=0;
        long transferCount=0;
        while (true){
            long transferedSize=fileChannel.transferTo(position,splitSize,socketChannel);
            if(transferedSize==0){
                break;
            }
            position+=splitSize;
            transferCount+=transferedSize;
            //System.out.println(transferedSize);
        }
        System.out.println("总字节数："+transferCount+"  耗时："+(System.currentTimeMillis()-startTime));
        fileChannel.close();
        socketChannel.close();
    }
}
