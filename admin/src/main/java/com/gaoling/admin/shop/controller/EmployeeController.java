package com.gaoling.admin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.entity.EmployeeInfo;
import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	//进入联盟帐号管理首页
	@RequestMapping("/data/employee")
	public String bdIndex(){
		return "data/employee";
	}
	
	//加载所有联盟帐号列表
	@ResponseBody
	@RequestMapping("/data/emp/list")
	public JsonResult loadAllEmployees(){
		JsonResult json=new JsonResult();
		try {
			json.setData(employeeService.loadAllEmployees());
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//加载指定联盟帐号信息
	 @ResponseBody
	 @RequestMapping("/data/emp/info")
	 public JsonResult getEmployeeDetail(@RequestParam int empId){
		 JsonResult json=new JsonResult();
		 try {
			json.setData(employeeService.getEmployee(empId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	 }
	
	//新增或更新地推人员
	@ResponseBody
	@RequestMapping("/data/emp/update")
	public JsonResult addOrUpdateBd(@ModelAttribute EmployeeInfo employee){
		JsonResult json=new JsonResult();
		try {
			EmployeeInfo emp=employeeService.getEmployeeByUnionName(employee.getUnionName());
			if(employee.getId()>0){
				if(null==emp||emp.getId()==employee.getId()){
					employeeService.updateEmployee(employee);
				}else{
					json.setResult(2);
				}
			}else{
				if(null==emp){
					employeeService.addEmployee(employee);
				}else{
					json.setResult(2);
				}
			}
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
}
