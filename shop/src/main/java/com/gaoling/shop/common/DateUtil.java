package com.gaoling.shop.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	// 获取系统当前时间
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	
	// 获取系统当前时间
	public static Date nowDate() {
		return new Date();
	}

	// 获取系统当前时间
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	// 日期加减
	public static String getDateOfInterval(String date, int interval, String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		c.add(Calendar.DAY_OF_MONTH, interval);
		return sdf.format(c.getTime());
	}

	// 分钟加减
	public static String getMinsOfInterval(String date, int interval, String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		c.add(Calendar.MINUTE, interval);
		return sdf.format(c.getTime());
	}

	// 小时加减
	public static String getHoursOfInterval(String date, int interval, String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(date));
		c.add(Calendar.HOUR_OF_DAY, interval);
		return sdf.format(c.getTime());
	}

	// 日期格式化
	public static String formatDate(String date, String pattern, String targetPattern) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(pattern);
		SimpleDateFormat tf = new SimpleDateFormat(targetPattern);
		return tf.format(sf.parse(date));
	}

	// 格式化时间
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	// 格式化时间
	public static String formatDate(long date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	// 计算两个时间之间的分钟数
	public static int getMinsIntervalOfTime(String time1, String time2) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
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
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
			long mills = date2.getTime() - date1.getTime();
			return Math.round(mills / (24 * 60 * 60 * 1000.0f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 判断两个日期是否在同一个月份
	public static boolean isSameMonth(String date1, String date2, String pattern) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(format.parse(date1));
		c2.setTime(format.parse(date2));
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = format.parse(date1);
		Date d2 = format.parse(date2);
		if (d1.getTime() > d2.getTime()) {
			return 1;
		} else if (d1.getTime() < d2.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
