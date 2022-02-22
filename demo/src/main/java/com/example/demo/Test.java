package com.example.demo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: Dash
 * @ClassName: Test
 * @description:
 * @author: chuf
 * @create: 2022-01-06 23:49
 **/
public class Test {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        set.add("e");
        System.out.println("set遍历之前 ====>" + set);
        final Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            final String next = iterator.next();
            System.out.println(next);
            iterator.remove();
        }
        System.out.println("set遍历之后 ====>" + set);
    }
}
