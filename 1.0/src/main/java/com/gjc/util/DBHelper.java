package com.gjc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.gjc.entity.ConnectInfo;

/**
 * 实现得到连接池以及对数据库的增删改查
 *
 */
public class DBHelper {
	private static String user;
	private static String url;
	private static String password;
	
	static {
		new ConnectInfo();
	}

	/**
	 * 静态方法来获取数据库的连接池
	 * @return  返回数据库的连接池
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url = ConnectInfo.getUrl();
			user = ConnectInfo.getUser();
			password = ConnectInfo.getPassword();		
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 通用的数据库连接关闭方法
	 * @param rs  查询结果集
	 * @param st  查询statement
	 * @param conn  数据库连接池
	 */
	public static void closeDB(ResultSet rs,Statement st,Connection conn) {
		//关闭的顺序是和创建的顺序相反
		try {
			if(rs!=null) {
				rs.close();
			}
			if(st!=null) {
				st.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 通用的SQL更新方法
	 * @param sql  基本的sql语句
	 * @param params  需要用来替换的参数
	 * @return  是否成功返回布尔值
	 */
	public static boolean updateData(String sql,List<Object> params) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;   //这个在这里没有用 是为了调用关闭方法用的
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++) {
				ps.setObject(i+1, params.get(i));
			}
			int count = ps.executeUpdate();
			if(count>0) {
				flag = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//注意一旦关闭 后面的所有连接都不可用 除非重新创建连接
			//故一般不去主动的关闭
			closeDB(rs,ps,conn);  
		}
		
		
		return flag;
	}
	
	
	/**
	 * 通用SQL的查询方法
	 * @param sql
	 * @param params
	 * @return
	 */
	
	public static ResultSet queryData(String sql,List<Object> params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.size();i++) {
				ps.setObject(i+1, params.get(i));
			}
			rs = ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
//			closeDB(rs,ps,conn); 
		}
		return rs;  
	}
	
	
	
	
}
