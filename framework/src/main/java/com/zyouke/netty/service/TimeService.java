package com.zyouke.netty.service;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;

/**
 * 请求时间的服务
 */
public class TimeService {

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        ChannelFactory channelFactory = new NioServerSocketChannelFactory();
        bootstrap.setFactory(channelFactory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("timer", new IdleStateHandler(new HashedWheelTimer(), 10000 / 1000, 0, 0));
                return pipeline;
            }
        });
        Channel channel = bootstrap.bind(new InetSocketAddress(8080));
    }

}
