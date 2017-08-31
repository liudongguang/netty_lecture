package com.ldg.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by LiuDongguang on 2017/8/31.
 */
public class NioServer {
    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//非阻塞
        //获取服务器端的socket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        //绑定端口
        serverSocket.bind(new InetSocketAddress(8899));
        //创建选择器对象
        Selector selector = Selector.open();
        //注册到selector上将serverSocketChannel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//服务器端关注连接事件
        //事件处理
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;
                    try {
                        if (selectionKey.isAcceptable()) {//表示客户端像服务端发起了连接
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();//此时ServerSocketChannel就用不到了，专而使用客户端的socketchannel
                            //将客户端的socketchannel注册到selector
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);//让客户端注册读取
                            String key = "[" + UUID.randomUUID().toString() + "]"; //为群发做准备
                            clientMap.put(key, client);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();//因为read注册的就是SocketChannel所以能强转
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int readCount = client.read(readBuffer);
                            if (readCount > 0) {
                                readBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ":" + receivedMessage);
                                //分发所有的客户端
                                String senderKey = null;
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                    }
                                }
                                for(Map.Entry<String, SocketChannel> entry : clientMap.entrySet()){
                                     SocketChannel value=entry.getValue();
                                     ByteBuffer writeBuffer=ByteBuffer.allocate(1024);
                                     writeBuffer.put((senderKey+":"+receivedMessage).getBytes());
                                     //把数据放置在buffer中为读，从buffer把数据写到外边叫做写
                                      writeBuffer.flip();
                                      value.write(writeBuffer);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //////非常重要
                selectionKeys.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
