package com.zyouke.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

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
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                SocketChannel clientChannel;
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                    clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("获取客户端连接 ：" + clientChannel);
                }else if (selectionKey.isReadable()){
                    clientChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.clear();
                    int readCount = clientChannel.read(buffer);
                    if (readCount > 0){
                        buffer.flip();
                        String message = new String(buffer.array(),0,readCount).replace("\n"," ");
                        System.out.println(clientChannel + ":" + message);
                    }
                    buffer.clear();
                    buffer.put("PONG\n".getBytes());
                    buffer.flip();
                    clientChannel.write(buffer);
                }
            }
        }
    }



}
