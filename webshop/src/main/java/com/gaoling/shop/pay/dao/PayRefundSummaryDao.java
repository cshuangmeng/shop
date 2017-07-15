package com.gaoling.shop.pay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.pay.pojo.PayRefundSummary;

@Repository
public interface PayRefundSummaryDao {

	List<PayRefundSummary> queryPayRefundSumarys(@Param("param") Map<Object, Object> param);
	void addPayRefundSummary(PayRefundSummary payRefundSummary);
	void updatePayRefundSummary(PayRefundSummary payRefundSummary);

}
