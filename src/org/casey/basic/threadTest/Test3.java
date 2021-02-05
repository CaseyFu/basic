
/*
 * 引入线程同步问题,多个线程同时启动,此程序已经在Hero中的方法中设置同步,
 * 1000个线程加1滴血,同时1000个线程减1滴血,最后血量一定是初始值,但线程未同步就可能会出现一定问题,结果会多点或少一点血
 * Hero中改变hurt()和recover()方法,加synchronized同步
 * 
 */
package org.casey.basic.threadTest;

public class Test3 {
	public static void main(String []args) {
		Test3 fk= new Test3();
		fk.exe();
	}
	public void exe() {
		Hero timo = new Hero("timo",3000);
		timo.information();
		
		Thread []hurt = new Thread[1000];
		Thread []recover = new Thread[1000];
		
		for(int i=0 ;i<1000; i++) {
			Thread t = new Thread() {
				public void run() {
					timo.hurt();
				}
			};
			t.start();
			hurt[i] = t;
		}
		
		for(int i=0; i<1000; i++) {
			Thread t0 = new Thread() {
				public void run() {
					timo.recover();
				}
			};
			t0.start();
			recover[i] = t0;
		}
		
		for(Thread t:hurt) {		//引入join,确保每个线程都是运行完了的
			try {
				t.join();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		for(Thread t:recover) {
			try {
				t.join();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		timo.information();
	}
}
