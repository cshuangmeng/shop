package com.gaoling.shop.pay.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.HttpClientUtil;
import com.gaoling.shop.common.MemcachedUtil;
import com.gaoling.shop.common.SignUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class WeiXinService extends CommonService{

	// 获取ticket,且每隔一小时刷新一次
	@Scheduled(fixedDelay = 3600 * 1000)
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
	
	// 获取关注用户的openId
	public Result getOAAccessToken(String code) {
		Object openId=MemcachedUtil.getInstance().getData(code);
		if(null==openId){
			String res=HttpClientUtil.sendHTTPS(AppConstant.WEIXIN_OA_ACCESS_TOKEN_URL
					+"&appid=" + AppConstant.USERMP_APP_ID + "&secret=" + AppConstant.USERMP_SECRET_KEY 
					+"&code="+code);
			JSONObject json=JSONObject.fromObject(res);
			if(json.containsKey("openid")){
				openId=json.getString("openid");
				//保存code与openId的关系
				MemcachedUtil.getInstance().setData(code, openId);
			}
		}
		return putResult(DataUtil.mapOf("openId",openId));
	}

	// JS－SDK签名生成
	public Result getRequestSign(String url) {
		HashMap<String, String> params = new HashMap<String, String>();
		HashMap<String, String> resultMap = new HashMap<String, String>();
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
		return putResult(resultMap);
	}
	
	// 接送微信的服务器校验请求
	public String getUserTextOrEventData(HttpServletRequest request)throws Exception{
		//读取请求体
		StringBuilder response=new StringBuilder();
		BufferedReader reader=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String row=null;
		while((row=reader.readLine())!=null){
			response.append(row);
		}
		Logger.getLogger("file").info("<------接收到微信的服务器校验请求数据----->"+response.toString());
		//获取微信传递的参数
		String timestamp=request.getParameter("timestamp");
		String nonce=request.getParameter("nonce");
		String echostr=request.getParameter("echostr");
		String signature=request.getParameter("signature");
		String token=AppConstant.USERMP_REQUEST_TOKEN;
		//校验请求发送方是否是微信
		HashMap<String,String> paramMap=new HashMap<String,String>();
		paramMap.put(timestamp, timestamp);
		paramMap.put(nonce, nonce);
		paramMap.put(token, token);
		return SignUtil.sign(paramMap, "SHA1").equals(signature)?echostr:null;
	}
	
}
