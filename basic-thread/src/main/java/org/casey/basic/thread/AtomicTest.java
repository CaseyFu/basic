package org.casey.basic.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadPool
 * @Author Fu Kai
 * @Description 原子访问测试
 * @Date 2020/7/4 16:58
 */

public class AtomicTest {
    @Test
    public void entranceTest() {
        AtomicInteger fk = new AtomicInteger(0);
        /*
         * 自增i++,包含3个原子访问
         * 1.取i的值
         * 2.i加1
         * 3.把加1之后的i值赋给原有i
         * i++、i--、i=i+1都不是原子操作,都可能造成中断而变得不安全
         */

        fk.incrementAndGet();
        fk.incrementAndGet();
        System.out.println(fk.get());
    }
}
