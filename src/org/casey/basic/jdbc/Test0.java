/*
 * 一次向数据库中插入100条数据,
 * 一次向数据库中删除这100条数据
 * 
 * 字段定位不是基零的,第一个字段为1
 */

package org.casey.basic.jdbc;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
public class Test0 {
	public String pass(int length) {
		//65~90 97~122
		String s = "";
		for(int i=0; i<length; ) {
			byte b = (byte)(Math.random()*58+65);
			if((b>=65&&b<=90) || (b>=97&&b<=122)) {
				s += (char)b;
				i++;
			}
		}
		return s;
	}
	public void Create() {
		/*
		 * 向local数据库的people表中添加id为50~150的数据
		 * 
		 */
		Connection ob = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ob = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
			System.out.println("创建对象成功"+ob);
			s = ob.createStatement();
			for(int i=1; i<1040; i++) {
				String id = ""+i+"";
				String name = "'fk "+i+"'";			
				String sql = "insert into people values("+id+" , "+name+");";
				s.execute(sql);																				
			}					
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
			System.out.println("插入完成");
			
		}
	}
	public void Delete() {
		/*
		 * 删除数据
		 * 
		 */
		Connection objec = null;
		Statement statemen = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			objec = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
			statemen = objec.createStatement();
			for(int i=50; i<=124; i++) {
				String sql = "delete from people where id = "+i+" ;";
				statemen.execute(sql);
			}			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			if(statemen != null) {
				try {
					statemen.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(objec != null) {
				try {
					objec.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}				
			}
		}
		
	}
	public void Query() {
		//查询数据库,
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
			statement = connection.createStatement();
			
			String sql = "select * from people;";
			
			ResultSet s = statement.executeQuery(sql);
			while(s.next()) {
				int id = s.getInt(1);		//字段定位不是基零的,第一个字段为1
				String name = s.getString("name");
				int age = s.getInt("age");
				double weight = s.getDouble(4);
				String address = s.getString(5);
				int sex =  s.getInt("sex");
				String sex0 = "";
				if(sex == 1)	sex0 = "男";
				else	sex0 = "女";
				String pass = s.getString(7);
				System.out.println("id:"+id+",名字:"+name+",年龄:"+age+",体重:"+weight+"kg,地址:"+address+",性别"+sex0+",密码:"+pass);
				
			}
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
		//System.out.println(new Test_Log4j().pass(2));
		new Test0().Create();
	}
}
