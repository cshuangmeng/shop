package com.gaoling.admin.shop.controller;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaoling.admin.shop.service.CooRequestService;

@Controller
public class CooRequestController {

	@Autowired
	private CooRequestService cooRequestService;
	
	//进入合作请求列表页面
	@RequestMapping("/data/coo")
	public String index(){
		return "data/coo";
	}
	
	//加载合作请求
	@ResponseBody
	@RequestMapping("/data/coo/list")
	public HashMap<String,Object> loadCooRequestList(@RequestParam int iDisplayStart
			,@RequestParam int iDisplayLength,@RequestParam(value="sSearch",required=false)String sSearch){
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("iTotalDisplayRecords", cooRequestService.getCooRequestSize(sSearch));
		resultMap.put("data", cooRequestService.loadCooRequestList(sSearch,iDisplayStart, iDisplayLength));
		return resultMap;
	}
	
	//导出商户申请查询结果
	@RequestMapping("/data/coo/export")
	public void storeExport(@RequestParam String sSearch,HttpServletRequest request
			,HttpServletResponse response){
		try {
			String agent=request.getHeader("user-agent");
			response.setContentType("application/vnd.ms-excel");
			String fileName="商户合作申请";
			if(agent.contains("Safari")){
				fileName=new String(fileName.getBytes("GB2312"), "ISO-8859-1");
			}else if(agent.contains("Firefox")){
				fileName=new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			}else{
				fileName=URLEncoder.encode(fileName, "UTF-8");
			}
	        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
	        cooRequestService.writeResultToExcel(sSearch, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
