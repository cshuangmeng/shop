package com.gaoling.admin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	// 获取系统当前时间
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}

	// 获取系统当前时间
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	// 获取系统当前时间
	public static Date nowDate() {
		return new Date();
	}

	// 格式化时间
	public static String getFormatTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	// 格式化时间
	public static String dateToDay(Date date) {
		return DateFormatUtils.format(date, "yyyy-MM-dd");
	}

	// 格式化时间
	public static Date dayToDate(String day) {
		try {
			return DateUtils.parseDate(day, "yyyy-MM-dd");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 计算两个时间之间的分钟数
	public static int getMinsIntervalOfTime(String time1, String time2) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
			long mins = (date2.getTime() - date1.getTime()) / (60 * 1000);
			return (int) mins;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 字符串转换日期
	public static Date parseDate(String str) {
		try {
			return DateUtils.parseDate(str, "yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
