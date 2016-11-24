package com.nio2.aio.socket;

import java.nio.ByteBuffer;

public class ByteBufferTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        ByteBuffer byteBuffer = ByteBuffer.wrap("hello byte buffer".getBytes());
        //byteBuffer.flip();
        byteBuffer.clear();
        
        printBuffer(byteBuffer);
        
        System.out.println(new String(byteBuffer.array()));
        
    }
    
    public static void printBuffer(ByteBuffer buffer ) {
        printBuffer(buffer, "default");
    }
    
    public static void printBuffer(ByteBuffer buffer ,String comments) {
        System.out.println(comments + " ----------------------");
        System.out.println("position : " + buffer.position());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("limit : " + buffer.limit());
    }

}
