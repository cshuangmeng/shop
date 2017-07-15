package com.gaoling.shop.pay.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.pay.dao.PayRefundSummaryDao;
import com.gaoling.shop.pay.pojo.PayRefundSummary;

@Service
public class PayRefundSummaryService {

	@Autowired
	private PayRefundSummaryDao payRefundSummaryDao;
	
	//获取支付退款汇总信息
	public PayRefundSummary getPayRefundSumary(int id){
		List<PayRefundSummary> summarys=queryPayRefundSumarys(DataUtil.mapOf("id",id));
		return summarys.size()>0?summarys.get(0):null;
	}
		
	//依据交易类型和交易ID查询支付退款汇总信息
	public PayRefundSummary queryPayRefundSumaryByTrade(int tradeType,int tradeId){
		List<PayRefundSummary> summarys=queryPayRefundSumarys(DataUtil.mapOf("tradeType",tradeType,"tradeId",tradeId));
		return summarys.size()>0?summarys.get(0):null;
	}
	
	//查询支付退款汇总信息
	public List<PayRefundSummary> queryPayRefundSumarys(Map<Object, Object> param){
		return payRefundSummaryDao.queryPayRefundSumarys(param);
	}
	
	//新增支付退款汇总信息
	public void addPayRefundSummary(PayRefundSummary payRefundSummary){
		payRefundSummaryDao.addPayRefundSummary(payRefundSummary);
	}
	
	//更新支付退款汇总信息
	public void updatePayRefundSummary(PayRefundSummary payRefundSummary){
		payRefundSummaryDao.updatePayRefundSummary(payRefundSummary);
	}
	
}
