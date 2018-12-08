package com.zyouke.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioSelector {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8080));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                SocketChannel socketChannel;
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(512);
                    socketChannel = (SocketChannel) selectionKey.channel();
                    int read = socketChannel.read(buffer);
                    if (read > 0){
                        buffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        System.out.println(socketChannel + ":" + String.valueOf(charset.decode(buffer).array()));
                    }
                }
                selectionKeys.remove(selectionKey);
            }
        }
    }



}
