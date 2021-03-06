package com.gaoling.shop.common;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;

public class SMSUtil {

	//淘宝大于短信验证码发送
	public static boolean sendCheckCode(String mobile,String code){
		try {
			TaobaoClient client = new DefaultTaobaoClient(AppConstant.ALIDAYU_SMS_URL, AppConstant.ALIDAYU_APP_KEY, AppConstant.ALIDAYU_APP_SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName(AppConstant.ALIDAYU_FREE_SIGN);
			req.setSmsParamString(JSONObject.fromObject(DataUtil.mapOf("code",code)).toString());
			req.setRecNum(mobile);
			req.setSmsTemplateCode(AppConstant.ALIDAYU_TEMPLATE_CODE);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			return rsp.isSuccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//淘宝大于新订单提醒短信发送
	public static boolean sendNewOrderNotice(String mobile,String goods,Integer num,String consigner,String address){
		try {
			TaobaoClient client = new DefaultTaobaoClient(AppConstant.ALIDAYU_SMS_URL, AppConstant.ALIDAYU_APP_KEY, AppConstant.ALIDAYU_APP_SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName(AppConstant.ALIDAYU_FREE_SIGN);
			req.setSmsParamString(JSONObject.fromObject(DataUtil.mapOf("goods",goods,"num",String.valueOf(num)
					,"consigner",consigner,"address",address)).toString());
			req.setRecNum(mobile);
			req.setSmsTemplateCode(AppConstant.ALIDAYU_TEMPLATE_ORDER_NOTICE);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			return rsp.isSuccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//淘宝大于账户余额不足提醒短信发送
	public static boolean sendAccountNotEnoughNotice(String mobile){
		try {
			TaobaoClient client = new DefaultTaobaoClient(AppConstant.ALIDAYU_SMS_URL, AppConstant.ALIDAYU_APP_KEY, AppConstant.ALIDAYU_APP_SECRET);
			AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
			req.setSmsType("normal");
			req.setSmsFreeSignName(AppConstant.ALIDAYU_FREE_SIGN);
			req.setRecNum(mobile);
			req.setSmsTemplateCode(AppConstant.ALIDAYU_TEMPLATE_ACCOUNT_NOTICE);
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			return rsp.isSuccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
