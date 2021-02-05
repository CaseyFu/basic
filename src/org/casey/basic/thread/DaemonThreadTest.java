package org.casey.basic.thread;

/**
 * @ClassName DaemonThreadTest
 * @Author Casey Fu
 * @Version v1.0.0
 * @Description todo
 * @Date 2020/9/24
 */

public class DaemonThreadTest {
    public static void main(String[] args){
      Thread daemonThread = new Thread(){
          @Override
          public void run() {
              int i = 0;
                while(true){
                    try{
                        Thread.sleep(300);
                        System.out.println("不停打印，直到主线程结束 - "+i);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    i++;
                }
          }
      };
      daemonThread.setDaemon(true);
      daemonThread.start();
      System.out.println("主线程开始运行");
      try{
          System.out.println("主线程已开始运行，接下来等待5s");
          Thread.sleep(5000);
          System.out.println("主线程已sleep 5s");
      }catch(Exception e){
          e.printStackTrace();
      }
    }

}
