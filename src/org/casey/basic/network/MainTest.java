package org.casey.basic.network;
import java.util.Date;


public class MainTest extends Thread{
	public static void main(String []args) {
		
		MainTest m1 = new MainTest();
		MainTest m2 = new MainTest();
		m1.start();
		m2.start();
		
		
		
	}

	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			try {
				Thread.sleep(0);
				System.out.println(i);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
