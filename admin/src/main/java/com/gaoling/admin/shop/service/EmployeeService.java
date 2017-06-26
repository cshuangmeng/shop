package com.gaoling.admin.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.shop.dao.EmployeeDao;
import com.gaoling.admin.shop.entity.EmployeeInfo;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	//保存职员信息
	public int addEmployee(EmployeeInfo employee){
		employee.setEmployeeId(DataUtil.buildUUID());
		employee.setCreateTime(DateUtil.getCurrentTime());
		return employeeDao.addEmployee(employee);
	}
	
	//更新职员信息
	public void updateEmployee(EmployeeInfo employee){
		employeeDao.updateEmployee(employee);
	}
	
	//获取职员信息
	public EmployeeInfo getEmployee(int id){
		return employeeDao.getEmployee(id);
	}
	
	//获取职员信息
	public EmployeeInfo getEmployeeByUnionName(String unionName){
		return employeeDao.getEmployeeByUnionName(unionName);
	}
	
	//获取所有职员信息
	public List<HashMap<String,Object>> loadAllEmployees(){
		return employeeDao.loadAllEmployees();
	}
	
	//获取不重复的职员帐号
	public String getUniqueUnionName(String unionName){
		int index=0;
		EmployeeInfo employeeInfo=getEmployeeByUnionName(unionName);
		while(null!=employeeInfo){
			index++;
			employeeInfo=getEmployeeByUnionName(unionName+index);
		}
		return index>0?unionName+index:unionName;
	}
	
}
