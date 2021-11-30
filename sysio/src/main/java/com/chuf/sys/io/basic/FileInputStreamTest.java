package com.chuf.sys.io.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class FileInputStreamTest {
    private static final Logger log = LoggerFactory.getLogger(FileInputStreamTest.class);
    private static final String path = "./sysio/xxoo.txt";

    public static void main(String[] args) {
        readFile1024();
    }

    /**
     * 一次读取一个字节
     */
    public static void readFile() {
        File file = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            while (!Objects.equals(-1, fis.read())) {
                log.info("输出:{}", fis.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * try-with-resource
     * 关闭资源
     */
    public static void readFileTWE() {
        File file = new File(path);
        try(FileInputStream fis = new FileInputStream(file)) {
            while (!Objects.equals(-1, fis.read())) {
                log.info("输出:{}", fis.read());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一次读取 1024 个字节
     */
    public static void readFile1024() {
        File file = new File(path);
        byte[] data = new byte[1024];
        int readlen = 0;
        try(FileInputStream fis = new FileInputStream(file)) {
            while ((readlen = fis.read(data)) != -1) {
                log.info("输出:{}", new String(data, 0, readlen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
