//HeroDAO
package org.casey.basic.gui;

import java.util.LinkedList;
import java.util.List;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class HeroDAO implements Hero_DAO{

	public HeroDAO() {
		//在此初始化数据库驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		//在此返回一个Connection
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoding=UTF-8", "root", "38524");
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		return c;
	}
	@Override
	public void add(Hero h) {
		String sql_insert = "insert into Hero values(null,?,?);";
		String sql_query = "select id from Hero where id = ?;";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement(sql_query);
			ps.setInt(1, h.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				System.out.println("存在id为"+h.getId()+"的英雄数据");
			}else {
				ps.close();
				ps = getConnection().prepareStatement(sql_insert);
				//ps.setInt(1, h.getId() );
				ps.setString(1, h.getName());
				ps.setDouble(2, h.getHp());
				ps.execute();
				System.out.println("添加id为"+h.getId()+"的英雄数据成功");
			}		
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
		}
	}

	@Override
	public void delete(Hero h) {
		//根据id删除英雄数据
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql_query = "select * from Hero where id = ? and name = ? and hp = ?;";
			ps = getConnection().prepareStatement(sql_query);
			ps.setInt(1, h.getId());
			ps.setString(2, h.getName());
			ps.setDouble(3, h.getHp());
			rs = ps.executeQuery();
			if(rs.next() == false) {
				System.out.println("id为:"+h.getId()+"name为:"+h.getName()+"hp为:"+h.getHp()+"的英雄数据不存在");
			}else {
				ps.close();
				String sql_delete = "delete from Hero where id = ?;";
				ps = getConnection().prepareStatement(sql_delete);
				ps.setInt(1, h.getId());
				ps.execute();
				System.out.println("删除id为:"+h.getId()+"的英雄数据成功");
			}
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
		}	
	}

	@Override
	public void update(Hero h, Hero h1) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql_query = "select * from Hero where id = ? and name = ? and hp = ?;";
			ps = getConnection().prepareStatement(sql_query);
			ps.setInt(1, h.getId());
			ps.setString(2, h.getName());
			ps.setDouble(3, h.getHp());
			rs = ps.executeQuery();
			if(rs.next() == false) {
				System.out.println("id为:"+h.getId()+"name为:"+h.getName()+"hp为:"+h.getHp()+"的英雄数据不存在");
			}else {
				ps.close();
				String sql_update = "update Hero set name = ?,hp = ? where id = ?;";
				ps = getConnection().prepareStatement(sql_update);
				ps.setString(1, h1.getName());
				ps.setDouble(2, h1.getHp());
				ps.setInt(3, h.getId());
				ps.execute();
				System.out.println("修改id为:"+h.getId()+"name为:"+h.getName()+"hp为:"+h.getHp()+"的英雄数据成功");
			}
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
		}	
		
	}
	
	@Override
	public Hero getHero(int id) {
		//返回一个英雄对象
		PreparedStatement ps = null;
		ResultSet rs = null;
		Hero h = null;
		try {
			String sql_query = "select * from Hero where id = ?;";
			ps = getConnection().prepareStatement(sql_query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next() == false) {
				System.out.println("id为:"+id+"的英雄数据不存在");
			}else {
				String name = rs.getString(2);
				double hp = rs.getDouble(3);
				h = new Hero(id, name, hp);
			}
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
		}	
		return h;
	}

	@Override
	public List<Hero> list() {
		//返回所有英雄对象
		List<Hero> L = new LinkedList<Hero>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql_query = "select * from Hero;";
			ps = getConnection().prepareStatement(sql_query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double hp = rs.getDouble(3);
				L.add(new Hero(id, name, hp));
			}
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
		}
		return L;
	}

	@Override
	public List<Hero> list(int start, int num) {
		//分页查询
		List<Hero> L = new LinkedList<Hero>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql_query = "select * from Hero limit ?,?;";
			ps = getConnection().prepareStatement(sql_query);
			ps.setInt(1, start);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double hp = rs.getDouble(3);
				L.add(new Hero(id, name, hp));
			}
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
		}
		return L;
	}
	public int getCount() {
		int count = 0;
		String sql_count = "select count(*) from Hero";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement(sql_count);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
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
		}
		return count;
	}
	public static void main(String []args) {
		System.out.println(new HeroDAO().getCount());
	}
}
