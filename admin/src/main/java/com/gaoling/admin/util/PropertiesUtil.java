package com.gaoling.admin.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {

	static Properties prop=new Properties();
	
	static{
		try {
			prop.load(PropertiesUtil.class.getResourceAsStream("/vms.properties"));
			Logger.getLogger("file").info(DateUtil.getCurrentTime()+" vms.properties loaded!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读取属性
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
}
