package com.gaoling.shop.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.goods.pojo.Shop;

@Repository
public interface ShopDao {

	List<Shop> queryShops(@Param("param")Map<Object,Object> param);
	void addShop(Shop shop);
	
}
