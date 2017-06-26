package com.gaoling.admin.shop.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gaoling.admin.shop.dao.StoreDao;
import com.gaoling.admin.shop.entity.AreaInfo;
import com.gaoling.admin.shop.entity.BdInfo;
import com.gaoling.admin.shop.entity.EmployeeInfo;
import com.gaoling.admin.shop.entity.StoreDetail;
import com.gaoling.admin.shop.entity.StoreInfo;
import com.gaoling.admin.shop.entity.StoreQueryBean;
import com.gaoling.admin.util.AppConstant;
import com.gaoling.admin.util.DataUtil;
import com.gaoling.admin.util.DateUtil;
import com.gaoling.admin.util.ExcelUtil;
import com.gaoling.admin.util.HttpClientUtil;
import com.gaoling.admin.util.OSSUtil;
import com.gaoling.admin.util.PropertiesUtil;


@Service
public class StoreService {

	@Autowired
	private StoreDao storeDao;
	@Autowired
	private StoreDetailService storeDetailService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private DictInfoService dictService;
	@Autowired
	private BdService bdService;
	@Autowired
	private EmployeeService employeeService;
	
	//新增商户
	public int addStore(StoreInfo store){
		store.setStoreId(DataUtil.buildUUID());
		store.setCreateTime(DateUtil.getCurrentTime());
		//store.setExamineState(AppConstant.STORE_STATUS_ENUM.EXAMINING.getValue());
		storeDao.addStore(store);
		//保存联盟帐号
		EmployeeInfo emp=new EmployeeInfo();
		emp.setSid(store.getId());
		emp.setUnionName(employeeService.getUniqueUnionName(DataUtil.getPinYinHeadChar(store.getStoreName())));
		emp.setUnionPwd(DataUtil.createNums(6));
		emp.setUnionState(AppConstant.ACCOUNT_STATE_TYPE.ENABLED.getValue());
		employeeService.addEmployee(emp);
		return store.getId();
	}
	
	//更新商户
	public void updateStore(StoreInfo store){
		store.setUpdateTime(DateUtil.getCurrentTime());
		storeDao.updateStore(store);
	}
	
	//编辑商户信息
	@Transactional
	public void editStore(StoreInfo store,StoreDetail detail,MultipartFile logoFile
			,HttpServletRequest request)throws Exception{
		//保存商户信息
		if(store.getId()>0){
			updateStore(store);
		}else{
			addStore(store);
		}
		//保存图片
		String workPath=request.getServletContext().getRealPath("/");
		String dir="resources/images/tmp/";
		//创建文件夹
		File file=new File(workPath+dir);
		if(!file.exists()){
			file.mkdirs();
		}
		//设置详细信息
		StoreDetail sd=storeDetailService.getStoreDetailByStoreId(store.getId());
		if(null==sd){
			sd=detail;
			sd.setSid(store.getId());
		}else{
			sd.setServicePhone(detail.getServicePhone());
			sd.setBusinessHours(detail.getBusinessHours());
		}
		//上传logo图片
		String imgUrl=uploadMultiImgs(workPath,dir,new MultipartFile[]{logoFile});
		if(imgUrl.length()>0){
			sd.setLogoImg(imgUrl);
		}
		//保存详细信息
		if(sd.getId()>0){
			storeDetailService.updateStoreDetail(sd);
		}else{
			storeDetailService.addStoreDetail(sd);
		}
	}
	
	//批量上传图片至OSS
	private String uploadMultiImgs(String workPath,String dir,MultipartFile[] imgFiles)throws Exception{
		String imgUrl="";
		for(MultipartFile imgFile:imgFiles){
			if(!imgFile.isEmpty()&&DataUtil.isImgFile(imgFile.getOriginalFilename())){
				String imgName=Calendar.getInstance().getTimeInMillis()+DataUtil.createNums(5)
						+imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."));
				String savePath=workPath+dir+imgName;
				File file=new File(savePath);
				imgFile.transferTo(file);
				//上传至OSS
				OSSUtil.uploadFileToOSS(savePath);
				//删除临时文件
				file.delete();
				imgUrl+=imgUrl.length()>0?";"+AppConstant.OSS_CDN_SERVER+imgName:AppConstant.OSS_CDN_SERVER+imgName;
			}
		}
		return imgUrl;
	}
	
