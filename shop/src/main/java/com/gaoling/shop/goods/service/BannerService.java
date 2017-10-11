package com.gaoling.shop.goods.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
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
	public Result loadWKBanners(Integer index,String system) {
		List<JSONObject> result = getSonDicts("wk" + index + "_top_banner").stream().map(r -> {
			JSONObject json = JSONObject.fromObject(r.get("value"));
			if(StringUtils.isNotEmpty(system)&&system.toLowerCase().trim().contains("ios")){
				json.put("target", 3);
			}
			json.put("img", AppConstant.OSS_CDN_SERVER + json.get("img"));
			return json;
		}).collect(Collectors.toList());
		return putResult(result);
	}
	
	//上传Banner
	public Result uploadBanner(MultipartFile[] launch,MultipartFile[] top,MultipartFile[] bottom)throws Exception{
		//上传启动页图片
		if(null!=launch&&launch.length>0){
			int index=1;
			String parent="wk3_top_banner";
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			deleteDict(null, parentId);
			for(MultipartFile i:launch){
				if(!i.isEmpty()){
					String fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					fileName+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), fileName);
					String key=parent+index;
					insertDictValue(key, "{\"url\":\"\",\"target\":\"2\",\"img\":\""+fileName+"\"}"
						, parentId, DateUtil.nowDate(), 1,"其他banner配置", index);
					index++;
				}
			}
		}
		//上传顶部图片
		if(null!=top&&top.length>0){
			int index=1;
			String parent="wk1_top_banner";
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			deleteDict(null, parentId);
			for(MultipartFile i:top){
				if(!i.isEmpty()){
					String fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					fileName+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), fileName);
					//检查banner是否已存在
					String key=parent+index;
					insertDictValue(key, "{\"url\":\"\",\"target\":\"2\",\"img\":\""+fileName+"\"}"
							, parentId, DateUtil.nowDate(), 1,"其他banner配置", index);
					index++;
				}
			}
		}
		//上传底部图片
		if(null!=bottom&&bottom.length>0){
			int index=1;
			String parent="wk2_top_banner";
			Integer parentId=Integer.parseInt(getDicts(parent).get(0).get("id").toString());
			deleteDict(null, parentId);
			for(MultipartFile i:bottom){
				if(!i.isEmpty()){
					String fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
					fileName+=i.getOriginalFilename().substring(i.getOriginalFilename().lastIndexOf("."));
					OSSUtil.uploadFileToOSS(i.getInputStream(), fileName);
					//检查banner是否已存在
					String key=parent+index;
					deleteDict(null, parentId);
					insertDictValue(key, "{\"url\":\"\",\"target\":\"2\",\"img\":\""+fileName+"\"}"
							, parentId, DateUtil.nowDate(), 1,"其他banner配置", index);
					index++;
				}
			}
		}
		return putResult();
	}

}
