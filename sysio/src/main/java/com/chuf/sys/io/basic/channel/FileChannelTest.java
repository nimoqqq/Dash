package com.chuf.sys.io.basic.channel;

import com.chuf.sys.io.basic.FileOutputStreamTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @program: Dash
 * @ClassName: FileChannelTest
 * @description: 文件数据通道传输
 * @author: chuf
 * @create: 2021-12-11 23:55
 **/
public class FileChannelTest {
    private static final Logger log = LoggerFactory.getLogger(FileChannelTest.class);
    private static final String fisPath = "./sysio/xxoo.txt";
    private static final String fosPath = "./sysio/ooxx.txt";

    public static void main(String[] args) {
        ltTwoG();
    }

    /**
     * 小于 2g 的传输
     */
    public static void ltTwoG() {
        try (FileInputStream fis = new FileInputStream(fisPath);
             FileOutputStream fos = new FileOutputStream(fosPath);
             final FileChannel fisChannel = fis.getChannel();
             final FileChannel fosChannel = fos.getChannel()) {
            // 参数：inputChannel的起始位置，传输数据的大小，目的channel
            // 返回值为传输的数据的字节数
            // transferTo一次只能传输2G的数据
            final long l = fisChannel.transferTo(0, fisChannel.size(), fosChannel);
            System.out.println(l);
        } catch (Exception e) {

        }
    }

    /**
     * 大于 2g 的传输
     */
    public static void lgTwoG() {
        try (FileInputStream fis = new FileInputStream(fisPath);
             FileOutputStream fos = new FileOutputStream(fosPath);
             final FileChannel fisChannel = fis.getChannel();
             final FileChannel fosChannel = fos.getChannel()) {
            long size = fisChannel.size();
            long capacity = fisChannel.size();
            // 分多次传输
            while (capacity > 0) {
                // transferTo返回值为传输了的字节数
                capacity -= fisChannel.transferTo(size-capacity, capacity, fosChannel);
            }
        } catch (Exception e) {

        }
    }
}
