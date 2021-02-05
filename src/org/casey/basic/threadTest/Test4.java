
//引入synchronized同步关键字,独木桥,一次只能有一条线程通过
package org.casey.basic.threadTest;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Test4 {
	public static void main(String []args) {
		
		Integer i = 5;
		Thread t = new Thread() {
			public void run() {
				System.out.println(now()+":"+this.getName()+"开始运行");
				synchronized(i) {
					System.out.println(now()+":"+this.getName()+":进入同步");
					try {
						Thread.sleep(5000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(now()+":"+this.getName()+":退出同步");
				}
				System.out.println(now()+":"+this.getName()+"结束运行");
			}
		};
		t.setName("Thread-t");
		Thread t0 = new Thread() {
			public void run() {
				System.out.println(now()+":"+this.getName()+"开始运行");
				synchronized(i) {
					System.out.println(now()+":"+this.getName()+":进入同步");
					try {
						Thread.sleep(5000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(now()+":"+this.getName()+":退出同步");
				}
				System.out.println(now()+":"+this.getName()+"结束运行");
			}
		};
		t0.setName("Thread-t0");
		t.start();
		t0.start();
	}
	
	public static String now() {
		
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
}
