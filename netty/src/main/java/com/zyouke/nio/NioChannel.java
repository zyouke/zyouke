package com.zyouke.nio;


import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;

public class NioChannel {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel fileChannel = FileChannel.open(Paths.get("F:/temp/t_area.sql"));
    }
}
