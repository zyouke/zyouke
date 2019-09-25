<<<<<<< HEAD
package com.zyouke.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;

public class HelloWorldClientHandler extends SimpleChannelInboundHandler<String> {
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端响应数据 ： " + msg);
        Thread.sleep(1000);
        ctx.channel().writeAndFlush("HelloWorld \r\n");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与远程服务请求注册。。。。。");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与远程服务 ：" + ctx.channel().remoteAddress() +"连接处于活跃");
        ctx.channel().writeAndFlush("HelloWorld\r\n");
    }
}
=======
package com.zyouke.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorldClientHandler extends SimpleChannelInboundHandler<String> {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端响应数据 ： " + msg);
        for(int i = 0; i < 100; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run(){
                    ctx.channel().writeAndFlush("HelloWorld \r\n");
                }
            });
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与远程服务请求注册。。。。。");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与远程服务 ：" + ctx.channel().remoteAddress() +"连接处于活跃");
        ctx.channel().writeAndFlush("HelloWorld\r\n");
    }
}
>>>>>>> f2d1797a1848f76811c8d4a2450fd5ad597ae7fa
