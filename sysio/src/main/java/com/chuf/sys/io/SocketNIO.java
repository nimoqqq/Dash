package com.chuf.sys.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @program: Dash
 * @ClassName: SocketNIO
 * @description:
 * @author: chuf
 * @create: 2021-12-05 22:59
 **/
public class SocketNIO {
    public static void main(String[] args) throws IOException {
        //客户端集合
        LinkedList<SocketChannel> clients = new LinkedList<>();
        //开启监听，接收客户端
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress(9090));
        //设置是否阻塞.(连接时候的)
        open.configureBlocking(false);
        while (true) {
            //accept 调用内核
            //bio 的时候，会阻塞等待
            //nio 的时候，不会阻塞，如果有客户端连接过来，就返回这个客户端的 fd。没有客户端过来就返回-1
            SocketChannel client = open.accept();
            if (client == null) {
                System.out.println("null....");
            } else {
                //设置是否阻塞.(传输数据时)
                client.configureBlocking(false);
                clients.add(client);
            }
            //可以在堆里   堆外
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            //遍历已经链接进来的客户端能不能读写数据
            for (SocketChannel c : clients) {   //串行化！！！！  多线程！！
                int num = c.read(buffer);  // >0  -1  0   //不会阻塞
                if (num > 0) {
                    buffer.flip();
                    byte[] aaa = new byte[buffer.limit()];
                    buffer.get(aaa);

                    String b = new String(aaa);
                    System.out.println(c.socket().getPort() + " : " + b);
                    buffer.clear();
                }
            }
        }
    }
}
