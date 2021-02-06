package org.casey.basic.thread;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * @ClassName Correspondence
 * @Author Casey Fu
 * @Description 线程之间的通信问题, 生产者与消费者
 * @Date 2020/7/4 16:07
 */
public class Correspondence {
    List<Integer> L = new ArrayList<>();

    public synchronized void P() {
        int i = new Random().nextInt(10);
        this.L.add(i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notify();
    }

    public synchronized void C() {
        if (this.L.isEmpty()) {
            try {
                System.out.println("容器元素已经取完,Consumer在此wait()等待释放占用线程的资源");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int i = this.L.remove(0);
        System.out.println(i);
    }

    public static void exe() {
        //生产者与消费者通信入口
        Correspondence fk = new Correspondence();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                fk.P();
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                fk.C();
            }
        });
        producer.start();
        consumer.start();
    }

    public static void main(String[] args) {
        Correspondence.exe();
    }
}
