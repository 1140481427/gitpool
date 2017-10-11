package com.neusoft.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class UtilC3P0 {
	private static ComboPooledDataSource ds = null;
	//线程局部变量
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
	
	static{
		try{
			ds = new ComboPooledDataSource("oracle");
		}catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	//得到连接
	public static Connection getConnection() throws SQLException{
		Connection conn = connectionHolder.get(); 
		if(conn!=null){
			return conn;
		}else{
			conn = ds.getConnection();
			//把得到的数据库连接设置到线程里
			connectionHolder.set(conn);
			return conn;
		}	
	}
	
	
	//关闭连接
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
				//把关闭的数据库连接从线程中移除
				connectionHolder.remove();
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
		try{
			if(conn != null){
				if(!conn.getAutoCommit()){
					conn.rollback();
				}
			}
		}catch(SQLException e){}
	}
	
	
	public static void main(String[] args) {
	
		try {
			Connection conn = UtilC3P0.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
