package com.gaoling.shop.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

public class OSSUtil {

	// 上传本地文件至OSS存储
	public static boolean uploadFileToOSS(File file,String fileName) {
		try {
			OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
					AppConstant.OSS_SECRETACCESSKEY);
			InputStream is = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(is.available());
			client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 上传本地文件至OSS存储
	public static boolean uploadFileToOSS(InputStream is,String fileName) {
		try {
			OSSClient client = new OSSClient(AppConstant.OSS_ENDPOINT, AppConstant.OSS_ACCESSKEYID,
					AppConstant.OSS_SECRETACCESSKEY);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(is.available());
			client.putObject(AppConstant.OSS_BUCKETNAME, fileName, is, meta);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			String xml="<ul><li title=\"华信生物药业股份有限公司\">厂名：华信生物药业股份有限公司</li><li title=\"界首市中原路439号\">厂址：界首市中原路439号</li><li title=\"0558-4820811\">厂家联系方式：0558-4820811</li><li title=\"730\">保质期：730 天</li><li title=\"华信牌富硒康口服液 250ml/瓶*2瓶\">产品名称：华信牌富硒康口服液 250ml/瓶*2瓶</li><li title=\"口服液\">产品剂型:口服液</li><li title=\"每日2次，每次30ml，启封后5日内饮完，勿用嘴对瓶口直接饮用。\">食用方法和食用量:每日2次，每次30ml，启封后5日内饮完，勿用嘴对瓶口直接饮用。</li><li title=\"1、本品不能代替药物；2、摇匀后饮用\">注意事项:1、本品不能代替药物；2、摇匀后饮用</li><li title=\"2003-02-13\">批准日期:2003-02-13</li><li title=\"无\">不适宜人群:无</li><li title=\"2010-09-07\">批准变更日期:2010-09-07</li><li title=\"置阴凉干燥处\">贮藏方法:置阴凉干燥处</li><li title=\"华信牌富硒康口服液\">产品名称:华信牌富硒康口服液</li><li title=\"免疫调节\">功能:免疫调节</li><li title=\"安徽省华信生物药业股份有限公司\">申请人中文名称:安徽省华信生物药业股份有限公司</li><li title=\"每100ml含：有机硒（以Se计）76μg\">功效成分/标志性成分含量:每100ml含：有机硒（以Se计）76μg</li><li title=\"硒摄入量不足而致的免疫力低下者。\">适宜人群:硒摄入量不足而致的免疫力低下者。</li><li title=\"富硒酵母、琼胶、甜蜜素\">主要原料:富硒酵母、琼胶、甜蜜素</li><li title=\"卫食健字(2003)第0038号\">批准文号:卫食健字(2003)第0038号</li></ul>";
			Document doc=DocumentHelper.parseText(xml);
			Element rootEle=doc.getRootElement();
			for (Iterator iterator = rootEle.elementIterator(); iterator.hasNext();) {
				Element e = (Element)iterator.next();
				System.out.println(e.getName()+","+e.getStringValue());
			}
			//System.out.println(JSONObject.fromObject(map));
			//boolean result=uploadFileToOSS(new File("/Users/huangmeng/Downloads/headImg.jpg"), "goods/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6)+".jpg");
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
