package com.zyouke.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * nio 客户端
 */
public class NioClient {

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            SocketChannel socketChannel = SocketChannel.open();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.connect(new InetSocketAddress(8080));
            buffer.put(String.valueOf(System.currentTimeMillis()).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
            int readCount = socketChannel.read(buffer);
            buffer.flip();
            String response = new String(buffer.array(),0,readCount);
            System.out.println(response);
        }
    }
}
