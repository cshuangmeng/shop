package com.gaoling.shop.goods.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.OSSUtil;
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
