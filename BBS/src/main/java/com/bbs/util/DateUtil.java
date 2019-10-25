package com.bbs.util;


/**
 * 时间帮助类
 * @author 啊哈
 *
 */
public class DateUtil {
	
	/**
	 * 获取当前时间
	 * @param
	 * @return java.sql.Date
	 */
	public static java.sql.Date getNowDateForSql() {
		
		java.util.Date date1 = new java.util.Date(System.currentTimeMillis());
		java.sql.Date date2 = new java.sql.Date(date1.getTime());
		
		return date2;
	}
	
}
