package me.sifan.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyDataSource implements DataSource{
	
		private String url = null;
	    private String user = null;
	    private String password = null;
	    public LinkedList<Connection> list = new LinkedList<>();
	    private int initCount = 5;
	    private int maxCount = 20;
	    private int currentCount = 0;

	    public MyDataSource(String url, String user, String password){
	        this.url = url;
	        this.user = user;
	        this.password = password;
	        for(int i = 0; i < initCount; i++){
	            try {
					list.addLast(DriverManager.getConnection(url, user, password));
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        System.out.println("初始大小 : " + list.size());
	    }


	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		synchronized(this){
//            if(currentCount < list.size()){
//                currentCount++;
                return list.removeFirst();
//            }
//            if(list.size() == 0 && currentCount < maxCount){
//                for(int i = 0; i < initCount; i++){
//                    list.addLast(DriverManager.getConnection(url, user, password));
//                }
//                currentCount++;
//                return list.removeFirst();
//            }
//            else{
//                throw new SQLException("...");
//            }
        }
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
