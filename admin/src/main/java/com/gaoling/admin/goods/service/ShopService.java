package com.gaoling.admin.goods.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.goods.dao.ShopDao;
import com.gaoling.admin.goods.pojo.Goods;
import com.gaoling.admin.goods.pojo.Shop;
import com.gaoling.admin.order.pojo.Order;
import com.gaoling.admin.order.service.OrderService;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;

@Service
public class ShopService extends CommonService{

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	
	//查询店铺
	public Shop getShop(int id){
		List<Shop> shops=queryShops(DataUtil.mapOf("id",id));
		return shops.size()>0?shops.get(0):null;
	}
	
	//审核店铺
	public void examineShop(int shopId,int state){
		Shop shop=getShop(shopId);
		shop.setState(state);
		updateShop(shop);
	}
	
	//加载店铺
	public List<Shop> queryShops(Map<Object,Object> param){
		return shopDao.queryShops(param);
	}
	
	//加载店铺
	public List<Map<String,Object>> queryShopsToMap(Map<Object,Object> param){
		return shopDao.queryShopsToMap(param);
	}
	
	//新增店铺信息
	public void addShop(Shop shop){
		shopDao.addShop(shop);
	}
	
	//更新店铺信息
	public void updateShop(Shop shop){
		shopDao.updateShop(shop);
	}
	
	//保存商铺信息
	@Transactional
	public Result saveOrUpdateShop(Shop shop,MultipartFile headImgFile,MultipartFile[] infoImgFile)throws Exception{
		Shop old=shop.getId()>0?getShop(shop.getId()):null;
		//上传头像
		String fileName="";
		if(!headImgFile.isEmpty()){
			fileName="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
			fileName+=headImgFile.getOriginalFilename().substring(headImgFile.getOriginalFilename().lastIndexOf("."));
			OSSUtil.uploadFileToOSS(fileName, headImgFile.getInputStream());
		}
		shop.setHeadImg(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getHeadImg():"");
		//上传描述
		shop.setInfoImgs("");
		for(MultipartFile ii:infoImgFile){
			if(!ii.isEmpty()){
				String file="shop/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
				file+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(file, ii.getInputStream());
				fileName+=StringUtils.isNotEmpty(fileName)?","+file:file;
			}
		}
		shop.setInfoImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getInfoImgs():"");
		shop.setIsShow(null!=old?old.getIsShow():0);
		shop.setAreaId(null!=old?old.getAreaId():0);
		shop.setFlowers(null!=old?old.getFlowers():0);
		shop.setFollowers(null!=old?old.getFollowers():0);
		shop.setAreaName(null!=old?old.getAreaName():"");
		shop.setState(null!=old?old.getState():0);
		shop.setCreateTime(null!=old?old.getCreateTime():DateUtil.nowDate());
		if(shop.getId()>0){
			updateShop(shop);
		}else{
			addShop(shop);
		}
		return putResult(shop);
	}
	
	//读取商铺详细信息
	public Shop getDetailShop(int id){
		Shop shop=getShop(id);
		if(null!=shop){
			shop.setHeadImg(shop.getFullHeadImg());
			shop.setInfoImgs(shop.getFullInfoImgs());
		}
		return shop;
	}
	
	//统计商品数据
	public Result statShopSummary(){
		//总商品数
		List<Goods> goods=goodsService.queryGoods(DataUtil.mapOf());
		int goodsSum=goods.size();
		//待审核商品数
		int submittedSum=goods.stream().filter(g->g.getState()==Goods.STATE_TYPE_ENUM.SUBMITTED.getState()).collect(Collectors.toList()).size();
		//在售商品
		int passedSum=goods.stream().filter(g->g.getState()==Goods.STATE_TYPE_ENUM.PASSED.getState()).collect(Collectors.toList()).size();
		//在售商品
		int deletedSum=goods.stream().filter(g->g.getState()==Goods.STATE_TYPE_ENUM.DELETED.getState()).collect(Collectors.toList()).size();
		//今日添加
		int todaySum=goods.stream().filter(g->DateUtils.isSameDay(g.getCreateTime(), DateUtil.nowDate())).collect(Collectors.toList()).size();
		//订单统计
		String endDate=DateUtil.dateToDay(DateUtil.nowDate());
		String startDate=DateUtil.dateToDay(DateUtils.addDays(DateUtil.nowDate(), -30));
		List<Integer> states=Arrays.asList(Order.STATE_TYPE_ENUM.NOSEND.getState(),Order.STATE_TYPE_ENUM.NORECEIVE.getState()
				,Order.STATE_TYPE_ENUM.NOCOMMENT.getState());
		List<String> categories=new ArrayList<String>();
		List<Integer> series1=new ArrayList<Integer>();
		orderService.stateShopStat(DataUtil.mapOf("startDate",startDate,"endDate",endDate,"states",states)).stream().forEach(o->{
			categories.add(o.get("df").toString());
			series1.add(Integer.valueOf(o.get("amount").toString()));
		});
		return putResult(DataUtil.mapOf("goodsSum",goodsSum,"submittedSum",submittedSum
				,"passedSum",passedSum,"deletedSum",deletedSum,"todaySum",todaySum,"line1",DataUtil.mapOf("categories",categories,"series1",series1)));
	}
	
}
