package com.chuf.sys.io.basic.byteBuffer;

import com.chuf.sys.io.utils.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @program: Dash
 * @ClassName: Translate
 * @description: 字符串与 ByteBuffer 的转换
 * @author: chuf
 * @create: 2021-12-11 23:06
 **/
public class Translate {

    public static void main(String[] args) {
        standardCharsets_decoder();
    }

    /**
     * 方法一
     * 编码：字符串调用getByte方法获得byte数组，将byte数组放入ByteBuffer中
     *
     * 解码：先调用ByteBuffer的flip方法，然后通过StandardCharsets的decoder方法解码
     */
    public static void standardCharsets_decoder() {
        String str = "hello";

        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 通过字符串的getByte方法获得字节数组，放入缓冲区中
        buffer.put(str.getBytes());
        ByteBufferUtil.debugAll(buffer);

        // 将缓冲区中的数据转化为字符串
        // 切换模式
        buffer.flip();
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s);
        ByteBufferUtil.debugAll(buffer);
    }

    /**
     * 方法二
     * 编码：通过StandardCharsets的encode方法获得ByteBuffer，此时获得的ByteBuffer为读模式，无需通过flip切换模式
     *
     * 解码：通过StandardCharsets的decoder方法解码
     */
    public static void method2() {
        String str1 = "hello";

        // 此时获得的ByteBuffer为读模式，无需通过flip切换模式
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(str1);

        // 方法三
        // 通过StandardCharsets的encode方法获得ByteBuffer
        // 此时获得的ByteBuffer为读模式，无需通过flip切换模式
        ByteBuffer buffer1 = ByteBuffer.wrap(str1.getBytes());

        ByteBufferUtil.debugAll(buffer);

        // 通过StandardCharsets解码，获得CharBuffer，再通过toString获得字符串
        String s = StandardCharsets.UTF_8.decode(buffer).toString();
        System.out.println(s);
        ByteBufferUtil.debugAll(buffer);
    }
}
