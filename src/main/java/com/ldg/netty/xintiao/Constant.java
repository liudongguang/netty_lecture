package com.ldg.netty.xintiao;

import java.nio.charset.Charset;

public interface Constant {
    byte server_beat=1;
    byte client_beat=2;
    byte client_msg=3;
    MessageProtocol sbeat=new MessageProtocol(server_beat);
    MessageProtocol cbeat=new MessageProtocol(client_beat);
    String UTF_8="utf-8";
}
