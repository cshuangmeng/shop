package com.gaoling.webshop.common;

public class AppConstant {

	public static final String TRUST_CROSS_ORIGINS = "*";// 跨域访问域名
	public static final String WEB_CROSS_ORIGINS = "*";// 跨域访问域名

	// 支付方式
	public static final int WEIXIN_PAY_WAY = 1;
	public static final int ZHIFUBAO_PAY_WAY = 2;
	public static final int COIN_PAY_WAY = 3;
	public static final int POINT_PAY_WAY = 4;

	// 页面埋点
	public static final int GOODS_DETAIL_PAGE = 20;// 商品详情页面
	public static final int SHOP_DETAIL_PAGE = 7;// 商铺详情页面
	public static final int COOPERATOR_DETAIL_PAGE = 11;// 合作商铺详情页面
	public static final int MY_TRIBE_PAGE = 23;// 我的部落页面
	public static final int STORE_DETAIL_PAGE = 19;// 商城介绍页面

	// 微信原生支付方式
	public static final String WEIXIN_TRADE_TYPE_NATIVE = "NATIVE";

	// 用户信息存储变量名
	public static final String STORE_USER_PARAM_NAME = "user";
	// 访问参数存储变量名
	public static final String HTTP_PARAM_NAME = "httpParam";
	// 图形验证码存储参数名
	public static final String STORE_VERIFY_CODE_NAME = "verify";
	// 图形验证码宽
	public static final int VERIFY_CODE_WIDTH = 200;
	// 图形验证码高
	public static final int VERIFY_CODE_HEIGHT = 80;

	public static String ALIDAYU_SMS_URL;// 阿里大于短信发送地址
	public static String ALIDAYU_APP_KEY;// 阿里大于appKey
	public static String ALIDAYU_APP_SECRET;// 阿里大于appSecret
	public static String ALIDAYU_FREE_SIGN;// 阿里大于商户签名
	public static String ALIDAYU_TEMPLATE_CODE;// 阿里大于短信模板
	public static String CHECKCODE_PREFIX;// 用户验证码存储变量名的前缀

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

	public static String USERPC_APP_ID;// 微信网站应用AppID
	public static String USERPC_SECRET_KEY;// 微信网站应用API调用Secret Key

	public static String WEIXIN_JSAPI_ACCESS_TOKEN_URL;// 微信公众号获取access_token接口
	public static String WEIXIN_JSAPI_TICKET_URL;// 微信公众号获取TICKET接口
	public static String WEIXIN_OA_ACCESS_TOKEN_URL;// 微信公众号获取openId接口
	public static String WEIXIN_DOWNLOAD_FILE_URL;// 从微信服务器上下载多媒体文件
	public static String WEIXIN_SNS_USERINFO_URL;// 获取微信用户的昵称
	public static String WEIXIN_PAY_UNIFIEDORDER;// 微信预支付接口
	public static String WEIXIN_ORDER_QUERY;// 微信支付查询接口
	public static String WEIXIN_ORDER_REFUND;// 微信申请退款接口
	public static String WEIXIN_TEMPLATE_SEND;// 微信模板消息发送接口

	public static String API_GETOPENIDBYCODE;// Code获取微信openid、unionid信息接口

	public static String PC_SNS_USERINFO_URL;// PC端获取微信用户的昵称

	public static String MEMCACHED_ADDR;// Memcached服务器配置

	public static String OSS_BUCKETNAME;// OSS图片存储目录
	public static String OSS_ENDPOINT;// OSS图片存储地址
	public static String OSS_ACCESSKEYID;// OSSAPI调用账户
	public static String OSS_SECRETACCESSKEY;// OSSAPI调用密匙
	public static String OSS_CDN_SERVER;// OSS加速域名

	// 全局错误码
	public static final int SUCCESS = 0;
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
	public static final int USER_OR_PASSWORD_INCORRECT = 117;
	public static final int VERIFY_CODE_INCORRECT = 118;

	static {
		ALIDAYU_SMS_URL = PropertiesUtil.getProperty("alidayu_sms_url");
		ALIDAYU_APP_KEY = PropertiesUtil.getProperty("alidayu_app_key");
		ALIDAYU_APP_SECRET = PropertiesUtil.getProperty("alidayu_app_secret");
		ALIDAYU_FREE_SIGN = PropertiesUtil.getProperty("alidayu_free_sign");
		ALIDAYU_TEMPLATE_CODE = PropertiesUtil.getProperty("alidayu_template_code");
		CHECKCODE_PREFIX = PropertiesUtil.getProperty("checkcode_prefix");

		USERMP_REQUEST_TOKEN = PropertiesUtil.getProperty("usermp.weixin_request_token");
		USERMP_APP_ID = PropertiesUtil.getProperty("usermp.weixin_app_id");
		USERMP_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_secret_key");
		USERMP_MCH_ID = PropertiesUtil.getProperty("usermp.weixin_mch_id");
		USERMP_PAY_SECRET_KEY = PropertiesUtil.getProperty("usermp.weixin_pay_secret_key");
		USERMP_PAY_NOTIFY = PropertiesUtil.getProperty("usermp.weixin_pay_notify");
		USERMP_PAY_CERT = PropertiesUtil.getProperty("usermp.weixin_pay_cert");

		PC_SNS_USERINFO_URL = PropertiesUtil.getProperty("wxapi.pc_sns_userinfo_url");
		USERPC_APP_ID = PropertiesUtil.getProperty("userpc.weixin_app_id");
		USERPC_SECRET_KEY = PropertiesUtil.getProperty("userpc.weixin_secret_key");

		WEIXIN_JSAPI_ACCESS_TOKEN_URL = PropertiesUtil.getProperty("wxapi.weixin_jsapi_access_token_url");
		WEIXIN_JSAPI_TICKET_URL = PropertiesUtil.getProperty("wxapi.weixin_jsapi_ticket_url");
		WEIXIN_OA_ACCESS_TOKEN_URL = PropertiesUtil.getProperty("wxapi.weixin_oa_access_token_url");
		WEIXIN_DOWNLOAD_FILE_URL = PropertiesUtil.getProperty("wxapi.weixin_download_file_url");
		WEIXIN_SNS_USERINFO_URL = PropertiesUtil.getProperty("wxapi.weixin_sns_userinfo_url");
		WEIXIN_PAY_UNIFIEDORDER = PropertiesUtil.getProperty("wxapi.weixin_pay_unifiedorder");
		WEIXIN_ORDER_QUERY = PropertiesUtil.getProperty("wxapi.weixin_order_query");
		WEIXIN_ORDER_REFUND = PropertiesUtil.getProperty("wxapi.weixin_order_refund");
		WEIXIN_TEMPLATE_SEND = PropertiesUtil.getProperty("wxapi.weixin_template_send");

		API_GETOPENIDBYCODE = PropertiesUtil.getProperty("api.getUnionIdByCode");

		MEMCACHED_ADDR = PropertiesUtil.getProperty("memcached.addr");

		OSS_BUCKETNAME = PropertiesUtil.getProperty("cdn.oss_bucketname");
		OSS_ENDPOINT = PropertiesUtil.getProperty("cdn.oss_endpoint");
		OSS_ACCESSKEYID = PropertiesUtil.getProperty("cdn.oss_accesskeyid");
		OSS_SECRETACCESSKEY = PropertiesUtil.getProperty("cdn.oss_secretaccesskey");
		OSS_CDN_SERVER = PropertiesUtil.getProperty("cdn.oss_cdn_server");
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
