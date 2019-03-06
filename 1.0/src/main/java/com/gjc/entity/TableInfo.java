package com.gjc.entity;

public class TableInfo {
	private String database;
	private String tableName;
	private String cloumnName;
	private String isNullAble;
	private String DataType;
	private String cloumnKey;
	private String cloumnComment;
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCloumnName() {
		return cloumnName;
	}
	public void setCloumnName(String cloumnName) {
		this.cloumnName = cloumnName;
	}
	public String getIsNullAble() {
		return isNullAble;
	}
	public void setIsNullAble(String isNullAble) {
		this.isNullAble = isNullAble;
	}
	public String getDataType() {
		return DataType;
	}
	public void setDataType(String dataType) {
		DataType = dataType;
	}
	public String getCloumnKey() {
		return cloumnKey;
	}
	public void setCloumnKey(String cloumnKey) {
		this.cloumnKey = cloumnKey;
	}
	public String getCloumnComment() {
		return cloumnComment;
	}
	public void setCloumnComment(String cloumnComment) {
		this.cloumnComment = cloumnComment;
	}
	
	@Override
	public String toString() {
		return "TableInfo [database=" + database + ", tableName=" + tableName + ", cloumnName=" + cloumnName
				+ ", isNullAble=" + isNullAble + ", DataType=" + DataType + ", cloumnKey=" + cloumnKey
				+ ", cloumnComment=" + cloumnComment + "]";
	}
	
}
