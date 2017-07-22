package com.kcx.support.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kcx.support.pojo.UserPurchase;

@Repository
public interface UserPurchaseMapper {

	List<UserPurchase> queryUserPurchases(@Param("param")Map<Object,Object> param);
	void addUserPurchase(UserPurchase userPurchase);
	void updateUserPurchase(UserPurchase userPurchase);
	
}
