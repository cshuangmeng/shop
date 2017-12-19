package com.gaoling.shop.pay.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.HttpClientUtil;
import com.gaoling.shop.common.SignUtil;
import com.gaoling.shop.common.XMLUtil;
import com.gaoling.shop.order.service.OrderService;
import com.gaoling.shop.system.pojo.PayParam;

import net.sf.json.JSONObject;

@Service
public class PayService {
	
	@Autowired
	private OrderService orderService;
	
	//处理用户支付请求
	public Map<String,Object> operateUserPayRequest(PayParam param)throws Exception{
		if(param.getPayWay()==AppConstant.WEIXIN_PAY_WAY){
			return weiXinPayRequest(param);
		}
		return null;
	}
	
	//处理用户支付请求
	public Map<String,Object> weiXinPayRequest(PayParam param)throws Exception{
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("appid",AppConstant.USERMP_APP_ID);
		paramMap.put("mch_id",AppConstant.USERMP_MCH_ID);
		paramMap.put("body",param.getBody());
		paramMap.put("total_fee",Math.round(param.getAmount()*100));//单位为分
		paramMap.put("nonce_str",param.getNonceStr());
		paramMap.put("out_trade_no",param.getTradeNo());
		paramMap.put("spbill_create_ip",param.getIp());
		paramMap.put("timeStamp", param.getTimestamp());
		paramMap.put("time_start",param.getStartTime());
		paramMap.put("trade_type",param.getTradeType());
		paramMap.put("openid",param.getOpenId());
		paramMap.put("notify_url", AppConstant.USERMP_PAY_NOTIFY);
		paramMap.put("sign", SignUtil.signValue(paramMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
		Logger.getLogger("file").info("<------请求参数----->"+JSONObject.fromObject(paramMap).toString());
		String response=HttpClientUtil.sendHTTPSWithXML(AppConstant.WEIXIN_PAY_UNIFIEDORDER,XMLUtil.createXMLString(paramMap, "xml"), ContentType.APPLICATION_XML);
		Logger.getLogger("file").info("<------响应内容----->"+response);
		//保存订单数据
		if(null!=response){
			Map<String,Object> responseMap=XMLUtil.readParamsFromXML(response);
			if(responseMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
				if(responseMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
					//设置返回值
					resultMap.put("appId", paramMap.get("appid"));
					resultMap.put("timeStamp", paramMap.get("timeStamp").toString());
					resultMap.put("nonceStr", paramMap.get("nonce_str").toString());
					resultMap.put("package", "prepay_id="+responseMap.get("prepay_id"));
					resultMap.put("signType", "MD5");
					resultMap.put("paySign", SignUtil.signValue(resultMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
					resultMap.put("out_trade_no",paramMap.get("out_trade_no"));
				}
			}
		}
		return resultMap;
	}
	
	//处理用户支付通知
	public Map<String,Object> operateUserPayNotify(HttpServletRequest request)throws Exception{
		return weiXinPayNotify(request);
	}
	
	//处理用户支付通知
	@Transactional
	public Map<String,Object> weiXinPayNotify(HttpServletRequest request)throws Exception{
		HashMap<String,Object> resultMap=new HashMap<String,Object>();
		//读取请求体
		StringBuilder response=new StringBuilder();
		BufferedReader reader=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String row=null;
		while((row=reader.readLine())!=null){
			response.append(row);
		}
		Logger.getLogger("file").info("<------接收到数据----->"+response.toString());
		//保存订单数据
		if(response.toString().length()>0){
			Map<String,Object> paramMap=XMLUtil.readParamsFromXML(response.toString());
			//校验签名是否正确
			String sign=paramMap.get("sign").toString();
			paramMap.remove("sign");
			if(SignUtil.signValue(paramMap, "MD5", AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase().equals(sign)){
				if(paramMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
					if(paramMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
						int amount=Integer.parseInt(paramMap.get("total_fee").toString());
						String outTradeNo=paramMap.get("transaction_id").toString();
						String tradeNo=paramMap.get("out_trade_no").toString();
						orderService.operatePayNotify(tradeNo, outTradeNo, amount/100f);
						resultMap.put("return_code", "SUCCESS");
						resultMap.put("return_msg", "OK");
					}
				}
			}
		}
		if(!resultMap.containsKey("return_code")){
			resultMap.put("return_code", "FAIL");
			resultMap.put("return_msg", "CHECK ERROR");
		}
		return resultMap;
	}
	
	//处理用户退款请求
	public boolean operateRefundPayRequest(PayParam param)throws Exception{
		if(param.getPayWay()==AppConstant.WEIXIN_PAY_WAY){
			return weiXinRefundRequest(param);
		}
		return false;
	}
	
	//处理用户退款请求
	@Transactional
	public boolean weiXinRefundRequest(PayParam param)throws Exception{
			//加载用户支付成功的订单
			HashMap<String,Object> paramMap=new HashMap<String,Object>();
			paramMap.put("appid",AppConstant.USERMP_APP_ID);
			paramMap.put("mch_id",AppConstant.USERMP_MCH_ID);
			paramMap.put("nonce_str",param.getNonceStr());
			paramMap.put("transaction_id", param.getOutTradeNo());
			paramMap.put("out_refund_no", param.getTradeNo());
			paramMap.put("total_fee",Math.round(param.getAmount()*100));//单位为分
			paramMap.put("refund_fee", Math.round(param.getRefund()*100));//单位为分
			paramMap.put("op_user_id", param.getOperator());
			paramMap.put("sign", SignUtil.signValue(paramMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
			Logger.getLogger("file").info("<------微信退款请求参数----->"+JSONObject.fromObject(paramMap).toString());
			String response=HttpClientUtil.sendHTTPSWithP12(AppConstant.WEIXIN_ORDER_REFUND,
					XMLUtil.createXMLString(paramMap, "xml"), AppConstant.USERMP_MCH_ID,AppConstant.USERMP_PAY_CERT);
			Logger.getLogger("file").info("<------微信退款响应内容----->"+response);
			//保存订单数据
			if(response.length()>0){
				Map<String,Object> responseMap=XMLUtil.readParamsFromXML(response);
				if(responseMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
					if(responseMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
						return true;
					}
				}
			}
		return false;
	}
	
	//处理用户提现请求
	public boolean operateUserTransferRequest(PayParam param){
		if(param.getPayWay()==AppConstant.WEIXIN_PAY_WAY){
			return weiXinTransferRequest(param);
		}
		return false;
	}
	
	public static boolean weiXinTransferRequest(PayParam param){
		//加载用户支付成功的订单
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("mch_appid",AppConstant.USERMP_APP_ID);
		paramMap.put("mchid",AppConstant.USERMP_MCH_ID);
		paramMap.put("nonce_str",param.getNonceStr());
		paramMap.put("partner_trade_no", param.getOutTradeNo());
		paramMap.put("openid", param.getOpenId());
		paramMap.put("check_name", "NO_CHECK");
		paramMap.put("amount",Math.round(param.getAmount()*100));
		paramMap.put("desc", param.getBody());
		paramMap.put("spbill_create_ip", param.getIp());
		paramMap.put("sign", SignUtil.signValue(paramMap, "MD5",AppConstant.USERMP_PAY_SECRET_KEY).toUpperCase());
		Logger.getLogger("file").info("<------微信企业支付请求参数----->"+JSONObject.fromObject(paramMap).toString());
		String response=HttpClientUtil.sendHTTPSWithP12(AppConstant.WEIXIN_TRANSFER_SEND,
				XMLUtil.createXMLString(paramMap, "xml"), AppConstant.USERMP_MCH_ID,AppConstant.USERMP_PAY_CERT);
		Logger.getLogger("file").info("<------微信企业支付响应内容----->"+response);
		//保存订单数据
		if(response.length()>0){
			Map<String,Object> responseMap=XMLUtil.readParamsFromXML(response);
			if(responseMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")){
				if(responseMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")){
					return true;
				}
			}
		}
		return false;
	}
	
}
