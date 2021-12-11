package com.chuf.sys.io.basic.byteBuffer;

import com.chuf.sys.io.utils.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @program: Dash
 * @ClassName: TestByteBuffer
 * @description:
 * @author: chuf
 * @create: 2021-12-11 22:49
 **/
public class TestByteBuffer {

    public static void main(String[] args) {
        //开辟缓存
        ByteBuffer buffer = ByteBuffer.allocate(10);
        //向 buffer 中写入一个字节
        buffer.put((byte) 97);
        //查看 buffer 状态
        ByteBufferUtil.debugAll(buffer);


        //向 buffer 中写入四个字节
        buffer.put(new byte[]{98, 99, 100, 101});
        ByteBufferUtil.debugAll(buffer);

        // 获取数据
        buffer.flip();
        ByteBufferUtil.debugAll(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);

        // 使用compact切换模式
        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

        //再次写入
        buffer.put((byte) 102);
        ByteBufferUtil.debugAll(buffer);
    }
}
