package com.gaoling.webshop.task;

import java.util.HashMap;

import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.common.DateUtil;
import com.gaoling.webshop.common.HttpClientUtil;

import net.sf.json.JSONObject;

public class NotifyTask implements Runnable{

	private String accessToken=null;
	private HashMap<String,Object> params=null;
	
	public NotifyTask(String accessToken,HashMap<String,Object> params){
		this.accessToken=accessToken;
		this.params=params;
	}
	
	public void run(){
		String url=null;
		String data=null;
		try {
			//拼装通知参数
			HashMap<String,Object> nofity=new HashMap<String,Object>();
			HashMap<String,Object> props=new HashMap<String,Object>();
			nofity.put("touser", params.get("openId"));
			nofity.put("template_id",params.get("tempId"));
			HashMap<String,Object> values=new HashMap<String,Object>();
			values.put("value",params.get("title"));
			values.put("color","#173177");
			props.put("first", values);
			for(int i=1;i<=5;i++){
				if(params.containsKey("keyword"+i)){
					props.put("keyword"+i, DataUtil.saveObjectToMap("value", params.get("keyword"+i)));
				}
			}
			props.put("remark", DataUtil.saveObjectToMap("value", params.get("remark")));
			nofity.put("data", props);
			//发送通知
			url=AppConstant.WEIXIN_TEMPLATE_SEND+"?access_token="+accessToken;
			data=JSONObject.fromObject(nofity).toString();
			String response=HttpClientUtil.sendHTTPSWithXML(url, data, ContentType.APPLICATION_JSON);
			Logger.getLogger("msg").info(DateUtil.getCurrentTime()+"|"+url+"|"+data+"|"+response);;
		} catch (Exception e) {
			Logger.getLogger("msg").error(DateUtil.getCurrentTime()+"|"+url+"|"+data+"|"+e.getMessage());;
			e.printStackTrace();
		}
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}
	
}
