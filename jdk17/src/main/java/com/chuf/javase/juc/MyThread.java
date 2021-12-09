package com.chuf.javase.juc;

/**
 * 创建线程的几种方式
 */
public class MyThread {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        Thread runnable = new Thread(new MyRunnable());
        runnable.start();
    }

}

/**
 * 方法1
 */
class MyThread1 extends Thread{

    @Override
    public void run() {
        System.out.println("自定线程...");
    }
}

/**
 * 方法2
 */
class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable创建自定线程...");
    }
}


