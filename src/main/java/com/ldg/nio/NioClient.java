package com.ldg.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by LiuDongguang on 2017/9/1.
 */
public class NioClient {
    public static void main(String[] args) throws Exception{
        try {
            SocketChannel socketChannel=SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector=Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));
            while (true){
                selector.select();//这里阻塞等待事件发生
                Set<SelectionKey> keys=selector.selectedKeys();
                for(SelectionKey selectionKey:keys){
                    if(selectionKey.isConnectable()){
                        SocketChannel client=(SocketChannel)selectionKey.channel();
                        if(client.isConnectionPending()){//连接正在进行
                            client.finishConnect();//完成连接
                            ByteBuffer writeBuffer=ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now()+"连接成功！").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);
                            //起一个线程完成输入
                            ExecutorService executorService= Executors.newSingleThreadExecutor();
                            executorService.submit(()->{
                               while (true){
                                   try {
                                       writeBuffer.clear();
                                       InputStreamReader input=new InputStreamReader(System.in);
                                       BufferedReader br=new BufferedReader(input);
                                       String sendMessage=br.readLine();
                                       writeBuffer.put(sendMessage.getBytes());
                                       writeBuffer.flip();
                                       client.write(writeBuffer);
                                   }catch (Exception e){
                                       e.printStackTrace();
                                   }
                               }
                            });
                        }
                        //注册读取事件
                        client.register(selector,SelectionKey.OP_READ);
                    }else if(selectionKey.isReadable()){
                        //读取服务器返回的内容
                        SocketChannel client= (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer=ByteBuffer.allocate(1024);
                        int readCount=client.read(readBuffer);
                        if(readCount>0){
                            String recervedMessage=new String(readBuffer.array(),0,readCount);
                            System.out.println(recervedMessage);
                        }
                    }
                }
                keys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
