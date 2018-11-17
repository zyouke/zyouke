package com.zyouke.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloWorldClientHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端响应数据 ： " + msg);
        ctx.channel().writeAndFlush("PANG \r\n");
    }
}
