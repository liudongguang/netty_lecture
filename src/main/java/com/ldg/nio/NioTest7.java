package com.ldg.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 */
public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        for(int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer=buffer.asReadOnlyBuffer();
        readOnlyBuffer.position(0);
        //readOnlyBuffer.put((byte)1);
    }
}
