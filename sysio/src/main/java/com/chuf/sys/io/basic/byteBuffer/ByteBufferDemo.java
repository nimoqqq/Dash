package com.chuf.sys.io.basic.byteBuffer;

import com.chuf.sys.io.utils.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: Dash
 * @ClassName: ByteBufferDemo
 * @description: 处理粘包和半包
 * @author: chuf
 * @create: 2021-12-11 23:23
 **/
public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //模拟粘包
        //第一次接收的包
        buffer.put("Hello,World\nI'm nimo\n Ho".getBytes());
        ByteBufferUtil.debugAll(buffer);
        List<ByteBuffer> list = split(buffer);
        for (ByteBuffer byteBuffer : list) {
            ByteBufferUtil.debugAll(byteBuffer);
        }

        //第二次接收的包
        buffer.put("w are you?\n".getBytes());
        List<ByteBuffer> list1 = split(buffer);
        for (ByteBuffer byteBuffer : list1) {
            ByteBufferUtil.debugAll(byteBuffer);
        }
    }

    public static List<ByteBuffer> split(ByteBuffer buffer) {
        List<ByteBuffer> list = new ArrayList<>();
        //切换读模式
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            //get(i) 不会改变 position
            if (buffer.get(i) == '\n') {
                //截取缓冲区
                int lenbuf = i + 1 - buffer.position();
                if (lenbuf > 0) {
                    ByteBuffer newbuffer = ByteBuffer.allocate(lenbuf);
                    for (int j = 0; j < lenbuf; j++) {
                        newbuffer.put(buffer.get());
                    }
                    //方便返回后读取
                    newbuffer.flip();
                    list.add(newbuffer);
                }
            }
        }
        // 切换为写模式，但是缓冲区可能未读完，这里需要使用compact
        buffer.compact();
        return list;
    }
}
