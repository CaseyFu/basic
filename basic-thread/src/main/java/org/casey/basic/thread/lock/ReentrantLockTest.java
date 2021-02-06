package org.casey.basic.thread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 可重入锁测试
 * @Date 2020/9/24
 */

public class ReentrantLockTest {

    /**
     * 可重入锁的基本使用，相当于一个互斥信号量
     */
    public static void reentry(){
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(currentThread().getName() + "占用对象锁");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(currentThread().getName() + "释放锁");
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(currentThread().getName() + "占用对象锁");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(currentThread().getName() + "释放锁");
                }
            }
        };
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

    /**
     * 可重入锁，请求锁，tryLock，如果在指定时间内没获得锁就放弃
     */
    public static void tryReentry(){
        Lock lock = new ReentrantLock();
        Thread t1 = new Thread() {

            @Override
            public void run() {
                boolean locked = false;
                try {
                    lock.tryLock(2, TimeUnit.SECONDS);
                    if(locked){
                        System.out.println(currentThread().getName() + " - 占用对象锁");
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(locked){
                        lock.unlock();
                        System.out.println(currentThread().getName() + "释放锁");
                    }
                }
            }
        };
        Thread t2 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                boolean locked = false;
                try {
                    lock.tryLock(2, TimeUnit.SECONDS);
                    if(locked){
                        System.out.println(currentThread().getName() + " - 占用对象锁");
                        Thread.sleep(3000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(locked){
                        lock.unlock();
                        System.out.println(currentThread().getName() + "释放锁");
                    }
                }
            }
        };
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }

    /**
     * 进程间的通信
     * 先通过lock获得一个Condition对象，再调用condition对象的await、signal、signalAll方法唤醒lock
     */
    public static void correspondence() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(currentThread().getName() + "占用对象锁1s");
                    Thread.sleep(1000);
                    System.out.println("await 释放锁");
                    condition.await();
                    System.out.println(currentThread().getName() + "重新占用锁");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(currentThread().getName() + "释放锁");
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(currentThread().getName() + "占用对象锁");
                    System.out.println("执行任务3s");
                    Thread.sleep(3000);
                    condition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println(currentThread().getName() + "释放锁");
                }
            }
        };
        t1.setName("t1");
        t2.setName("t2");
        // t1一定要先于t2执行，不然会造成t1线程一直等待
        t1.start();
        Thread.sleep(500);
        t2.start();
    }
    public static void main(String[] args) throws InterruptedException {
        // reentry();
        // tryReentry();
        correspondence();
    }
}
