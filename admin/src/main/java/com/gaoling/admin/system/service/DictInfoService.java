package com.gaoling.admin.system.service;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.util.AppConstant;
import com.gaoling.admin.util.DataUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DictInfoService extends CommonService{
	
	private final String newOrderNoticePhonesConfigKey="new_order_notice_phones";

	//加载新订单通知手机号
	public JSONArray getNewOrderNoticePhones(){
		String value=getString(newOrderNoticePhonesConfigKey);
		return StringUtils.isNotEmpty(value)&&DataUtil.isJSONArray(value)?JSONArray.fromObject(value):new JSONArray();
	}
	
	//添加新订单通知手机号
	@SuppressWarnings("unchecked")
	public Result addNewNoticePhone(String contact,String telephone){
		//检查手机号码是否已存在
		String value=getString(newOrderNoticePhonesConfigKey);
		JSONArray array=new JSONArray();
		if(StringUtils.isNotEmpty(value)&&DataUtil.isJSONArray(value)){
			array=JSONArray.fromObject(value);
			Optional<JSONObject> ip=array.stream().filter(a->{
				JSONObject obj=JSONObject.fromObject(a);
				return !DataUtil.isEmpty(obj.get("telephone"))&&obj.getString("telephone").equals(telephone);
			}).findFirst();
			if(ip.isPresent()){
				Logger.getLogger("file").info("联系人"+telephone+"已存在");
				return putResult(AppConstant.USER_ALREADY_EXISTS);
			}
		}
		array.add(DataUtil.mapOf("contact", contact, "telephone", telephone));
		Map<String,Object> set=DataUtil.mapOf("value",array.toString());
		Map<String,Object> param=DataUtil.mapOf("name",newOrderNoticePhonesConfigKey);
		updateDictValue(set, param);
		return putResult();
	}
	
	//删除新订单通知手机号
	public Result deleteNoticePhone(String telephone){
		//检查手机号码是否已存在
		String value=getString(newOrderNoticePhonesConfigKey);
		if(StringUtils.isEmpty(value)||!DataUtil.isJSONArray(value)){
			Logger.getLogger("file").info("new_order_notice_phones 配置项不存在");
			return putResult(AppConstant.OPERATE_FAILURE);
		}
		JSONArray array=JSONArray.fromObject(value);
		JSONArray update=new JSONArray();
		for(int i=0;i<array.size();i++){
			JSONObject obj=array.getJSONObject(i);
			if(!DataUtil.isEmpty(obj.get("telephone"))&&!Arrays.asList(telephone.split(",")).contains(obj.getString("telephone"))){
				update.add(obj);
			}
		}
		Map<String,Object> set=DataUtil.mapOf("value",update.toString());
		Map<String,Object> param=DataUtil.mapOf("name",newOrderNoticePhonesConfigKey);
		updateDictValue(set, param);
		return putResult();
	}
	
}
