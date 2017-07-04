package com.gaoling.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.goods.pojo.Goods;

@Repository
public interface GoodsDao {

	List<Goods> queryGoods(@Param("param")Map<Object,Object> param);
	List<Map<String,Object>> queryGoodsToMap(@Param("param")Map<Object,Object> param);
	void addGoods(Goods goods);
	void updateGoods(Goods goods);
	
}
