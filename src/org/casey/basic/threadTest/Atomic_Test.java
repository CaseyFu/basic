
/*
 * 原子访问测试
 */
package org.casey.basic.threadTest;
import java.util.concurrent.atomic.AtomicInteger;
public class Atomic_Test {
	public void entrance_test() {
		AtomicInteger fk = new AtomicInteger(0);
		/*
		 * 自增i++,包含3个原子访问
		 * 1.取i的值
		 * 2.i加1
		 * 3.把加1之后的i值赋给原有i
		 * i++、i--、i=i+1都不是原子操作,都可能造成中断而变得不安全
		 */
		
		
		fk.incrementAndGet();
		fk.incrementAndGet();
		System.out.println(fk.get());
	}
	
	/*
	 * 比较非原子操作x++与线程安全AtomicInteger
	 */
	
	public void entrance_compare() {
		
	}
	public static void main(String []args) {
		Atomic_Test fk = new Atomic_Test();
		fk.entrance_test();
	}
}
