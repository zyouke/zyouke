<<<<<<< HEAD
package com.zyouke.netty.simple;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;

public class HelloWorldServerHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        System.out.println("接受客户端" + ctx.channel().remoteAddress() + "的请求：" + message);
        ctx.channel().writeAndFlush("200 \r\n");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 连接注册");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 连接取消注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 链接活跃中");
    }

    /**
     * 打印
     * @param channelHandlerContext
     * @return
     */
    public String print(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        SocketAddress localAddress = channel.localAddress();
        SocketAddress remoteAddress = channel.remoteAddress();
        return remoteAddress.toString().substring(1) + "<===>" + localAddress.toString().substring(1);
    }
}
=======
package com.zyouke.netty.simple;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.Random;

public class HelloWorldServerHandler extends SimpleChannelInboundHandler<String> {
    private Random random = new Random();
    protected void channelRead0(ChannelHandlerContext ctx, String message) throws Exception {
        System.out.println(Thread.currentThread().getName() +"处理请求：" + message);
        Thread.sleep(random.nextInt(500));
        ctx.channel().writeAndFlush("200 \r\n");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 连接注册");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 连接取消注册");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(print(ctx) + " 链接活跃中");
    }

    /**
     * 打印
     * @param channelHandlerContext
     * @return
     */
    public String print(ChannelHandlerContext channelHandlerContext) {
        Channel channel = channelHandlerContext.channel();
        SocketAddress localAddress = channel.localAddress();
        SocketAddress remoteAddress = channel.remoteAddress();
        return remoteAddress.toString().substring(1) + "<===>" + localAddress.toString().substring(1);
    }
}
>>>>>>> f2d1797a1848f76811c8d4a2450fd5ad597ae7fa
