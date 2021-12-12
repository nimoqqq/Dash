package com.chuf.sys.io.basic.channel.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Dash
 * @ClassName: SelectServer
 * @description: 多路复用器 selector
 * @author: chuf
 * @create: 2021-12-12 18:17
 **/
public class SelectServer {
    public static void main(String[] args) {
        try (final ServerSocketChannel server = ServerSocketChannel.open()) {
            server.bind(new InetSocketAddress(8080));

            Selector selector = Selector.open();
            server.configureBlocking(false);

            // 将通道注册到选择器中，并设置感兴趣的事件
            /**
             * connect - 客户端连接成功时触发
             * accept - 服务器端成功接受连接时触发
             * read - 数据可读入时触发，有因为接收能力弱，数据暂不能读入的情况
             * write - 数据可写出时触发，有因为发送能力弱，数据暂不能写出的情况
             */
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                // 不会阻塞，也就是不管有没有事件，立刻返回，自己根据返回值检查是否有事件
                // final int ready = selector.selectNow();
                // 阻塞直到绑定事件发生，或是超时（时间单位为 ms）
                // final int ready = selector.select(1000);

                // 若没有事件就绪，线程会被阻塞，反之不会被阻塞。从而避免了CPU空转
                // 返回值为就绪的事件个数
                final int ready = selector.select();
                // 获取所有事件
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                // 使用迭代器遍历事件
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    // 判断key的类型
                    if(key.isAcceptable()) {
                        // 获得key对应的channel
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        System.out.println("before accepting...");

                        // 获取连接并处理，而且是必须处理，否则需要取消
                        SocketChannel socketChannel = channel.accept();
                        System.out.println("after accepting...");

                        // 处理完毕后移除
                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
