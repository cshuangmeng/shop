package com.gaoling.shop.goods.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.goods.dao.GoodsDao;
import com.gaoling.shop.goods.pojo.Goods;
import com.gaoling.shop.goods.pojo.Shop;
import com.gaoling.shop.order.pojo.Order;
import com.gaoling.shop.order.service.OrderService;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;

import net.sf.json.JSONObject;

@Service
public class GoodsService extends CommonService{

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShopService shopService;
	
	//查询商品详情
	public Result loadGoodsDetail(int id){
		Goods goods=getGoods(id);
		if(null==goods){
			return putResult(AppConstant.GOODS_NOT_EXISTS);
		}
		Shop shop=shopService.getShop(goods.getShopId());
		//追加其他参数
		int sellAmount=orderService.queryOrders(DataUtil.mapOf("goodsId",id,"states"
				,Arrays.asList(Order.STATE_TYPE_ENUM.NOSEND.getState(),Order.STATE_TYPE_ENUM.NORECEIVE.getState()
				,Order.STATE_TYPE_ENUM.NOCOMMENT.getState()))).size();
		float miniPrice=goods.getCoinEnable()>0||goods.getPointEnable()>0?goods.getPrice()*goods.getCashDiscount()*0.1f:goods.getPrice();
		goods.getExtras().put("miniPrice", miniPrice);
		goods.getExtras().put("freight", getInteger("freight"));
		goods.getExtras().put("sellAmount", sellAmount);
		goods.getExtras().put("goodsArea", shop.getAreaName());
		goods.getExtras().put("backPoint", goods.getPrice()*getInteger("cash_to_point_rate"));
		return putResult(goods);
	}
	
	//查询商品列表
	public Result loadGoods(Map<Object,Object> param){
		int typeId=null!=param.get("typeId")?Integer.parseInt(param.get("typeId").toString()):0;
		JSONObject json=JSONObject.fromObject(getString("prepare_sell_recommend"));
		List<Goods> goods=queryGoods(param);
		goods.stream().forEach(g->{
			float miniPrice=g.getCoinEnable()>0||g.getPointEnable()>0?g.getPrice()*g.getCashDiscount()*0.1f:g.getPrice();
			g.getExtras().put("miniPrice", miniPrice);
		});
		if(json.containsKey("goods_type_"+typeId)){
			String infoImgs=Arrays.asList(json.getJSONObject("goods_type_"+typeId).getString("imgs").split(","))
					.stream().map(i->AppConstant.OSS_CDN_SERVER+i).reduce((a,b)->a+","+b).get();
			JSONObject recommend=json.getJSONObject("goods_type_"+typeId);
			recommend.put("imgs", infoImgs);
			return putResult(DataUtil.mapOf("goods",goods,"recommend",recommend));
		}else{
			return putResult(DataUtil.mapOf("goods",goods));
		}
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
