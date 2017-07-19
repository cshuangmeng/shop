package com.gaoling.webshop.tribe.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.webshop.tribe.pojo.TribeMember;

@Repository
public interface TribeMemberDao {

	List<TribeMember> queryTribeMembers(@Param("param")Map<Object,Object> param);
	List<Map<String,Object>> queryMyTribeMembers(int tribeId);
	void addTribeMember(TribeMember tribe);
	void updateTribeMember(TribeMember tribe);
	
	
}
