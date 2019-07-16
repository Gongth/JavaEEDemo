package me.sifan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sifan.jdbc.utils.JDBCUtils;

public class PoolTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date startTime = new Date();
		
		Connection conn = JDBCUtils.getConnection();
		
		Date endTime = new Date();
		
		int time = (int)(endTime.getTime() - startTime.getTime());
		
		System.out.println(conn);
		System.out.println(time);
		
		startTime = new Date();
		
		Connection conn2 = JDBCUtils.getConnectionNotByPool();	
		
		endTime = new Date();
		
		time = (int)(endTime.getTime() - startTime.getTime());
		
		System.out.println(conn2);
		System.out.println(time);
		
		JDBCUtils.release(null, null, conn);
		JDBCUtils.release(null, null, conn2);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
