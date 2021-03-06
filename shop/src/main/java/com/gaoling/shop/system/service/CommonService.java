package com.gaoling.shop.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.system.dao.DictInfoDao;
import com.gaoling.shop.system.dao.ResponseInfoDao;
import com.gaoling.shop.system.pojo.Result;

@Service
public class CommonService {

	@Autowired
	private DictInfoDao dictInfoDao;
	@Autowired
	private ResponseInfoDao responseInfoDao;

	// 查询字典数值
	public String getString(String name) {
		return dictInfoDao.queryDictValue(name);
	}

	// 查询字典数值
	public int getInteger(String name) {
		return Integer.parseInt(dictInfoDao.queryDictValue(name));
	}

	// 查询字典数值
	public List<Map<String, Object>> getSonDicts(String parentName) {
		return dictInfoDao.queryDicts(DataUtil.mapOf("parentName", parentName));
	}

	// 查询字典数值
	public List<Map<String, Object>> getDicts(String name) {
		return dictInfoDao.queryDicts(DataUtil.mapOf("name", name));
	}

	// 查询字典数值
	public List<Map<String, Object>> queryDicts(Map<String, Object> param) {
		return dictInfoDao.queryDicts(param);
	}

	// 查询字典数值
	public String getString(String name, String def) {
		String value = dictInfoDao.queryDictValue(name);
		return StringUtils.isNotEmpty(value) ? value : def;
	}

	// 查询字典数值
	public int getInteger(String name, int def) {
		String value = dictInfoDao.queryDictValue(name);
		return StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : def;
	}

	// 设置返回结果
	public Result putResult(int code, Object data, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}

	// 设置返回结果
	public Result putResult(int code, Object... params) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(String.format(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), code), params));
		return result;
	}

	// 设置返回结果
	public Result putResult(int code) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}

	// 设置返回结果
	public Result putResult(Object data) {
		Result result = new Result();
		result.setData(data);
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}

	// 设置返回结果
	public Result putResult() {
		Result result = new Result();
		result.setMsg(responseInfoDao.queryResponseTextCn(CommonService.class.getSimpleName(), result.getCode()));
		return result;
	}

	// 更新字典数值
	public int updateDictValue(Integer id,String name,String value,String remark) {
		Map<String,Object> set=DataUtil.mapOf("name",name,"value",value,"remark",remark);
		Map<String,Object> param=DataUtil.mapOf("id",id);
		return dictInfoDao.updateDictValue(set,param);
	}
	
	// 更新字典数值
	public int updateDictValue(Integer id,Integer state) {
		Map<String,Object> set=DataUtil.mapOf("state",state);
		Map<String,Object> param=DataUtil.mapOf("id",id);
		return dictInfoDao.updateDictValue(set,param);
	}
	
	// 更新字典数值
	public int updateDictValue(Map<String,Object> set,Map<String,Object> param) {
		return dictInfoDao.updateDictValue(set, param);
	}

	// 删除字典数值
	public int deleteDict(Integer id,String name, Integer parentId) {
		return dictInfoDao.deleteDict(id, name, parentId);
	}

	// 保存字典数值
	public int insertDictValue(String name, String value, Integer parentId, Date createTime, Integer state,
			String remark, Integer orderIndex) {
		return dictInfoDao.insertDictValue(name, value, parentId, createTime, state, remark, orderIndex);
	}

}
