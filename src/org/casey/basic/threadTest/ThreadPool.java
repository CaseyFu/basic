package org.casey.basic.threadTest;

import java.util.List;
import java.util.LinkedList;

public class ThreadPool {

    final List<Runnable> L = new LinkedList<>();//装任务容器

    //把任务加入容器,待线程处理
    public void add(Runnable r) {
        synchronized (this.L) {
            this.L.add(r);
            this.L.notifyAll();        //有任务,通知等待的线程去执行
        }

    }

    public ThreadPool() {
        //线程池中的线程个数
        int threadPoolSize = 5;
        for (int i = 0; i < threadPoolSize; i++) {
            new Pool("This is Thread-" + i).start();
        }
    }

    class Pool extends Thread {
        public Pool(String name) {
            super(name);
        }

        public void run() {
            System.out.println("启动线程:" + this.getName());
            //一直循环的目的是:执行了一次任务后返回线程池执行其他任务
            while (true) {
                Runnable doTask;
                synchronized (L) {
                    //无任务,则等待,等待过程释放资源,释放同步锁
                    //用while而不用if的原因:	如果用if那么被唤醒了之后所有的线程都得到唤醒,可能线程数大于任务数,就会有线程执行空容器的操作
                    //用while,每回被唤醒之后判断一次容器L是否为空,如果空则继续wait();确保容器为空时完全没有线程再取容器里的任务
                    while (L.isEmpty()) {
                        try {
                            L.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //有任务
                    doTask = L.remove(0);
                }
                System.out.println(this.getName() + "获取到任务");
                doTask.run();
                System.out.println("任务已被" + this.getName() + "执行");
            }
        }
    }
}
