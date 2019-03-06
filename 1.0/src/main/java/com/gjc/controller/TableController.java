package com.gjc.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gjc.entity.ConnectInfo;
import com.gjc.entity.Replace;
import com.gjc.entity.TableInfo;
import com.gjc.util.DBHelper;
import com.gjc.util.DateUtil;

public class TableController {
	private static String db;
	private static String tables;
	private static String[] tableList;
	
	static {
		new Replace();
		Replace.setDate(DateUtil.formatTime(new Date()));
	}
	
	/**
	 * 返回所有表的信息
	 * @return
	 */
	public static Map<String,List<TableInfo>> backTablesInfo(){
		Map<String,List<TableInfo>> map = new  HashMap<String,List<TableInfo>>();
		if(null!=ConnectInfo.getTable()&&!"".equals(ConnectInfo.getTable())) {
			tables = ConnectInfo.getTable();
			tableList = tables.split(",");
		}
		for(String s:tableList) {
			try {
				map.put(s, backTableInfo(s));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
		
	}
	
	/*
	 * 返回表的信息
	 */
	public static List<TableInfo> backTableInfo(String table) throws Exception {
		String sql = "select * from information_schema.columns where table_schema = ? and table_name = ? order by ordinal_position";
		ArrayList<Object> list = new ArrayList<Object>();
		
		list.add(ConnectInfo.getDb());
		list.add(table);
		ResultSet rs = DBHelper.queryData(sql, list);
		List<TableInfo> tableInfoList = new ArrayList<TableInfo>();
		while (rs.next()) {
			TableInfo tableInfo = new TableInfo();
			tableInfo.setDatabase(db);
			tableInfo.setTableName(table);
			tableInfo.setCloumnName(rs.getString("COLUMN_NAME"));
			tableInfo.setIsNullAble(rs.getString("IS_NULLABLE"));
			tableInfo.setDataType(rs.getString("DATA_TYPE"));
			tableInfo.setCloumnKey(rs.getString("COLUMN_KEY"));
			tableInfo.setCloumnComment(rs.getString("COLUMN_COMMENT"));
			tableInfoList.add(tableInfo);
			System.out.println(tableInfo);
		}
		return tableInfoList;
	}

}
