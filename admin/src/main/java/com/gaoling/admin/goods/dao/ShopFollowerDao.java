package com.gaoling.admin.goods.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopFollowerDao {

	int queryFollowersOfShop(@Param("shopId")int shopId,@Param("state")int state);
	
}
