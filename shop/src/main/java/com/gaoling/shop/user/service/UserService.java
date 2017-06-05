package com.gaoling.shop.user.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.shop.common.AppConstant;
import com.gaoling.shop.common.DataUtil;
import com.gaoling.shop.common.DateUtil;
import com.gaoling.shop.common.HttpClientUtil;
import com.gaoling.shop.common.MemcachedUtil;
import com.gaoling.shop.common.SMSUtil;
import com.gaoling.shop.system.pojo.Result;
import com.gaoling.shop.system.service.CommonService;
import com.gaoling.shop.tribe.pojo.Tribe;
import com.gaoling.shop.tribe.service.TribeService;
import com.gaoling.shop.user.dao.UserDao;
import com.gaoling.shop.user.pojo.User;

import net.sf.json.JSONObject;

@Service
public class UserService extends CommonService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private TribeService tribeService;
	
	//下发验证码
	public Result sendCode(String mobile){
		if(StringUtils.isEmpty(mobile)){
			Logger.getLogger("file").info("UserService | sendCode | mobile="+mobile);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		if(!DataUtil.isPhoneNum(mobile)){
			Logger.getLogger("file").info("UserService | sendCode | mobile="+mobile);
			return putResult(AppConstant.DATA_FORMAT_INCORRECT);
		}
		String code=DataUtil.createNums(4);
		//发送验证码
		if(SMSUtil.send(mobile, code)){
			//存储验证码
			int expireMins=getInteger("sms_code_expire_mins");
			MemcachedUtil.getInstance().setData(AppConstant.CHECKCODE_PREFIX+mobile, code, expireMins);
			Logger.getLogger("file").info("UserService | sendCode | mobile="+mobile+" | code="+code);
			return putResult();
		}
		return putResult(AppConstant.SMS_SEND_FAILURE);
	}
	
	//用户注册
	@Transactional
	public Result register(String code,String mobile,String openId){
		//检查参数是否填写
		if(StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(openId)){
			Logger.getLogger("file").info("UserService | register | mobile="+mobile+" | code="+code+" | openId="+openId);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查验证码是否正确
		String saveCode=MemcachedUtil.getInstance().getData(AppConstant.CHECKCODE_PREFIX+mobile,"");
		if(!saveCode.equals(code)){
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		//检查用户是否存在
		User user=getUserByOpenId(openId);
		if(null==user){
			//查询用户微信信息
			String response=HttpClientUtil.getNetWorkInfo(AppConstant.WEIXIN_SNS_USERINFO_URL
					+"&access_token="+AppConstant.USERMP_ACCESS_TOKEN+"&openid="+openId, "");
			if(StringUtils.isNotEmpty(response)){
				JSONObject json=JSONObject.fromObject(response);
				if(!json.containsKey("errcode")){
					user=new User();
					user.setUuid(DataUtil.buildUUID());
					user.setCellphone(mobile);
					user.setCreateTime(DateUtil.nowDate());
					user.setHeadImg(json.getString("headimgurl"));
					user.setLoginTime(user.getCreateTime());
					user.setNickname(json.getString("nickname"));
					user.setOpenId(openId);
					user.setPassword("");
					user.setState(User.STATE_TYPE_ENUM.ACTIVATED.getState());
					addUser(user);
				}else{
					return putResult(AppConstant.OPERATE_FAILURE);
				}
			}else{
				return putResult(AppConstant.OPERATE_FAILURE);
			}
		}
		return putResult(user);
	}
	
	//用户登录
	public Result login(String openId){
		//检查参数是否填写
		if(StringUtils.isEmpty(openId)){
			Logger.getLogger("file").info("UserService | login | openId="+openId);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查用户是否存在
		User user=getUserByOpenId(openId);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//更新用户登录时间
		user.setLoginTime(DateUtil.nowDate());
		updateUser(user);
		//加载我的部落
		Tribe tribe=tribeService.getTribeByUserId(user.getId());
		if(null!=tribe){
			user.getExtras().put("tribeCode", tribe.getId());
		}
		return putResult(user);
	}
	
	//扣除/增加用户部落币、部落分
	@Transactional
	public boolean operateAccount(int userId,int point,int coin)throws Exception{
		User user=getUser(userId, true);
		boolean flag=true;
		if(null!=user){
			if((point<0&&user.getPoint()+point>=0)||point>=0){
				user.setPoint(user.getPoint()+point);
			}else{
				flag=false;
			}
			if((coin<0&&user.getCoin()+coin>=0)||coin>=0){
				user.setCoin(user.getCoin()+coin);
			}else{
				flag=false;
			}
		}else{
			flag=false;
		}
		if(flag){
			updateUser(user);
		}else{
			throw new Exception("User Account Balance Inadequate!");
		}
		return false;
	}
	
	//查询单一用户
	public User getUser(int id,boolean lock){
		return userDao.getUser(id, lock);
	}
	
	//依据openId查询用户
	public User getUserByOpenId(String openId){
		List<User> users=queryUsers(DataUtil.mapOf("openId",openId));
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
