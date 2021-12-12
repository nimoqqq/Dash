package com.chuf.sys.io.basic.channel.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @program: Dash
 * @ClassName: Client
 * @description:
 * @author: chuf
 * @create: 2021-12-12 17:50
 **/
public class Client {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 建立连接
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            System.out.println("waiting...");
            socketChannel.write(StandardCharsets.UTF_8.encode("HI ~"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
