package com.ldg.netty.xintiao;

public class MessageProtocol {
    private int length;
    private byte type;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageProtocol() {
    }

    public MessageProtocol(byte type) {
        this.type = type;
        this.length=0;
        this.content="";
    }

    @Override
    public String toString() {
        return "MessageProtocol{" +
                "length=" + length +
                ", type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
