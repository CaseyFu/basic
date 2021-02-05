
//利用Lock把MyStack改为线程安全类
package org.casey.basic.threadTest;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.List;
import java.util.LinkedList;
public class MyStack_Lock <T>{
	
	List<T> L = new LinkedList<T>();
	Lock fk = new ReentrantLock();
	Condition fk0 = fk.newCondition();
	public void push(T t) {
		fk.lock();
		this.L.add(t);
		fk0.signal();
		fk.unlock();
	}
	public T pop() {
		//必须保证L中有元素时才能出栈,
		fk.lock();
			if(this.L.isEmpty()) 
			{
				try {
					fk0.await();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		T temp = this.L.remove(this.L.size()-1);			
		fk.unlock();
		return temp;
	}
	
	
	public static void main(String []args) {
		MyStack_Lock<Integer> kk = new MyStack_Lock<>();
		Thread t = new Thread() {
			public void run() {
				for(int i=0; i<10; i++) {
					
					kk.push(i);
				}			
			}
		};
		Thread t0 = new Thread() {
			public void run() {
				for(int i=0; i<10; i++) {
					try {
						Thread.sleep(0);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					int temp = kk.pop();
					System.out.println(temp);
				}			
			}
		};
		t.start();
		t0.start();
		
	}
}
