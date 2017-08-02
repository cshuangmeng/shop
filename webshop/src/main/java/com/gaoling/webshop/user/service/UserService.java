package com.gaoling.webshop.user.service;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaoling.webshop.common.AppConstant;
import com.gaoling.webshop.common.DataUtil;
import com.gaoling.webshop.common.DateUtil;
import com.gaoling.webshop.common.HttpClientUtil;
import com.gaoling.webshop.common.MemcachedUtil;
import com.gaoling.webshop.common.OSSUtil;
import com.gaoling.webshop.common.SMSUtil;
import com.gaoling.webshop.system.pojo.Result;
import com.gaoling.webshop.system.service.CommonService;
import com.gaoling.webshop.tribe.pojo.Tribe;
import com.gaoling.webshop.tribe.service.TribeService;
import com.gaoling.webshop.user.dao.UserDao;
import com.gaoling.webshop.user.pojo.User;

import net.sf.json.JSONObject;

@Service
public class UserService extends CommonService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private TribeService tribeService;
	
	//下发验证码
	public Result sendCode(String mobile,String verifyCode,String code){
		if(StringUtils.isEmpty(mobile)){
			Logger.getLogger("file").info("UserService | sendCode | mobile="+mobile);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		if(!DataUtil.isPhoneNum(mobile)){
			Logger.getLogger("file").info("UserService | sendCode | mobile="+mobile);
			return putResult(AppConstant.DATA_FORMAT_INCORRECT);
		}
		if(StringUtils.isNotEmpty(verifyCode)&&!verifyCode.equalsIgnoreCase(code)){
			return putResult(AppConstant.VERIFY_CODE_INCORRECT);
		}
		code=DataUtil.createNums(4);
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
	public Result register(String openId,String code,String cellphone,String password)throws Exception{
		//检查参数是否填写
		if(StringUtils.isEmpty(code)||StringUtils.isEmpty(cellphone)||StringUtils.isEmpty(password)){
			Logger.getLogger("file").info("UserService | register | cellphone="+cellphone
					+",code="+code+",password="+password);
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
			String url=AppConstant.PC_SNS_USERINFO_URL+"?access_token="+MemcachedUtil.getInstance().getData(openId, "")+"&openid="+openId;
			String response=HttpClientUtil.getNetWorkInfo(url, "");
			if(StringUtils.isNotEmpty(response)){
				json=JSONObject.fromObject(response);
			}
		}
		//检查用户是否存在
		User user=getUserByCellphone(cellphone);
		if(null!=user){
			return putResult(AppConstant.USER_ALREADY_EXISTS);
		}
		user=new User();
		user.setUuid(DataUtil.buildUUID());
		user.setCellphone(cellphone);
		user.setCreateTime(DateUtil.nowDate());
		if(null!=json&&!json.containsKey("errcode")){
			user.setHeadImg("user/"+DateUtil.getCurrentTime("yyyyMMddHHmmssSSS")+DataUtil.createNums(6)+".jpg");
			OSSUtil.uploadFileToOSS(new URL(json.getString("headimgurl")).openStream(), user.getHeadImg());
		}else{
			user.setHeadImg("");
		}
		user.setLoginTime(user.getCreateTime());
		user.setNickname(null!=json&&!json.containsKey("errcode")?json.getString("nickname"):user.getCellphone());
		user.setOpenId("");
		user.setWebId(null!=openId?openId:"");
		user.setUnionId(null!=json&&!json.containsKey("errcode")?json.getString("unionid"):"");
		user.setPassword(password);
		user.setState(User.STATE_TYPE_ENUM.ACTIVATED.getState());
		addUser(user);
		return putResult(user);
	}
	
	//用户微信登录
	public Result login(String code,String cellphone,String password){
		//检查参数是否填写
		if(StringUtils.isEmpty(code)&&(StringUtils.isEmpty(cellphone)||StringUtils.isEmpty(password))){
			Logger.getLogger("file").info("UserService | login | code="+code+",cellphone="+cellphone+" password="+password);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查用户是否存在
		User user=null;
		if(StringUtils.isNotEmpty(code)){
			String response=HttpClientUtil.getNetWorkInfo(AppConstant.API_GETOPENIDBYCODE+"&code="+code, "");
			JSONObject json=DataUtil.isJSONObject(response)?JSONObject.fromObject(response):null;
			if(null!=json&&json.getInt("code")==0&&StringUtils.isNotEmpty(json.getJSONObject("data").getString("unionId"))){
				user=getUserByUnionId(json.getJSONObject("data").getString("unionId"));
			}
			if(null==user){
				Result result=putResult(AppConstant.USER_NOT_EXISTS);
				result.setData(json.getJSONObject("data").getString("openId"));
				return result;
			}
			user.setWebId(json.getJSONObject("data").getString("openId"));
		}else{
			user=getUserByPassword(cellphone, password);
			if(null==user){
				return putResult(AppConstant.USER_OR_PASSWORD_INCORRECT);
			}
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
	
	//重置修改密码
	public Result resetPassword(String code,String cellphone,String password){
		//检查参数是否填写
		if(StringUtils.isEmpty(code)&&(StringUtils.isEmpty(cellphone))){
			Logger.getLogger("file").info("UserService | login | code="+code+",cellphone="+cellphone+" password="+password);
			return putResult(AppConstant.PARAM_IS_NULL);
		}
		//检查用户是否存在
		User user=getUserByCellphone(cellphone);
		if(null==user){
			return putResult(AppConstant.USER_NOT_EXISTS);
		}
		//检查验证码是否正确
		String saveCode=MemcachedUtil.getInstance().getData(AppConstant.CHECKCODE_PREFIX+cellphone,"");
		if(!saveCode.equals(code)){
			return putResult(AppConstant.CHECK_CODE_INCORRECT);
		}
		if(StringUtils.isNotEmpty(password)){
			//更新密码
			user.setPassword(password);
			updateUser(user);
		}
		return putResult();
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
