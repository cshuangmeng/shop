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
			String[] str={"4003022001201706186275734163"};
			PayParam pay=new PayParam();
			for(String no:str){
				pay.setAmount(59);
				pay.setRefund(59f);
				pay.setNonceStr(DataUtil.createLetters(32));
				pay.setOperator(AppConstant.USERMP_MCH_ID);
				pay.setTradeType(AppConstant.WEIXIN_TRADE_TYPE_JSAPI);
				pay.setOutTradeNo(no);
				pay.setTradeNo(DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(3));
				System.out.println(no+"退款结果:"+weiXinRefundRequest(pay));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
}
