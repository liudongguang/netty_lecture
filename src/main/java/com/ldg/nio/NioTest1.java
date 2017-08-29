package com.ldg.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * Created by LiuDongguang on 2017/8/29.
 */
public class NioTest1 {
    public static void main(String[] args) {
        //随机数打印
        IntBuffer buffer=IntBuffer.allocate(10);
        for(int i=0;i<buffer.capacity();i++){
            int randomNumber=new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        buffer.flip();//读写切换一定要调用
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
