package org.casey.basic.threadTest;

//wait(); notify(); notifyAll(); 线程之间的通信,只能在synchronized方法中使用
class Producer implements Runnable {
    Temp t = null;

    public Producer(Temp t) {
        this.t = t;
    }

    public void run() {
        int i = 0;
        while (true) {
            if (i == 0)
                t.set("zhangsan", "nan");
            else
                t.set("lisi", "nv");
            i = (i + 1) % 2;
        }
    }
}

class Consumer implements Runnable {
    Temp t = null;

    public Consumer(Temp t) {
        this.t = t;
    }

    public void run() {
        while (true)
            t.get();
    }
}

class Temp {
    private String name = "lisi";
    private String sex = "nv";
    boolean bfull = false;

    public synchronized void set(String name, String sex) {
        if (bfull) {
            try {
                wait();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        this.name = name;
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        this.sex = sex;
        bfull = true;
        notify();
    }

    public synchronized void get() {
        if (!bfull) {
            try {
                wait();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        System.out.println("姓名:" + name + "---性别:" + sex);
        bfull = false;
        notify();
    }
}

public class Thread4 {
    public static void main(String[] args) {
        Temp t = new Temp();
        Thread t1 = new Thread(new Producer(t));
        t1.start();
        Thread t2 = new Thread(new Consumer(t));
        t2.start();
    }
}

