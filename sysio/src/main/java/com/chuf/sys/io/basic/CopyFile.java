package com.chuf.sys.io.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {

    public static void main(String[] args) {
        copyJpeg();
    }

    public static void copyJpeg() {
        byte[] data = new byte[1024];
        int len = 0;
        try (FileInputStream fis = new FileInputStream(new File("./sysio/dog.jpeg"));
             FileOutputStream fos = new FileOutputStream(new File("./sysio/dogCopy.jpeg"))) {
            while ((len = fis.read(data)) != -1) {
                fos.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
