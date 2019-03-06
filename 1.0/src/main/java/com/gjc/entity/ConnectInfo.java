package com.gjc.entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ConnectInfo {
	private static String user;
	private static String url;
	private static String password;
	private static String db;
	private static String table;
	private static String tableClomn;
	
	static {
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(new FileInputStream("src/main/resources/conn.properties"),"utf-8");
			Properties props = new Properties();  
			try {
				props.load(reader);
				user = props.getProperty("user");
				url = props.getProperty("url");
				password = props.getProperty("password");
				db = props.getProperty("db");
				table = props.getProperty("table");
				tableClomn =  props.getProperty("tableClomn");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	}

	
	public static String getTableClomn() {
		return tableClomn;
	}

	public static void setTableClomn(String tableClomn) {
		ConnectInfo.tableClomn = tableClomn;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ConnectInfo.user = user;
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConnectInfo.url = url;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConnectInfo.password = password;
	}

	public static String getDb() {
		return db;
	}

	public static void setDb(String db) {
		ConnectInfo.db = db;
	}

	public static String getTable() {
		return table;
	}

	public static void setTable(String table) {
		ConnectInfo.table = table;
	}
	
}
