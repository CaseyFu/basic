
//lock同步,用trylock()能很好的规避死锁,但要手动释放占用的对象,不释放一样会死锁
package org.casey.basic.threadTest;
import java.text.SimpleDateFormat;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit; 
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class the_Lock {
	
	public String now() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	public void log(String name) {
		System.out.format("%s:%s:%s%n",now(),Thread.currentThread().getName(),name);
	}
	public void entrance_lock() {
		//利用lock进行同步,trylock在规定时间内没得到任务就离开
		Lock fk = new ReentrantLock();		
		Thread t = new Thread() {
			public void run() {
				
				boolean locked = false;	//是否占领成功
				try {
					log("开始占领");					
					locked = fk.tryLock(2, TimeUnit.SECONDS);	//等待2s,如果还没得到任务就离开
					if(locked) {
						log("正在占领中,其他进程无法掠夺");
						Thread.sleep(5000);
					}else
						log("未得到任务,离开");					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(locked) fk.unlock();			
					log("退出");					
				}
			}
		};
		Thread t0 = new Thread() {
			public void run() {
				boolean locked = false;	//是否占领成功
				try {
					log("开始占领");
					locked = fk.tryLock(2, TimeUnit.SECONDS);	//等待2s,如果还没得到任务就离开
					if(locked) {
						log("正在占领中,其他进程无法掠夺");
						Thread.sleep(5000);
					}else
						log("未得到任务,离开");					
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					if(locked) fk.unlock();					
					log("退出");					
				}
			}
		};
		t.setName("t");
		t0.setName("t0");
		t.start();
		t0.start();
	}
	public void entrance_Correspond() {
		//Lock的同步线程的交互通信
		//通过Lock对象得到一个Condition对象,然后调用Condition对象的await(),signal(),signalAll()方法
		//如wait()与notify()必须在synchronized下一样,await()、signal()必须在lock锁下
		Lock fk = new ReentrantLock();
		Condition fk0 = fk.newCondition();
		List<Byte> L = new ArrayList<Byte>();
		Thread producer = new Thread() {
			public void run() {						
				for(int i=0; i<100; i++) {
					try {
						fk.lock();
						byte b = (byte)(Math.random()*128);
						L.add(b);						
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}finally {
						fk0.signal();	//容器中有了任务就唤醒
						fk.unlock();
					}															
				}				
			}
		};
		Thread consumer = new Thread() {
			public void run() {
				while(true) {										
					try {					
						fk.lock();
						if(L.isEmpty()) fk0.await();
					}catch(InterruptedException e){
						e.printStackTrace();
					}finally {
						fk.unlock();
					}
					
					byte temp = L.remove(0);
					System.out.println("取出的数据"+temp);
				}
			}
		};
		producer.start();
		consumer.start();
		
	}
	public static void main(String []args) {
		the_Lock fk = new the_Lock();
		fk.entrance_Correspond();
		
	}
}
