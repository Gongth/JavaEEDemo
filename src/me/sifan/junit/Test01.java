package me.sifan.junit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import me.sifan.jdbc.utils.JDBCUtils;

public class Test01 {

	@Test
	public void function(){
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement ps = null;
		for(int i = 0; i < 1000000; i++) {
			try {
				conn = JDBCUtils.getConnection();
				ps = conn.prepareStatement("select * from student");
				JDBCUtils.release(null, ps, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("所需时长 -- "+(end-start)+"ms");
	}
}
