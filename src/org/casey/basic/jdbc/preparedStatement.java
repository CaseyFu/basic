package org.casey.basic.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class preparedStatement {
	//准备好了的PreparedStatement
	//1.性能优于Statement,
	//2.参数
	//3.防止sql注入
	
	public void insert_Statement() {
		Statement s = null;
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");
			s = c.createStatement();
			String sql = "insert into test0 values(null,"+"'Statement'"+");";
			for(int i=0; i<10000; i++) {
				s.execute(sql);
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(s!=null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void insert_PreparedStatement() {
		PreparedStatement s = null;
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");
			
			String sql = "insert into test1 values(null,?);";
			s = c.prepareStatement(sql);
			for(int i=0; i<10000; i++) {
				
				s.setString(1, "PreparedSt");
				s.execute();
			}		
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(s!=null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void attack_Statement() {
		//SQL注入式攻击
		Statement s = null;
		Connection c = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");
			s = c.createStatement();
			String sql = "select * from test0 where id=5 or name = "+"'statement'"+";";
			r = s.executeQuery(sql);
			while(r.next()) {
				int id = r.getInt(1);
				String name = r.getString(2);
				System.out.println("id:"+id+"name:"+name);
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(s!=null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void attack_PreparedStatement() {
		//SQL注入式攻击
		PreparedStatement s = null;
		Connection c = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");
			String sql = "select * from test0 where id=?;";
			s = c.prepareStatement(sql);
			s.setString(1, "100000");
			r = s.executeQuery();
			while(r.next()) {
				int id = r.getInt(1);
				String name = r.getString(2);
				System.out.println("id:"+id+"name:"+name);
			}
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(s!=null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(c!=null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String []args) {
		preparedStatement fk = new preparedStatement();
		fk.attack_PreparedStatement();
	}
}
