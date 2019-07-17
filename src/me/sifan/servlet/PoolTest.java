package me.sifan.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.sifan.jdbc.utils.JDBCUtils;

public class PoolTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection conn = null;
		
		for(int i = 0; i < 21; i++) {
			try {
				Date startTime = new Date();
				conn = JDBCUtils.getConnection();
				System.out.println(conn + "" + (i+1));
				Date endTime = new Date();
				int time = (int)(endTime.getTime() - startTime.getTime());
				System.out.println(time);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	
		
		
//		startTime = new Date();
//		
//		Connection conn2 = JDBCUtils.getConnectionNotByPool();	
//		
//		endTime = new Date();
//		
//		time = (int)(endTime.getTime() - startTime.getTime());
//		
//		System.out.println(conn2);
//		System.out.println(time);
//		
//		JDBCUtils.release(null, null, conn);
//		JDBCUtils.release(null, null, conn2);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
