package com.gaoling.shop.tribe.service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.dao.TribeDao;
import com.gaoling.shop.tribe.pojo.Tribe;
import com.gaoling.shop.user.pojo.User;
import com.gaoling.shop.user.service.UserService;

@Service
public class TribeService extends CommonService{

	@Autowired
	private TribeDao tribeDao;
	@Autowired
	private UserService userService;
	@Autowired
	private TribeMemberService tribeMemberService;
	
	//获取我的部落成员列表
	public Result getMyTribeInfo(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Tribe tribe=getTribeByUserId(user.getId());
		if(null!=tribe){
			List<Map<String,Object>> members=tribeMemberService.queryMyTribeMembers(tribe.getId());
			return putResult(DataUtil.mapOf("tribe",tribe,"members",members));
		}
		return putResult();
	}
	
	//编辑部落名称
	public Result editTribeName(String uuid,String name){
		//检查参数
		if(StringUtils.isEmpty(uuid)||StringUtils.isEmpty(name)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		Tribe tribe=getTribeByUserId(user.getId());
		if(null==tribe){
			return putResult(AppConstant.TRIBE_NOT_EXISTS);
		}
		tribe.setNickname(name);
		updateTribe(tribe);
		return putResult();
	}
	
	//扣除/增加部落部落币、部落分
	@Transactional
	public Tribe operateTribeAccount(int tribeId,int point,int coin){
		Tribe tribe=getTribe(tribeId);
		boolean flag=true;
		if(null!=tribe){
			if((point<0&&tribe.getPoint()+point>=0)||point>=0){
				tribe.setPoint(tribe.getPoint()+point);
			}else{
				flag=false;
			}
			if((coin<0&&tribe.getCoin()+coin>=0)||coin>=0){
				tribe.setCoin(tribe.getCoin()+coin);
			}else{
				flag=false;
			}
		}else{
			flag=false;
		}
		if(flag){
			updateTribe(tribe);
		}else{
			throw new InvalidParameterException("Tribe Account Balance Inadequate!");
		}
		return tribe;
	}
	
	//获取部落信息
	public Tribe getTribeByUserId(int userId){
		List<Tribe> tribes=queryTribes(DataUtil.mapOf("userId",userId));
		return tribes.size()>0?tribes.get(0):null;
	}
	
	//获取部落信息
	public Tribe getTribe(int id){
		List<Tribe> tribes=queryTribes(DataUtil.mapOf("id",id));
		return tribes.size()>0?tribes.get(0):null;
	}
	
	//查询部落信息
	public List<Tribe> queryTribes(Map<Object,Object> param){
		return tribeDao.queryTribes(param);
	}
	
	//保存部落信息
	public void addTribe(Tribe tribe){
		tribeDao.addTribe(tribe);
	}
	
	//更新部落信息
	public void updateTribe(Tribe tribe){
		tribeDao.updateTribe(tribe);
	}
	
}
