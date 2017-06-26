package com.gaoling.admin.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.CooRequestDao;
import com.gaoling.admin.shop.entity.CooRequest;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.ExcelUtil;


@Service
public class CooRequestService {

	@Autowired
	private CooRequestDao cooRequestDao;
	
	//加载合作请求
	public List<CooRequest> loadCooRequestList(String fullname,int offset,int limit){
		return cooRequestDao.loadCooRequestList(fullname, offset, limit);
	}
	
	//获取合作请求总条数
	public int getCooRequestSize(String fullname){
		return cooRequestDao.getCooRequestSize(fullname);
	}
	
	//将符合指定条件的商户信息写入Excel
	public void writeResultToExcel(String fullname,HttpServletResponse response){
		try {
			//加载查询结果
			List<CooRequest> resultList=loadCooRequestList(fullname, 0, 0);
			String[] titles = { "商户名称:fullname:string", "地址:address:string", "联系人:linkman:string",
					"联系电话:telephone:string", "申请时间:createTime:string" };
			List<HashMap<String,Object>> dataList=new ArrayList<HashMap<String,Object>>();
			for(CooRequest result:resultList){
				dataList.add((HashMap<String,Object>)DataUtil.objectToMap(result));
			}
			ExcelUtil.writeResultToExcel(titles, dataList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
