package com.gaoling.admin.system.service;

import java.net.URLDecoder;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.HttpClientUtil;
import com.gaoling.admin.util.RedisUtil;

import net.sf.json.JSONObject;

@Service
public class WeiXinService extends CommonService{

	//长链接转短链接
	public String longUrl2ShortUrl(String longUrl){
		try {
			String token=RedisUtil.get("usermp_access_token");
			String url=String.format(getString("weixin_api_shorturl"), token);
			JSONObject json=JSONObject.fromObject(DataUtil.mapOf("action","long2short","long_url",longUrl));
			String response=HttpClientUtil.sendHTTPSWithXML(url, json.toString(), ContentType.TEXT_PLAIN);
			if(DataUtil.isJSONObject(response)){
				JSONObject config=JSONObject.fromObject(response);
				if(config.getInt("errcode")==0){
					return URLDecoder.decode(config.getString("short_url"), "UTF-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
