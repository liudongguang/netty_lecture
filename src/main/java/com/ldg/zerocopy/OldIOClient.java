package com.ldg.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by LiuDongguang on 2017/9/1.
 */
public class OldIOClient {
    public static void main(String[] args) throws Exception{
        Socket socket=new Socket("localhost",8899);
        String fileName="D:\\狸窝转换\\00076_0.mp4";
        InputStream inputStream=new FileInputStream(fileName);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        byte[] buffer=new byte[4096];
        long readCount;
        long total=0;
        long startTime=System.currentTimeMillis();
        while ((readCount=inputStream.read(buffer))>=0){
            total+=readCount;
            dataOutputStream.write(buffer);
        }
        System.out.println("总字节数："+total+"  耗时："+(System.currentTimeMillis()-startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
