package org.casey.basic.thread;

import org.junit.Test;

/**
 * @ClassName InterruptTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description 线程中断，关闭线程不要用stop()，stop()可能会破坏数据的一致性
 * @Date 2020/9/21
 */

public class InterruptTest {
    @Test
    public void closeThread() throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                while(true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("interrupted");
                        break;
                    }
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("interrupted when sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t.start();
        Thread.sleep(2000);
        t.interrupt();
    }
}
