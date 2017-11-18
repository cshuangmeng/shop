package com.gaoling.admin.goods.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.goods.dao.GoodsDao;
import com.gaoling.admin.goods.pojo.Goods;
import com.gaoling.admin.goods.pojo.GoodsType;
import com.gaoling.admin.goods.pojo.Shop;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class GoodsService extends CommonService{

	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ShopService shopService;
	@Autowired
	private GoodsTypeService goodsTypeService;
	
	//查询商品信息
	public List<Map<String,Object>> loadGoods(Map<Object,Object> param){
		return goodsDao.queryGoodsToMap(param);
	}
	
	//审核商品信息
	public void examineGoods(String goodsIds,int state){
		if(StringUtils.isNotEmpty(goodsIds)){
			Arrays.asList(goodsIds.split(",")).forEach(g->{
				Goods goods=getGoods(Integer.parseInt(g));
				goods.setState(state);
				updateGoods(goods);
			});
		}
	}
	
	//保存或更新商品信息
	@Transactional
	public void saveOrUpdateGoods(Goods goods,MultipartFile headImg,MultipartFile[] infoImg
			,MultipartFile[] detailImg,String params){
		Goods old=goods.getId()>0?getGoods(goods.getId()):null;
		//上传头像
		try {
			String fileName="";
			if(!headImg.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
				fileName+=headImg.getOriginalFilename().substring(headImg.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, headImg.getInputStream());
			}
			goods.setHeadImg(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getHeadImg():"");
			//上传描述
			fileName="";
			for(MultipartFile ii:infoImg){
				if(!ii.isEmpty()){
					String file="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
					file+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
					fileName+=StringUtils.isNotEmpty(fileName)?","+file:file;
					OSSUtil.uploadFileToOSS(file, ii.getInputStream());
				}
			}
			goods.setInfoImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getInfoImgs():"");
			//上传详情
			fileName="";
			for(MultipartFile di:detailImg){
				if(!di.isEmpty()){
					String file="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
					file+=di.getOriginalFilename().substring(di.getOriginalFilename().lastIndexOf("."));
					fileName+=StringUtils.isNotEmpty(fileName)?","+file:file;
					OSSUtil.uploadFileToOSS(file, di.getInputStream());
				}
			}
			//设置商品参数
			goods.setDetails(null!=old?old.getDetails():"");
			if(StringUtils.isNotEmpty(params)){
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
				for(int i=0;i<params.split(",").length;i++){
					list.add(DataUtil.mapOf("label",params.split(",")[i].split("=")[0],"value",params.split(",")[i].split("=")[1]));
				}
				goods.setDetails(JSONArray.fromObject(list).toString());
			}
			goods.setDetailImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getDetailImgs():"");
			goods.setCreateTime(null!=old?old.getCreateTime():DateUtil.nowDate());
			if(null!=old){
				updateGoods(goods);
			}else{
				addGoods(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询商品
	public Goods getGoods(int id){
		List<Goods> goods=queryGoods(DataUtil.mapOf("id",id));
		return goods.size()>0?goods.get(0):null;
	}
	
	//读取商品详细信息
	public Goods getDetailGoods(int id){
		Goods goods=getGoods(id);
		if(null!=goods){
			//处理商品参数
			Shop shop=shopService.getShop(goods.getShopId());
			goods.getExtras().put("shopName", shop.getName());
			GoodsType type=goodsTypeService.getGoodsType(goods.getTypeId());
			goods.getExtras().put("typeName", type.getName());
			goods.setHeadImg(goods.getFullHeadImg());
			goods.setInfoImgs(goods.getFullInfoImgs());
			goods.setDetailImgs(goods.getFullDetailImgs());
		}
		return goods;
	}
	
	//读取待编辑商品的相关信息
	@SuppressWarnings("unchecked")
	public Goods getEditingGoods(int id){
		Goods goods=getGoods(id);
		if(null!=goods){
			//处理商品参数
			if(StringUtils.isNotEmpty(goods.getDetails())){
				String details=JSONArray.fromObject(goods.getDetails()).stream().map(j->{
					JSONObject param=JSONObject.fromObject(j);
					return param.get("label")+"="+param.get("value");
				}).reduce((a,b)->a+","+b).get().toString();
				goods.getExtras().put("params", details);
			}
		}
		return goods;
	}
	
	//加载商品
	public List<Goods> queryGoods(Map<Object,Object> param){
		return goodsDao.queryGoods(param);
	}
	
	//新增商品
	public void addGoods(Goods goods){
		goodsDao.addGoods(goods);
	}
	
	//更新商品
	public void updateGoods(Goods goods){
		goodsDao.updateGoods(goods);
	}
	
}
