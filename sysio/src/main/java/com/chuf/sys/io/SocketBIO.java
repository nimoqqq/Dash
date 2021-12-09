package com.chuf.sys.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: Dash
 * @ClassName: SocketIO
 * @description:
 * @author: chuf
 * @create: 2021-12-05 22:40
 **/
public class SocketBIO {
    public static void main(String[] args) {
        byte[] data = new byte[1024];
        int len = 0;
        try {
            ServerSocket serverSocket = new ServerSocket(9090, 20);
            Socket socket = serverSocket.accept(); //阻塞状态
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            while ((len = bis.read(data)) != -1) {
                System.out.println(new String(data, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
