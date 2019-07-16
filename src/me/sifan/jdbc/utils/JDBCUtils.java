package me.sifan.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import me.sifan.jdbc.ConnectionPool;

public class JDBCUtils {
	
	private static String driverClass = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static ConnectionPool pool = null;
	
	static {
		//获取配置文件的输入流
		InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		//读取配置文件
		Properties properties = new Properties();
		try {
			properties.load(is);
			driverClass = properties.getProperty("driverClass");
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			//注册驱动
			Class.forName(driverClass);
			//获取数据库连接池
			pool = new ConnectionPool(url, username, password);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取连接
	public static Connection getConnection() {
		return pool.getConnection();
	}
	
	public static Connection getConnectionNotByPool() {
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//释放连接
	public static void release(ResultSet rs, Statement st, Connection conn) {
		closeRs(rs);
		closeSt(st);
		pool.freeConn(conn);
	}
	
	private static void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
	}
	
	private static void closeSt(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st = null;
		}
	}
	
	private static void closeConn(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
}
