package com.gaoling.admin.goods.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.goods.dao.GoodsTypeDao;
import com.gaoling.admin.goods.pojo.GoodsType;
import com.gaoling.admin.util.AppConstant;
import com.gaoling.admin.util.DataUtil;

@Service
public class GoodsTypeService {

	@Autowired
	private GoodsTypeDao goodsTypeDao;
	
	//加载商品类型
	public List<GoodsType> loadGoodsTypes(){
		return goodsTypeDao.queryGoodsTypes(DataUtil.mapOf("states",Arrays.asList(AppConstant.ACCOUNT_STATE_TYPE.ENABLED.getValue())));
	}
	
}
