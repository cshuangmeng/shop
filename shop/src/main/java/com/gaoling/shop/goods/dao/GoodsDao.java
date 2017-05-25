package com.gaoling.shop.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.goods.pojo.Goods;

@Repository
public interface GoodsDao {

	List<Goods> queryGoods(@Param("param")Map<Object,Object> param);
	
}
