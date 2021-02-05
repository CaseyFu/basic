
//守护进程daemon,当一个程序里只有守护进程了,那么结束程序,daemon常用来做日志、性能统计
package org.casey.basic.threadTest;

public class Test1 {
	
	public static void main(String []args) {
		Thread t = new Thread() {
			public void run() {
				int count = 0;
				while(true) {
					try {
						Thread.sleep(1000);
					}catch(Exception e) {
						e.printStackTrace();
					}
					System.out.println(count++);
				}	
			}
		};
		
		Thread t0 = new Thread() {
			public void run() {
				for(int i=1; i<100; i++) {
					
					System.out.println("释放技能Q");
					try {
						Thread.sleep(1000);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					if(i%3==0) {
						System.out.println("Q技能能却5s");
						Thread t1 = new Thread() {
							public void run() {
								try {
									Thread.sleep(5000);
								}catch(Exception e) {
									e.printStackTrace();
								}	
							}
						};
						t1.start();
						try {
							t1.join();
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					
					
				}
			}
		};
		t.setDaemon(true);	//此例中daemon用来计时
		t.start();
		t0.start();
	}
}
