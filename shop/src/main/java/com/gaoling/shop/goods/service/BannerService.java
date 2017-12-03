package com.gaoling.shop.goods.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.OSSUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BannerService extends CommonService {
	
	private final static int ROLE_ADMIN=1;
	
	//用户登录
	@SuppressWarnings("unchecked")
	public Result bannerUserLogin(String username,String password){
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			Logger.getLogger("file").info("用户名或密码为空,username="+username+",password="+password);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		Map<String,Object> dictInfo=queryDicts(DataUtil.mapOf("name","banner_user")).get(0);
		List<Map<String,Object>> json=JSONArray.fromObject(dictInfo.get("value"));
		Optional<Map<String,Object>> ip=json.stream().filter(u->{
			JSONObject o=JSONObject.fromObject(u);
			return o.getString("username").equals(username)&&o.getString("password").equals(password);
		}).findFirst();
		if(!ip.isPresent()){
			Logger.getLogger("file").info("用户名或密码错误,username="+username+",password="+password);
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Map<String,Object> obj=ip.get();
		obj.put("token", DataUtil.buildUUID());
		obj.put("timestamp", DateUtil.nowDate().getTime());
		Map<String,Object> set=DataUtil.mapOf("value",JSONArray.fromObject(json).toString());
		Map<String,Object> param=DataUtil.mapOf("id",Integer.valueOf(dictInfo.get("id").toString()));
		updateDictValue(set, param);
		return putResult(DataUtil.mapOf("token",obj.get("token"),"role",obj.get("role")));
	}
	
	//校验登录token是否正确
	@SuppressWarnings("unchecked")
	public Result isTokenCollect(String token){
		if(StringUtils.isEmpty(token)){
			Logger.getLogger("file").info("token为空,token="+token);
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		Map<String,Object> dictInfo=queryDicts(DataUtil.mapOf("name","banner_user")).get(0);
		JSONArray json=JSONArray.fromObject(dictInfo.get("value"));
		Optional<JSONObject> ip=json.stream().filter(u->{
			JSONObject o=JSONObject.fromObject(u);
			return !DataUtil.isEmpty(o.get("token"))&&o.getString("token").equals(token);
		}).findFirst();
		if(!ip.isPresent()){
			Logger.getLogger("file").info("用户未登录或token不正确,token="+token);
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		JSONObject obj=ip.get();
		if(obj.getLong("timestamp")+obj.getInt("expire")*60*1000<=DateUtil.nowDate().getTime()){
			Logger.getLogger("file").info("token已过期,token="+token+",timestamp="+obj.get("timestamp")+",expire="+obj.get("expire"));
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		return putResult(obj);
	}
	
	//加载Banner
	public Result loadWKBanners(Integer index,String system,String appType) {
		List<Map<String,Object>> result=getSonDicts("banner_"+appType).stream().filter(d->{
			String name="banner_"+appType+"_"+system+"_"+index+"_";
			return Integer.parseInt(d.get("state").toString())==1&&d.get("name").toString().startsWith(name);
		}).sorted((a,b)->Integer.parseInt(a.get("orderIndex").toString())-Integer.parseInt(b.get("orderIndex").toString())).collect(Collectors.toList());
		List<JSONObject> banners=result.stream().map(r->{
			JSONObject json = JSONObject.fromObject(r.get("value"));
			json.put("img", AppConstant.OSS_CDN_SERVER + json.get("img"));
			return json;
		}).collect(Collectors.toList());
		return putResult(banners);
	}
	
	//加载Banner
	public Result getBannerList(String token){
		//加载APP
		Result result=isTokenCollect(token);
		if(null==result.getData()){
			return result;
		}
		JSONObject obj=JSONObject.fromObject(result.getData());
		String key="banner";
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("parentName",key)).stream().filter(a->obj.getInt("role")==ROLE_ADMIN
				||a.get("name").toString().equals(obj.getString("banner"))).collect(Collectors.toList());
		apps.stream().forEach(a->{
			a.put("host", AppConstant.OSS_CDN_SERVER);
			a.put("banners",queryDicts(DataUtil.mapOf("parentId",Integer.parseInt(a.get("id").toString()),"states",Arrays.asList(0,1))));
		});
		return putResult(apps);
	}
	
	//加载AppType
	public Result getAppList(String token){
		//加载APP
		Result result=isTokenCollect(token);
		if(null==result.getData()){
			return result;
		}
		JSONObject obj=JSONObject.fromObject(result.getData());
		String key="banner";
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("parentName",key,"states",Arrays.asList(0,1))).stream().filter(s->{
			return obj.getInt("role")==ROLE_ADMIN||obj.getString("banner").equals(s.get("name").toString());
		}).collect(Collectors.toList());
		return putResult(apps);
	}
	
	//编辑AppType
	public Result editApp(String token,Integer id,String name,String value,Integer state,String remark){
		Result result=isTokenCollect(token);
		if(null==result.getData()){
			return result;
		}
		JSONObject obj=JSONObject.fromObject(result.getData());
		if(null!=id&&id>0){
			if(obj.getInt("role")!=ROLE_ADMIN&&!obj.getString("banner").equals(name)){
				return putResult(AppConstant.CHECK_CODE_INCORRECT);
			}
			Map<String,Object> set=DataUtil.mapOf("name",name,"value",value.toString(),"state",state,"remark",remark);
			Map<String,Object> param=DataUtil.mapOf("id",id);
			updateDictValue(set,param);
		}else{
			if(obj.getInt("role")!=ROLE_ADMIN){
				return putResult(AppConstant.CHECK_CODE_INCORRECT);
			}
			String key="banner";
			List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("name",key));
			if(apps.size()>0){
				int parentId=Integer.parseInt(apps.get(0).get("id").toString());
				insertDictValue(name, value, parentId, DateUtil.nowDate(), 1, remark, 0);
			}
		}
		return putResult();
	}
	
	//编辑Banner
	public Result editBanner(String token,Integer id,MultipartFile file,String appType,Integer index
			,String platform,String target,String url,String remark,Integer state){
		try {
			Result result=isTokenCollect(token);
			if(null==result.getData()){
				return result;
			}
			JSONObject obj=JSONObject.fromObject(result.getData());
			if(null!=id&&id>0){
				List<Map<String,Object>> dicts=queryDicts(DataUtil.mapOf("id",id));
				if(dicts.size()>0){
					Map<String,Object> dict=dicts.get(0);
					if(obj.getInt("role")!=ROLE_ADMIN&&!obj.getString("banner").equals(appType)){
						return putResult(AppConstant.CHECK_CODE_INCORRECT);
					}
					String i=dict.get("name").toString().split("_")[dict.get("name").toString().split("_").length-1];
					String name=appType+"_"+platform+"_"+index+"_"+i;
					String img="";
					JSONObject value=JSONObject.fromObject(dict.get("value").toString());
					if(null!=file&&!file.isEmpty()){
						img="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
						img+=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
						OSSUtil.uploadFileToOSS(file.getInputStream(), img);
						value.put("img", img);
					}
					value.put("target", target);
					value.put("url", url);
					Map<String,Object> set=DataUtil.mapOf("name",name,"value",value.toString(),"state",state,"remark",remark);
					Map<String,Object> param=DataUtil.mapOf("id",id);
					updateDictValue(set, param);
				}
			}else{
				List<Map<String,Object>> map=queryDicts(DataUtil.mapOf("name",appType));
				int parentId=Integer.valueOf(map.get(0).get("id").toString());
				if(obj.getInt("role")!=ROLE_ADMIN&&!obj.getString("banner").equals(appType)){
					return putResult(AppConstant.CHECK_CODE_INCORRECT);
				}
				map=queryDicts(DataUtil.mapOf("parentId",parentId)).stream().filter(a->{
					String name=a.get("name").toString();
					return name.startsWith(appType+"_"+platform+"_"+index+"_");
				}).collect(Collectors.toList());
				int lastIndex=map.size()>0?map.stream().map(a->{
					String[] array=a.get("name").toString().split("_");
					return Integer.valueOf(array[array.length-1]);
				}).max((a,b)->a-b).get():1;
				String name=appType+"_"+platform+"_"+index+"_"+(lastIndex+1);
				String img="";
				if(!file.isEmpty()){
					img="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					img+=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(file.getInputStream(), img);
				}
				Map<String,Object> value=DataUtil.mapOf("img",img,"target",target,"url",url);
				insertDictValue(name, JSONObject.fromObject(value).toString(), parentId, DateUtil.nowDate(), state, remark, parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return putResult();
	}
	
	//操作APP状态
	public Result editAppState(String token,int id,int state){
		if(id>0){
			Result result=isTokenCollect(token);
			if(null==result.getData()){
				return result;
			}
			JSONObject obj=JSONObject.fromObject(result.getData());
			String banner=queryDicts(DataUtil.mapOf("id",id)).get(0).get("name").toString();
			if(obj.getInt("role")!=ROLE_ADMIN&&!obj.getString("banner").equals(banner)){
				return putResult(AppConstant.CHECK_CODE_INCORRECT);
			}
			if(state==2){//删除
				deleteDict(null,null, id);
				deleteDict(id,null, null);
			}else{
				updateDictValue(id, state);
				//同步更新子项状态
				queryDicts(DataUtil.mapOf("parentId",id)).stream().forEach(s->updateDictValue(Integer.parseInt(s.get("id").toString()), state));
			}
		}
		return putResult();
	}
	
	//操作Banner状态
	public Result editBannerState(String token,int id,int state){
		if(id>0){
			Result result=isTokenCollect(token);
			if(null==result.getData()){
				return result;
			}
			JSONObject obj=JSONObject.fromObject(result.getData());
			Integer parentId=Integer.valueOf(queryDicts(DataUtil.mapOf("id",id)).get(0).get("parentId").toString());
			String banner=queryDicts(DataUtil.mapOf("id",parentId)).get(0).get("name").toString();
			if(obj.getInt("role")!=ROLE_ADMIN&&!obj.getString("banner").equals(banner)){
				return putResult(AppConstant.CHECK_CODE_INCORRECT);
			}
			updateDictValue(id, state);
		}
		return putResult();
	}
	
}
