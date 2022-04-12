package com.example.demo;

import java.util.*;

/**
 * @program: Dash
 * @ClassName: Test
 * @description:
 * @author: chuf
 * @create: 2022-01-06 23:49
 **/
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        if (list == null) {
            System.out.println("");
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
