package com.zyouke.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannel {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        FileInputStream inputStream = new FileInputStream("f:/temp/t_area.sql");
        FileOutputStream outputStream = new FileOutputStream("f:/temp/user.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();
        FileChannel outputStreamChannel = outputStream.getChannel();
        while (true){
            buffer.clear();
            int read = inputStreamChannel.read(buffer);
            System.out.println(read);
            if (read < 0){
                break;
            }
            buffer.flip();
            System.out.println(new String(buffer.array(),"UTF-8"));
            //outputStreamChannel.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }
}
