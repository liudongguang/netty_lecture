package com.ldg.zerocopy;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LiuDongguang on 2017/9/1.
 */
public class OldIOServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket=new ServerSocket(8899);
        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
            try{
                byte[] byteArr=new byte[4096];
                while (true){
                    int readCount=dataInputStream.read(byteArr,0,byteArr.length);
                    if(-1==readCount){
                        break;
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
