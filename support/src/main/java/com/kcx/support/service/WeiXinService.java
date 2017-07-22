package com.kcx.support.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kcx.support.common.AppConstant;
import com.kcx.support.common.DataUtil;
import com.kcx.support.common.DateUtil;
import com.kcx.support.common.HttpClientUtil;
import com.kcx.support.common.SignUtil;

import net.sf.json.JSONObject;

@Service
public class WeiXinService {
	
	// 获取ticket,且每隔一小时刷新一次
	//@Scheduled(fixedDelay = 3600 * 1000)
	public void getTicketSchedule() {
		// 获取用户端access_token
		AppConstant.USERMP_ACCESS_TOKEN=getAccessToken(AppConstant.USERMP_APP_ID, AppConstant.USERMP_SECRET_KEY);
		// 获取用户端ticket
		AppConstant.USERMP_TICKET=getTicket(AppConstant.USERMP_ACCESS_TOKEN);
	}
	
	private String getAccessToken(String appId,String secret){
		String accessToken=null;
		String url=AppConstant.WEIXIN_JSAPI_ACCESS_TOKEN_URL +"&appid=" + appId + "&secret=" + secret;
		String response = HttpClientUtil.sendHTTPS(url);
		JSONObject json = JSONObject.fromObject(response);
		if (json.containsKey("access_token")) {
			accessToken = json.getString("access_token");
		}
		Logger.getLogger("file").info(DateUtil.getCurrentTime() +" Access Token init completed!|"+url+"|"+response);
		return accessToken;
	}
	
	private String getTicket(String accessToken){
		String ticket=null;
		String url=AppConstant.WEIXIN_JSAPI_TICKET_URL+"&access_token="+accessToken;
		String response = HttpClientUtil.sendHTTPS(url);
		JSONObject json = JSONObject.fromObject(response);
		if (json.getInt("errcode") == 0) {
			ticket = json.getString("ticket");
		}
		Logger.getLogger("file").info(DateUtil.getCurrentTime()
				+" TICKET init completed!|"+url+"|"+response);
		return ticket;
	}
	
	// JS－SDK签名生成
	public Map<String,Object> getRequestSign(String url) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String noncestr = DataUtil.createStrings(16);
		long timestamp = new Date().getTime();
		//去掉#号以及后面的内容
		url=url.substring(0, url.indexOf("#")>=0?url.indexOf("#"):url.length());
		params.put("url", url);
		params.put("noncestr", noncestr);
		params.put("timestamp", String.valueOf(timestamp/1000));
		params.put("jsapi_ticket", AppConstant.USERMP_TICKET);
		resultMap.put("appId", AppConstant.USERMP_APP_ID);
		resultMap.put("nonceStr", noncestr);
		resultMap.put("timestamp", params.get("timestamp"));
		resultMap.put("signature", SignUtil.signValue(params,"SHA1"));
		return resultMap;
	}
	
}
