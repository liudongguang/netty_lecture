package com.ldg.nio;

import java.nio.ByteBuffer;

/**
 * 类型化的get和put
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(64);
        byteBuffer.putInt(15);
        byteBuffer.putLong(500000L);
        byteBuffer.putDouble(14.336);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)2);


        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
    }
}
