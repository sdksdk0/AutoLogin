package cn.tf.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("获取链接失败");
		}
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if(conn!=null){
			try {
				conn.close();//是否关闭，看你从何处获取的链接。从池中获取的，不会关闭的。
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
}
