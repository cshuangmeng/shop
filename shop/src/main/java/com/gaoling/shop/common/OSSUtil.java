package com.gaoling.shop.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class OSSUtil {

	// 上传本地文件至OSS存储
	public static boolean uploadFileToOSS(File file,String fileName) {
		try {
			OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
					AppConstant.OSS_SECRETACCESSKEY);
			InputStream is = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(is.available());
			client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 上传本地文件至OSS存储
	public static boolean uploadFileToOSS(InputStream is,String fileName) {
		try {
			OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
					AppConstant.OSS_SECRETACCESSKEY);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(is.available());
			client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
