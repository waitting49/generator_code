package com.gjc.util;

import java.lang.reflect.Field;
import java.util.Date;

import javax.swing.JLabel;

/**
 * 反射工具类
 * @author gjc
 *
 */
public class ReflexUtil {
	/*
	 * 反射获取对象属性并拼接成sql语句查询的格式	column1,column2
	 */
	public static String columnsName(Object object) {
		// 获取对象对应类中的所有属性域	
		Field[] fields = object.getClass().getDeclaredFields();
		Integer len = fields.length;
		if(len==0) {
			return "";
		}else if(len==1) {
			return fields[0].getName();
		}
		String r = fields[0].getName();
		for(int i = 0; i < len; i++) {
			// 对于每个属性，获取属性名
			String varName = fields[i].getName();
			r += r + "," + varName;
		}
		return r;
	}	
}