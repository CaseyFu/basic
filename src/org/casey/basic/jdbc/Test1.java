/*
 * 获取自动增长的id
 * 获取元数据
 * 使用提交,要做一起做,错了一起不做,都做正确了才提交,错了一个就不提交
 * 使用提交练习,删除数据库前10条数据,请求IO,Y删除N取消删除
 */
package org.casey.basic.jdbc;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.util.Scanner;
public class Test1 {
	public static void main(String []args) {
		new Test1().entrance_commit_exercise();
	}
	public void entrance_derive_metaData() throws SQLException {
		Connection c = null;
		DatabaseMetaData database = c.getMetaData();		
	}
	public void entrance_commit_exercise() {
		/*
		 * 设计一个代码，删除表中前10条数据，但是删除前会在控制台弹出一个提示：是否要删除数据(Y/N)
		 * 如果用户输入Y，则删除;如果输入N则不删除。如果输入的既不是Y也不是N，则重复提示
		 * 从id为0的开始检测,有就删,没有id就+1
		 */
		PreparedStatement ps = null;
		PreparedStatement ps0 = null;
		Connection c = null;
		ResultSet r = null;
		int id = 0;		//从0开始删10条数据
		int count = 0;	//数据库中的数据数量	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8","root","38524");		
			
			//统计数据库中数据
			String sql_count = "select count(*) from people;";
			ps = c.prepareStatement(sql_count);
			r = ps.executeQuery();			
			while(r.next()) {
				count = r.getInt(1);
			}
			ps.close();
			int temp = count;
			String sql_select = "select * from people where id = ?;";
			ps = c.prepareStatement(sql_select);
			//打印将要删除行的信息,
			while(true) {
				if(count == 0 || (count+10 == temp)) break;
				ps.setInt(1, id);
				r = ps.executeQuery();
				if(r.next()) {							
					System.out.println("试图删除id为:"+id+"的行");
					count--;
				}
				id++;				
			}
			//回归初始状态
			count += 10;	
			id = 0;
						
			c.setAutoCommit(false);		
			//数据库中没有数据了,或删除了10条数据就跳出
			while(true) {
				if(count == 0 || (count+10 == temp)) break;
				ps.setInt(1, id);
				r = ps.executeQuery();
				if(r.next()) {		
					String sql_delete = "delete from people where id = ?;";				
					ps0 = c.prepareStatement(sql_delete);
					ps0.setInt(1, id);
					ps0.execute();
					count--;
				}
				id++;				
			}	
			String string = "";
			Scanner scanner = null;
			while(true) {
				System.out.println("是否删除这10条数据?(Y/N)");
				scanner = new Scanner(System.in);
				string = scanner.nextLine();	
				if(string.equals("Y")) {
					c.commit();//提交
					System.out.println("删除成功");
					break;
				}
				if(string.equals("N")) {
					System.out.println("删除已取消");	
					break;
				}
			}								
			scanner.close();			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {			
			if(ps0 != null) {
				try {
					ps0.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}		
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
	public void entrance_commit() {
		//Commit提交
		Statement s = null;
		Connection c = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
			String sql0 = "update people set name = "+"'付凯1号'"+" where id = 1013;";
			String sql1 = "updata people set name = "+"'付凯2号'"+" where id = 1016;";
			c.setAutoCommit(false);		//设置不自动提交
			s = c.createStatement();
			s.execute(sql0);
			s.close();		//s需再次使用时必须先关闭
			
			s = c.createStatement();		
			s.execute(sql1);
			c.commit();			//手动提交语句
			//手动提交的目的是:确保sql0和sql1都能执行才提交,有一个错误就不提交&&
		
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
			if(c != null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void entrance_derive_auto_increment_id() {
		//插入一个自动增长的id,删除上一个的上一个id的行,终要删一个
		PreparedStatement s = null;
		Connection c = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");					
			String sql_insert = "insert into people values(null,?);";		
			s = c.prepareStatement(sql_insert, Statement.RETURN_GENERATED_KEYS);
			s.setString(1, "凯凯");
			s.execute();
			r = s.getGeneratedKeys();
			int preid = 0;
			int number = 0;
			while(r.next()) {
				number = r.getInt(1);
				preid = number - 2;
				System.out.println("新插入自动增长的id为:"+number);
			}
			s.close();												
			String sql_query = "select * from people where id = ?;";							
			s = c.prepareStatement(sql_query);					
			while(true) {
				s.setInt(1, preid);
				r = s.executeQuery();
				if(r.next()) {
					String sql_delete = "delete from people where id = ?;";
					PreparedStatement s0 = c.prepareStatement(sql_delete);
					s0.setInt(1, preid);
					s0.execute();
					break;
				}							
				preid--;
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
			if(c != null) {
				try {
					c.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
