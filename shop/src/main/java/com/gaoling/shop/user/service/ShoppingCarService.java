package com.gaoling.shop.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.goods.pojo.Goods;
import com.gaoling.shop.goods.service.GoodsService;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.dao.ShoppingCarDao;
import com.gaoling.shop.user.pojo.ShoppingCar;
import com.gaoling.shop.user.pojo.User;

@Service
public class ShoppingCarService extends CommonService{

	@Autowired
	private ShoppingCarDao shoppingCarDao;
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsService goodsService;
	
	//查询用户购物车信息
	public Result queryMyShoppingCar(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		List<Map<String,Object>> goods=shoppingCarDao.queryMyShoppingCar(user.getId());
		for(Map<String,Object> g:goods){
			StringBuffer urls = new StringBuffer();
			if (!DataUtil.isEmpty(g.get("headImg"))) {
				for (String images : g.get("headImg").toString().split(",")) {
					urls.append(urls.length() > 0 ? "," : "");
					urls.append(AppConstant.OSS_CDN_SERVER + images);
				}
			}
			g.put("headImg", urls.toString().length() > 0 ? urls.toString() : g.get("headImg").toString());
		}
		return putResult();
	}
	
	//添加购物车
	public Result addGoodsToShoppingCar(int goodsId,String uuid,int amount){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		amount=amount<=0?1:amount;
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//检查商品是否存在
		Goods goods=goodsService.getGoods(goodsId);
		if(null==goods){
			return putResult(AppConstant.GOODS_NOT_EXISTS);
		}
		//是否已添加
		List<ShoppingCar> cars=shoppingCarDao.queryShoppingCars(DataUtil.mapOf("userId",user.getId(),"goodsId",goods.getId()));
		ShoppingCar car=null;
		if(cars.size()>0){
			car=cars.get(0);
			shoppingCarDao.updateAmountOfShoppingCar(user.getId(), goods.getId(), amount);
		}else{
			car=new ShoppingCar();
			car.setGoodsId(goods.getId());
			car.setUserId(user.getId());
			car.setAmount(amount);
			car.setJoinTime(DateUtil.nowDate());
			shoppingCarDao.addGoodsToShoppingCar(car);
		}
		return putResult();
	}
	
	//移除购物车
	public Result removeGoodsFromShoppingCar(String uuid,int goodsId){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		shoppingCarDao.removeGoodsFromShoppingCar(user.getId(), goodsId);
		return putResult();
	}
	
}
