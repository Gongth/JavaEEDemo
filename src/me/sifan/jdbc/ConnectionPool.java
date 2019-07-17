package me.sifan.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 	这是一个数据库连接池类
 * 
 * @author Sifan
 * @Date 2019-07-16
 * @version 1.0
 */

public class ConnectionPool{
	//1.声明用来保存数据库连接所要用到的信息的变量
	private String url = null;
	private String username = null;
	private String password = null;
	//2.声明用来保存连接的容器
	private static LinkedList<Connection> conns = new LinkedList<Connection>();
	
	//优化代码
	private int initCount = 5;
	private int maxCount = 20;
	private int currentCount = 0;
	
	//3.带参数的构造方法,用来初始化连接池
	public ConnectionPool(String url, String username, String password){
		//3.1初始化成员变量
		this.url = url;
		this.username = username;
		this.password = password;
		//3.2创建初始数据库连接,并存储在容器中
		for(int i=0;i<initCount;i++) {
			//3.2.2保存数据库连接
			try {
				conns.addLast(createConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//3.2.1创建一个数据库连接
	private Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	//4.获取数据库连接
	public synchronized Connection getConnection() throws SQLException {
		//从容器的开头取数据
		if(conns.size() > 0) {
			currentCount++;
			return conns.removeFirst();
		}else if(currentCount < maxCount) {
			for(int i=0;i<initCount;i++) {
				//3.2.2保存数据库连接
				try {
					conns.addLast(createConnection());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			currentCount++;
			return conns.removeFirst();
		}else {
			throw new SQLException("连接数量已达到最大值");
		}
	}
	
	//5.释放连接
	public void freeConn(Connection conn) {
		//将这个连接对象添加到容器的尾部
		conns.addLast(conn);
	}
}
