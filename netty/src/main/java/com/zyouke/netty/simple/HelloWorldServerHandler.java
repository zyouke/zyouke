package com.zyouke.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloWorldServerHandler extends SimpleChannelInboundHandler<String>{

    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        System.out.println("接受客户端的请求：" + message);
        ctx.channel().writeAndFlush("PANG \r\n");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前连接的客户端 ： " + ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("管道"+ ctx.channel().remoteAddress()+"取消注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("管道" + ctx.channel().remoteAddress() + "活跃");
    }
}
