package org.casey.basic.threadTest;
//java自带的线程池,查找含Magic的.java文件

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_default {

    LinkedBlockingQueue<Runnable> L = new LinkedBlockingQueue<Runnable>();
    ThreadPoolExecutor fk = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, L);
    //第一个参数:初始线程条数5条
    //第二个参数:如果5条线程不够用,最多再增加10条线程
    //第三、四个参数:过了60s后,增加的线程没有执行任务,回收增加的
    //第五个参数:存放任务的容器LinkedBlockingQueue

    public void Traverse(File f) {
        if (f.isDirectory()) {
            for (File f0 : f.listFiles())
                Traverse(f0);
        }
        if (f.isFile() && f.getName().endsWith(".java")) {
            fk.execute(new Runnable() {
                public void run() {
                    FileInputStream in = null;
                    byte[] b = new byte[(int) f.length()];
                    try {
                        in = new FileInputStream(f);
                        in.read(b);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String temp = new String(b);
                    if (temp.contains("Magic")) {
                        System.out.println("找到含有Magic的.java文件:" + f.getAbsolutePath());
                    }
                }
            });
        }
    }

    public static void main(String[] args) {

        File f = new File("G:/java/ggg");
        ThreadPool_default fk = new ThreadPool_default();
        fk.Traverse(f);
    }

}