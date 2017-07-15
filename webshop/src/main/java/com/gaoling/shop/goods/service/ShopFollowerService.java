package com.gaoling.shop.goods.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.goods.dao.ShopFollowerDao;
import com.gaoling.shop.goods.pojo.ShopFollower;
import com.gaoling.shop.system.service.CommonService;

@Service
public class ShopFollowerService extends CommonService{

	@Autowired
	private ShopFollowerDao shopFollowerDao;
	
	//加载用户的关注记录
	public ShopFollower getShopFollower(int userId,int shopId,int state){
		List<ShopFollower> followers=queryShopFollowers(DataUtil.mapOf("userId",userId,"shopId",shopId,"state",state));
		return followers.size()>0?followers.get(0):null;
	}
	
	//加载用户的关注记录
	public ShopFollower getShopFollower(int userId,int shopId){
		List<ShopFollower> followers=queryShopFollowers(DataUtil.mapOf("userId",userId,"shopId",shopId));
		return followers.size()>0?followers.get(0):null;
	}
	
	//查询关注记录
	public List<ShopFollower> queryShopFollowers(Map<Object,Object> param){
		return shopFollowerDao.queryShopFollowers(param);
	}
	
	//加载门店总的关注数
	public int queryFollowersOfShop(int shopId,int state){
		return shopFollowerDao.queryFollowersOfShop(shopId, state);
	}
	
	//添加关注记录
	public void addShopFollower(ShopFollower shopFollower){
		shopFollowerDao.addShopFollower(shopFollower);
	}
	
	//更新关注记录
	public void updateShopFollower(ShopFollower shopFollower){
		shopFollowerDao.updateShopFollower(shopFollower);
	}
	
}
