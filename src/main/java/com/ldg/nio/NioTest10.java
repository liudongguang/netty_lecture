package com.ldg.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by LiuDongguang on 2017/8/30.
 */
public class NioTest10 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile=new RandomAccessFile("NioTest10.txt","rw");
        FileChannel fileChannel=randomAccessFile.getChannel();
        FileLock fileLock=fileChannel.lock(3,6,true);//共享锁  只能读
        System.out.println("valid:"+fileLock.isValid());
        System.out.println("lock type："+fileLock.isShared());
        fileLock.release();
        randomAccessFile.close();
    }
}
