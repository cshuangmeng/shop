package com.gaoling.admin.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.goods.dao.ShopDao;
import com.gaoling.admin.goods.pojo.Shop;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;

@Service
public class ShopService extends CommonService{

	@Autowired
	private ShopDao shopDao;
	
	//查询店铺详情
	public Result loadShopDetail(int id,String uuid){
		Shop shop=getShop(id);
		return putResult(shop);
	}
	
	//查询店铺
	public Shop getShop(int id){
		List<Shop> shops=queryShops(DataUtil.mapOf("id",id));
		return shops.size()>0?shops.get(0):null;
	}
	
	//加载店铺
	public List<Shop> queryShops(Map<Object,Object> param){
		return shopDao.queryShops(param);
	}
	
	//保存商铺信息
	@Transactional
	public Result saveShopByUpload(Shop shop,MultipartFile headImgFile,MultipartFile[] infoImgFile)throws Exception{
		//上传头像
		String fileName="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
		fileName+=headImgFile.getOriginalFilename().substring(headImgFile.getOriginalFilename().lastIndexOf("."));
		OSSUtil.uploadFileToOSS(fileName, headImgFile.getInputStream());
		shop.setHeadImg(fileName);
		//上传描述
		shop.setInfoImgs("");
		for(MultipartFile ii:infoImgFile){
			if(!ii.isEmpty()){
				fileName="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
				fileName+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, ii.getInputStream());
				shop.setInfoImgs(StringUtils.isNotEmpty(shop.getInfoImgs())?shop.getInfoImgs()+","+fileName:fileName);
			}
		}
		shop.setCreateTime(DateUtil.nowDate());
		shopDao.addShop(shop);
		return putResult(shop);
	}
	
}
