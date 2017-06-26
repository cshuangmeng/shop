package com.gaoling.admin.shop.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.shop.entity.JsonResult;
import com.gaoling.admin.shop.entity.StoreDetail;
import com.gaoling.admin.shop.entity.StoreInfo;
import com.gaoling.admin.shop.entity.StoreQueryBean;
import com.gaoling.admin.shop.service.AreaService;
import com.gaoling.admin.shop.service.BdService;
import com.gaoling.admin.shop.service.DictInfoService;
import com.gaoling.admin.shop.service.StoreDetailService;
import com.gaoling.admin.shop.service.StoreService;

@Controller
public class StoreController {
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private DictInfoService dictService;
	@Autowired
	private BdService bdService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private StoreDetailService storeDetailService;

	//进入商户列表页面
	@RequestMapping("/data/store")
	public String storeindex(Model model){
		try {
			model.addAttribute("cityList", areaService.loadCityList());
			model.addAttribute("storeTypeList", dictService.loadDicts("store_type"));
			model.addAttribute("stateList", dictService.loadDicts("store_examine_state"));
			model.addAttribute("bdList", bdService.loadAllBdFullName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "data/store";
	}

	//拼装查询条件查询指定条件小的商户信息
	@RequiresRoles("bd")
	@ResponseBody
	@RequestMapping("/data/store/query")
	public JsonResult storeQuery(@ModelAttribute("storeQuery")StoreQueryBean storeQuery){
		JsonResult json=new JsonResult();
		try {
			json.setData(storeService.loadStoreWithQuery(storeQuery));
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	//导出商户查询结果
	@RequestMapping("/data/store/export")
	public void storeExport(@ModelAttribute("storeQuery")StoreQueryBean storeQuery,
			HttpServletRequest request,HttpServletResponse response){
		try {
			String agent=request.getHeader("user-agent");
			response.setContentType("application/vnd.ms-excel");
			String fileName="商户信息";
			if(agent.contains("Safari")){
				fileName=new String(fileName.getBytes("GB2312"), "ISO-8859-1");
			}else if(agent.contains("Firefox")){
				fileName=new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			}else{
				fileName=URLEncoder.encode(fileName, "UTF-8");
			}
	        response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
	        storeService.writeResultToExcel(storeQuery, response);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//进入商户编辑页面
	@RequestMapping("/data/store/update")
	public String updateStore(@RequestParam(value="storeId",required=false,defaultValue="0")int storeId
			,Model model){
		//加载表单基本数据
		model.addAttribute("cityList", areaService.loadCityList());
		model.addAttribute("storeTypeList", dictService.loadDicts("store_type"));
		model.addAttribute("bdList", bdService.loadAllBdFullName());
		if(storeId>0){
			StoreInfo storeInfo=storeService.getStore(storeId);
			model.addAttribute("areaInfo", areaService.getAreaByAreacode(areaService.getAreaByAreacode(storeInfo.getAreacode()).getCityCode()));
			model.addAttribute("storeInfo", storeInfo);
			model.addAttribute("storeDetail", storeDetailService.getStoreDetailByStoreId(storeId));
		}
		return "data/storeUpdate";
	}
	
	//查看商户详细信息
	@RequestMapping("/data/store/info")
	public String storeDetail(@RequestParam int storeId,Model model){
		try {
			HashMap<String, Object> storeMap=storeService.loadStoreDetail(storeId);
			model.addAttribute("storeMap", storeMap);
			return "data/storeDetail";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//保存商户编辑信息
	@RequestMapping(value="/data/store/submit",method=RequestMethod.POST)
	public String submitStore(@ModelAttribute("store")StoreInfo store,@ModelAttribute("detail")StoreDetail detail
			,@RequestParam(value="logoFile",required=false)MultipartFile logoFile,HttpServletRequest request){
		try{
			storeService.editStore(store, detail, logoFile, request);
			return "redirect:/data/store";
		} catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
	//商户信息审核
	@ResponseBody
	@RequestMapping("/data/store/approve")
	public JsonResult approve(@RequestParam("storeIds")String storeIds,String status
			,@RequestParam(required=false)String reason){
		JsonResult json=new JsonResult();
		try {
			storeService.approveStoreInfo(storeIds, status, reason);
		} catch (Exception e) {
			json.setResult(1);
			e.printStackTrace();
		}
		return json;
	}
	
	// 录入excel
	@RequestMapping("/data/store/import")
	public String addExcel(@RequestParam MultipartFile excelFile,HttpServletRequest request)
			throws IOException {
		try {
			storeService.importStoreInfoByExcel(excelFile,request);
			return "redirect:/data/store";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/error";
	}
	
}
