
//登录验证,输入用户名密码,如数据库中有就登录成功
//统计数据库中有多少条数据
//分页
package org.casey.basic.jdbc;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class login {
	
	public void entrance_login(int id, String password) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncodeing=UTF-8", "root", "38524");
			statement = connection.createStatement();
			String sql = "select * from people where id = "+id+" and password = "+"'"+password+"';";
			result = statement.executeQuery(sql);
			if(result.next()) {
				System.out.println("登录成功");
			}else {
				System.out.println("登录失败");
			}									
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(result != null) {
				try {
					result.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void count() {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncodeing=UTF-8", "root", "38524");
			statement = connection.createStatement();
			String sql = "select count(*) from people;";
			result = statement.executeQuery(sql);
			int total = 0;
			while(result.next()) {
				total = result.getInt(1);	
			}
			
			System.out.println("一共有"+total+"条数据");								
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(result != null) {
				try {
					result.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void appart_page(int start, int num) {
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncodeing=UTF-8", "root", "38524");
			statement = connection.createStatement();
			String sql = "select * from people limit "+start+" , "+num+";";
			statement.execute(sql);			//使用getResultSet得到查询结果
			result = statement.getResultSet();
			while(result.next()) {
				int id = result.getInt(1);
				String name = result.getString("name");
				int age = result.getInt("age");
				double weight = result.getDouble(4);
				String address = result.getString(5);
				int sex =  result.getInt("sex");
				String sex0 = "";
				if(sex == 1)	sex0 = "男";
				else	sex0 = "女";
				String pass = result.getString(7);
				System.out.println("id:"+id+",名字:"+name+",年龄:"+age+",体重:"+weight+"kg,地址:"+address+",性别"+sex0+",密码:"+pass);				
			}							
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(result != null) {
				try {
					result.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(statement != null) {
				try {
					statement.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String []args) {
		new login().appart_page(1,4);	//从第1条数据开始,读取4条数据
	}
}
