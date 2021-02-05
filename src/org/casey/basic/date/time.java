
//获取系统当前是时间，继承Thread与实现Runnable
package org.casey.basic.date;

import java.util.*;

public class time {
    public static void main(String[] args) {
        nowTimeThread t = new nowTimeThread();
        Thread t1 = new Thread(t);
        nowTimeRunnable t2 = new nowTimeRunnable();
        Thread t3 = new Thread(t2);
        t3.start();
        t1.start();
        System.out.println("系统当前的时间" + new Date());

    }
}

class nowTimeThread extends Thread {
    public void run() {
        System.out.println("System.currentTime1:" + System.currentTimeMillis());
    }
}

class nowTimeRunnable implements Runnable {
    public void run() {
        System.out.println("System.currentTime2:" + System.currentTimeMillis());
    }
}