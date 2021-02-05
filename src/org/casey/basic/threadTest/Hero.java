package org.casey.basic.threadTest;

public class Hero {
	private String name;
	private double hp;
	public Hero(String name, double hp){
		this.name = name;
		if(hp>0)
			this.hp = hp;
	}
	public void hurt() {
		synchronized(this) {
			//如果这个英雄的血量为0了就等待加血
			if(this.hp == 0) {	
				try {
					System.out.println("英雄的血量值为0等待加血后再折磨");
					this.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			this.hp -= 1;
			this.notify();
		}
	
		
	}
	public synchronized void recover() {
		//如果血量满了1000了就等待放血
		if(this.hp == 1000) {
			try {
				System.out.println("英雄的血量值为1000太肉了等待放血后再折磨");
				Thread.sleep(10);
				this.wait();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.hp += 1;
		this.notify();
	}
	public boolean isDead() {
		return this.hp>0?false:true;
	}
	public void information() {
		String s = "";
		if(isDead()) {
			s = "死亡";
		}else {
			s = "存活";
		}
		System.out.println("英雄:"+this.name+"目前"+s+"血量是:"+this.hp);
	}
}
