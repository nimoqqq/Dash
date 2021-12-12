package com.chuf.sys.io.basic.channel.server;

import com.chuf.sys.io.utils.ByteBufferUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @program: Dash
 * @ClassName: ServerSocketChannelBIO
 * @description: 阻塞模式
 * @author: chuf
 * @create: 2021-12-12 17:40
 **/
public class ServerSocketChannelBIO {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        // 用户存放连接的集合
        ArrayList<SocketChannel> channels = new ArrayList<>();
        try (ServerSocketChannel server = ServerSocketChannel.open()) {
            //为服务器通道绑定端口
            server.bind(new InetSocketAddress(8080));
            while (true) {
                System.out.println("before connecting...");
                // 设置为非阻塞模式，没有连接时返回null，不会阻塞线程
//                server.configureBlocking(false);
                //阻塞,等待客户端连接，返回 fd（文件描述符）
                final SocketChannel client = server.accept();
                System.out.println("after connecting...");
                channels.add(client);

                for (SocketChannel channel : channels) {
                    System.out.println("before reading");
                    // 设置为非阻塞模式，若通道中没有数据，会返回0，不会阻塞线程
//                    channel.configureBlocking(false);
                    // 处理通道中的数据
                    // 当通道中没有数据可读时，会阻塞线程
                    channel.read(buffer);
                    buffer.flip();
                    ByteBufferUtil.debugRead(buffer);
                    buffer.clear();
                    System.out.println("after reading");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
