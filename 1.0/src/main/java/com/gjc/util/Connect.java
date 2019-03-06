package com.gjc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/*
 * Mysql连接
 */
public class Connect {
	 Logger logger = Logger.getLogger(this.getClass().getName());
	 
	 private String driver   = "com.mysql.jdbc.Driver";
	 private String url      = "jdbc:mysql:http://192.168.0.145:3306/park";
	 private String user     = "root";
	 private String password = "521";
	 
	 public Connection getConnection() throws ClassNotFoundException, SQLException {
		 	Connection conn = null;
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
	    }
	 
	 	/**
	     * 
	     * @Title: GeneratorTableModel 
	     * @Description: 根据数据库表名查询所有字段名、类型、长度、备注、是否主键等信息
	     * @param tableName
	     * @return 
	     * @return List<Map<String,String>>
	     * @throws Exception 
	     */
	    public Map<String, List<Map<String,String>>> queryTableColumns(List<String> tableNamesList) throws Exception {
	        Map<String, List<Map<String,String>>> listMap = new HashMap<String, List<Map<String,String>>>();
	        Connection connection = null;
	        Statement stmt = null;
	        ResultSet rSet = null;
	        try{
	            connection = this.getConnection();
	            stmt = connection.createStatement();
	            for(String tableName:tableNamesList){ 
	                tableName = tableName.toUpperCase();
	                String sqlStr = "SELECT  * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = "
	                		+ tableName + 
	                		" ORDER BY ORDINAL_POSITION";
	                
	                rSet = stmt.executeQuery(sqlStr);
	                List<Map<String, String>> list = new ArrayList<Map<String,String>>();

	                while (rSet.next()) {
	                    String table_name = rSet.getString("TABLE_NAME");
	                    String columnId = rSet.getString("COLUMN_NAME");
	                    String dataType = rSet.getString("DATA_TYPE");
	                    String dataLength = rSet.getString("CHARACTER_MAXIMUM_LENGTH");
	                    String nullable = rSet.getString("IS_NULLABLE");
	                    
	                    String entity = this.tableNameToEntity(table_name);
	                    String columnName = this.ColumnIdToName(columnId);
	                    
	                    Map<String, String> map = new HashMap<String, String>();
	                    map.put("entity", entity);
	                    map.put("tableName", table_name);
	                    map.put("columnId", columnId);
	                    map.put("columnName", columnName);
	                    map.put("dataType", dataType);
	                    map.put("dataLength", dataLength);
	                    map.put("nullable", nullable);

	                    list.add(map);
	                }
	                listMap.put(tableName, list);
	            }
	        }catch (Exception e) {
	            throw new Exception(e);
	        }finally{
	            this.releaseConnection(connection, stmt, rSet);
	        }
	        
	        return listMap;
	    }
	    
	    public String firstToLower(String str) throws Exception{
	        return str.substring(0, 1).toLowerCase()+ str.substring(1, str.length());
	    }
	    
	    public String firstToUpper(String str) throws Exception{
	        return str.substring(0, 1).toUpperCase()+ str.substring(1, str.length());
	    }

	    /**
	     * 
	     * @Title: tableNameToEntity 
	     * @Description: 通过表名获取实体名，如：T_USER转为TUser
	     * @param table_name
	     * @return
	     * @throws Exception
	     * @return String
	     */
	    public String tableNameToEntity(String table_name) throws Exception{
	        String[] tableStrs = table_name.toLowerCase().split("_");
	        StringBuffer bf = new StringBuffer();
	        for(String str:tableStrs){
	            str = firstToUpper(str);
	            bf.append(str);
	        }
	        return bf.toString();
	    }
	    
	    /**
	     * 
	     * @Title: tableNameToEntity 
	     * @Description: 通过字段名获取字段别名，如：USER_ID转为userId
	     * @param table_name
	     * @return
	     * @throws Exception
	     * @return String
	     */
	    public String ColumnIdToName(String columnId) throws Exception{
	        String[] tableStrs = columnId.toLowerCase().split("_");
	        StringBuffer bf = new StringBuffer();
	        for(String str:tableStrs){
	            if(!bf.toString().isEmpty()){
	                str = firstToUpper(str);
	            }
	            
	            bf.append(str);
	        }
	        return bf.toString();
	    }
	    
	    
	    /**
	     * 
	     * @Title: releaseConnection
	     * @Description: 释放数据库连接
	     * @param conn
	     * @param stmt
	     * @param rs
	     * @throws SQLException
	     * @return void
	     */
	    public void releaseConnection(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    }

	    
	    public static void main(String[] args)  {
	    	Connect connect = new Connect();
	    	List<String> tableNamesList = new ArrayList<String>();
	    	tableNamesList.add("label");
	    	Map<String, List<Map<String,String>>> map = new HashMap<String, List<Map<String,String>>>();
	    	try {
				map = connect.queryTableColumns(tableNamesList);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	System.out.println(map);
	    }
}
