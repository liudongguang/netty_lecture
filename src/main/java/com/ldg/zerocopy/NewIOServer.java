package com.ldg.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by LiuDongguang on 2017/9/1.
 */
public class NewIOServer {
    public static void main(String[] args) throws Exception {
        InetSocketAddress address = new InetSocketAddress(8899);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.setReuseAddress(false);//在time wait状态下也能绑定
        serverSocket.bind(address);
        ByteBuffer byteBuffer=ByteBuffer.allocate(4096);
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int readCount=0;
            while (-1!=readCount){
                try {
                    readCount=socketChannel.read(byteBuffer);

                }catch (Exception e){
                    e.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }
    }
}
