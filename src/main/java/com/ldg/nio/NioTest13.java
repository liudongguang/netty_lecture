package com.ldg.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by LiuDongguang on 2017/9/1.
 */
public class NioTest13 {
    public static void main(String[] args) throws Exception{
        String inputFile="Nio13In.txt";
        String outputFile="Nio13Out.txt";
        RandomAccessFile inputRandomAccessFile=new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandomAccessFile=new RandomAccessFile(outputFile,"rw");
        long inputLength=new File(inputFile).length();

        FileChannel inputFileChannel=inputRandomAccessFile.getChannel();
        FileChannel outFileChannel=outputRandomAccessFile.getChannel();
        //内存映射Buffer
        MappedByteBuffer inputData=inputFileChannel.map(FileChannel.MapMode.READ_ONLY,0,inputLength);
        Charset charset=Charset.forName("iso-8859-1"); // 因为不会丢失字节，所以没有出现问题
       // Charset charset=Charset.forName("utf-8");
        //将字节数组转化为字符串  解码
        CharsetDecoder decoder=charset.newDecoder();
        //将字符串转换为字节数组
        CharsetEncoder encoder=charset.newEncoder();

        CharBuffer charBuffer=decoder.decode(inputData);
        ByteBuffer outputBuffer=encoder.encode(charBuffer);

        outFileChannel.write(outputBuffer);
        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}
