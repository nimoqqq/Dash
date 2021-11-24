package com.chuf.sys.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

/**
 * @program: Dash
 * @ClassName: OSFileIO
 * @description:
 * @author: chuf
 * @create: 2021-11-23 23:10
 **/
public class OSFileIO {

    private final static Logger log = LoggerFactory.getLogger(OSFileIO.class);
    private final static byte[] data = "123456789\n".getBytes();
    private final static String path = "./sysio/src/main/resources/out.txt";

    public static void main(String[] args) throws Exception {
        log.info("Start process {}...", OSFileIO.class.getName());
//        testBasicFileIO();
    }

    /**
     * 最基本的 file 写
     *
     * @throws Exception
     * 此时数据只是存到了 pagecache 中，被标记为 Direct，等到触发内核 flush 机制，才会被刷到磁盘中
     */
    public static void testBasicFileIO() throws Exception {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        while (true) {
            log.debug("写入文本:{}", Arrays.toString(data));
            out.write(data);
            Thread.sleep(100);
        }
    }

    /**
     * 测试 buffer 文件 IO
     * 先在 jvm 内存中创建一个 8kb 的字节数组
     * syscall  write(8KBbyte[])
     * 结论：
     *      比 FileOutputStream 写入更快，因为先将数据缓存到 jvm 内存中，到 8kb 时才会调用一次写操作，减少了用户态转换内核态的次数
     */
    public static void testBufferFileIO() throws Exception {
        File file = new File(path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while (true) {
            log.debug("写入文本:{}", Arrays.toString(data));
            out.write(data);
            Thread.sleep(100);
        }
    }

    public static void testRandomAccessFileWrite() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(path,"rw");

        raf.write("hello world".getBytes());
        raf.write("hello java".getBytes());

        //设置文件指针偏移位置，从该文件的头开始。发生下一次读活写
        raf.seek(4);
        raf.write("ooxx".getBytes());

        //返回一该文件唯一关联的 FileChannel 对象
        FileChannel channel = raf.getChannel();

        //堆外  和文件映射的
        //mode -一个常量READ_ONLY，根据该文件是否是要被映射的只读，读/写，或私人（写入时复制），分别
        //position - 映射区域要启动的文件中的位置; 必须是非负的
        //size - 要映射的区域的大小; 必须是非负数，不得大于Integer.MAX_VALUE
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);

        //不是系统调用，但是数据会到达内核的 pagecache
        map.put("@@@@".getBytes());

//        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.put("123".getBytes());

        //读写交替
        buffer.flip();
        //读取该缓冲区当前位置的字节，然后增加位置
        buffer.get();

        //压缩此缓冲区
        buffer.compact();

        //清除此缓冲区。 位置设置为零，限制设置为容量，标记被丢弃。
        buffer.clear();
    }
}
