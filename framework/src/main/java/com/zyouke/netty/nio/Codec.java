package com.zyouke.netty.nio;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 编解码
 */
public class Codec {
    private static final ByteBuffer cacheBuffer = ByteBuffer.allocate(100);
    private static boolean isCache = false;
    private final static int head_length = 4;//数据包长度
    /**
     * 解码
     */
    public static boolean decode(SocketChannel channel) throws Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate(50);
        channel.read(byteBuffer);// 当前read事件
        int bodyLen = -1;
        byte[] headByte = new byte[4];
        if (isCache) {
            cacheBuffer.flip();
            int cacheBufferLength = cacheBuffer.remaining();
            byteBuffer.flip();
            int byteTotalLength = cacheBufferLength + byteBuffer.remaining();
            if (byteTotalLength > byteBuffer.capacity()){
                byteBuffer = ByteBuffer.allocate(byteTotalLength);
            }
           // byteBuffer.put(cacheBuffer).put(tempBuffer);
        }
        byteBuffer.flip();
        while (byteBuffer.remaining() > 0) {
            if (bodyLen == -1) {// 还没有读出包头，先读出包头
                if (byteBuffer.remaining() >= head_length) {// 可以读出包头，否则缓存
                    byteBuffer.mark();
                    byteBuffer.get(headByte);
                    bodyLen = Codec.byteArrayToInt(headByte);
                } else {
                    byteBuffer.reset();
                    isCache = true;
                    cacheBuffer.clear();
                    cacheBuffer.put(byteBuffer);
                    break;
                }
            } else {// 已经读出包头
                if (byteBuffer.remaining() >= bodyLen) {// 大于等于一个包，否则缓存
                    byte[] bodyByte = new byte[bodyLen];
                    byteBuffer.get(bodyByte, 0, bodyLen);
                    if(byteBuffer.remaining() >0){
                        cacheBuffer.clear();
                        cacheBuffer.put(byteBuffer);
                        isCache = true;
                    }
                    System.out.println("receive from clien content is:" + new String(bodyByte));
                    return true;
                } else {
                    byteBuffer.reset();
                    cacheBuffer.clear();
                    cacheBuffer.put(byteBuffer);
                    isCache = true;
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 编码
     * @param request
     */
    public static ByteBuffer encoded(String request){
        byte[] bytes = request.getBytes();
        int head = bytes.length;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4 + head);
        byteBuffer.put(intToBytes(head));
        byteBuffer.put(bytes);
        byteBuffer.flip();
        return byteBuffer;
    }





    /**
     * byte[]转int
     *
     * @param bytes
     * @return
     */
    private static int byteArrayToInt(byte[] bytes) {
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

    /**
     * int到byte[]
     *
     * @param value
     * @return
     */
    private static byte[] intToBytes(int value) {
        byte[] result = new byte[4];
        // 由高位到低位
        result[0] = (byte) ((value >> 24) & 0xFF);
        result[1] = (byte) ((value >> 16) & 0xFF);
        result[2] = (byte) ((value >> 8) & 0xFF);
        result[3] = (byte) (value & 0xFF);
        return result;
    }
}
