package org.casey.basic.thread;

// import com.google.common.util.concurrent.ThreadFactoryBuilder;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPool
 * @Author Casey Fu
 * @Description 线程池
 * @Date 2020/7/4 16:58
 */
public class ThreadPoolTest {
    @Test
    public void run() throws InterruptedException {
        // 使用hutool创建的ThreadFactory也可以用guava的ThreadFactory
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNamePrefix("thread-")
                .build();
        ExecutorService executor = new ThreadPoolExecutor(
                5,
                20,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }

        // 关闭数据库连接池
        executor.shutdown();
        executor.awaitTermination(1000L, TimeUnit.SECONDS);
    }
}
