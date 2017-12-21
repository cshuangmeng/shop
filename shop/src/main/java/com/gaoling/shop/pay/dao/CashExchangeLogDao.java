package com.gaoling.shop.pay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.pay.pojo.CashExchangeLog;

@Repository
public interface CashExchangeLogDao {

	List<CashExchangeLog> selectCashExchangeLog(@Param("param")Map<String,Object> param);
	int insertCashExchangeLog(CashExchangeLog cashExchangeLog);
	int updateCashExchangeLog(@Param("record")CashExchangeLog cashExchangeLog,@Param("param")Map<String,Object> param);

}
