package com.nio2.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

/**
 * AIO服务端
 * @author haiswang
 *
 */
public class AioServer {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        try {
            final AsynchronousServerSocketChannel asynServer = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(8899));
            asynServer.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                final ByteBuffer buffer = ByteBuffer.allocate(1024);
                @Override
                public void completed(AsynchronousSocketChannel result, Void attachment) {
                    
                    try {
                        /******************从客户端接收消息*******************/
                        buffer.clear();
                        Future<Integer> future = result.read(buffer);
                        while(!future.isDone()) {
                        }
                        
                        buffer.flip();
                        System.out.println("Received message : " + new String(buffer.array()));
                        
                        /******************向客户端发送消息*******************/
                        buffer.clear();
                        buffer.put("Hello AIO client.".getBytes());
                        buffer.flip();
                        future = result.write(buffer);
                        while(!future.isDone()) {
                        }
                    }  finally {
                        try {
                            result.close();
                            asynServer.accept(null ,this);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    // TODO Auto-generated method stub
                    System.out.println("failed : " + exc);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        while(true) {
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void printBuffer(ByteBuffer buffer ,String comments) {
        System.out.println(comments + " ----------------------");
        System.out.println("position : " + buffer.position());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("limit : " + buffer.limit());
    }
}
