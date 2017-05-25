package com.gaoling.shop.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

import net.sf.json.JSONObject;

public class OSSUtil {

	// 上传本地文件至OSS存储
	public static void uploadFileToOSS(String file, String saveName, JSONObject json) throws Exception {
		OSSClient client = new OSSClient(json.getString("endpoint"), json.getString("accesskeyid"),
				json.getString("secretaccesskey"));
		InputStream is = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(is.available());
		saveName = StringUtils.isNotEmpty(saveName) ? saveName : file.substring(file.lastIndexOf("/") + 1);
		if (DataUtil.isImg(saveName)) {
			client.putObject(json.getString("bucketname"), json.getString("imgDir") + "/" + saveName, is, meta);
		} else if (DataUtil.isVideo(saveName)) {
			client.putObject(json.getString("bucketname"), json.getString("videoDir") + "/" + saveName, is, meta);
		}
	}
	
	// 上传本地文件至OSS存储
	public static void uploadFileToOSS(File file, String saveName, JSONObject json) throws Exception {
		OSSClient client = new OSSClient(json.getString("endpoint"), json.getString("accesskeyid"),
				json.getString("secretaccesskey"));
		InputStream is = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(is.available());
		saveName = StringUtils.isNotEmpty(saveName) ? saveName : file.getName().substring(file.getName().lastIndexOf("/") + 1);
		if (DataUtil.isImg(saveName)) {
			client.putObject(json.getString("bucketname"), json.getString("imgDir") + "/" + saveName, is, meta);
		} else if (DataUtil.isVideo(saveName)) {
			client.putObject(json.getString("bucketname"), json.getString("videoDir") + "/" + saveName, is, meta);
		}
	}

}
