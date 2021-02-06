package org.casey.basic.benchmark.other;

/**
 * @ClassName SingletonTest
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/11 10:18
 */

public class SingletonTest {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new StarvedSingletonThread();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}

class LazySingletonThread extends Thread {
    /**
     * 懒汉 非线程安全
     */
    @Override
    public void run() {
        int hash = LazySingleton.getLazySingleton().hashCode();
        System.out.println(hash);
    }
}

class StarvedSingletonThread extends Thread {
    /**
     * 饿汉 线程安全
     */
    @Override
    public void run() {
        int hash = StarvedSingleton.getStarvedSingleton().hashCode();
        System.out.println(hash);
    }
}
