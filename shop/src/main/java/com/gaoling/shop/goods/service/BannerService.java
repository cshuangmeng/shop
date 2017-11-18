package com.gaoling.shop.goods.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.OSSUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class BannerService extends CommonService {
	
	//加载Banner
	public Result loadWKBanners(Integer index,String system,String appType) {
		String name="wk" + index + "_top_banner"+(StringUtils.isNotEmpty(appType)?"_"+appType:"");
		List<JSONObject> result = getSonDicts(name).stream().filter(d->Integer.parseInt(d.get("state").toString())==1).map(r -> {
			JSONObject json = JSONObject.fromObject(r.get("value"));
			if(StringUtils.isNotEmpty(system)&&system.toLowerCase().contains("ios")){
				json.put("url", json.get("iosUrl"));
				json.put("target", json.get("iosTarget"));
				json.put("img", AppConstant.OSS_CDN_SERVER + json.get("iosImg"));
			}else{
				json.put("url", json.get("androidUrl"));
				json.put("target", json.get("androidTarget"));
				json.put("img", AppConstant.OSS_CDN_SERVER + json.get("androidImg"));
			}
			json.put("androidImg", AppConstant.OSS_CDN_SERVER + json.get("androidImg"));
			json.put("iosImg", AppConstant.OSS_CDN_SERVER + json.get("iosImg"));
			return json;
		}).collect(Collectors.toList());
		return putResult(result);
	}
	
	//加载Banner
	public Result getBannerList(){
		//加载APP
		String key="banner";
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("parentName",key));
		apps.stream().forEach(a->{
			a.put("host", AppConstant.OSS_CDN_SERVER);
			a.put("banners",queryDicts(DataUtil.mapOf("parentId",Integer.parseInt(a.get("id").toString()),"states",Arrays.asList(0,1))));
		});
		return putResult(apps);
	}
	
	//加载AppType
	public Result getAppList(){
		//加载APP
		String key="banner";
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("parentName",key));
		return putResult(apps);
	}
	
	//编辑AppType
	public Result editApp(int id,String name,String value,Integer state,String remark){
		if(id>0){
			Map<String,Object> set=DataUtil.mapOf("name",name,"value",value.toString(),"state",state,"remark",remark);
			Map<String,Object> param=DataUtil.mapOf("id",id);
			updateDictValue(set,param);
		}else{
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
	public Result editBanner(int id,MultipartFile file,String appType,int index
			,String platform,String target,String url,String remark,int state){
		try {
			if(id>0){
				List<Map<String,Object>> dicts=queryDicts(DataUtil.mapOf("id",id));
				if(dicts.size()>0){
					Map<String,Object> dict=dicts.get(0);
					String i=dict.get("name").toString().split("_")[dict.get("name").toString().split("_").length-1];
					String name="banner_"+appType+"_"+platform+"_"+index+"_"+i;
					String img="";
					JSONObject value=JSONObject.fromObject(dict.get("value").toString());
					if(!file.isEmpty()){
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
				List<Map<String,Object>> map=queryDicts(DataUtil.mapOf("name","banner_"+appType));
				int parentId=Integer.valueOf(map.get(0).get("id").toString());
				map=queryDicts(DataUtil.mapOf("parentId",parentId));
				int lastIndex=map.stream().map(a->{
					String[] array=a.get("name").toString().split("_");
					return Integer.valueOf(array[array.length-1]);
				}).max((a,b)->a-b).get();
				String name="banner_"+appType+"_"+platform+"_"+index+"_"+(lastIndex+1);
				String img="";
				if(!file.isEmpty()){
					img="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					img+=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(file.getInputStream(), img);
				}
				Map<String,Object> obj=DataUtil.mapOf("img",img,"target",target,"url",url);
				insertDictValue(name, JSONObject.fromObject(obj).toString(), parentId, DateUtil.nowDate(), state, remark, parentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return putResult();
	}
	
	//操作APP状态
	public Result editAppState(int id,int state){
		if(id>0){
			updateDictValue(id, state);
			//同步更新子项状态
			queryDicts(DataUtil.mapOf("parentId",id)).stream().forEach(s->updateDictValue(Integer.parseInt(s.get("id").toString()), state));
		}
		return putResult();
	}
	
	//操作Banner状态
	public Result editBannerState(int id,int state){
		if(id>0){
			updateDictValue(id, state);
		}
		return putResult();
	}
	
	//上传Banner
	@Transactional
	public Result uploadBanner(String appType,MultipartFile[] launch,MultipartFile[] top,MultipartFile[] bottom
			,String[] target,String[] url,String[] key)throws Exception{
		int seq=0;
		//上传启动页图片
		uploadBannerImg(appType, launch, target, url, key[0], seq);
		seq+=launch.length;
		//上传顶部图片
		uploadBannerImg(appType, top, target, url, key[1], seq);
		seq+=top.length;
		//上传底部图片
		uploadBannerImg(appType, bottom, target, url, key[2], seq);
		return putResult();
	}
	
	//上传Banner
	@Transactional
	public void uploadBannerImg(String appType,MultipartFile[] file,String[] target
			,String[] url,String key,int seq)throws Exception{
		//上传启动页图片
		if(null!=file&&file.length>0){
			int index=1;
			String parent=key+"_"+appType;
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			if(Arrays.asList(file).stream().filter(f->!f.isEmpty()).findFirst().isPresent()){
				deleteDict(null, parentId);
			}
			for(int n=0;n<file.length;n++){
				MultipartFile i=file[n];
				String androidImg="";
				String androidTarget="";
				String androidUrl="";
				if(!i.isEmpty()){
					//android图片
					androidImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					androidImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), androidImg);
					androidTarget=target[seq];
					androidUrl=url[seq];
				}
				//ios图片
				i=file[++n];
				seq++;
				String iosImg="";
				String iosTarget="";
				String iosUrl="";
				if(!i.isEmpty()){
					iosImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					iosImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), iosImg);
					iosTarget=target[seq];
					iosUrl=url[seq];
				}
				//保存信息
				String name=parent+index;
				if(StringUtils.isNotEmpty(androidImg)||StringUtils.isNotEmpty(iosImg)){
					insertDictValue(name, "{\"androidUrl\":\""+androidUrl+"\",\"iosUrl\":\""+iosUrl+"\",\"androidTarget\":\""
							+androidTarget+"\",\"iosTarget\":\""+iosTarget+"\",\"androidImg\":\""+androidImg+"\",\"iosImg\":\""
							+iosImg+"\"}", parentId, DateUtil.nowDate(), 1,"banner配置", index++);
				}
				seq++;
			}
		}
	}

}
