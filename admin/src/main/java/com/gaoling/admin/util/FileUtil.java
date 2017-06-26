package com.gaoling.admin.util;

import java.io.File;

public class FileUtil {
	
	//创建文件夹
	public static boolean mkdirs(String dir){
		File file=new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return true;
	}
	
}
