package com.gaoling.admin.util;

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
			params.put(e.getName(), e.getText());
		}
		return params;
	}
	
	public static void main(String[] args) {
		try {
			String response="<xml><appid><![CDATA[wx250bd1433fb8ab2a]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1306782801]]></mch_id><nonce_str><![CDATA[bNDIFnCTvpdfztgggqpuADvatIijMokI]]></nonce_str><openid><![CDATA[oSMNJwP_koxgO98bswZQvrrS76cQ]]></openid><out_trade_no><![CDATA[20160121220026429633861045929781]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[A7D809FAFD40730B0C41EAFA3C56DD44]]></sign><time_end><![CDATA[20160121222540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1003020509201601212828614616]]></transaction_id></xml>";
			Map<String,Object> paramMap=readParamsFromXML(response);
			System.out.println("<------请求参数----->");
			for (Iterator<String> iterator = paramMap.keySet().iterator(); iterator.hasNext();) {
				String type = iterator.next();
				System.out.println(type+"="+paramMap.get(type));
			}
			//校验签名是否正确
			String key="2016futeplusfutejia201510futejia";
			String sign=paramMap.get("sign").toString();
			paramMap.remove("sign");
			System.out.println(SignUtil.signValue(paramMap, "MD5", key).equals(sign));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
