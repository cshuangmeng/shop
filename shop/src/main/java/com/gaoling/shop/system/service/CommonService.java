package com.gaoling.shop.system.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.system.dao.DictInfoDao;
import com.gaoling.shop.system.dao.ResponseInfoDao;
import com.gaoling.shop.system.pojo.Result;

@Service
public class CommonService {

	@Autowired
	private DictInfoDao dictInfoDao;
	@Autowired
	private ResponseInfoDao responseInfoDao;
	
	//查询字典数值
	public String getString(String name){
		return dictInfoDao.queryDictValue(name);
	}
	
	//查询字典数值
	public int getInteger(String name){
		return Integer.parseInt(dictInfoDao.queryDictValue(name));
	}
	
	//查询字典数值
	public String getString(String name,String def){
		String value=dictInfoDao.queryDictValue(name);
		return StringUtils.isNotEmpty(value)?value:def;
	}
	
	//查询字典数值
	public int getInteger(String name,int def){
		String value=dictInfoDao.queryDictValue(name);
		return StringUtils.isNotEmpty(value)?Integer.parseInt(value):def;
	}
	
	//设置返回结果
	public Result putResult(int code,Object data){
		Result result=new Result();
		result.setCode(code);
		result.setData(data);
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), code));
		return result;
	}
	
	//设置返回结果
	public Result putResult(int code){
		Result result=new Result();
		result.setCode(code);
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}
	
	//设置返回结果
	public Result putResult(Object data){
		Result result=new Result();
		result.setData(data);
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}
	
	//设置返回结果
	public Result putResult(){
		Result result=new Result();
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}
	
}
