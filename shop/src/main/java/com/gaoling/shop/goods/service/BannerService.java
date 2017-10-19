package com.gaoling.shop.goods.service;

import java.util.Arrays;
import java.util.List;
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
				json.put("url", json.get("ios"));
				json.put("img", AppConstant.OSS_CDN_SERVER + json.get("iosImg"));
			}else{
				json.put("url", json.get("android"));
				json.put("img", AppConstant.OSS_CDN_SERVER + json.get("androidImg"));
			}
			json.put("androidImg", AppConstant.OSS_CDN_SERVER + json.get("androidImg"));
			json.put("iosImg", AppConstant.OSS_CDN_SERVER + json.get("iosImg"));
			return json;
		}).collect(Collectors.toList());
		return putResult(result);
	}
	
	//上传Banner
	@Transactional
	public Result uploadBanner(String appType,MultipartFile[] launch,MultipartFile[] top,MultipartFile[] bottom
			,String[] target,String[] android,String[] ios,String[] key)throws Exception{
		//上传启动页图片
		int seq=0;
		if(null!=launch&&launch.length>0){
			int index=1;
			String parent=key[0]+"_"+appType;
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			if(Arrays.asList(launch).stream().filter(f->!f.isEmpty()).findFirst().isPresent()){
				deleteDict(null, parentId);
			}
			for(int n=0;n<launch.length;n++){
				MultipartFile i=launch[n];
				String androidImg="";
				if(!i.isEmpty()){
					//android图片
					androidImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					androidImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), androidImg);
				}
				//ios图片
				i=launch[++n];
				String iosImg="";
				if(!i.isEmpty()){
					iosImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					iosImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), iosImg);
				}
				//保存信息
				String name=parent+index;
				if(StringUtils.isNotEmpty(androidImg)||StringUtils.isNotEmpty(iosImg)){
					insertDictValue(name, "{\"android\":\""+android[seq]+"\",\"ios\":\""+ios[seq]+"\",\"target\":\""
							+target[seq]+"\",\"androidImg\":\""+androidImg+"\",\"iosImg\":\""+iosImg+"\"}", parentId, DateUtil.nowDate(), 1,"启动页banner配置", index);
				}
				index++;
				seq++;
			}
		}
		//上传顶部图片
		if(null!=top&&top.length>0){
			int index=1;
			String parent=key[1]+"_"+appType;
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			if(Arrays.asList(top).stream().filter(f->!f.isEmpty()).findFirst().isPresent()){
				deleteDict(null, parentId);
			}
			for(int n=0;n<top.length;n++){
				MultipartFile i=top[n];
				//android图片
				String androidImg="";
				if(!i.isEmpty()){
					androidImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					androidImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), androidImg);
				}
				//ios图片
				i=top[++n];
				String iosImg="";
				if(!i.isEmpty()){
					iosImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					iosImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), iosImg);
				}
				//保存信息
				String name=parent+index;
				if(StringUtils.isNotEmpty(androidImg)||StringUtils.isNotEmpty(iosImg)){
					insertDictValue(name, "{\"android\":\""+android[seq]+"\",\"ios\":\""+ios[seq]+"\",\"target\":\""
							+target[seq]+"\",\"androidImg\":\""+androidImg+"\",\"iosImg\":\""+iosImg+"\"}", parentId, DateUtil.nowDate(), 1,"顶部banner配置", index);
				}
				index++;
				seq++;
			}
		}
		//上传底部图片
		if(null!=bottom&&bottom.length>0){
			int index=1;
			String parent=key[2]+"_"+appType;
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			if(Arrays.asList(bottom).stream().filter(f->!f.isEmpty()).findFirst().isPresent()){
				deleteDict(null, parentId);
			}
			for(int n=0;n<bottom.length;n++){
				MultipartFile i=bottom[n];
				//android图片
				String androidImg="";
				if(!i.isEmpty()){
					androidImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					androidImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), androidImg);
				}
				//ios图片
				i=bottom[++n];
				String iosImg="";
				if(!i.isEmpty()){
					iosImg="other/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					iosImg+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), iosImg);
				}
				//保存信息
				String name=parent+index;
				deleteDict(null, parentId);
				if(StringUtils.isNotEmpty(androidImg)||StringUtils.isNotEmpty(iosImg)){
					insertDictValue(name, "{\"android\":\""+android[seq]+"\",\"ios\":\""+ios[seq]+"\",\"target\":\""
							+target[seq]+"\",\"androidImg\":\""+androidImg+"\",\"iosImg\":\""+iosImg+"\"}", parentId, DateUtil.nowDate(), 1,"底部banner配置", index);
				}
				index++;
				seq++;
			}
		}
		return putResult();
	}

}
