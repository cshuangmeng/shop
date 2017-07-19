package com.gaoling.webshop.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {
	
	//XML组装
	public static String createXMLString(Map<String,Object> params,String root){
		Document doc=DocumentHelper.createDocument();
		Element rootEle=doc.addElement(root);
		Element e=null;
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			e=rootEle.addElement(key);
			e.setText(params.get(key).toString());
		}
		return doc.asXML();
	}
	
	//读取XML
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> readParamsFromXML(String xml)throws Exception{
		Map<String,Object> params=new HashMap<String,Object>();
		Document doc=DocumentHelper.parseText(xml);
		Element rootEle=doc.getRootElement();
		for (Iterator iterator = rootEle.elementIterator(); iterator.hasNext();) {
			Element e = (Element)iterator.next();
			params.put(e.getName(), e.getStringValue());
		}
		return params;
	}
	
}
