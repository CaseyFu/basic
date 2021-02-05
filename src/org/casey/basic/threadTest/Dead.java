
//3个线程之间的死锁,了解一哈
package org.casey.basic.threadTest;

public class Dead {
	
	public static void main(String []args) {
		Hero h0 = new Hero("h0", 999);
		Hero h1 = new Hero("h1", 999);
		Hero h2 = new Hero("h2", 999);
		Thread t0 = new Thread() {
			public void run() {
				System.out.println("t0开始占有0号位:");
				synchronized(h0) {
					System.out.println("t0已经占有0号位:");
					System.out.println("t0试图占有1号位:");
					System.out.println("等待中...");
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(h1) {
						System.out.println("t0成功占有1号位:");
					}
				}
			}
		};
		Thread t1 = new Thread() {
			public void run() {
				System.out.println("t1开始占有1号位:");
				synchronized(h1) {
					System.out.println("t1已经占有1号位:");
					System.out.println("t1试图占有2号位:");
					System.out.println("等待中...");
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(h2) {
						System.out.println("t1成功占有2号位:");
					}
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				System.out.println("t2开始占有2号位:");
				synchronized(h2) {
					System.out.println("t2已经占有2号位:");
					System.out.println("t2试图占有0号位:");
					System.out.println("等待中...");
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(h0) {
						System.out.println("t2成功占有0号位:");
					}
				}
			}
		};
		t0.start();
		t1.start();
		t2.start();
	}
}