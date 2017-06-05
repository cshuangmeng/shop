package com.gaoling.shop.pay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.shop.pay.pojo.UserTradeLog;

@Repository
public interface UserTradeLogDao {

	void addUserTradeLog(UserTradeLog userTradeLog);
	List<Map<String,Object>> queryUserTradeLogs(@Param("userId")int userId);

}
