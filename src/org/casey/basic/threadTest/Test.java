
/*
 * join,加入一个线程
 * 设置循环与sleep,观察是否同步运行
 */

package org.casey.basic.threadTest;

import java.io.IOException;

public class Test implements Runnable{
	
	public void run() {
		for(int i=0; i<10; i++) {
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("这里是run()方法");
		}
			
	}
	
	public static void main(String []args) {
		Test test = new Test();
		Thread t = new Thread(test);
		
		t.start();	//必须先启动线程,再加入另一个线程中
		//一个副线程加入另一个主线程,需要等待副线程完全运行结束才执行主线程剩余部分
		try {
				t.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//此处先打印run方法,如果没有join那么有可能是先run,也有可能是先main
		for(int i=0; i<10; i++) {
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
			System.out.println("这里是main()方法");
		}
			
	}
}
