package org.casey.basic.threadTest;

public class Hero_modify_test {
	public void entrance_test() {
		//原子类型的伤害与恢复
		Hero_modify h = new Hero_modify("kk",472);
		Thread t = new Thread() {
			public void run() {
				for(int i=0 ;i<100000; i++) {
					h.hurt();
				}
			}
		};
		Thread t0 = new Thread() {
			public void run() {
				for(int i=0 ;i<100000; i++) {
					h.recover();
				}
			}
		};
		t.start();
		t0.start();
		try {
			t.join();
			t0.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		h.information();
	}
	public static void main(String []args) {
		Hero_modify_test fk = new Hero_modify_test();
		fk.entrance_test();
	}
}
