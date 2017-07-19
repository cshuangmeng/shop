package com.gaoling.webshop.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileUtil {
	
	//创建文件夹
	public static boolean mkdirs(String dir){
		File file=new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return true;
	}
	
	//复制文件
	public static boolean copyFile(String srcFile,String destFile){
		try {
			FileInputStream is=new FileInputStream(srcFile);
			FileOutputStream os=new FileOutputStream(destFile);
			byte[] bytes=new byte[1024];
			int b=0;
			while((b=is.read(bytes))!=-1){
				os.write(bytes, 0, b);
			}
			os.close();
			is.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
