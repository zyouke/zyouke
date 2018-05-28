package com.zyouke.netty.handler;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] byteArr = new byte[buf.readableBytes()];
        buf.readBytes(byteArr);
        String request = new String(byteArr,"UTF-8");
	System.out.println("客户端请求:" + request);
	ByteBuf  response = Unpooled.copiedBuffer(String.valueOf(System.currentTimeMillis()).getBytes());
	ctx.write(response);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
	ctx.flush();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    
    
}
