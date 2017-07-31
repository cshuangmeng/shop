package com.gaoling.webshop.goods.service;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.common.DateUtil;
import com.gaoling.webshop.common.OSSUtil;
import com.gaoling.webshop.common.ThreadCache;
import com.gaoling.webshop.goods.dao.GoodsDao;
import com.gaoling.webshop.goods.pojo.Goods;
import com.gaoling.webshop.goods.pojo.Shop;
import com.gaoling.webshop.order.pojo.Order;
import com.gaoling.webshop.order.service.OrderService;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.system.service.CommonService;
import com.gaoling.webshop.user.pojo.User;

import net.sf.json.JSONArray;
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
		User user=(User)ThreadCache.getData(AppConstant.STORE_USER_PARAM_NAME);
		Shop shop=shopService.getShop(goods.getShopId());
		//追加其他参数
		int sellAmount=orderService.queryOrders(DataUtil.mapOf("goodsId",id,"states"
				,Arrays.asList(Order.STATE_TYPE_ENUM.NOSEND.getState(),Order.STATE_TYPE_ENUM.NORECEIVE.getState()
				,Order.STATE_TYPE_ENUM.NOCOMMENT.getState()))).size();
		float miniPrice=goods.getCoinEnable()>0||goods.getPointEnable()>0?Math.round(goods.getPrice()*goods.getCashDiscount()):goods.getPrice();
		goods.getExtras().put("miniPrice", miniPrice);
		goods.getExtras().put("freight", getInteger("freight"));
		goods.getExtras().put("sellAmount", sellAmount);
		goods.getExtras().put("goodsArea", shop.getAreaName());
		goods.getExtras().put("backPoint", goods.getPrice()*getInteger("cash_to_point_rate"));
		goods.getExtras().put("buyEnable", 1);
		if(DataUtil.isJSONObject(goods.getDetails())){
			goods.getExtras().put("detailsJson", JSONArray.fromObject(goods.getDetails()));
		}
		if(StringUtils.isNotEmpty(goods.getFullDetailImgs())){
			goods.getExtras().put("fullDetailImg", goods.getFullDetailImgs().split(","));
		}
		//检查商品是否可购买
		if(null!=user){
			int buyAmount=getEnableBuyAmount(user.getId(), goods.getId());
			if(buyAmount>0){
				goods.getExtras().put("buyAmount", buyAmount);
			}else if(buyAmount==0){
				goods.getExtras().put("buyEnable", 0);
				goods.getExtras().put("buyAmount", buyAmount);
			}
		}
		//门店信息
		return putResult(DataUtil.mapOf("goods",goods,"shop",shop));
	}
	
	//检查商品是否还可购买
	public int getEnableBuyAmount(int userId,int goodsId){
		int buyAmount=-1;
		int buyTotal=getTotalBuyAmount(userId, goodsId);
		if(buyTotal>=0){
			//查询已成功购买的数量
			Goods goods=getGoods(goodsId);
			List<Order> orders=orderService.queryOrders(DataUtil.mapOf("userId",userId,"typeIds"
					,Arrays.asList(goods.getTypeId()),"states",Order.NORMALORDERSTATES));
			int currentAmount=orders.size();
			//检查是否可购买
			buyAmount=currentAmount<buyTotal?buyTotal-currentAmount:0;
		}
		return buyAmount;
	}
	
	//检查商品可购买总件数
	public int getTotalBuyAmount(int userId,int goodsId){
		//默认无上限
		int buyTotal=-1;
		Goods goods=getGoods(goodsId);
		//检查商品是否可购买
		JSONObject json=JSONObject.fromObject(getString("prepare_sell_recommend"));
		if(DataUtil.isJSONObject(json.get("goods_type_"+goods.getTypeId()))){
			JSONObject config=json.getJSONObject("goods_type_"+goods.getTypeId());
			if(config.containsKey("max_buy_amount")){
				buyTotal=config.getInt("max_buy_amount");
			}
		}
		return buyTotal;
	}
	
	//查询商品列表
	public Result loadGoods(Map<Object,Object> param)throws Exception{
		//设置门店
		if(!DataUtil.isEmpty(param.get("shopIds"))){
			param.put("shopIds", Arrays.asList(param.get("shopIds").toString().split(","))
					.stream().map(s->Integer.parseInt(s)).collect(Collectors.toList()));
		}
		//商品名称解码
		if(!DataUtil.isEmpty(param.get("name"))){
			param.put("name", URLDecoder.decode(URLDecoder.decode(param.get("name").toString(), "UTF-8"), "UTF-8"));
		}
		int typeId=null!=param.get("typeId")?Integer.parseInt(param.get("typeId").toString()):0;
		int total=queryGoodsCount(param);
		//分页设置
		JSONObject json=JSONObject.fromObject(getString("goods_list_pagesize"));
		int page=!DataUtil.isEmpty(param.get("page"))?Integer.parseInt(param.get("page").toString()):1;
		int limit=json.getInt("pagesize");
		param.put("offset", (page-1)*limit);
		param.put("limit", limit);
		//拼装排序规则
		if(Goods.GOODS_SORT_TYPE_ENUM.COMPRE.getType()==Integer.parseInt(param.get("sort").toString())){
			param.put("orderBy", "t.create_time");
		}else if(Goods.GOODS_SORT_TYPE_ENUM.PRICE.getType()==Integer.parseInt(param.get("sort").toString())){
			param.put("orderBy", "t.price");
		}else if(Goods.GOODS_SORT_TYPE_ENUM.DISCOUNT.getType()==Integer.parseInt(param.get("sort").toString())){
			param.put("orderBy", "t.cash_discount");
		}
		if(!DataUtil.isEmpty(param.get("rule"))&&Integer.parseInt(param.get("rule").toString())>0){
			param.put("orderRule", "desc");
		}else{
			param.put("orderRule", "asc");
		}
		json=JSONObject.fromObject(getString("prepare_sell_recommend"));
		List<Goods> goods=queryGoods(param);
		goods.stream().forEach(g->{
			float miniPrice=g.getCoinEnable()>0||g.getPointEnable()>0?Math.round(g.getPrice()*g.getCashDiscount()):g.getPrice();
			g.getExtras().put("miniPrice", miniPrice);
		});
		if(json.containsKey("goods_type_"+typeId)&&json.getJSONObject("goods_type_"+typeId).containsKey("imgs")){
			String infoImgs=Arrays.asList(json.getJSONObject("goods_type_"+typeId).getString("imgs").split(","))
					.stream().map(i->AppConstant.OSS_CDN_SERVER+i).reduce((a,b)->a+","+b).get();
			JSONObject recommend=json.getJSONObject("goods_type_"+typeId);
			recommend.put("imgs", infoImgs);
			return putResult(DataUtil.mapOf("goods",goods,"recommend",recommend,"page",page
					,"total",total%limit>0?total/limit+1:total/limit));
		}else{
			return putResult(DataUtil.mapOf("goods",goods,"page",page
					,"total",total%limit>0?total/limit+1:total/limit));
		}
	}
	
	//保存商品信息
	@Transactional
	public Result saveGoodsByUpload(Goods goods,MultipartFile headImg,MultipartFile[] infoImg,MultipartFile[] detailImg)throws Exception{
		//上传头像
		String fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
		fileName+=headImg.getOriginalFilename().substring(headImg.getOriginalFilename().lastIndexOf("."));
		OSSUtil.uploadFileToOSS(headImg.getInputStream(), fileName);
		goods.setHeadImg(fileName);
		//上传描述
		for(MultipartFile ii:infoImg){
			if(!ii.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
				fileName+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(ii.getInputStream(), fileName);
				goods.setInfoImgs(StringUtils.isNotEmpty(goods.getInfoImgs())?goods.getInfoImgs()+","+fileName:fileName);
			}
		}
		//上传详情
		for(MultipartFile di:detailImg){
			if(!di.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
				fileName+=di.getOriginalFilename().substring(di.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(di.getInputStream(), fileName);
				goods.setDetailImgs(StringUtils.isNotEmpty(goods.getDetailImgs())?goods.getDetailImgs()+","+fileName:fileName);
			}
		}
		goods.setState(Goods.STATE_TYPE_ENUM.PASSED.getState());
		goods.setCreateTime(DateUtil.nowDate());
		goodsDao.addGoods(goods);
		return putResult(goods);
	}
	
	//加载首页推荐商品
	public Result loadIndexGoodsList(){
		//加载推荐欢迎大礼包
		JSONObject json=JSONObject.fromObject(getString("index_goods_list_pagesize"));
		JSONObject obj=json.getJSONObject("type_"+Goods.GOODS_TYPE_ENUM.WELCOME.getType());
		List<Map<Object,Object>> welcomes=queryGoods(DataUtil.mapOf("typeId",Goods.GOODS_TYPE_ENUM.WELCOME.getType()
				,"orderBy",obj.getString("orderBy"),"offset",0,"limit",obj.getInt("pagesize"))).stream()
				.map(g->DataUtil.mapOf("id",g.getId(),"name",g.getName(),"price",g.getPrice(),"img",g.getFullHeadImg())).collect(Collectors.toList());
		//加载品质生活
		obj=json.getJSONObject("type_"+Goods.GOODS_TYPE_ENUM.LIVE.getType());
		List<Map<Object,Object>> lives=queryGoods(DataUtil.mapOf("typeId",Goods.GOODS_TYPE_ENUM.LIVE.getType()
				,"orderBy",obj.getString("orderBy"),"offset",0,"limit",obj.getInt("pagesize"))).stream()
				.map(g->DataUtil.mapOf("id",g.getId(),"name",g.getName(),"price",g.getPrice(),"img",g.getFullHeadImg()
				,"discount",Math.round(g.getPrice()*g.getCashDiscount()))).collect(Collectors.toList());
		//加载家庭健康
		obj=json.getJSONObject("type_"+Goods.GOODS_TYPE_ENUM.HEALTH.getType());
		List<Map<Object,Object>> healths=queryGoods(DataUtil.mapOf("typeId",Goods.GOODS_TYPE_ENUM.HEALTH.getType()
				,"orderBy",obj.getString("orderBy"),"offset",0,"limit",obj.getInt("pagesize"))).stream()
				.map(g->DataUtil.mapOf("id",g.getId(),"name",g.getName(),"price",g.getPrice(),"img",g.getFullHeadImg()
				,"discount",Math.round(g.getPrice()*g.getCashDiscount()))).collect(Collectors.toList());
		//加载旗舰店
		json=JSONObject.fromObject(getString("index_shop_list_pagesize"));
		List<Map<Object,Object>> shops=shopService.queryShops(DataUtil.mapOf("orderBy",obj.getString("orderBy"),"offset",0,"limit",obj.getInt("pagesize"))).stream()
				.map(s->DataUtil.mapOf("id",s.getId(),"name",s.getName(),"img",s.getFullHeadImg())).collect(Collectors.toList());
		//加载首页Banner
		List<Map<Object,Object>> topBanners=getSonDicts("home_top_banner").stream().map(g->{
			JSONObject banner=JSONObject.fromObject(g.get("value").toString());
			return DataUtil.mapOf("url",banner.get("url"),"img",AppConstant.OSS_CDN_SERVER+banner.get("img"));
		}).collect(Collectors.toList());
		//加载首页Banner
		List<Map<Object,Object>> bottomBanners=getSonDicts("home_bottom_banner").stream().map(g->{
			JSONObject banner=JSONObject.fromObject(g.get("value").toString());
			return DataUtil.mapOf("url",banner.get("url"),"img",AppConstant.OSS_CDN_SERVER+banner.get("img"));
		}).collect(Collectors.toList());
		return putResult(DataUtil.mapOf("welcomes", welcomes, "lives", lives, "shops", shops
				, "healths", healths, "topBanners", topBanners, "bottomBanners", bottomBanners));
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
	
	//查询商品数量
	public int queryGoodsCount(Map<Object,Object> param){
		return goodsDao.queryGoodsCount(param);
	}
	
}
