package com.gaoling.admin.goods.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.admin.goods.pojo.GoodsType;

@Repository
public interface GoodsTypeDao {

	List<GoodsType> queryGoodsTypes(@Param("param")Map<Object,Object> param);
	
}
