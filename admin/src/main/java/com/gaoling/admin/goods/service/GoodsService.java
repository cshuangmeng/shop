package com.gaoling.admin.goods.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.goods.dao.GoodsDao;
import com.gaoling.admin.goods.pojo.Goods;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;

@Service
public class GoodsService extends CommonService{

	@Autowired
	private GoodsDao goodsDao;
	
	//查询商品信息
	public List<Map<String,Object>> loadGoods(Map<Object,Object> param){
		return goodsDao.queryGoodsToMap(param);
	}
	
	//审核商品信息
	public void examineGoods(int goodsId,int state){
		Goods goods=getGoods(goodsId);
		goods.setState(state);
		updateGoods(goods);
	}
	
	//保存或更新商品信息
	@Transactional
	public void saveOrUpdateGoods(Goods goods,MultipartFile headImg,MultipartFile[] infoImg,MultipartFile[] detailImg)throws Exception{
		Goods old=goods.getId()>0?getGoods(goods.getId()):null;
		//上传头像
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
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
				fileName+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, ii.getInputStream());
				fileName+=StringUtils.isNotEmpty(fileName)?","+fileName:fileName;
			}
		}
		goods.setInfoImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getInfoImgs():"");
		//上传详情
		fileName="";
		for(MultipartFile di:detailImg){
			if(!di.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6);
				fileName+=di.getOriginalFilename().substring(di.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, di.getInputStream());
				fileName+=StringUtils.isNotEmpty(fileName)?","+fileName:fileName;
			}
		}
		goods.setDetailImgs(StringUtils.isNotEmpty(fileName)?fileName:null!=old?old.getDetailImgs():"");
		goods.setCreateTime(null!=old?old.getCreateTime():DateUtil.nowDate());
		if(null!=old){
			updateGoods(goods);
		}else{
			addGoods(goods);
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
	
	//新增商品
	public void addGoods(Goods goods){
		goodsDao.addGoods(goods);
	}
	
	//更新商品
	public void updateGoods(Goods goods){
		goodsDao.updateGoods(goods);
	}
	
}
