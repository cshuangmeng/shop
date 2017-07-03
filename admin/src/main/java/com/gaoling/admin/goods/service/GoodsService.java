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
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.OSSUtil;

@Service
public class GoodsService extends CommonService{

	@Autowired
	private GoodsDao goodsDao;
	
	//保存商品信息
	@Transactional
	public Result saveGoodsByUpload(Goods goods,MultipartFile headImg,MultipartFile[] infoImg,MultipartFile[] detailImg)throws Exception{
		//上传头像
		String fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
		fileName+=headImg.getOriginalFilename().substring(headImg.getOriginalFilename().lastIndexOf("."));
		OSSUtil.uploadFileToOSS(fileName, headImg.getInputStream());
		goods.setHeadImg(fileName);
		//上传描述
		for(MultipartFile ii:infoImg){
			if(!ii.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
				fileName+=ii.getOriginalFilename().substring(ii.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, ii.getInputStream());
				goods.setInfoImgs(StringUtils.isNotEmpty(goods.getInfoImgs())?goods.getInfoImgs()+","+fileName:fileName);
			}
		}
		//上传详情
		for(MultipartFile di:detailImg){
			if(!di.isEmpty()){
				fileName="goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS"+DataUtil.createNums(6));
				fileName+=di.getOriginalFilename().substring(di.getOriginalFilename().lastIndexOf("."));
				OSSUtil.uploadFileToOSS(fileName, di.getInputStream());
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
