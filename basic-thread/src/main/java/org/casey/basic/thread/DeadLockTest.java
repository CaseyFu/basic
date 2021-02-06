package org.casey.basic.thread;

import org.junit.Test;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName DeadLock
 * @Author Fu Kai
 * @Description 3个线程之间的死锁, 及使用可重入锁规避死锁
 * @Date 2020/7/4 16:58
 */

public class DeadLockTest {

    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void deadlock() {
        User user0 = new User();
        User user1 = new User();
        User user2 = new User();


        Thread t0 = new Thread(() -> {
            System.out.println("t0开始占有0号位:");
            synchronized (user0) {
                System.out.println("t0已经占有0号位:");
                System.out.println("t0试图占有1号位:");
                System.out.println("等待中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (user1) {
                    System.out.println("t0成功占有1号位:");
                }
            }
        });
        Thread t1 = new Thread(() -> {
            System.out.println("t1开始占有1号位:");
            synchronized (user1) {
                System.out.println("t1已经占有1号位:");
                System.out.println("t1试图占有2号位:");
                System.out.println("等待中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (user2) {
                    System.out.println("t1成功占有2号位:");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2开始占有2号位:");
            synchronized (user2) {
                System.out.println("t2已经占有2号位:");
                System.out.println("t2试图占有0号位:");
                System.out.println("等待中...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (user0) {
                    System.out.println("t2成功占有0号位:");
                }
            }
        });
        t0.start();
        t1.start();
        t2.start();
    }

    /**
     * 使用可重入锁规避死锁
     */
    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void entrance() {
        Lock locked0 = new ReentrantLock();
        Lock locked1 = new ReentrantLock();
        Thread t0 = new Thread(() -> {
            boolean isLocked0 = false;
            System.out.println(Thread.currentThread().getName() + "试图领locked0");
            try {
                locked0.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked0");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "试图领locked1...等待中...");
                //修改点
                isLocked0 = locked1.tryLock(5, TimeUnit.SECONDS);//5s得不到locked1就放弃占有,
                //放弃占有之后不能unlock,所以要判断是否放弃了locked1

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (isLocked0) {
                    System.out.println(Thread.currentThread().getName() + "已成功占领locked1");
                    locked1.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + "占领locked1不成功,退出");
                }
                locked0.unlock();
            }
        });
        t0.start();

        Thread t1 = new Thread(() -> {
            boolean isLocked1 = false;
            System.out.println(Thread.currentThread().getName() + "试图领locked1");
            try {
                locked1.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked1");
                Thread.sleep(500);
                System.out.println(Thread.currentThread().getName() + "试图领locked0...等待中...");
                isLocked1 = locked0.tryLock(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (isLocked1) {
                    System.out.println(Thread.currentThread().getName() + "已成功占领locked0");
                    locked0.unlock();
                } else {
                    System.out.println(Thread.currentThread().getName() + "占领locked0不成功,退出");
                }
                locked1.unlock();
            }
        });
        t1.start();
    }

    /**
     * 使用可重入锁规避死锁
     */
    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void entrance0() {
        //2个对象之间的死锁,用lock对象实现占用,
        //lock是锁一个区间,锁门的钥匙是lock,开门的钥匙是unlock
        ReentrantLock locked0 = new ReentrantLock();
        Lock locked1 = new ReentrantLock();
        Thread t0 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "试图领locked0");
            try {
                locked0.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked0");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "试图领locked1...等待中...");
                locked1.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locked1.unlock();
                locked0.unlock();
            }
        });
        t0.start();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "试图领locked1");
            try {
                locked1.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked1");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "试图领locked0...等待中...");
                locked0.lock();
                System.out.println(Thread.currentThread().getName() + "已成功占领locked0");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locked0.unlock();
                locked1.unlock();
            }
        });
        t1.start();
    }
}