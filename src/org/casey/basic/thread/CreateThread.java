package org.casey.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CreateThread
 * @Author caseyfu
 * @Description 创建线程的三种方法
 * @Date 2020/7/4 10:46
 */

class ThreadTest extends Thread {
    // 创建线程的方式 继承Thread类创建
    public ThreadTest() {
    }

    public ThreadTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void entrance1() {
        new ThreadTest("线程1").start();
        new ThreadTest("线程2").start();
    }

    public void entrance2() {
        // 内部类创建线程
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}

class RunnableTest implements Runnable {
    // 创建线程的方式 实现Runnable接口
    public int i = 0;

    public synchronized void decrement() {
        System.out.println(Thread.currentThread().getName() + " : " + i);
        this.i--;
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
    public void increment() {
        System.out.println(i);
        this.i++;
//        try {
//            Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void run() {
        while (i < 1000000) {
            increment();
        }
    }

    public void entrance()   {
        RunnableTest rt1 = new RunnableTest();
//        RunnableTest rt2 = new RunnableTest();

        long start = System.currentTimeMillis();
        int i=0;
        while(i<1000000){
            System.out.println(i);
            i++;
        }

        long end = System.currentTimeMillis();
        long a = end - start;
        start = System.currentTimeMillis();

        Thread t1 = new Thread(rt1, "线程1"); // 进程实体
        Thread t2 = new Thread(rt1, "线程2");
        Thread t3 = new Thread(rt1, "线程3");
        Thread t4 = new Thread(rt1, "线程4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        end = System.currentTimeMillis();
        long b = end-start;
        System.out.println("单线程: "+a+"ms");
        System.out.println("4条线程: "+b+"ms");
    }
}

class CallableFutureTest implements Callable<Object> {
    // 创建线程的方式 实现Callable
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            Thread.sleep(200);
        }
        return Thread.currentThread().getName() + "执行完成";
    }

    public void entrance() {
        CallableFutureTest cft = new CallableFutureTest();
        FutureTask<Object> ft1 = new FutureTask<>(cft);
        FutureTask<Object> ft2 = new FutureTask<>(cft);
        new Thread(ft1, "线程1").start();
        new Thread(ft2, "线程2").start();
        try {
            System.out.println("线程1状态:" + ft1.get());
            System.out.println("线程2状态:" + ft2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class CreateThread {
    public static void main(String[] args) {
        ThreadTest tt = new ThreadTest();
        RunnableTest rt = new RunnableTest();
        CallableFutureTest cft = new CallableFutureTest();
//        tt.entrance2();
        rt.entrance();
//        cft.entrance();
    }
}
