package com.nio2.aio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * AIO客户端
 * @author haiswang
 *
 */
public class AioClient {

    /**
     * @param args
     * @throws IOException 
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        
        AsynchronousSocketChannel client = null;
        final ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        try {
            client = AsynchronousSocketChannel.open();
            Future<Void> result = client.connect(new InetSocketAddress("127.0.0.1", 8899));
            while(!result.isDone()) {
                System.out.println("connect is not done ,wait.");
                try {
                    Thread.currentThread().sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("yeah ,i am connect success.");
            /******************往服务端发送消息********************/
            buffer.put("Hello AIO Server.".getBytes());
            //这边消息写完了以后需要掉一下flip()相当于转换成写,不然服务端会接收不到字符
            buffer.flip();
            Future<Integer> future = client.write(buffer);
            //这边写完了以后需要等代写结束,不然下面的buffer不能复用,如果不等这边写结束直接复用会出现奇怪的问题
            while(!future.isDone()) {
            }
            
            /******************从客户端接收消息*******************/
            //复用上面的buffer,这边也可以不复用,如果不复用就无需等待上面的操作执行结束
            buffer.clear();
            //从服务端读取消息
            future = client.read(buffer);
            //等待读取结束
            while(!future.isDone()) {
            }
            //状态转换成,转到读取模式
            buffer.flip();
            System.out.println("Client Recive : " + new String(buffer.array()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if(null!=client) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
