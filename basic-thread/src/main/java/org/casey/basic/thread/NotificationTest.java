package org.casey.basic.thread;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @ClassName NotificationTest
 * @Author Fu Kai
 * @Description notify()和notifyAll()的区别
 * notify()随机唤醒等待池中的一个线程, notifyAll()则是全部唤醒
 * @Date 2020/7/4 16:07
 */

public class NotificationTest {

    private volatile boolean go = false;

    private synchronized void shouldGo() throws InterruptedException {
        while (!go) {
            System.out.println(Thread.currentThread().getName() + " is going to wait on this object");
            wait(); // 释放同步锁
            System.out.println(Thread.currentThread().getName() + " is woken up");
        }
        go = false;
    }

    private synchronized void go() {
        while (!go) {
            System.out.println(Thread.currentThread().getName() + " is going to notify all or one thread waiting on this object");
            go = true;
            notify();
//            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final NotificationTest test = new NotificationTest();
        Runnable waitTask = () -> {
            try {
                test.shouldGo();
            } catch (InterruptedException ex) {
                Logger.getLogger(NotificationTest.class.getName()).
                        log(Level.SEVERE, null, ex);
            }
            System.out.println(Thread.currentThread().getName() + " finished Execution");
        };

        Runnable notifyTask = () -> {
            test.go();
            System.out.println(Thread.currentThread().getName() + " finished Execution");
        };

        Thread t1 = new Thread(waitTask, "线程1"); //will wait
        Thread t2 = new Thread(waitTask, "线程2"); //will wait
        Thread t3 = new Thread(waitTask, "线程3"); //will wait
        Thread t4 = new Thread(notifyTask, "通知线程"); //will notify

        //starting all waiting thread
        t1.setPriority(10);
        t2.setPriority(1);
        t3.setPriority(1);

        t2.start();
        t1.start();
        t3.start();

        //pause to ensure all waiting thread started successfully
        Thread.sleep(200);

        //starting notifying thread
        t4.start();
    }
}
