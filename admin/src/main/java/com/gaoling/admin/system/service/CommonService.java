package com.gaoling.admin.system.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.system.dao.DictInfoDao;
import com.gaoling.admin.system.dao.ResponseInfoDao;
import com.gaoling.admin.system.pojo.Result;
import com.gaoling.admin.util.DataUtil;

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
	
	//查询字典集合
	public List<Map<String,Object>> getDicts(String parentName){
		List<Map<String,Object>> result=dictInfoDao.queryDicts(DataUtil.mapOf("parentName",parentName));
		if(result.size()>0){
			result.stream().forEach(r->{
				String name=r.get("name").toString();
				if(name.contains("_")){
					r.put("code", name.split("_")[name.split("_").length-1]);
				}
			});
		}
		return result;
	}
	
	// 更新字典数值
	public int updateDictValue(Map<String,Object> set,Map<String,Object> param) {
		return dictInfoDao.updateDictValue(set, param);
	}
	
	//设置返回结果
	public Result putResult(int code,Object data,String msg){
		Result result=new Result();
		result.setCode(code);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}
	
	//设置返回结果
	public Result putResult(int code,Object... params){
		Result result=new Result();
		result.setCode(code);
		result.setMsg(String.format(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), code), params));
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
