package com.kcx.support.common;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class OSSUtil {

	// 上传本地文件至OSS存储
	public static boolean uploadFileToOSS(String localFilePath,String fileName) {
		try {
			Configuration cfg = new Configuration(Zone.zone0());
			UploadManager uploadManager = new UploadManager(cfg);
			Auth auth = Auth.create(AppConstant.QINIU_ACCESSKEYID, AppConstant.QINIU_SECRETACCESSKEY);
			String upToken = auth.uploadToken(AppConstant.QINIU_BUCKETNAME);
		    uploadManager.put(localFilePath, fileName, upToken);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
