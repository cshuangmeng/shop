package com.gaoling.shop.tribe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.dao.TribeMemberDao;
import com.gaoling.shop.tribe.pojo.TribeMember;

@Service
public class TribeMemberService extends CommonService{

	@Autowired
	private TribeMemberDao tribeMemberDao;
	
	//获取我的成员信息
	public TribeMember queryTribeMemberByUserId(int userId,int tribeId){
		List<TribeMember> members=queryTribeMembers(DataUtil.mapOf("userId",userId,"tribeId",tribeId));
		return members.size()>0?members.get(0):null;
	}
	
	//加载指定部落的成员信息
	public List<Map<String,Object>> queryMyTribeMembers(int tribeId){
		return tribeMemberDao.queryMyTribeMembers(tribeId);
	}
	
	//获取成员信息
	public TribeMember getRecommender(int userId){
		List<TribeMember> members=queryTribeMembers(DataUtil.mapOf("userId",userId));
		return members.size()>0?members.get(0):null;
	}
	
	//查询部落成员信息
	public List<TribeMember> queryTribeMembers(Map<Object,Object> param){
		return tribeMemberDao.queryTribeMembers(param);
	}
	
	//保存部落成员信息
	public void addTribeMember(TribeMember tribe){
		tribeMemberDao.addTribeMember(tribe);
	}
	
	//更新部落成员信息
	public void updateTribeMember(TribeMember tribe){
		tribeMemberDao.updateTribeMember(tribe);
	}
	
}
