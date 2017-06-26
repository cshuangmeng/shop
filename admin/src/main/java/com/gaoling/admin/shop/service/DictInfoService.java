package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.DictInfoDao;
import com.gaoling.admin.shop.entity.DictInfo;


@Service
public class DictInfoService {

	@Autowired
	private DictInfoDao dictDao;
	
	//保存字典
	public int addDict(DictInfo dict){
		return dictDao.addDict(dict);
	}
	
	//更新字典
	public void updateDict(DictInfo dict){
		dictDao.updateDict(dict);
	}
	
	//删除字典
	public void deleteDict(int id){
		dictDao.deleteDict(id);
	}
	
	//获取字典信息
	public DictInfo getDict(int id){
		return dictDao.getDict(id);
	}
	
	//获取字典信息
	public DictInfo getDictByName(String dictCode,String dictName){
		return dictDao.getDictByName(dictCode, dictName);
	}
	
	//加载所有字典信息
	public List<DictInfo> loadAllDicts(){
		return dictDao.loadAllDicts();
	}
	
	//加载所有字典值
	public List<HashMap<String,Object>> loadDicts(String dictCode){
		return dictDao.loadDicts(dictCode);
	}
	
	//加载字典值
	public String getDictName(String dictCode,String dictValue){
		return dictDao.getDictName(dictCode, dictValue);
	}
	
}
