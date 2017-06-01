package com.gaoling.shop.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.goods.pojo.ShopFollower;

@Repository
public interface ShopFollowerDao {

	List<ShopFollower> queryShopFollowers(@Param("param")Map<Object,Object> param);
	int queryFollowersOfShop(@Param("shopId")int shopId,@Param("state")int state);
	void addShopFollower(ShopFollower shopFollower);
	void updateShopFollower(ShopFollower shopFollower);
	
}
