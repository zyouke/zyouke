package com.zyouke.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * bio 编程
 */
public class BioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("当前连接的客户端 ：" + socket.getRemoteSocketAddress().toString());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream in = socket.getInputStream();
                        InputStreamReader reader = new InputStreamReader(in);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String info = null;
                        while ((info = bufferedReader.readLine()) != null){
                            System.out.println(info);
                        }
                        in.close();
                        reader.close();
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }


}
