package com.gaoling.webshop.common;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	// 获取系统当前时间
	public static String getCurrentTime() {
		return DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
	}
	
	// 获取系统当前时间
	public static Date nowDate() {
		return new Date();
	}

	// 获取系统当前时间
	public static String getCurrentTime(String pattern) {
		return DateFormatUtils.format(new Date(),pattern);
	}
	
	// 日期加减
	public static String getDateOfInterval(String date, int interval, String pattern) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseDate(date,pattern));
		c.add(Calendar.DAY_OF_MONTH, interval);
		return DateFormatUtils.format(c.getTime(),pattern);
	}

	// 分钟加减
	public static String getMinsOfInterval(String date, int interval, String pattern) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseDate(date,pattern));
		c.add(Calendar.MINUTE, interval);
		return DateFormatUtils.format(c.getTime(),pattern);
	}

	// 小时加减
	public static String getHoursOfInterval(String date, int interval, String pattern) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(DateUtils.parseDate(date,pattern));
		c.add(Calendar.HOUR_OF_DAY, interval);
		return DateFormatUtils.format(c.getTime(),pattern);
	}

	// 日期格式化
	public static String formatDate(String date, String pattern, String targetPattern) throws Exception {
		return DateFormatUtils.format(DateUtils.parseDate(date,pattern),targetPattern);
	}

	// 格式化时间
	public static String formatDate(Date date) {
		return DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	// 格式化时间
	public static String formatDateToDay(Date date) {
		return DateFormatUtils.format(date,"yyyy-MM-dd");
	}

	// 格式化时间
	public static String formatDate(long date) {
		return DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
	}

	// 计算两个时间之间的分钟数
	public static int getMinsIntervalOfTime(String time1, String time2) {
		try {
			Date date1 = DateUtils.parseDate(time1,"yyyy-MM-dd HH:mm:ss");
			Date date2 = DateUtils.parseDate(time2,"yyyy-MM-dd HH:mm:ss");
			long mills = date2.getTime() - date1.getTime();
			return Math.round(mills / (60 * 1000.0f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	// 计算两个时间之间的天数
	public static int getDaysIntervalOfTime(String time1, String time2) {
		try {
			Date date1 = DateUtils.parseDate(time1,"yyyy-MM-dd");
			Date date2 = DateUtils.parseDate(time2,"yyyy-MM-dd");
			long mills = date2.getTime() - date1.getTime();
			return Math.round(mills / (24 * 60 * 60 * 1000.0f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 判断两个日期是否在同一个月份
	public static boolean isSameMonth(String date1, String date2, String pattern) throws Exception {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(DateUtils.parseDate(date1,pattern));
		c2.setTime(DateUtils.parseDate(date2,pattern));
		return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
	}
	
	// 将分钟转换成文字描述
	public static String convertMinsToDesc(int mins) {
		StringBuffer str=new StringBuffer();
		int days=mins/(24*60);
		str.append(days+"天");
		int hours=(mins%(24*60))/60;
		str.append(hours+"小时");
		mins=(mins%(24*60))%60;
		str.append(mins+"分钟");
		return str.toString();
	}
	
	// 日期比较
	public static int compareDate(String date1, String date2) throws Exception {
		Date d1 = DateUtils.parseDate(date1,"yyyy-MM-dd");
		Date d2 = DateUtils.parseDate(date2,"yyyy-MM-dd");
		if (d1.getTime() > d2.getTime()) {
			return 1;
		} else if (d1.getTime() < d2.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
