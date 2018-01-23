package com.ldg.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by LiuDongguang on 2017/8/30.
 */
public class NioTest12 {
    public static void main(String[] args) throws  Exception{
        int[] ports=new int[5];
        ports[0]=5000;
        ports[1]=5001;
        ports[2]=5002;
        ports[3]=5003;
        ports[4]=5004;

        Selector selector=Selector.open();
        for(int i=0;i<ports.length;i++){
            ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);//成为非阻塞
            ServerSocket serverSocket=serverSocketChannel.socket();
            InetSocketAddress inetSocketAddress=new InetSocketAddress(ports[i]);
            serverSocket.bind(inetSocketAddress);//完成了绑定
            //注册  实现通道与选择器直接的关联
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口："+ports[i]);
        }
        while(true){
            int keynumbers=selector.select();
            System.out.println("key number:"+keynumbers);
            Set<SelectionKey> selectionKeys= selector.selectedKeys();
            System.out.println("selectionKeys:"+selectionKeys);
            Iterator<SelectionKey> keyiter=selectionKeys.iterator();
            while (keyiter.hasNext()){
                SelectionKey selectionKey=keyiter.next();
                if(selectionKey.isAcceptable()){//是接受
                    ServerSocketChannel serverSocketChannel=(ServerSocketChannel)selectionKey.channel();
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);//连接建立好了
                    //将新连接添加到selector中
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    keyiter.remove();//一定要删除，不需要再监听这个事件
                    System.out.println("获得客户端连接："+socketChannel);
                }else if(selectionKey.isReadable()){//数据的读取
                     SocketChannel socketChannel=(SocketChannel)selectionKey.channel();
                     int byteRead =0;
                     while (true){
                         ByteBuffer byteBuffer=ByteBuffer.allocate(512);
                         byteBuffer.clear();
                         int read=socketChannel.read(byteBuffer);
                         if(read<=0){
                             break;
                         }
                         //往回写
                         byteBuffer.flip();
                         socketChannel.write(byteBuffer);
                         byteRead+=read;
                     }
                    System.out.println("读取："+byteRead+"   来自于："+socketChannel);
                    keyiter.remove();//*********一定注意删除
                }
            }
        }

    }

}