	//加载商户详细信息供WEB页面查看
	public HashMap<String,Object> loadStoreDetail(int storeId)throws Exception{
		HashMap<String,Object> storeMap=new HashMap<String, Object>();
		//加载商户信息
		StoreInfo store=storeDao.getStore(storeId);
		StoreDetail detail=storeDetailService.getStoreDetailByStoreId(storeId);
		storeMap.putAll(DataUtil.objectToMap(store));
		storeMap.putAll(DataUtil.objectToMap(detail));
		//转换代码属性为中文内容
		AreaInfo areaInfo=areaService.getAreaByAreacode(store.getAreacode());
		storeMap.put("city", null!=areaInfo&&null!=areaInfo.getCity()?areaInfo.getCity():"");
		storeMap.put("region", null!=areaInfo&&null!=areaInfo.getRegion()?areaInfo.getRegion():"");
		storeMap.put("typeName", dictService.getDictName("store_type", store.getTypeId()));
		storeMap.put("stateName", dictService.getDictName("store_examine_state", store.getExamineState()));
		BdInfo bd=bdService.getBd(store.getBdId());
		storeMap.put("bdName", null!=bd&&null!=bd.getFullname()?bd.getFullname():"");
		return storeMap;
	}
	
	//获取商户信息
	public StoreInfo getStore(int id){
		return storeDao.getStore(id);
	}
	
	//获取商户信息
	public StoreInfo getStoreByStoreId(String storeId){
		return storeDao.getStoreByStoreId(storeId);
	}
	
	//加载符合指定条件的商户信息
	public List<HashMap<String,Object>> loadStoreWithQuery(StoreQueryBean storeQuery){
		return storeDao.loadStoreWithQuery(storeQuery);
	}
	
