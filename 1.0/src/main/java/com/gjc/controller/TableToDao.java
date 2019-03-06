package com.gjc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gjc.entity.TableInfo;
import com.gjc.util.StringUtil;

/**
 * mysql:
 * 
 * @author gc
 * sql字段类型对应
 */
public class TableToDao {
	private  String column;
	private  String replaceColumn;
	private  String upperColumn;
	private  String JDBCType;
	private  String javaType;
	private  String comment;
	
	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUpperColumn() {
		return upperColumn;
	}

	public void setUpperColumn(String upperColumn) {
		this.upperColumn = upperColumn;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}


	public String getJDBCType() {
		return JDBCType;
	}


	public void setJDBCType(String JDBCType) {
		this.JDBCType = JDBCType;
	}


	public String getReplaceColumn() {
		return replaceColumn;
	}


	public void setReplaceColumn(String replaceColumn) {
		this.replaceColumn = replaceColumn;
	}

	public static List<TableToDao> getDaoInfo(List<TableInfo> list) {
		Map<String,String> mysqlJDBC = new HashMap<String,String>();
		mysqlJDBC.put("BIGINT", "BIGINT");
		mysqlJDBC.put("BIT", "BIT");
		mysqlJDBC.put("BLOB", "BLOB");
		mysqlJDBC.put("CHAR", "CHAR");
		mysqlJDBC.put("CLOB", "TEXT");
		mysqlJDBC.put("DATE", "TIMESTAMP");
		mysqlJDBC.put("DECIMAL", "DECIMAL");
		mysqlJDBC.put("DOUBLE", "DOUBLE");
		mysqlJDBC.put("FLOAT", "FLOAT");
		mysqlJDBC.put("INTEGER", "INTEGER");
		mysqlJDBC.put("SMALLINT", "SMALLINT");
		mysqlJDBC.put("TIME", "TIME");
		mysqlJDBC.put("TIMESTAMP", "TIMESTAMP");
		mysqlJDBC.put("DATETIME", "TIMESTAMP");
		mysqlJDBC.put("TINYINT", "TINYINT");
		mysqlJDBC.put("VARCHAR", "VARCHAR");
		mysqlJDBC.put("INT", "INTEGER");
		mysqlJDBC.put("BOOLEAN", "BOOLEAN");
		mysqlJDBC.put("TEXT", "VARCHAR");
		
		Map<String,String> JDBCJava = new HashMap<String,String>();
		JDBCJava.put("BIGINT", "Long");
		JDBCJava.put("BIT", "Boolean");
		JDBCJava.put("BLOB", "byte[]");
		JDBCJava.put("CHAR", "String");
		JDBCJava.put("TEXT", "String");
		JDBCJava.put("DATE", "Date");
		JDBCJava.put("DECIMAL", "BigDecimal");
		JDBCJava.put("DOUBLE", "Double");
		JDBCJava.put("FLOAT", "Float");
		JDBCJava.put("INTEGER", "Integer");
		JDBCJava.put("SMALLINT", "Integer");
		JDBCJava.put("TIME", "Time");
		JDBCJava.put("TIMESTAMP", "Date");
		JDBCJava.put("DATETIME", "Date");
		JDBCJava.put("TINYINT", "Integer");
		JDBCJava.put("VARCHAR", "String");
		JDBCJava.put("INT", "Integer");
		
		/**
		 * 生成对应的
		 */
		List<TableToDao> daoInfolist = new  ArrayList<TableToDao>();
		for(TableInfo tableInfo:list) {
			if(null==tableInfo.getDataType()) {
				 try {
					throw new Exception("数据类型为NULL,表结构数据错误");
				} catch (Exception e) {
					e.printStackTrace();
				}  
			}else {
				TableToDao tableToDao = new TableToDao();
				String str = tableInfo.getDataType().toUpperCase();
				tableToDao.setColumn(tableInfo.getCloumnName());
				if(null!=mysqlJDBC.get(str)) {
					tableToDao.setJDBCType(mysqlJDBC.get(str));
				}else {
					try {
						throw new Exception("字段类型异常:\t"+str);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				String replaceColumn = "";
				try {
					replaceColumn = StringUtil.firstToLower(StringUtil.replaceUnderLineAndUpperCase(tableInfo.getCloumnName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				tableToDao.setReplaceColumn(replaceColumn);
				try {
					tableToDao.setUpperColumn(StringUtil.replaceUnderLineAndUpperCase(tableInfo.getCloumnName()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				tableToDao.setJavaType(JDBCJava.get(mysqlJDBC.get(str)));
				tableToDao.setComment(tableInfo.getCloumnComment());
				daoInfolist.add(tableToDao);
			}
		}
		return daoInfolist;
	}
}
