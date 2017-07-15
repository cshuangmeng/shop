package com.gaoling.shop.user.service;

import java.net.URL;
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
import com.gaoling.shop.common.OSSUtil;
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
	public Result register(String code,String cellphone,String openId,String password,int platform)throws Exception{
		//检查参数是否填写
		if(StringUtils.isEmpty(code)||StringUtils.isEmpty(cellphone)||(StringUtils.isEmpty(openId)&&StringUtils.isEmpty(password))){
			Logger.getLogger("file").info("UserService | register | cellphone="+cellphone
					+",code="+code+",openId="+openId+",password="+password+",platform="+platform);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查验证码是否正确
		String saveCode=MemcachedUtil.getInstance().getData(AppConstant.CHECKCODE_PREFIX+cellphone,"");
		if(!saveCode.equals(code)){
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		//使用微信注册
		JSONObject json=null;
		if(StringUtils.isNotEmpty(openId)){
			String url=AppConstant.WEIXIN_SNS_USERINFO_URL+"&access_token="+AppConstant.USERMP_ACCESS_TOKEN+"&openid="+openId;
			if(platform==AppConstant.PLATFORM_TYPE_ENUM.PC.getType()){
				url=AppConstant.PC_SNS_USERINFO_URL+"?access_token="+MemcachedUtil.getInstance().getData(openId, "")+"&openid="+openId;
			}
			String response=HttpClientUtil.getNetWorkInfo(url, "");
			if(StringUtils.isNotEmpty(response)){
				json=JSONObject.fromObject(response);
			}
		}
		//检查用户是否存在
		User user=getUserByCellphone(cellphone);
		if(null==user){//新用户
			if(null!=json&&!json.containsKey("errcode")){//使用微信注册
				user=new User();
				user.setUuid(DataUtil.buildUUID());
				user.setCellphone(cellphone);
				user.setCreateTime(DateUtil.nowDate());
				user.setHeadImg("user/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6)+".jpg");
				OSSUtil.uploadFileToOSS(new URL(json.getString("headimgurl")).openStream(), user.getHeadImg());
				user.setLoginTime(user.getCreateTime());
				user.setNickname(json.getString("nickname"));
				user.setOpenId(platform!=AppConstant.PLATFORM_TYPE_ENUM.PC.getType()?openId:"");
				user.setWebId(platform==AppConstant.PLATFORM_TYPE_ENUM.PC.getType()?openId:"");
				user.setUnionId(json.getString("unionid"));
				user.setPassword("");
				user.setState(User.STATE_TYPE_ENUM.ACTIVATED.getState());
				addUser(user);
			}else{//使用手机号注册
				user=new User();
				user.setUuid(DataUtil.buildUUID());
				user.setCellphone(cellphone);
				user.setCreateTime(DateUtil.nowDate());
				user.setHeadImg("");
				user.setLoginTime(user.getCreateTime());
				user.setNickname("");
				user.setOpenId("");
				user.setWebId("");
				user.setUnionId("");
				user.setPassword(password);
				user.setState(User.STATE_TYPE_ENUM.ACTIVATED.getState());
				addUser(user);
			}
		}else{//老用户
			if(null!=json&&!json.containsKey("errcode")){//使用微信注册
				OSSUtil.uploadFileToOSS(new URL(json.getString("headimgurl")).openStream(), user.getHeadImg());
				user.setNickname(json.getString("nickname"));
				user.setOpenId(platform!=AppConstant.PLATFORM_TYPE_ENUM.PC.getType()?openId:"");
				user.setWebId(platform==AppConstant.PLATFORM_TYPE_ENUM.PC.getType()?openId:"");
				user.setUnionId(json.getString("unionid"));
				updateUser(user);
			}
		}
		return putResult(user);
	}
	
	//用户微信登录
	public Result login(String unionId,String cellphone,String password,int platform){
		//检查参数是否填写
		if(StringUtils.isEmpty(unionId)&&(StringUtils.isEmpty(cellphone)||StringUtils.isEmpty(password))){
			Logger.getLogger("file").info("UserService | login | unionId="+unionId+",cellphone="+cellphone+" password="+password);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查用户是否存在
		User user=null;
		if(StringUtils.isNotEmpty(unionId)){
			user=getUserByUnionId(unionId);
		}else{
			user=getUserByPassword(cellphone, password);
		}
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
	public boolean operateUserAccount(int userId,int point,int coin)throws Exception{
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
	
	//依据openId查询用户
	public User getUserByPassword(String cellphone,String password){
		List<User> users=queryUsers(DataUtil.mapOf("cellphone",cellphone,"password",password));
		return users.size()>0?users.get(0):null;
	}
	
	//依据unionId查询用户
	public User getUserByUnionId(String unionId){
		List<User> users=queryUsers(DataUtil.mapOf("unionId",unionId));
		return users.size()>0?users.get(0):null;
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
