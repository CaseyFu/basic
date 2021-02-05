/*
 * 让线程池找含有Magic的.java文件
 */

package org.casey.basic.threadTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class MainTest {
	
	ThreadPool pool = new ThreadPool();
	/*
	 * this is annotation 
	 * @return void
	 */
	public void entrance_ThreadPool() {		
		
		for(int i=0; i<100; i++) {
			Runnable fk = new Runnable() {
				public void run() {
					System.out.println("这里是任务,执行时间为1s");
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			
			//设置投放任务速度,查看线程执行任务后回到线程池执行其他任务
			if(i < 20) {	//前20个任务,1s投1次
				
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				pool.add(fk);
			}
			else if(i>=20 && i<50) {	//21~50个任务0.8s投一次
				try {
					Thread.sleep(800);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				pool.add(fk);
			}
			else{	//51~100个任务0.4s投一次
				try {
					Thread.sleep(400);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				pool.add(fk);
			}						
		}
	}
	public void entrance_Search(File f) {
		//查找带有Magic的.java文件,如果找到.java文件,就放入线程池,让线程池来查找
		if(f.isFile() && f.getName().endsWith(".java")){
			
			pool.add(new Runnable() {
				public void run() {
					byte []b = new byte[(int)f.length()];
					FileInputStream in = null;
					try {
						in = new FileInputStream(f);
						in.read(b);
					}catch(IOException e) {
						e.printStackTrace();
					}
					String temp = new String(b);
					if(temp.contains("Magic")) {
						System.out.println("找到含Magic的.java文件:"+f.getAbsolutePath());
					}					
				}
			});			
		}
		if(f.isDirectory()) {	
			for(File f0:f.listFiles()) {
				entrance_Search(f0);
			}
		}
		
	}
	
	public static void main(String []args) {
		File f = new File("G:/java/ggg");
		MainTest fk = new MainTest();
		
	}	
}
