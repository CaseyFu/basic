package org.casey.basic.thread;

import org.junit.Test;

/**
 * @ClassName DaemonTest
 * @Author Fu Kai
 * @Description 守护进程daemon, 当一个程序里只有守护进程了, 那么结束程序, daemon常用来做日志 性能统计
 * @Date 2020/7/4 16:58
 */
public class DaemonTest {

    @Test
    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public void main() {
        Thread t0 = new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(count++);
            }
        }
        );

        Thread t1 = new Thread(() -> {
            for (int i = 1; i < 100; i++) {
                System.out.println("释放技能Q");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (i % 3 == 0) {
                    System.out.println("Q技能能却5s");
                    Thread t2 = new Thread(() -> {
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    );
                    t2.start();
                    try {
                        t2.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        );
        // 此例中daemon用来计时
        t0.setDaemon(true);
        t0.start();
        t1.start();
    }
}
