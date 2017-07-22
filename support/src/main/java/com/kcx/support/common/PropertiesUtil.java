package com.kcx.support.common;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {

	static Properties prop=new Properties();
	
	static{
		try {
			prop.load(PropertiesUtil.class.getResourceAsStream("/support.properties"));
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" shop.properties loaded!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读取属性
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
	//读取属性
	public static int getInteger(String key){
		return Integer.parseInt(prop.getProperty(key));
	}
	
}
