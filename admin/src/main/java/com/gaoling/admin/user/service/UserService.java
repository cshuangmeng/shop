package com.gaoling.admin.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.admin.system.service.CommonService;
import com.gaoling.admin.user.dao.UserDao;
import com.gaoling.admin.user.pojo.User;
import com.gaoling.admin.util.DataUtil;

@Service
public class UserService extends CommonService{

	@Autowired
	private UserDao userDao;
	
	//查询单一用户
	public User getUser(int id,boolean lock){
		return userDao.getUser(id, lock);
	}
	
	//更新用户提现特权
	public void giveUserCashExchangeRight(String userIds,int right){
		for(String userId:userIds.split(",")){
			User user=getUser(Integer.valueOf(userId), false);
			user.setCashExchangeEnable(right);
			updateUser(user);
		}
	}
	
	//依据cellphone查询用户
	public User getUserByCellphone(String cellphone){
		List<User> users=queryUsers(DataUtil.mapOf("cellphone",cellphone));
		return users.size()>0?users.get(0):null;
	}
	
	//依据UUID查询用户
	public User getUserByUUID(String uuid){
		List<User> users=queryUsers(DataUtil.mapOf("uuid",uuid));
		return users.size()>0?users.get(0):null;
	}
	
	//查询多用户
	public List<User> queryUsers(Map<Object,Object> param){
		return userDao.queryUsers(param);
	}
	
	//新增用户
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	//更新用户
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
}
