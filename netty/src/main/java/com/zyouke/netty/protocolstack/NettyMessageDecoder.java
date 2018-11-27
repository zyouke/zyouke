package com.zyouke.netty.protocolstack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.serial.SerialMarshallerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 解码器
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder{
    private NettyMarshallingDecoder nettyMarshallingDecoder;
    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        SerialMarshallerFactory serialMarshallerFactory = new SerialMarshallerFactory();
        DefaultUnmarshallerProvider provider = new DefaultUnmarshallerProvider(serialMarshallerFactory,new MarshallingConfiguration());
        nettyMarshallingDecoder = new NettyMarshallingDecoder(provider);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null){
            return null;
        }
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setPriority(in.readInt());
        header.setSessionID(in.readLong());
        header.setType(in.readInt());
        Map<String,Object> attachment = new HashMap<String, Object>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            int keySize = in.readInt();
            byte[] bytes = new byte[keySize];
            in.readBytes(bytes);
            String key = new String(bytes, "UTF-8");
            attachment.put(key,nettyMarshallingDecoder.decode(ctx,in));
        }
        if (in.readableBytes() > 4){
            nettyMessage.setBody(nettyMarshallingDecoder.decode(ctx, in));
        }
        nettyMessage.setHeader(header);
        return nettyMessage;
    }
}
