package com.ldg.netty.firstexample;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * Created by liudo on 2017/5/14.
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest){
            HttpRequest httpRequest= (HttpRequest) msg;
            System.out.println("请求方法名："+httpRequest.method().name());
            URI uri=new URI(httpRequest.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico图标");
                return;
            }
            //读取客户端请求，返回给客户端响应
            ByteBuf content= Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response=new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
            ctx.channel().close();
        }
        System.out.println("channelRead0.........."+msg.getClass().getName());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("channelRegistered..........");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("channelUnregistered..........");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("channelActive..........");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("channelInactive..........");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("handlerAdded..........");
    }
}
