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
	
	//查询店铺
	public Shop getShop(int id){
		List<Shop> shops=queryShops(DataUtil.mapOf("id",id));
		return shops.size()>0?shops.get(0):null;
	}
	
	//审核店铺
	public void examineShop(int shopId,int state){
		Shop shop=getShop(shopId);
		shop.setState(state);
		updateShop(shop);
	}
	
	//加载店铺
	public List<Shop> queryShops(Map<Object,Object> param){
		return shopDao.queryShops(param);
	}
	
	//加载店铺
	public List<Map<String,Object>> queryShopsToMap(Map<Object,Object> param){
		return shopDao.queryShopsToMap(param);
	}
	
	//新增店铺信息
	public void addShop(Shop shop){
		shopDao.addShop(shop);
	}
	
	//更新店铺信息
	public void updateShop(Shop shop){
		shopDao.updateShop(shop);
	}
	
	//保存商铺信息
	@Transactional
	public Result saveOrUpdateShop(Shop shop,MultipartFile headImgFile,MultipartFile[] infoImgFile)throws Exception{
		Shop old=shop.getId()>0?getShop(shop.getId()):null;
		//上传头像
		String fileName="";
		if(!headImgFile.isEmpty()){
			fileName="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
			fileName+=headImgFile.getOriginalFilename().substring(headImgFile.getOriginalFilename().lastIndexOf("."));
			OSSUtil.uploadFileToOSS(fileName, headImgFile.getInputStream());
		}
		shop.setHeadImg(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getHeadImg():"");
		//上传描述
		shop.setInfoImgs("");
		for(MultipartFile ii:infoImgFile){
			if(!ii.isEmpty()){
				String file="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
				file+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(file, ii.getInputStream());
				fileName+=StringUtils.isNotEmpty(fileName)?","+file:file;
			}
		}
		shop.setInfoImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getInfoImgs():"");
		shop.setIsShow(null!=old?old.getIsShow():0);
		shop.setAreaId(null!=old?old.getAreaId():0);
		shop.setFlowers(null!=old?old.getFlowers():0);
		shop.setFollowers(null!=old?old.getFollowers():0);
		shop.setAreaName(null!=old?old.getAreaName():"");
		shop.setState(null!=old?old.getState():0);
		shop.setCreateTime(null!=old?old.getCreateTime():DateUtil.nowDate());
		if(shop.getId()>0){
			updateShop(shop);
		}else{
			addShop(shop);
		}
		return putResult(shop);
	}
	
	//读取商铺详细信息
	public Shop getDetailShop(int id){
		Shop shop=getShop(id);
		if(null!=shop){
			shop.setHeadImg(shop.getFullHeadImg());
			shop.setInfoImgs(shop.getFullInfoImgs());
		}
		return shop;
	}
	
}
