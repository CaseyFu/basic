package org.casey.basic.thread;

import org.junit.Test;

/**
 * @ClassName TheUseOfInterruptTest
 * @Author Fu Kai
 * @Version v1.0.0
 * @Description TheBestWayToStopThread
 * @Date 2020/9/21
 */

public class TheUseOfInterruptTest {
    /**
     * 通过下面的例子明白
     * 1. 线程的中断状态, 本质就是线程对象的一个成员变量
     * 2. 线程对象.interrupt() 将该线程对象中断状态设置为中断true
     * 3. 当线程t在sleep或wait或join休眠时被外界线程调用interrupt打断会抛出InterruptedException, 并会重置中断状态为false
     */
    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void knowInterrupt() throws InterruptedException {
        Thread t = new Thread(() -> {
            // 2. t线程默认无中断标志 中断状态为false
            // System.out.println("Thread started: "+ Thread.currentThread().isInterrupted());
            System.out.println("Thread started: "+ Thread.interrupted());
            try {
                // 3. t线程休眠3s, 等待main线程休眠2s之后t线程被打上中断标志, 而此时t线程还在休眠, 所以抛出异常
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // 5. 抛出异常之后中断标志会被重置, 所以t线程当前中断状态为false
                System.out.println("interrupted when sleep: " + Thread.currentThread().isInterrupted());
                System.out.println("interrupted when sleep: " + Thread.interrupted());
            }
            System.out.println("执行任务中...");
            System.out.println("Thread end");
        });
        t.start();
        // 1. main线程休眠1s
        Thread.sleep(1000);
        // 4. 等待main线程休眠1s之后给t线程对象打上中断标志
        t.interrupt();
    }

    /**
     * 通过下面的例子明白
     * 1. Thread.interrupted() 查看当前运行的线程中断状态, 并重置中断状态 false
     * 2. Thread.currentThread().isInterrupted() 仅仅查看当前运行的线程中断状态, 不重置
     */
    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void knowInterrupt0() {
        // 1. 一来就设置main线程的中断状态为true
        Thread.currentThread().interrupt();
        // 2. 查看main线程中断状态, 已经调用interrupt() 所以中断状态为true
        System.out.println("Thread.currentThread().isInterrupted(): " + Thread.currentThread().isInterrupted());
        // 3. 查看main线程中断状态, Thread.currentThread().isInterrupted()不重置中断状态 所以中断状态还为true
        System.out.println("Thread.currentThread().isInterrupted(): " + Thread.currentThread().isInterrupted());
        // 4. 查看main线程中断状态, Thread.currentThread().isInterrupted()不重置中断状态 所以中断状态还为true, 并重置中断状态为false
        System.out.println("Thread.interrupted(): " + Thread.interrupted());
        // 5. 查看main线程中断状态, 中断状态已被上面重置为false 所以中断状态还为false 再次重置中断状态为false
        System.out.println("Thread.interrupted(): " + Thread.interrupted());
    }

    @Test
    public void killThread() throws InterruptedException {
        @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
        Thread t = new Thread(() -> {
            System.out.println("Thread started");
            while (true) {
                // 2. t线程不断循环执行任务, 检查到设置了中断为true后跳出循环, 并不重置中断状态为false
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("interrupted");
                    break;
                }
                System.out.println("任务执行中...");
            }
            System.out.println("Thread end");
        });
        t.start();
        // 1. 仅仅让t线程跑1ms 之后给t线程设置中断状态为true
        Thread.sleep(1);
        t.interrupt();
    }
}
