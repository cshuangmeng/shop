package com.gaoling.shop.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.goods.dao.ShopDao;
import com.gaoling.shop.goods.pojo.Shop;
import com.gaoling.shop.goods.pojo.ShopFollower;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.UserService;

@Service
public class ShopService extends CommonService{

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopFollowerService shopFollowerService;
	@Autowired
	private UserService userService;
	
	//查询店铺详情
	public Result loadShopDetail(int id,String uuid){
		Shop shop=getShop(id);
		if(null!=shop){
			shop.setFollowers(shop.getFollowers()+shopFollowerService.queryFollowersOfShop(shop.getId()
					, ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState()));
		}
		if(StringUtils.isNotEmpty(uuid)){
			User user=userService.getUserByUUID(uuid);
			if(null!=user){
				ShopFollower follower=shopFollowerService.getShopFollower(user.getId(),shop.getId()
						,ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState());
				shop.getExtras().put("isFollowed", null!=follower?1:0);
			}
		}
		return putResult(shop);
	}
	
	//查询品牌店铺
	public Result loadShowShops(){
		return putResult(queryShops(DataUtil.mapOf("isShow",AppConstant.YES_OR_NO_ENUM.YES.getState())));
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
	
	//关注/取关店铺
	public Result followShop(int shopId,String uuid,int state){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//加载店铺
		Shop shop=getShop(shopId);
		if(null!=shop){
			//关注
			if(state==ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState()){
				//检查是否已关注
				ShopFollower follower=shopFollowerService.getShopFollower(user.getId(),shop.getId());
				if(null!=follower&&follower.getState()==ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState()){
					return putResult(AppConstant.USER_ALREADY_FOLLOWED);
				}
				if(null==follower){
					follower=new ShopFollower();
					follower.setUserId(user.getId());
					follower.setShopId(shop.getId());
					follower.setSubTime(DateUtil.nowDate());
					follower.setState(state);
					shopFollowerService.addShopFollower(follower);
				}else{
					follower.setSubTime(DateUtil.nowDate());
					follower.setState(ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState());
					shopFollowerService.updateShopFollower(follower);
				}
			}else if(state==ShopFollower.STATE_TYPE_ENUM.CANCEL.getState()){//取消关注
				ShopFollower follower=shopFollowerService.getShopFollower(user.getId()
						,shop.getId(),ShopFollower.STATE_TYPE_ENUM.FOLLOWED.getState());
				if(null==follower){
					return putResult(AppConstant.USER_NO_FOLLOWED);
				}
				follower.setCancelTime(DateUtil.nowDate());
				follower.setState(state);
				shopFollowerService.updateShopFollower(follower);
			}
		}
		return putResult();
	}
	
}
