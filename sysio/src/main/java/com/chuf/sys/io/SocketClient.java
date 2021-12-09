package com.chuf.sys.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @program: Dash
 * @ClassName: SocketClient
 * @description:
 * @author: chuf
 * @create: 2021-12-05 22:28
 **/
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9090);
        socket.setSendBufferSize(20);
        socket.setTcpNoDelay(true);
        OutputStream os = socket.getOutputStream();
        String str = "Hello world";
        os.write(str.getBytes(StandardCharsets.UTF_8));
    }
}
