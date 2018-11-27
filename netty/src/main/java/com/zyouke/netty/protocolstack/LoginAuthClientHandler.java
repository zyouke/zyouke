package com.zyouke.netty.protocolstack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginAuthClientHandler extends SimpleChannelInboundHandler<NettyMessage> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(loginAuthRequest());
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessage nettyMessage) throws Exception {
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == 1){
            System.out.println("握手成功" + nettyMessage.toString());
            ctx.fireChannelRead(nettyMessage);
        }
    }

    private NettyMessage loginAuthRequest(){
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(1);
        nettyMessage.setHeader(header);
        return nettyMessage;
    }





}
