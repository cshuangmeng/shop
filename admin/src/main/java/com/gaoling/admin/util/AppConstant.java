package com.gaoling.admin.util;

public class AppConstant {

	public static String SESSION_DATA_NAME;// 用户登录信息存储的参数名称

	public static String TEMP_FILE_DIR;// 文件上传临时存储目录

	public static String ALIDAYU_SMS_URL;// 阿里大于短信发送地址
	public static String ALIDAYU_APP_KEY;// 阿里大于appKey
	public static String ALIDAYU_APP_SECRET;// 阿里大于appSecret
	public static String ALIDAYU_FREE_SIGN;// 阿里大于商户签名
	public static String ALIDAYU_TEMPLATE_CODE;// 阿里大于短信模板
	public static String CHECKCODE_PREFIX;// 用户验证码存储变量名的前缀

	public static String MEMCACHED_ADDR;// Memcached服务器配置

	public static String OSS_BUCKETNAME;// OSS图片存储目录
	public static String OSS_ENDPOINT;// OSS图片存储地址
	public static String OSS_ACCESSKEYID;// OSSAPI调用账户
	public static String OSS_SECRETACCESSKEY;// OSSAPI调用密匙
	public static String OSS_CDN_SERVER;// OSS加速域名

	// 全局错误码
	public static final int SYSTEM_ERROR_CODE = 500;
	public static final int PARAM_IS_NULL = 100;
	public static final int DATA_FORMAT_INCORRECT = 101;
	public static final int SMS_SEND_FAILURE = 102;
	public static final int CHECK_CODE_INCORRECT = 103;
	public static final int USER_NOT_EXISTS = 104;
	public static final int NOT_MYSELF_OPERATE = 105;
	public static final int GOODS_NOT_EXISTS = 106;
	public static final int SHOP_NOT_EXISTS = 107;
	public static final int USER_ALREADY_FOLLOWED = 108;
	public static final int USER_NO_FOLLOWED = 109;
	public static final int OPERATE_FAILURE = 110;
	public static final int PAY_PRICE_INCORRECT = 111;
	public static final int ACCOUNT_BALANCE_INADEQUATE = 112;
	public static final int TRIBE_NOT_EXISTS = 113;
	public static final int ORDER_STATE_INCORRECT = 114;
	public static final int OUT_OF_BOUNDS = 115;
	public static final int USER_ALREADY_EXISTS = 116;

	static {
		SESSION_DATA_NAME = PropertiesUtil.getProperty("session_data_name");
		TEMP_FILE_DIR = PropertiesUtil.getProperty("temp_file_dir");

		ALIDAYU_SMS_URL = PropertiesUtil.getProperty("alidayu_sms_url");
		ALIDAYU_APP_KEY = PropertiesUtil.getProperty("alidayu_app_key");
		ALIDAYU_APP_SECRET = PropertiesUtil.getProperty("alidayu_app_secret");
		ALIDAYU_FREE_SIGN = PropertiesUtil.getProperty("alidayu_free_sign");
		ALIDAYU_TEMPLATE_CODE = PropertiesUtil.getProperty("alidayu_template_code");
		CHECKCODE_PREFIX = PropertiesUtil.getProperty("checkcode_prefix");

		MEMCACHED_ADDR = PropertiesUtil.getProperty("memcached.addr");

		OSS_BUCKETNAME = PropertiesUtil.getProperty("cdn.oss_bucketname");
		OSS_ENDPOINT = PropertiesUtil.getProperty("cdn.oss_endpoint");
		OSS_ACCESSKEYID = PropertiesUtil.getProperty("cdn.oss_accesskeyid");
		OSS_SECRETACCESSKEY = PropertiesUtil.getProperty("cdn.oss_secretaccesskey");
		OSS_CDN_SERVER = PropertiesUtil.getProperty("cdn.oss_cdn_server");
	}

	// 是否状态枚举
	public static enum YES_OR_NO_ENUM {
		yes("Y"), no("N");
		private String value;

		private YES_OR_NO_ENUM(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// App存储方式
	public static enum APP_STORAGE_TYPE {
		CDN("CDN"), Nginx("Nginx");
		private String value;

		private APP_STORAGE_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// 帐号状态
	public static enum ACCOUNT_STATE_TYPE {
		ENABLED(1), DISABLED(0);
		private int value;

		private ACCOUNT_STATE_TYPE(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

}
