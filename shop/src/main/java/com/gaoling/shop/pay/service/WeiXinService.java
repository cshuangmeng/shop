package com.gaoling.shop.pay.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.HttpClientUtil;
import com.gaoling.shop.common.MemcachedUtil;
import com.gaoling.shop.common.OSSUtil;
import com.gaoling.shop.common.SignUtil;
import com.gaoling.shop.goods.pojo.Goods;
import com.gaoling.shop.goods.pojo.Shop;
import com.gaoling.shop.goods.service.GoodsService;
import com.gaoling.shop.goods.service.ShopService;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.pojo.Tribe;
import com.gaoling.shop.tribe.service.TribeService;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.UserService;

import net.sf.json.JSONObject;

@Service
public class WeiXinService extends CommonService{
	
	@Autowired
	private UserService userService;
	@Autowired
	private TribeService tribeService;
	@Autowired
	private GoodsService goodsSevice;
	@Autowired
	private ShopService shopService;
	
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
	public Result getOAAccessToken(String code,int platform)throws Exception{
		String appId=AppConstant.USERMP_APP_ID;
		String secret=AppConstant.USERMP_SECRET_KEY;
		if(platform==AppConstant.PLATFORM_TYPE_ENUM.PC.getType()){
			appId=AppConstant.USERPC_APP_ID;
			secret=AppConstant.USERPC_SECRET_KEY;
		}
		String openId=MemcachedUtil.getInstance().getData(code,"");
		String unionId=null;
		String token=null;
		if(StringUtils.isEmpty(openId)){
			String res=HttpClientUtil.sendHTTPS(AppConstant.WEIXIN_OA_ACCESS_TOKEN_URL+"&appid="+appId+"&secret="+secret+"&code="+code);
			JSONObject json=JSONObject.fromObject(res);
			if(json.containsKey("openid")){
				openId=json.getString("openid");
				token=json.getString("access_token");
			}
		}else{
			unionId=openId.split(",")[1];
			openId=openId.split(",")[0];
			token=MemcachedUtil.getInstance().getData(openId, "");
		}
		//更新用户头像
		String url=AppConstant.WEIXIN_SNS_USERINFO_URL+"&access_token="+AppConstant.USERMP_ACCESS_TOKEN+"&openid="+openId;
		if(platform==AppConstant.PLATFORM_TYPE_ENUM.PC.getType()){
			url=AppConstant.PC_SNS_USERINFO_URL+"?access_token="+token+"&openid="+openId;
		}
		String response=HttpClientUtil.getNetWorkInfo(url, "");
		if(StringUtils.isNotEmpty(response)){
			JSONObject json=JSONObject.fromObject(response);
			if(!json.containsKey("errcode")){
				//查找用户信息
				User user=userService.getUserByUnionId(unionId);
				if(null!=user){
					//保存用户头像
					String fileName=StringUtils.isNotEmpty(user.getHeadImg())&&!user.getHeadImg().startsWith("http")
							?user.getHeadImg():"user/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6))+".jpg";
					if(!DataUtil.isEmpty(json.get("headimgurl"))){
						OSSUtil.uploadFileToOSS(new URL(json.getString("headimgurl")).openStream(), fileName);
					}
					if(!DataUtil.isEmpty(json.get("nickname"))){
						user.setNickname(json.getString("nickname"));
					}
					unionId=json.getString("unionid");
					user.setHeadImg(fileName);
					userService.updateUser(user);
				}
				//保存code与openId的关系
				MemcachedUtil.getInstance().setData(code, openId+","+unionId, getInteger("openId_code_save_mins"));
				//保存openId与accessToken的关系
				MemcachedUtil.getInstance().setData(openId, token, getInteger("openId_code_save_mins"));
			}
		}
		return putResult(DataUtil.mapOf("openId",openId,"unionId",unionId));
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
	
	// 获取分享配置
	public Result getShareConfig(HttpServletRequest request)throws Exception{
		//读取请求体
		String uuid=request.getParameter("uuid");
		int action=StringUtils.isNotEmpty(request.getParameter("action"))?Integer.parseInt(request.getParameter("action")):0;
		int goodsId=StringUtils.isNotEmpty(request.getParameter("goodsId"))?Integer.parseInt(request.getParameter("goodsId")):0;
		int shopId=StringUtils.isNotEmpty(request.getParameter("shopId"))?Integer.parseInt(request.getParameter("shopId")):0;
		//读取分享配置
		String value=getString("weixin_share_config_"+action,"");
		if(StringUtils.isNotEmpty(value)){
			JSONObject json=JSONObject.fromObject(value);
			//我的部落分享,有部落时才分享部落邀请页面
			if(action==AppConstant.MY_TRIBE_PAGE){
				json=JSONObject.fromObject(getString("weixin_share_config_"+AppConstant.STORE_DETAIL_PAGE));
				if(StringUtils.isNotEmpty(uuid)){
					User user=userService.getUserByUUID(uuid);
					if(null!=user){
						Tribe tribe=tribeService.getTribeByUserId(user.getId());
						//存在部落,跳转正确的分享页面
						if(null!=tribe){
							JSONObject j=JSONObject.fromObject(getString("weixin_share_config_"+action));
							if(j.containsKey("link")&&j.getString("link").contains("%s")){
								j.put("link", String.format(j.getString("link"), String.valueOf(tribe.getId())));
							}
							json=j;
						}
					}
				}
			}
			//拼装特殊分享内容
			String desc=json.getString("desc");
			if(action==AppConstant.GOODS_DETAIL_PAGE&&goodsId>0){//商品详情
				Goods goods=goodsSevice.getGoods(goodsId);
				desc=null!=goods?goods.getName():"";
				if(json.containsKey("link")&&json.getString("link").contains("%s")){
					json.put("link", String.format(json.getString("link"), String.valueOf(goods.getId())));
				}
			}else if((action==AppConstant.SHOP_DETAIL_PAGE||action==AppConstant.COOPERATOR_DETAIL_PAGE)&&shopId>0){//商铺详情
				Shop shop=shopService.getShop(shopId);
				desc=null!=shop?shop.getName():"";
				if(json.containsKey("link")&&json.getString("link").contains("%s")){
					json.put("link", String.format(json.getString("link"), String.valueOf(shop.getId())));
				}
			}
			desc=desc.contains("%s")?String.format(json.getString("desc"), desc):desc;
			//检查是否需要重写URL
			String link=json.getString("link");
			if(json.containsKey("redirect")&&json.getInt("redirect")>0){
				link=String.format(getString("weixin_mp_code_url"), getString("weixin_mp_app_id"), URLEncoder.encode(link, "UTF-8"));
			}
			return putResult(DataUtil.mapOf("title",json.get("title"),"desc",desc,"link",link,"imgUrl",json.get("imgUrl")));
		}
		return putResult(AppConstant.OPERATE_FAILURE);
	}
	
}
