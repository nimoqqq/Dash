package com.chuf.sys.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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


}
