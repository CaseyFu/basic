package org.casey.basic.network;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class Response {
	private Connection c = null;
	private PreparedStatement ps = null;
	private ResultSet r = null;
	public Response() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/local?characterEncoing=UTF-8", "root", "38524");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	public String response(String s) {
		String sql_query = "select response from android where recive = ?;";		
		try {
			ps = c.prepareStatement(sql_query);
			ps.setString(1, s);
			r = ps.executeQuery();
			if(r.next()) {
				String res = r.getString(1);
				return res;
			}
			ps.close();
			r.close();					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "我爱你";
	}
	public void close() {
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
	}

}
