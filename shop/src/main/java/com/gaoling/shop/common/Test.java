package com.gaoling.shop.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gaoling.shop.system.pojo.PayParam;

import net.sf.json.JSONObject;

public class Test {
	
	public static void main(String[] args) {
		try {
			PayParam param=new PayParam();
			param.setOutTradeNo(String.valueOf(new Date().getTime()));
			param.setBody("测试企业付款");
			param.setNonceStr(DataUtil.createNums(6));
			param.setIp("192.168.0.108");
			param.setAmount(1f);
			param.setOpenId("oePMUv1Lh4uTN2MNvGaT4il26Ygg");
			weiXinTransferRequest(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void download(){
		String token="rRx0jEJkQr8UoMurjU5-_KyTpQRdGLPTkmc-6vqAS1HsJivjTG9_0n7JTKOH8xGFjzEr1A5H8TwWxFZns8L9FY-SJ8DVh9QapVGAH0h9a-0RMDRe57rKfujdKe_g1-nWLPIgADADHA";
		String mediaId="z9b33J3jtA2gY_iM58OQFNvS6dgVfYADDlSk4xcfqs_50gn3bejEFAX_GeIMURuN";
		String url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="+token+"&media_id="+mediaId;
		System.out.println(url);
		HttpClientUtil.getNetWorkInfo(url, "D:/Temp/1.mp3");
	}

	public static boolean weiXinRefundRequest(PayParam param)throws Exception{
		//加载用户支付成功的订单
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("appid",AppConstant.USERMP_APP_ID);
		paramMap.put("mch_id",AppConstant.USERMP_MCH_ID);
		paramMap.put("nonce_str",param.getNonceStr());
		paramMap.put("transaction_id", param.getOutTradeNo());
		paramMap.put("out_refund_no", param.getTradeNo());
		paramMap.put("total_fee",Math.round(param.getAmount()*100));
		paramMap.put("refund_fee", Math.round(param.getRefund()*100));
		paramMap.put("op_user_id", param.getOperator());
		paramMap.put("sign", SignUtil.signValue(paramMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
		Logger.getLogger("file").info("<------微信退款请求参数----->"+JSONObject.fromObject(paramMap).toString());
		String response=HttpClientUtil.sendHTTPSWithP12(AppConstant.WEIXIN_ORDER_REFUND,
				XMLUtil.createXMLString(paramMap, "xml"), AppConstant.USERMP_MCH_ID,AppConstant.USERMP_PAY_CERT);
		Logger.getLogger("file").info("<------微信退款响应内容----->"+response);
		//保存订单数据
		if(response.length()>0){
			Map<String,Object> responseMap=XMLUtil.readParamsFromXML(response);
			if(responseMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
				if(responseMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean weiXinTransferRequest(PayParam param)throws Exception{
		//加载用户支付成功的订单
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mch_appid",AppConstant.USERMP_APP_ID);
		paramMap.put("mchid",AppConstant.USERMP_MCH_ID);
		paramMap.put("nonce_str",param.getNonceStr());
		paramMap.put("partner_trade_no", param.getOutTradeNo());
		paramMap.put("openid", param.getOpenId());
		paramMap.put("check_name", "NO_CHECK");
		paramMap.put("amount",Math.round(param.getAmount()*100));
		paramMap.put("desc", param.getBody());
		paramMap.put("spbill_create_ip", param.getIp());
		paramMap.put("sign", SignUtil.signValue(paramMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
		Logger.getLogger("file").info("<------微信企业支付请求参数----->"+JSONObject.fromObject(paramMap).toString());
		String response=HttpClientUtil.sendHTTPSWithP12(AppConstant.WEIXIN_TRANSFER_SEND,
				XMLUtil.createXMLString(paramMap, "xml"), AppConstant.USERMP_MCH_ID,AppConstant.USERMP_PAY_CERT);
		Logger.getLogger("file").info("<------微信企业支付响应内容----->"+response);
		//保存订单数据
		if(response.length()>0){
			Map<String,Object> responseMap=XMLUtil.readParamsFromXML(response);
			System.out.println(JSONObject.fromObject(responseMap));
			if(responseMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
				if(responseMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
					return true;
				}
			}
		}
		return false;
	}
	
}
