package org.casey.basic.jdbc;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class TestDataBasePool {
	public static void TestPool() {
		DataBasePool pool = new DataBasePool(10);
		Thread []t = new Thread[100];
		for(int i=0; i<100; i++) {
			Thread t1 = new Thread() {
				public void run() {	
					System.out.println("this is 2");
					Connection c = pool.getPool();
					PreparedStatement ps = null;
					try {
						String sql_insert = "insert into Hero values(null,?,?);";
						ps = c.prepareStatement(sql_insert);
						ps.setString(1, new Test0().pass(3));
						ps.setInt(2, (int)(Math.random()*9+18));
						ps.execute();
						//System.out.println("线程:"+this.getName()+"得到连接,插入一条数据");
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						pool.backPool(c);
					}
				}
			};
			t1.start();
			t[i] = t1;
		}
		for(Thread t0:t) {
			try {
				t0.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void TestCompare() {
		//比较使用线程池和在每一个线程中开关连接的性能
		//在每个线程中开关连接
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Thread []t = new Thread[100];
		for(int i=0; i<100; i++) {
			Thread t1 = new Thread() {
				public void run() {
					System.out.println("this is 1");
					Connection c = null;
					PreparedStatement ps = null;
					String sql_insert = "insert into Hero values(null,?,?);";
					try {
						c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
						ps = c.prepareStatement(sql_insert);
						ps.setString(1, new Test0().pass(3));
						ps.setInt(2, (int)(Math.random()*9+18));
						ps.execute();
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						if(ps != null) {
							try {
								ps.close();
							}catch(SQLException e) {
								e.printStackTrace();
							}
						}
						if(c != null) {
							try {
								c.close();
							}catch(SQLException e) {
								e.printStackTrace();
							}
						}
					}
				}
			};
			t1.start();	
			t[i] = t1;
		}
		for(Thread t0:t) {
			try {
				t0.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String []args) {
		long start = System.currentTimeMillis();
		TestDataBasePool.TestCompare();	
		long end = System.currentTimeMillis();
		System.out.println("1已执行完");
		System.out.println("每次开关连接用时:"+(end-start));
		
		long start0 = System.currentTimeMillis();
		TestDataBasePool.TestPool();
		long end0 = System.currentTimeMillis();
		System.out.println("2已执行完");
		System.out.println("数据库池用时:"+(end0-start0));
		System.exit(0);
	}
}
