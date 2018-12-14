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
        System.out.println("服务端启动，监听端口 ：8080");
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("获取客户端连接 ：" + socketChannel);
                    selectionKeys.remove(selectionKey);
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    while (true){
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if (read <= 0){
                            break;
                        }
                        buffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String message = String.valueOf(charset.decode(buffer).array());
                        System.out.println(socketChannel + ":" + message + "读取 ：" + read);
                        socketChannel.write(buffer);
                    }
                    selectionKeys.remove(selectionKey);
                }
            }
        }
    }



}
