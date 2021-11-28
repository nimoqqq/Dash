package com.chuf.sys.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: Dash
 * @ClassName: SocketIOPropertites
 * @description:
 * @author: chuf
 * @create: 2021-11-28 21:19
 **/
public class SocketIOPropertites {
    private static final Logger log = LoggerFactory.getLogger(SocketIOPropertites.class);
    //设置接收缓冲区大小的大小。 该值必须大于0
    private static final int RECEIVE_BUFFER = 10;
    //是否启用或禁用套接字选项
    private static final boolean REUSE_ADDR = false;
    //指定的超时，以毫秒为单位
    private static final int SO_TIMEOUT = 0;
    //请求进入连接队列的最大长度
    private static final int BACK_LOG = 2;
    //是否开启心跳
    private static final boolean CLI_KEEPALIVE = false;
    //接收TCP紧急数据
    private static final boolean CLI_OOB = false;
    //设置接收缓冲区大小的大小。 该值必须大于0
    private static final int CLI_REC_BUF = 20;

    private static final boolean CLI_REUSE_ADDR = false;
    private static final int CLI_SEND_BUF = 20;
    private static final boolean CLI_LINGER = true;
    private static final int CLI_LINGER_N = 0;
    private static final int CLI_TIMEOUT = 0;
    private static final boolean CLI_NO_DELAY = false;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(9090), BACK_LOG);
            //设置内部套接字接收缓冲区的大小，并设置通告给远程对等体的TCP接收窗口的大小。
            serverSocket.setReceiveBufferSize(RECEIVE_BUFFER);
            //当TCP连接关闭时，连接可能会在连接关闭后一段时间内保持在超时状态
            serverSocket.setReuseAddress(REUSE_ADDR);
            //使用此选项设置为非零超时，对此ServerSocket的accept（）的调用将仅阻止此时间.
            serverSocket.setSoTimeout(SO_TIMEOUT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("server up use 9090");
        try {
            while (true) {
                Socket client = serverSocket.accept();
                log.info("client port {}", client.getPort());
                //启用/禁用 SO_KEEPALIVE
                client.setKeepAlive(CLI_KEEPALIVE);
                //如果用户希望接收紧急数据，则必须启用此选项.默认情况下，此选项被禁用
                client.setOOBInline(CLI_OOB);
                //增加接收缓冲区大小可以提高大容量连接的网络I / O的性能，同时减少它可以帮助减少输入数据的积压
                client.setReceiveBufferSize(CLI_REC_BUF);
                client.setReuseAddress(CLI_REUSE_ADDR);
                client.setSendBufferSize(CLI_SEND_BUF);
                client.setSoLinger(CLI_LINGER, CLI_LINGER_N);
                client.setSoTimeout(CLI_TIMEOUT);
                client.setTcpNoDelay(CLI_NO_DELAY);

                //client.read  阻塞
                new Thread(
                        () -> {
                            try {
                                InputStream inputStream = client.getInputStream();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                char[] data = new char[1024];
                                while (true) {
                                    int num = bufferedReader.read(data);
                                    if (num > 0) {
                                        System.out.println("client read some data is :" + num + " val :" + new String(data, 0, num));
                                    } else if (num == 0) {
                                        System.out.println("client readed nothing!");
                                        continue;
                                    } else {
                                        System.out.println("client readed -1...");
                                        System.in.read();
                                        client.close();
                                        break;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
