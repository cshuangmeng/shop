package com.gaoling.shop.common;

public class AppConstant {

	public static final String TRUST_CROSS_ORIGINS = "*";// 跨域访问域名
	public static final String WEB_CROSS_ORIGINS = "*";// 跨域访问域名

	// 支付方式
	public static final int WEIXIN_PAY_WAY = 1;
	public static final int ZHIFUBAO_PAY_WAY = 2;
	public static final int COIN_PAY_WAY = 3;
	public static final int POINT_PAY_WAY = 4;

	public static String ALIDAYU_SMS_URL;// 阿里大于短信发送地址
	public static String ALIDAYU_APP_KEY;// 阿里大于appKey
	public static String ALIDAYU_APP_SECRET;// 阿里大于appSecret
	public static String ALIDAYU_FREE_SIGN;// 阿里大于商户签名
	public static String ALIDAYU_TEMPLATE_CODE;// 阿里大于短信模板
	public static String CHECKCODE_PREFIX;// 用户验证码存储变量名的前缀

	public static int XINGE_IOSENV;// 信鸽ios开发证书编号
	public static String XINGE_MESSAGE_TITLE;// 伏特加推送通知标题
	public static String XINGE_ANDROID_ACCESS_ID;// 信鸽Android Access ID
	public static String XINGE_ANDROID_SECRET_KEY;// 信鸽Android Secret Key
	public static String XINGE_ANDROID_ACTIVITY;// 信鸽Android通知点击后跳转的Activity地址
	public static String XINGE_IOS_ACCESS_ID;// 信鸽IOS Access ID
	public static String XINGE_IOS_SECRET_KEY;// 信鸽IOS Secret Key

	public static String USERMP_REQUEST_TOKEN;// 微信与服务器通信的token
	public static String USERMP_APP_ID;// 用户端微信公众号AppID
	public static String USERMP_SECRET_KEY;// 用户端微信公众号API调用Secret Key
	public static String USERMP_MCH_ID;// 用户端微信商户ID
	public static String USERMP_PAY_SECRET_KEY;// 用户端微信商户API调用Secret Key
	public static String USERMP_PAY_NOTIFY;// 用户端微信支付通知接口
	public static String USERMP_PAY_MSG_BODY;// 用户端微信商户支付消息内容
	public static String USERMP_PAY_CERT;// 用户端微信商户付款证书
	public static String USERMP_ACCESS_TOKEN;//// 用户端微信公众号access_token
	public static String USERMP_TICKET;// 用户端微信公众号ticket

	public static String WEIXIN_JSAPI_ACCESS_TOKEN_URL;// 微信公众号获取access_token接口
	public static String WEIXIN_JSAPI_TICKET_URL;// 微信公众号获取TICKET接口
	public static String WEIXIN_OA_ACCESS_TOKEN_URL;// 微信公众号获取openId接口
	public static String WEIXIN_DOWNLOAD_FILE_URL;// 从微信服务器上下载多媒体文件
	public static String WEIXIN_SNS_USERINFO_URL;// 获取微信用户的昵称
	public static String WEIXIN_PAY_UNIFIEDORDER;// 微信预支付接口
	public static String WEIXIN_ORDER_QUERY;// 微信支付查询接口
	public static String WEIXIN_ORDER_REFUND;// 微信申请退款接口
	public static String WEIXIN_TEMPLATE_SEND;// 微信模板消息发送接口

	public static String MEMCACHED_ADDR;// Memcached服务器配置

	// 全局错误码
	public static final int SYSTEM_ERROR_CODE = 500;
	public static final int PARAM_IS_NULL = 100;
	public static final int DATA_FORMAT_INCORRECT = 101;
	public static final int SMS_SEND_FAILURE = 102;
	public static final int CHECK_CODE_INCORRECT = 103;
	public static final int USER_NOT_EXISTS = 104;
	public static final int NOT_MYSELF_OPERATE = 105;

	static {
		ALIDAYU_SMS_URL = PropertiesUtil.getProperty("alidayu_sms_url");
		ALIDAYU_APP_KEY = PropertiesUtil.getProperty("alidayu_app_key");
		ALIDAYU_APP_SECRET = PropertiesUtil.getProperty("alidayu_app_secret");
		ALIDAYU_FREE_SIGN = PropertiesUtil.getProperty("alidayu_free_sign");
		ALIDAYU_TEMPLATE_CODE = PropertiesUtil.getProperty("alidayu_template_code");
		CHECKCODE_PREFIX = PropertiesUtil.getProperty("checkcode_prefix");

		XINGE_IOSENV = PropertiesUtil.getInteger("xinge.iosenv");
		XINGE_MESSAGE_TITLE = PropertiesUtil.getProperty("xinge.message_title");
		XINGE_ANDROID_ACCESS_ID = PropertiesUtil.getProperty("xinge.android_access_id");
		XINGE_ANDROID_SECRET_KEY = PropertiesUtil.getProperty("xinge.android_secret_key");
		XINGE_ANDROID_ACTIVITY = PropertiesUtil.getProperty("xinge.android_activity");
		XINGE_IOS_ACCESS_ID = PropertiesUtil.getProperty("xinge.ios_access_id");
		XINGE_IOS_SECRET_KEY = PropertiesUtil.getProperty("xinge.ios_secret_key");

		USERMP_REQUEST_TOKEN = PropertiesUtil.getProperty("usermp.weixin_request_token");
		USERMP_APP_ID = PropertiesUtil.getProperty("usermp.weixin_app_id");
		USERMP_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_secret_key");
		USERMP_MCH_ID = PropertiesUtil.getProperty("usermp.weixin_mch_id");
		USERMP_PAY_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_pay_secret_key");
		USERMP_PAY_NOTIFY = PropertiesUtil.getProperty("usermp.weixin_pay_notify");
		USERMP_PAY_CERT = PropertiesUtil.getProperty("usermp.weixin_pay_cert");

		WEIXIN_JSAPI_ACCESS_TOKEN_URL = PropertiesUtil.getProperty("wxapi.weixin_jsapi_access_token_url");
		WEIXIN_JSAPI_TICKET_URL = PropertiesUtil.getProperty("wxapi.weixin_jsapi_ticket_url");
		WEIXIN_OA_ACCESS_TOKEN_URL = PropertiesUtil.getProperty("wxapi.weixin_oa_access_token_url");
		WEIXIN_DOWNLOAD_FILE_URL = PropertiesUtil.getProperty("wxapi.weixin_download_file_url");
		WEIXIN_SNS_USERINFO_URL = PropertiesUtil.getProperty("wxapi.weixin_sns_userinfo_url");
		WEIXIN_PAY_UNIFIEDORDER = PropertiesUtil.getProperty("wxapi.weixin_pay_unifiedorder");
		WEIXIN_ORDER_QUERY = PropertiesUtil.getProperty("wxapi.weixin_order_query");
		WEIXIN_ORDER_REFUND = PropertiesUtil.getProperty("wxapi.weixin_order_refund");
		WEIXIN_TEMPLATE_SEND = PropertiesUtil.getProperty("wxapi.weixin_template_send");

		MEMCACHED_ADDR = PropertiesUtil.getProperty("memcached.addr");
	}

}
