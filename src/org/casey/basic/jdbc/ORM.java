/*
 * ORM
 * 对象与数据库的对应
 */

package org.casey.basic.jdbc;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.LinkedList;
public class ORM {
	private static PreparedStatement ps = null;
	private static Connection c = null;
	private static ResultSet r = null;
	public static void linkDatabase() {
		//连接数据库
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("连接到数据库成功");
	}
	public static void closeDatabase() {
		//关闭数据库
		if(r != null) {
			try {
				r.close();
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
		System.out.println("关闭数据库成功");
	}
	public static void add(Hero h) {
		//增加一个英雄到数据库
		String sql_add = "insert into Hero values(?,?,?);";
		String sql_query = "select id from Hero where id = ?;";		
		try {		
			ps = c.prepareStatement(sql_query);
			ps.setInt(1, h.getId());
			r = ps.executeQuery();
			if(r.next()) {
				//id存在,退出
				System.out.println("id存在,增加失败,退出");			
			}else {
				ps.close();
				ps = c.prepareStatement(sql_add);
				ps.setInt(1, h.getId());
				ps.setString(2, h.getName());
				ps.setInt(3, h.getAge());
				ps.execute();
				System.out.println("增加成功");					
			}
			r.close();
			ps.close();			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void delete(Hero h) {
		//从数据库中删除与h相符的英雄
		String sql_query = "select id from Hero where id = ?;";
		String sql_delete = "delete from Hero where id = ?;";
		try {
			ps = c.prepareStatement(sql_query);
			ps.setInt(1, h.getId());
			r = ps.executeQuery();
			if(r.next()) {
				ps.close();
				ps = c.prepareStatement(sql_delete);
				ps.setInt(1,h.getId());
				ps.execute();
				System.out.println("删除成功");
			}else {
				System.out.println("无对应英雄信息,删除失败,退出");
			}
			r.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static List<Hero> list(){
		//将表中的所有对象复制到L
		List<Hero> L = new LinkedList<Hero>();
		int count = ORM.count();	//获取对象个数
		int id = 1;	//id从1开始检索
		String sql_query = "select * from Hero where id = ?;";
		try {
			while(count > 0) {
				ps = c.prepareStatement(sql_query);
				while(true) {										
					ps.setInt(1, id);
					r = ps.executeQuery();
					id++;
					if(r.next()) {					
						L.add(new Hero(r.getInt(1),r.getString(2),r.getInt(3)));
						break;
					}					
				}				
				count--;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				r.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}		
		return L;
	}
	public static int count() {
		//返回对象数量
		int count = 0;
		String sql_count = "select count(*) from Hero;";
		try {
			ps = c.prepareStatement(sql_count);
			r = ps.executeQuery();
			while(r.next()) {
				count = r.getInt(1);
			}
			r.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	public static Hero getHero(int id) {
		String sql_query = "select * from Hero where id = ?;";
		Hero h = null;
		try {
			ps = c.prepareStatement(sql_query);
			ps.setInt(1, id);
			r = ps.executeQuery();
			if(r.next()) {
				h = new Hero(r.getInt(1),r.getString(2),r.getInt(3));				
			}else {
				System.out.println("无此id信息");
			}
			r.close();
			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return h;
	}
	public static void update(Hero h,String s, String s0) {
		//更新信息
		try {
			switch(s) {
		case"name":{
			String sql_update = "update Hero set name = ? where id = ?;";		
			ps = c.prepareStatement(sql_update);
			ps.setString(1, s0);
			ps.setInt(2, h.getId());
			ps.execute();
			System.out.println("修改id为:"+h.getId()+"的英雄的name为:"+s0+"成功");
		}break;
		case"age":{
			String sql_update = "update Hero set age = ? where id = ?;";		
			ps = c.prepareStatement(sql_update);
			
			ps.setInt(1, (int)Integer.valueOf(s0));
			ps.setInt(2, h.getId());
			ps.execute();
			System.out.println("修改id为:"+h.getId()+"的英雄的age为:"+(int)Integer.valueOf(s0)+"成功");
		}break;
		default:System.out.println("无此字段");
		}
		ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	public static void main(String []args) {
		//List<Hero> L = new LinkedList<Hero>();
		ORM.linkDatabase();
		System.out.println(ORM.count());
		//ORM.delete(new Hero(4,"kkk",19));
		
		Hero h = ORM.getHero(1999);
		if(h != null) {
			ORM.update(h,"age", "99");
		}
			
		ORM.closeDatabase();
	}
}
