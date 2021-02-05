package org.casey.basic.threadTest;
import java.util.concurrent.atomic.AtomicInteger;
public class Hero_modify {
	AtomicInteger fk = new AtomicInteger();		//最终对象
	private String name;
	private int hp;	
	public Hero_modify(String name, int hp){
		this.name = name;
		if(hp>0)
			this.hp = hp;
		fk.set(hp);			//构造时就设最终对象的初值
	}
	
	public void hurt() {						
		fk.decrementAndGet();	//自减
	}
	public void recover() {	
		fk.incrementAndGet();	//自增
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
		System.out.println("英雄:"+this.name+"目前"+s+"血量是:"+fk.get());//获取最终对象的值,也就是获取当前生命值
	}
}
