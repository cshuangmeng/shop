package com.gaoling.webshop.common;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;

public class SMSUtil {

	//淘宝大于短信发送
	public static boolean send(String mobile,String code){
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
	
}
