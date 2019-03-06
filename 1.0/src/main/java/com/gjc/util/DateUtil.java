package com.gjc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具包
 * @author gc
 *
 */
public class DateUtil {
	private static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	
	private static final String Y_M_D = "yyyy-MM-dd";
	
	/**
	 * 格式化时间输出
	 * @param pattern 格式化类型
	 * @param date 时间
	 * @return
	 */
	public static String format(String pattern, Date date) {
		if (null == date) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 格式化时间输出(精确到秒)
	 * @param date 时间
	 * @return
	 */
	public static String formatTime(Date date) {
		return format(Y_M_D_H_M_S,date);
	}
	
	/**
	 * 格式化时间输出(精确到天)
	 * @param date 时间
	 * @return
	 */
	public static String formatDay(Date date) {
		return format(Y_M_D,date);
	}
	
}
