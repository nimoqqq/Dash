package com.chuf.sys.io.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileTest {
    private static final Logger log = LoggerFactory.getLogger(FileTest.class);
    private static final String path = "./sysio/xxoo.txt";

    public static void main(String[] args) {
        testFile();
    }

    public static void testFile() {
        //只是在java 中创建了一个对象
        File file = new File(path);
        try {
            //在磁盘创建该文件
            boolean newFile = file.createNewFile();
            log.info("文件创建成功{}", newFile);
            log.info("文件名称:{}", file.getName());
            log.info("文件绝对路径:{}", file.getAbsolutePath());
            log.info("文件父级目录:{}", file.getParent());
            log.info("文件大小:{}", file.length());
            log.info("文件是否存在:{}", file.exists());
            log.info("是不是一个文件:{}", file.isFile());
            log.info("是不是同一个目录:{}", file.isDirectory());
//            log.info("删除文件:{}", file.delete());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.valueOf(e));
        }
    }


}
