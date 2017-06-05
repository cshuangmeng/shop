package com.gaoling.shop.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.user.dao.AddressDao;
import com.gaoling.shop.user.pojo.Address;
import com.gaoling.shop.user.pojo.User;

@Service
public class AddressService extends CommonService{

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserService userService;
	
	//加载用户地址
	public Result loadMyAddresses(String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		return putResult(queryAddresses(DataUtil.mapOf("userId",user.getId())));
	}
	
	//新增地址
	public Result saveNewAddress(Address address,String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		address.setUserId(user.getId());
		address.setState(Address.STATE_TYPE_ENUM.enabled.getState());
		address.setCreateTime(DateUtil.nowDate());
		addAddress(address);
		return putResult();
	}
	
	//新增地址
	public Result getAddressInfo(String uuid,int id){
		//检查参数
		if(StringUtils.isEmpty(uuid)){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//检查是否修改的自己的地址
		Address oldAddress=getAddress(id);
		if(null==oldAddress||oldAddress.getUserId()!=user.getId()){
			return putResult(AppConstant.NOT_MYSELF_OPERATE);
		}
		return putResult(oldAddress);
	}
	
	//修改地址
	public Result updateOldAddress(Address address,String uuid){
		//检查参数
		if(StringUtils.isEmpty(uuid)||address.getId()<=0){
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//加载用户
		User user=userService.getUserByUUID(uuid);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//检查是否修改的自己的地址
		Address oldAddress=getAddress(address.getId());
		if(null==oldAddress||oldAddress.getUserId()!=user.getId()){
			return putResult(AppConstant.NOT_MYSELF_OPERATE);
		}
		address.setState(oldAddress.getState());
		updateAddress(address);
		return putResult();
	}
	
	//获取用户的默认地址
	public Address getDefaultAddresses(int userId){
		List<Address> addresses=queryAddresses(DataUtil.mapOf("userId",userId,"isDefault",1));
		return addresses.size()>0?addresses.get(0):null;
	}
	
	//查询地址
	public List<Address> queryAddresses(Map<Object,Object> param){
		return addressDao.queryAddresses(param);
	}
	
	//查询地址
	public Address getAddress(int id){
		List<Address> results=addressDao.queryAddresses(DataUtil.mapOf("id",id));
		return results.size()>0?results.get(0):null;
	}
	
	//保存地址
	public void addAddress(Address address){
		addressDao.addAddress(address);
	}
	
	//更新地址
	public void updateAddress(Address address){
		addressDao.updateAddress(address);
	}
	
}