	//将符合指定条件的商户信息写入Excel
	public void writeResultToExcel(StoreQueryBean storeQuery,HttpServletResponse response){
		try {
			//加载查询结果
			List<HashMap<String,Object>> dataList=loadStoreWithQuery(storeQuery);
			String[] titles = { "名称:storeName:string","类型:typeName:string", "城市:city:string"
					, "区划:region:string", "负责人:bdName:string", "充电宝数:pbTotalNo:number"
					, "状态:stateName:string", "创建时间:createTime:string" };
			ExcelUtil.writeResultToExcel(titles, dataList, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//通过Excel导入商户信息
	@Transactional
	public void importStoreInfoByExcel(MultipartFile excelFile,HttpServletRequest request) throws Exception {
		if (!excelFile.isEmpty()) {
			// 获取上传文件的后缀
			BufferedInputStream in = new BufferedInputStream(excelFile.getInputStream());
			StoreDetail storeDetail = new StoreDetail();
			StoreInfo storeInfo = new StoreInfo();
			HSSFWorkbook wb = new HSSFWorkbook(in);
			List<HSSFPictureData> pics=wb.getAllPictures();
			// 获取第一个表
			HSSFSheet sheet = wb.getSheetAt(0);
			DecimalFormat df = new DecimalFormat("#");
			for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
				HSSFRow row = sheet.getRow(rowNum);
				String bdName=row.getCell(0).getRichStringCellValue().getString();
				String typeName = row.getCell(1).getRichStringCellValue().getString();
				String storeName = row.getCell(2).getRichStringCellValue().getString();
				String address = row.getCell(3).getRichStringCellValue().getString();
				String region = row.getCell(4).getRichStringCellValue().getString();
				double lon = row.getCell(5).getNumericCellValue();
				double lat = row.getCell(6).getNumericCellValue();
				String businessHours = row.getCell(7).getRichStringCellValue().getString();
				String servicePhone = "";
				if(row.getCell(8).getCellType()==HSSFCell.CELL_TYPE_STRING){
					servicePhone=row.getCell(8).getRichStringCellValue().getString();
				}else{
					servicePhone=df.format(row.getCell(8).getNumericCellValue());
				}
				//判断商户是否已存在
				StoreInfo oldStore=loadStoreByFullName(storeName);
				if(null!=oldStore){
					continue;
				}
				storeInfo.setTypeId(dictService.getDictByName("store_type", typeName).getDictValue());
				storeInfo.setBdId(bdService.loadBdByFullName(bdName).getId());
				storeInfo.setStoreName(storeName);
				storeInfo.setAddress(address);
				storeInfo.setAreacode(areaService.getAreaByRegion(region).getAreacode());
				storeInfo.setLon((float)lon);
				storeInfo.setLat((float)lat);
				addStore(storeInfo);
				storeDetail.setLogoImg(saveImgFromExcel(rowNum, pics, sheet, request));
				storeDetail.setSid(storeInfo.getId());
				storeDetail.setBusinessHours(businessHours);
				storeDetail.setServicePhone(servicePhone);
				storeDetailService.addStoreDetail(storeDetail);
				EmployeeInfo employee=new EmployeeInfo();
				employee.setSid(storeInfo.getId());
				employee.setUnionName(employeeService.getUniqueUnionName(DataUtil.getPinYinHeadChar(storeName)));
				employee.setUnionPwd(DataUtil.createNums(6));
				employeeService.addEmployee(employee);
			}
			wb.close();
		}
	}
	
	//判断单元格中是否存在图片
	public String saveImgFromExcel(int row,List<HSSFPictureData> pics,HSSFSheet sheet,HttpServletRequest request)throws Exception{
		for(HSSFShape shape:sheet.getDrawingPatriarch().getChildren()){
			if(shape instanceof HSSFPicture){
				HSSFClientAnchor anchor=(HSSFClientAnchor)shape.getAnchor();
				HSSFPicture pic=(HSSFPicture)shape;
				if(row==anchor.getRow1()){
					HSSFPictureData imgData=pics.get(pic.getPictureIndex()-1);
					String imgName=Calendar.getInstance().getTimeInMillis()+DataUtil.createNums(5)+".jpg";
					//保存图片
					String workPath=request.getServletContext().getRealPath("/");
					String dir="resources/images/tmp/";
					//创建文件夹
					File file=new File(workPath+dir);
					if(!file.exists()){
						file.mkdirs();
					}
					String savePath=workPath+dir+imgName;
					file=new File(savePath);
					FileUtils.copyInputStreamToFile(new ByteArrayInputStream(imgData.getData()), file);
					//上传至OSS
					OSSUtil.uploadFileToOSS(savePath);
					//删除临时文件
					file.delete();
					return AppConstant.OSS_CDN_SERVER+imgName;
				}
			}
		}
		return null;
	}
	
	//审核商户信息
	public boolean approveStoreInfo(String storeIds,String status,String reason){
		if(StringUtils.isNotEmpty(storeIds)){
			String[] storeIdArray=storeIds.split(",");
			//逐个处理商户审核信息
			for(int i=0;i<storeIdArray.length;i++){
				int storeId=Integer.parseInt(storeIdArray[i]);
				StoreInfo store=storeDao.getStore(storeId);
				boolean flag=false;
				/*if(store.getExamineState().equals(AppConstant.STORE_STATUS_ENUM.EXAMINING.getValue())){//审核拒绝或下线
					if(status.equals(AppConstant.STORE_STATUS_ENUM.PASSED.getValue())){
						store.setCooperateTime(DateUtil.getCurrentTime());
						store.setExamineState(status);
						flag=true;
					}else if(status.equals(AppConstant.STORE_STATUS_ENUM.REFUSED.getValue())){
						store.setExamineState(status);
						StoreDetail detail=storeDetailService.getStoreDetailByStoreId(store.getId());
						detail.setReason(reason);
						storeDetailService.updateStoreDetail(detail);
						flag=true;
					}
				}else if(store.getExamineState().equals(AppConstant.STORE_STATUS_ENUM.PASSED.getValue())){//审核通过
					if(status.equals(AppConstant.STORE_STATUS_ENUM.OVERDUED.getValue())){
						store.setExamineState(status);
						flag=true;
					}
				}*/
				if(flag){
					updateStore(store);
					//发送消息
					HttpClientUtil.getNetWorkInfo(PropertiesUtil.getProperty("bdmsg_send_url")+"?storeId="+store.getStoreId());
				}
			}
		}
		return true;
	}
	
	//根据商户名查询商户
	public StoreInfo loadStoreByFullName(String fullName){
		return storeDao.getStoreByFullName(fullName);
	}					
	
}
