package com.vic.http.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 时间差
 * @author chen
 *
 */
public class TimeCompare {

	/** 小时 */
	private static final int hour = 1000 * 60 * 60;
//	private static final int hour = 1000 * 60;
	/**
	 * 支持 yyyy-MM-dd HH精确到小时
	 * @param start 开始时间 if：2012-2-10 11
	 * @param end 结束时间  if:2012-02-11 13:12:21
	 * @return 小时数
	 */
	public static int compareHourTime(String start,String end){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			Date d1 = df.parse(end);
			Date d2 = df.parse(start);
			long diff = d1.getTime() - d2.getTime();
			int hours = (int)(diff / hour);
			return hours;
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 根据当前时间 获取时间差 支持 yyyy-MM-dd HH精确到小时
	 * @param start 开始时间 if：2012-2-10 11
	 * @return 小时数
	 */
	public static int compareHourTime(String start){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		try {
			Date d2 = sdf.parse(start);
			long diff = System.currentTimeMillis() - d2.getTime();
			int hours = (int)(diff / hour);
			return hours;
		} catch (Exception e) {
		}
		return 0;
	}
}