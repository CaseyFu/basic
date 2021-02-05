
//设置线程的优先级,Thread.MAX_PRIORITY与Thread.MIN_PRIORITY
package org.casey.basic.threadTest;

public class Test0 {
	public static void main(String []args) {
		Thread t = new Thread() {
			public void run() {
				
				
				for(int i=0; i<100; i++) {
					try {
						Thread.sleep(0);
					}catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println("这里是tttttttttt线程");
				}
			}
		};
		
		Thread t0 = new Thread() {
			public void run() {
				
				
				for(int i=0; i<100; i++) {
					Thread.yield();	//屈服线程,让其他线程有更多机会占用cpu	
					
					try {
						Thread.sleep(0);
					}catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println("这里是t0t0t0t0t0t0线程");
				}
			}
		};
		
		//设置优先级,优先级高的先得到cpu资源
		t.setPriority(5);//参数为一个整形数
		t0.setPriority(5);
		t.start();
		t0.start();
	}
}
