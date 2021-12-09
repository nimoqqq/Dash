package com.chuf.javase.juc;

/**
 * 创建线程的几种方式
 */
public class MyThread {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        Thread runnable = new Thread(new MyRunnable());
        //设置线程优先级 1-10
        runnable.setPriority(1);
        //将该线程设置为守护线程
//        runnable.setDaemon(true);
        runnable.start();
        //向 runnable 线程发送中断请求，是否能立即响应，还需要看具体代码
        runnable.interrupt();
        try {
            //主线程等待当前线程执行完，在执行
            runnable.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            //线程休眠 20 毫秒
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //方法 3
        Thread thread = new Thread(() -> {
            System.out.println("使用 lamdba 创建线程");
        });
        thread.start();
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



/**
 * 线程的几种状态
 * new 创建，还未执行
 * run 运行中的线程,正在执行 run 方法的 java 代码
 * blocked 阻塞状态
 * waiting 等待状态
 * timed waiting 计时等待
 * Terminated 结束
 */
