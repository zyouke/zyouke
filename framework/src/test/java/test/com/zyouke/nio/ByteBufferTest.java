package test.com.zyouke.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    @Test
    public void byteBufferBaseTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("添加元素之前.............");
        byteBufferPrintln(byteBuffer);
        System.out.println("添加元素之后.............");
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        byteBufferPrintln(byteBuffer);
        System.out.println("flip之后.............");
        byteBuffer.flip();
        byteBufferPrintln(byteBuffer);
        System.out.println("get之后.............");
        byteBuffer.get(new byte[3],1,2);
        byteBufferPrintln(byteBuffer);
    }

    private void byteBufferPrintln(ByteBuffer byteBuffer){
        System.out.println("capacity " + byteBuffer.capacity());
        System.out.println("remaining " + byteBuffer.remaining());
        System.out.println("limit " + byteBuffer.limit());
        System.out.println("position " + byteBuffer.position());
        System.out.println("isReadOnly " + byteBuffer.isReadOnly());
    }

}
