package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.shop.dao.AdcreativeDao;
import com.gaoling.admin.shop.entity.Adcreative;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;


@Service
public class AdcreativeService {

	@Autowired
	private AdcreativeDao adcreativeDao;
	
	//保存广告素材
	public int addAdcreative(Adcreative adcreative,MultipartFile imgFile)throws Exception{
		adcreative.setImgUrl(OSSUtil.uploadFileToOSS(new MultipartFile[]{imgFile}));
		adcreative.setCreateTime(DateUtil.getCurrentTime());
		return adcreativeDao.addAdcreative(adcreative);
	}
	
	//更新广告素材
	public void updateAdcreative(Adcreative adcreative,MultipartFile imgFile)throws Exception{
		String imgUrl=OSSUtil.uploadFileToOSS(new MultipartFile[]{imgFile});
		if(StringUtils.isNotEmpty(imgUrl)){
			adcreative.setImgUrl(imgUrl);
		}
		adcreative.setUpdateTime(DateUtil.getCurrentTime());
		adcreativeDao.updateAdcreative(adcreative);
	}
	
	//删除广告素材
	public void deleteAdcreative(int id){
		adcreativeDao.deleteAdcreative(id);
	}
	
	//删除广告组中的所有素材
	public void deleteAdcreativeByGroupId(int groupId){
		adcreativeDao.deleteAdcreativeByGroupId(groupId);
	}
	
	//加载广告素材信息
	public HashMap<String,Object> getAdcreative(int id){
		return adcreativeDao.getAdcreative(id);
	}
	
	//加载广告组中的所有素材
	public List<HashMap<String,Object>> loadAdcreativeByGroupId(int groupId){
		return adcreativeDao.loadAdcreativeByGroupId(groupId);
	}
	
}
