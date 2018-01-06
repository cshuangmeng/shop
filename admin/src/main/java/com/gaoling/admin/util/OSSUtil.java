package com.gaoling.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class OSSUtil {

	// 上传本地文件至OSS存储
	public static void uploadFileToOSS(String file) throws Exception {
		OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
				AppConstant.OSS_SECRETACCESSKEY);
		InputStream is = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(is.available());
		String fileName = file.substring(file.lastIndexOf("/") + 1);
		client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
	}

	// 上传文件至OSS存储
	public static void uploadFileToOSS(String fileName, InputStream is) throws Exception {
		OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
				AppConstant.OSS_SECRETACCESSKEY);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(is.available());
		client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
	}

	// 上传文件至OSS存储
	public static void uploadFileToOSS(String fileName, File file) throws Exception {
		OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
				AppConstant.OSS_SECRETACCESSKEY);
		InputStream is = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(is.available());
		client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
	}

	// 批量上传文件至OSS
	public static String uploadFileToOSS(MultipartFile[] imgFiles) throws Exception {
		String imgUrl = "";
		// 创建文件夹
		FileUtil.mkdirs(AppConstant.TEMP_FILE_DIR);
		if (null != imgFiles) {
			for (MultipartFile imgFile : imgFiles) {
				if (null != imgFile && !imgFile.isEmpty() && DataUtil.isImgFile(imgFile.getOriginalFilename())) {
					String imgName = Calendar.getInstance().getTimeInMillis() + DataUtil.createNums(5)
							+ imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."));
					String savePath = AppConstant.TEMP_FILE_DIR + imgName;
					File file = new File(savePath);
					imgFile.transferTo(file);
					// 上传至OSS
					OSSUtil.uploadFileToOSS(savePath);
					// 删除临时文件
					file.delete();
					imgUrl += imgUrl.length() > 0 ? ";" + AppConstant.OSS_CDN_SERVER + imgName
							: AppConstant.OSS_CDN_SERVER + imgName;
				}
			}
		}
		return imgUrl;
	}

}
