package com.chuf.sys.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

/**
 * @program: Dash
 * @ClassName: C10kClient
 * @description:
 * @author: chuf
 * @create: 2021-11-27 22:47
 **/
public class C10kClient {

    private static final Logger log = LoggerFactory.getLogger(C10kClient.class);

    public static void main(String[] args) {
        LinkedList<SocketChannel> socketLists = new LinkedList<>();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9090);

        for (int i = 10000; i < 65000; i++) {
            try {
                SocketChannel socketChannel1 = SocketChannel.open();
                SocketChannel socketChannel2 = SocketChannel.open();

                socketChannel1.bind(new InetSocketAddress("192.168.2.121", i));
                socketChannel1.connect(inetSocketAddress);
                boolean open1 = socketChannel1.isOpen();
                log.info("open1:{}", open1);
                socketLists.add(socketChannel1);

                socketChannel2.bind(new InetSocketAddress("192.168.2.121", i));
                socketChannel2.connect(inetSocketAddress);
                boolean open2 = socketChannel2.isOpen();
                log.info("open2:{}", open2);
                socketLists.add(socketChannel2);
            } catch (IOException e) {
                log.error(String.valueOf(e));
            }
        }
    }
}
