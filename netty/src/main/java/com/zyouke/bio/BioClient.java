package com.zyouke.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * bio 客户端
 */
public class BioClient {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream out = socket.getOutputStream();
                        out.write(String.valueOf(System.currentTimeMillis()).getBytes());
                        out.flush();
                        socket.shutdownOutput();
                        InputStream in = socket.getInputStream();
                        byte[] bytes =  new byte[1024];
                        in.read(bytes);
                        System.out.println("服务端返回的消息：" + new String(bytes));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
