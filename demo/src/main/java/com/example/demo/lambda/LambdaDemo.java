package com.example.demo.lambda;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 表达式
 * https://objcoding.com/2019/03/04/lambda/
 */
public class LambdaDemo {

    public static void main(String[] args) {
//        functionalInterface();
        lambdaAndCollections();
    }

    /**
     * lambda 表达式写法
     */
    public static void functionalInterface() {
        // 无参的
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread run");
            }
        }).start();

        new Thread(() -> System.out.println("Lambda Thread run")).start();

        // 带参数的
        List<String> list = Arrays.asList("a", "bcd", "ef", "g");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null) {
                    return -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(list.toArray()));

        Collections.sort(list, (s1, s2) -> {
            if (s1 == null) {
                return 1;
            }
            if (s2 == null) {
                return -1;
            }
            return s2.length() - s1.length();
        });
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * Lambda and Collections
     */
    public static void lambdaAndCollections() {
        // 需要重写 remove 方法，才能执行删除操作
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        List<Integer> list1 = Lists.newArrayList(1,2,3,4);

        //forEach 作用是对容器中的每个元素执行action指定的动作
        list.forEach(e -> {
            if(e > 2){
                System.out.println(e);
            }
        });
        System.out.println("forEach ===>" + list);

        //removeIf 删除容器中所有满足filter指定条件的元素
        list1.removeIf(e -> e < 2);
        System.out.println("removeIf ===>" + list1);

        //replaceAll 对每个元素执行operator指定的操作，并用操作结果来替换原来的元素
        list1.replaceAll(e -> {
            if(e > 3) {
                return 0;
            }
            return e;
        });
        System.out.println("replaceAll===>" + list1);


    }
}
