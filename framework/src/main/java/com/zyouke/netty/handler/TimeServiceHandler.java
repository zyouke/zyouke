package com.zyouke.netty.handler;

import com.zyouke.utils.ThreadUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class TimeServiceHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf readByteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[readByteBuf.readableBytes()];
        readByteBuf.readBytes(bytes);
        String request = new String(bytes, "UTF-8");
        System.out.println(ThreadUtil.getThreadName() + "获取客户:"+request+"对时间的请求");
        ByteBuf writeByteBuf = Unpooled.copiedBuffer(DateFormatUtils.format(new Date(),"yyyy-mm-dd HH:MM:SS:SSS").getBytes());
        ctx.writeAndFlush(writeByteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端出现异常,清空资源");
        ctx.close();
    }
}
