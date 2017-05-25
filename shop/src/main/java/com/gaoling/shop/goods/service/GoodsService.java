package com.gaoling.shop.goods.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.goods.dao.GoodsDao;
import com.gaoling.shop.goods.pojo.Goods;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

@Service
public class GoodsService extends CommonService{

	@Autowired
	private GoodsDao goodsDao;
	
	//查询商品详情
	public Result loadGoodsDetail(int id){
		return putResult(getGoods(id));
	}
	
	//查询商品列表
	public Result loadGoods(Map<Object,Object> param){
		return putResult(queryGoods(param));
	}
	
	//查询商品
	public Goods getGoods(int id){
		List<Goods> goods=queryGoods(DataUtil.mapOf("id",id));
		return goods.size()>0?goods.get(0):null;
	}
	
	//加载商品
	public List<Goods> queryGoods(Map<Object,Object> param){
		return goodsDao.queryGoods(param);
	}
	
}
