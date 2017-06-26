package com.gaoling.admin.shop.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gaoling.admin.shop.entity.EmployeeInfo;


@Repository
public interface EmployeeDao {

	int addEmployee(EmployeeInfo employee);
	void updateEmployee(EmployeeInfo employee);
	EmployeeInfo getEmployee(int id);
	EmployeeInfo getEmployeeByUnionName(String unionName);
	List<HashMap<String,Object>> loadAllEmployees();
	
}
