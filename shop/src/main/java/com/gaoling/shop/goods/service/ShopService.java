package com.gaoling.shop.goods.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.goods.dao.ShopDao;
import com.gaoling.shop.goods.pojo.Shop;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

@Service
public class ShopService extends CommonService{

	@Autowired
	private ShopDao shopDao;
	
	//查询店铺详情
	public Result loadShopDetail(int id){
		return putResult(getShop(id));
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
	
}
