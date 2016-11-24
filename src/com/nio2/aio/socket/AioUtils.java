package com.nio2.aio.socket;

import java.nio.ByteBuffer;

public class AioUtils {
    
    public static void printBuffer(ByteBuffer buffer) {
        printBuffer(buffer, "Default");
    }
    
    public static void printBuffer(ByteBuffer buffer ,String comments) {
        System.out.println(comments + " buffer info======>");
        System.out.println("position : " + buffer.position());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("limit : " + buffer.limit());
    }
    
}
