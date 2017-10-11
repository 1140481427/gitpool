package com.neusoft.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 利用配置文件更换连接数据库对象
 * @author Administrator
 *
 */
public class DbUtil {
	static Properties ps;
	static{
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		ps=new Properties();
		try {
			ps.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		getConnection();
	}
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName(ps.getProperty("driver"));
			conn = DriverManager.getConnection(ps.getProperty("url"),ps.getProperty("user"),ps.getProperty("psw"));
			System.out.println("连接数据库成功");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Statement stmt){
		if(null != stmt){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs){
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn){
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn,ResultSet rs,Statement stmt){
		close(rs);
		close(stmt);
		close(conn);
	}
	
	public static void close(Connection conn,Statement stmt){
		close(stmt);
		close(conn);
	}
	
	public static void close(ResultSet rs,Statement stmt){
		close(rs);
		close(stmt);
	}
	
	public static void rollback(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
