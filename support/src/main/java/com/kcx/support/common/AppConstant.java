package com.kcx.support.common;

public class AppConstant {

	public static final String TRUST_CROSS_ORIGINS = "*";// 跨域访问域名

	// 支付方式
	public static final int WEIXIN_PAY_WAY = 1;
	public static final int ZHIFUBAO_PAY_WAY = 2;

	// 微信公众号支付方式
	public static final String WEIXIN_TRADE_TYPE_JSAPI = "JSAPI";

	// 用户信息存储变量名
	public static final String STORE_USER_PARAM_NAME = "user";
	//访问参数存储变量名
	public static final String HTTP_PARAM_NAME = "httpParam";

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

	public static String QINIU_BUCKETNAME;// 七牛图片存储目录
	public static String QINIU_ACCESSKEYID;// 七牛API调用账户
	public static String QINIU_SECRETACCESSKEY;// 七牛API调用密匙
	public static String QINIU_CDN_SERVER;// 七牛加速域名

	public static int LEVEL_MONEY;// 关卡费用,单位分

	// 全局错误码
	public static final int SYSTEM_ERROR_CODE = 500;
	public static final int PARAM_IS_NULL = 100;
	public static final int DATA_FORMAT_INCORRECT = 101;
	public static final int USER_NOT_EXISTS = 102;
	public static final int NOT_MYSELF_OPERATE = 103;
	public static final int OPERATE_FAILURE = 104;
	public static final int PAY_PRICE_INCORRECT = 105;
	public static final int ORDER_STATE_INCORRECT = 106;

	static {

		USERMP_APP_ID = PropertiesUtil.getProperty("usermp.weixin_app_id");
		USERMP_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_secret_key");
		USERMP_MCH_ID = PropertiesUtil.getProperty("usermp.weixin_mch_id");
		USERMP_PAY_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_pay_secret_key");
		USERMP_PAY_NOTIFY = PropertiesUtil.getProperty("usermp.weixin_pay_notify");
		USERMP_PAY_MSG_BODY = PropertiesUtil.getProperty("usermp.weixin_pay_msg_body");
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

		LEVEL_MONEY = Integer.parseInt(PropertiesUtil.getProperty("level_money"));

		QINIU_BUCKETNAME = PropertiesUtil.getProperty("qiniu.oss_bucketname");
		QINIU_ACCESSKEYID = PropertiesUtil.getProperty("qiniu.oss_accesskeyid");
		QINIU_SECRETACCESSKEY = PropertiesUtil.getProperty("qiniu.oss_secretaccesskey");
		QINIU_CDN_SERVER = PropertiesUtil.getProperty("qiniu.oss_cdn_server");
	}

	// 是/否
	public static enum YES_OR_NO_ENUM {
		NO(0), YES(1);
		private int state;

		private YES_OR_NO_ENUM(int state) {
			this.state = state;
		}

		public int getState() {
			return state;
		}
	}

}
