package com.zyouke.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioSelector {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            Set<SelectionKey> selectionKeys = selector.keys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    keyIterator.remove();
                    System.out.println("当前连接的客户端 ：" + channel);
                }else if (selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    while (true){
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.clear();
                        int read = socketChannel.read(buffer);
                        if (read < 0){
                            break;
                        }
                        buffer.flip();
                        System.out.println(new String(buffer.array(),"UTF-8"));
                        socketChannel.write(buffer);
                    }
                }
            }
        }
    }



}
