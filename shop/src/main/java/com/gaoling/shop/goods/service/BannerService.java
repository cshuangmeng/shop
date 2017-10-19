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
		List<JSONObject> result = getSonDicts("wk" + index + "_top_banner"+(StringUtils.isNotEmpty(appType)?"_"+appType:"")).stream().map(r -> {
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
		String key=getString("banner_config_prefix");
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("key",key,"parentId",0));
		apps.stream().forEach(a->{
			a.put("banners",queryDicts(DataUtil.mapOf("parentId",Integer.parseInt(a.get("id").toString()))));
		});
		return putResult(apps);
	}
	
	//加载AppType
	public Result getAppList(){
		//加载APP
		String key=getString("banner_config_prefix");
		List<Map<String,Object>> apps=queryDicts(DataUtil.mapOf("key",key,"parentId",0));
		return putResult(apps);
	}
	
	//编辑AppType
	public Result editApp(int id,String name,String value,String remark){
		if(id>0){
			updateDictValue(id, name, value, remark);
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
	public Result editBanner(int id,MultipartFile[] file,String appType,String platform,String target,String url,String remark,int state){
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
