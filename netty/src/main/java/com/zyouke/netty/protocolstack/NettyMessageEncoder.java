package com.zyouke.netty.protocolstack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.serial.SerialProviderDescriptor;

import java.util.List;
import java.util.Map;

/**
 * 使用 netty协议栈开发，用到的编码器
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

    private NettyMarshallingEncoder nettyMarshallingEncoder;
    public  NettyMessageEncoder(){
        MarshallerFactory marshallerFactory = new SerialProviderDescriptor().getMarshallerFactory();
        nettyMarshallingEncoder = new NettyMarshallingEncoder(new DefaultMarshallerProvider(marshallerFactory,new MarshallingConfiguration()));
    }


    @Override
    public void encode(ChannelHandlerContext ctx, NettyMessage nettyMessage, List<Object> out) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null){
            System.out.println("该消息不是合法的，请确认");
            return;
        }
        ByteBuf buffer = Unpooled.buffer();
        Header header = nettyMessage.getHeader();
        buffer.writeInt(header.getCrcCode());
        buffer.writeInt(header.getLength());
        buffer.writeInt(header.getPriority());
        buffer.writeInt(header.getType());
        buffer.writeLong(header.getSessionID());
        Map<String, Object> attachmentMap = header.getAttachment();
        if (attachmentMap.size() > 0){
            for (Map.Entry<String, Object> entry : attachmentMap.entrySet()) {
                String key = entry.getKey();
                byte[] keyBytes = key.getBytes("UTF-8");
                buffer.writeInt(keyBytes.length);
                buffer.writeBytes(keyBytes);
                Object value = entry.getValue();
                this.nettyMarshallingEncoder.encode(ctx,value,buffer);
            }
            if (nettyMessage.getBody() != null){
                this.nettyMarshallingEncoder.encode(ctx,nettyMessage.getBody(),buffer);
            }else {
                buffer.writeInt(0);
                buffer.setInt(4,buffer.readableBytes());
            }
        }

    }
}
