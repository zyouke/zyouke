package com.zyouke.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * bio 编程
 */
public class BioServer {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("当前连接的客户端 ：" + socket.getRemoteSocketAddress().toString());
            try {
                byte[] bytes = new byte[1024];
                InputStream in = socket.getInputStream();
                in.read(bytes);
                System.out.println("接收客户端请求的消息：" + new String(bytes));
                OutputStream out = socket.getOutputStream();
                out.write("PONG\n".getBytes());
                out.flush();
                socket.shutdownOutput();
                socket.shutdownInput();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
