package com.zyouke.netty.handler;

import com.zyouke.utils.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeClientHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readByteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[readByteBuf.readableBytes()];
        readByteBuf.readBytes(bytes);
        String response = new String(bytes, "UTF-8");
        System.out.println("获取服务端响应:"+response);
        ByteBuf writeByteBuf = Unpooled.copiedBuffer(RandomUtil.getRandomString().getBytes());
        ctx.writeAndFlush(writeByteBuf);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端和服务端已建立起链接....");
        ByteBuf writeByteBuf = Unpooled.copiedBuffer(RandomUtil.getRandomString().getBytes());
        ctx.writeAndFlush(writeByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端出现异常,清空资源");
        ctx.close();
    }
}
