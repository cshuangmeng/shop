package com.gaoling.shop.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.user.pojo.ShoppingCar;

@Repository
public interface ShoppingCarDao {

	List<Map<String,Object>> queryMyShoppingCar(@Param("userId")int userId,@Param("goodsIds")List<Integer> goodsIds);
	List<ShoppingCar> queryShoppingCars(@Param("param")Map<Object,Object> param);
	void addGoodsToShoppingCar(ShoppingCar car);
	void removeGoodsFromShoppingCar(@Param("userId")int userId,@Param("goodsIds")List<Integer> goodsIds);
	void updateAmountOfShoppingCar(@Param("userId")int userId,@Param("goodsId")int goodsId,@Param("amount")int amount);
	
}
