
/*
 * 数据库连接池
 */
package org.casey.basic.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.LinkedList;
public class DataBasePool {
	private int poolSize = 0;
	LinkedList<Connection> L = new LinkedList<Connection>();
	public DataBasePool(int size) {
		this.poolSize = size;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		createPool();
	}
	public void createPool() {
		try {
			for(int i=0; i<this.poolSize; i++) {
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");
				L.add(c);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	public Connection getPool() {
		//得到一个连接,池中没有了连接就等待
		synchronized(this.L) {
			
			while(this.L.isEmpty()) {
				try {
					//System.out.println("池中的连接已被取完,等待中...");
					this.L.wait();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}				
			}
			Connection c = this.L.removeFirst();
			return c;
		}
	}
	public void backPool(Connection c) {
		//返回一个连接到池中,唤醒
		synchronized(this.L) {
			this.L.add(c);
			//System.out.println("返回了一个连接,唤醒");
			this.L.notifyAll();
		}
	}
	public void closePool() {
		try {
			for(Connection c:this.L) {
				c.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("所有连接已被关闭");
	}
}
