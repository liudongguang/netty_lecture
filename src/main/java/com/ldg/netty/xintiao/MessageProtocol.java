package com.ldg.netty.xintiao;

import java.util.Arrays;

public class MessageProtocol {
    private int length;
    private byte type;
    private byte[] content;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public MessageProtocol() {
    }

    public MessageProtocol(byte type) {
        this.type = type;
        this.length=1;
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
                "length=" + length +
                ", type=" + type +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
