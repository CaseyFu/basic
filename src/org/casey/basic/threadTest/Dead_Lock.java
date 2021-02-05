
//Lock中的死锁,以及用trylock规避死锁
package org.casey.basic.threadTest;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
public class Dead_Lock {
	public void entrance_voidDeadLock() {
		//使用trylock来规避死锁
		ReentrantLock locked0 = new ReentrantLock();
		Lock locked1 = new ReentrantLock();
		new Thread() {
			public void run() {
				boolean islocked1 = false;
				System.out.println(Thread.currentThread().getName()+"试图领locked0");
				try {
					locked0.lock();			
					System.out.println(Thread.currentThread().getName()+"已成功占领locked0");				
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+"试图领locked1...等待中...");
					//修改点
					islocked1 = locked1.tryLock(5, TimeUnit.SECONDS);//5s得不到locked1就放弃占有,
					//放弃占有之后不能unlock,所以要判断是否放弃了locked1
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(islocked1) {
						System.out.println(Thread.currentThread().getName()+"已成功占领locked1");
						locked1.unlock();
					}else System.out.println(Thread.currentThread().getName()+"占领locked1不成功,退出");					
					locked0.unlock();
				}								
			}
		}.start();
		new Thread() {
			public void run() {
				boolean islocked0 = false;
				System.out.println(Thread.currentThread().getName()+"试图领locked1");
				try {
					locked1.lock();				
					System.out.println(Thread.currentThread().getName()+"已成功占领locked1");				
					Thread.sleep(500);
					System.out.println(Thread.currentThread().getName()+"试图领locked0...等待中...");
					islocked0 = locked0.tryLock(5, TimeUnit.SECONDS);	
					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(islocked0) {
						System.out.println(Thread.currentThread().getName()+"已成功占领locked0");
						locked0.unlock();
					}else System.out.println(Thread.currentThread().getName()+"占领locked0不成功,退出");
					
					locked1.unlock();
				}												
			}
		}.start();					
	}
	public void entrance_deadLock() {
		//2个对象之间的死锁,用lock对象实现占用,
		//lock是锁一个区间,锁门的钥匙是lock,开门的钥匙是unlock
		ReentrantLock locked0 = new ReentrantLock();
		Lock locked1 = new ReentrantLock();
		new Thread() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+"试图领locked0");
				try {
					locked0.lock();			
					System.out.println(Thread.currentThread().getName()+"已成功占领locked0");				
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+"试图领locked1...等待中...");
					locked1.lock();
					System.out.println(Thread.currentThread().getName()+"已成功占领locked1");
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					locked1.unlock();
					locked0.unlock();
				}								
			}
		}.start();
		new Thread() {
			public void run() {
				System.out.println(Thread.currentThread().getName()+"试图领locked1");
				try {
					locked1.lock();				
					System.out.println(Thread.currentThread().getName()+"已成功占领locked1");				
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName()+"试图领locked0...等待中...");
					locked0.lock();	
					System.out.println(Thread.currentThread().getName()+"已成功占领locked0");
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					locked0.unlock();
					locked1.unlock();
				}												
			}
		}.start();						
	}
	public static void main(String []args) {
		Dead_Lock fk = new Dead_Lock();
		fk.entrance_voidDeadLock();
	}
}
