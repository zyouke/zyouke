package com.zyouke.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * nio buffer
 */
public class NioBuffer {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(5);
        /*for (int i = 0; i < 5; i++) {
            int num = new SecureRandom().nextInt(100);
            buffer.put(num);
        }
        //buffer.flip();
        //buffer.clear();
        //buffer.compact();
        buffer.duplicate();
        while (buffer.hasRemaining()){
            System.out.println("元素 ： " + buffer.get());
            System.out.println("capacity : " + buffer.capacity());
            System.out.println("limit : " + buffer.limit());
            System.out.println("position : " + buffer.position());
            System.out.println("==================================");
        }
    */
        System.out.println(buffer.order().toString());
    }
}
