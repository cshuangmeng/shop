package com.gaoling.shop.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<Map<String,Object>> goods=shoppingCarDao.queryMyShoppingCar(user.getId(),null);
		for(Map<String,Object> g:goods){
			StringBuffer urls = new StringBuffer();
			if (!DataUtil.isEmpty(g.get("headImg"))) {
				for (String images : g.get("headImg").toString().split(",")) {
					urls.append(urls.length() > 0 ? "," : "");
					urls.append(AppConstant.OSS_CDN_SERVER + images);
				}
			}
			g.put("headImg", urls.toString().length() > 0 ? urls.toString() : g.get("headImg").toString());
			g.put("buyEnable", 1);
			//检查商品是否可购买
			int buyAmount=goodsService.getEnableBuyAmount(user.getId(), Integer.parseInt(g.get("goodsId").toString()));
			if(buyAmount>0){
				g.put("buyAmount", buyAmount);
			}else if(buyAmount==0){
				g.put("buyEnable", 0);
				g.put("buyAmount", buyAmount);
			}
		}
		//按照店铺分组
		Map<String,List<Map<String,Object>>> result=goods.stream().collect(Collectors
				.groupingBy(r->r.get("shopName")+"_"+r.get("shopId"),Collectors.toList()));
		List<Map<Object,Object>> car=result.entrySet().stream().map(k->DataUtil.mapOf("shopName",k.getKey().split("_")[0]
				,"shopId",k.getKey().split("_")[1],"goods",k.getValue())).collect(Collectors.toList());
		return putResult(car);
	}
	
	//一次添加多个商品
	@Transactional
	public Result addMultiGoodsToShoppingCar(String items,String uuid){
		if(StringUtils.isEmpty(items)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		String[] data=null;
		for(String item:items.split("_")){
			data=item.split(",");
			Result r=addGoodsToShoppingCar(Integer.parseInt(data[0]), uuid, Integer.parseInt(data[1]));
			if(r.getCode()>0){
				return r;
			}
		}
		return putResult();
	}
	
	//添加购物车
	@Transactional
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
		//判断用户是否可添加该商品
		int buyAmount=goodsService.getEnableBuyAmount(user.getId(), goodsId);
		if(buyAmount>=0&&amount>buyAmount){
			return putResult(AppConstant.OUT_OF_BOUNDS,new Object[]{goodsService.getTotalBuyAmount(user.getId(), goodsId)});
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
	@Transactional
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
		shoppingCarDao.removeGoodsFromShoppingCar(user.getId(), Arrays.asList(goodsId));
		return putResult();
	}
	
	//查询购物车
	public List<ShoppingCar> queryShoppingCars(Map<Object,Object> param){
		return shoppingCarDao.queryShoppingCars(param);
	}
	
	//更新购物车商品数量
	public void updateAmountOfShoppingCar(int userId,int goodsId,int amount){
		shoppingCarDao.updateAmountOfShoppingCar(userId, goodsId, amount);
	}
	
	//移除购物车
	public void removeGoodsFromShoppingCar(int userId, List<Integer> goodsIds){
		shoppingCarDao.removeGoodsFromShoppingCar(userId, goodsIds);
	}
	
	//查看我的购物商品
	public List<Map<String,Object>> queryMyShoppingCar(int userId,List<Integer> goodsIds){
		return shoppingCarDao.queryMyShoppingCar(userId, goodsIds);
	}
	
	//查看我的购物商品
	public ShoppingCar queryShoppingCars(int userId,int goodsId){
		List<ShoppingCar> cars=queryShoppingCars(DataUtil.mapOf("userId",userId,"goodsId",goodsId));
		return cars.size()>0?cars.get(0):null;
	}
	
}
