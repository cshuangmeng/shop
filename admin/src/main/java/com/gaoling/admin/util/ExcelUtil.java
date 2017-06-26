package com.gaoling.admin.util;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	//将符合指定条件的商户信息写入Excel
	public static void writeResultToExcel(String[] titles,List<HashMap<String,Object>> dataList,HttpServletResponse response){
		try {
			//XSSFWorkbook
			HSSFWorkbook workbook=new HSSFWorkbook();
	        //产生工作表对象
	        HSSFSheet sheet=workbook.createSheet();
	        //创建表头
	        HSSFRow row=sheet.createRow(0);
	        HSSFCell cell=null;
	        HSSFFont font=workbook.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        HSSFCellStyle style=workbook.createCellStyle();
	        style.setFont(font);
	        for(int i=0;i<titles.length;i++){
	        	cell=row.createCell(i);
	        	cell.setCellValue(new HSSFRichTextString(titles[i].split(":")[0]));
	        	cell.setCellStyle(style);
	        }
	        //写入数据
	        HashMap<String,Object> resultMap=null;
	        String title=null;
	        String[] data=null;
	        for(int i=0;i<dataList.size();i++){
	        	resultMap=dataList.get(i);
	            row=sheet.createRow(i+1);
	            for(int j=0;j<titles.length;j++){
	            	title=titles[j];
	            	data=title.split(":");
		            cell=row.createCell(j);
		            if(data[2].equals("string")){
		            	cell.setCellValue(new HSSFRichTextString(
		            			null!=resultMap.get(data[1])?resultMap.get(data[1]).toString():""));
		            }else if(data[2].equals("number")){
		            	cell.setCellValue(null!=resultMap.get(data[1])
		            			?Double.parseDouble(resultMap.get(data[1]).toString()):0);
		            }
	            }
	        }
	        //将Excel写入浏览器
	        OutputStream os=response.getOutputStream();
	        workbook.write(os); 
	        workbook.close();
	        os.flush();
	        os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
