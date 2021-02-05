
/*
 * 使用java操作数据库过程
 * 
 */
package org.casey.basic.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Test {
	public static void main(String []args) {
		
		Connection ob = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");			
			System.out.println("第一步:加载com.mysql.jdbc.Driver驱动成功");
			
			ob = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8",
							"root","38524");
			System.out.println("第二步:连接到数据库,获取的对象是:"+ob);
			
			//第三步:创建Statement对象,用于执行sql语句
			s = ob.createStatement();
			System.out.println("第三步:成功创建Statement对象"+s+"用于执行sql语句");
			
			//第四步:准备sql语句
			String sql = "insert into people(id,name,age,weight,address,sex,password) "
					+ "values("+999+","+"'付凯'"+","+99+","+999+","+"'重庆渝北'"+","+1+","+"'38524')";
			
			//第五步:执行sql语句
			s.execute(sql);
			
			//第六步:关闭数据库,先关闭Statement再关闭Connection
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(s != null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ob != null) {
				try {
					ob.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
