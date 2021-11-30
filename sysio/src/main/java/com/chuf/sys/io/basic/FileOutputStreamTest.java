package com.chuf.sys.io.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
    private static final Logger log = LoggerFactory.getLogger(FileOutputStreamTest.class);
    private static final String path = "./sysio/xxoo.txt";


    public static void main(String[] args) {
        writeFile();
    }

    public static void writeFile() {
        File file = new File(path);
        // FileOutputStream fos = new FileOutputStream(file) 覆盖原有文件内容
        // FileOutputStream fos = new FileOutputStream(file, true) 追加到文件后面
        try (FileOutputStream fos = new FileOutputStream(file)) {
            //输入一个字节
            fos.write('H');
            //输入一个字符串，转换成字节
            String str = "Hello world";
            fos.write(str.getBytes());

            fos.write(str.getBytes(), 0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
