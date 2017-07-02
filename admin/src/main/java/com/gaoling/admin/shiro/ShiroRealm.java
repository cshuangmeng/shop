package com.gaoling.admin.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gaoling.admin.goods.entity.SysUser;
import com.gaoling.admin.goods.service.SysUserService;


@Component
public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserService userService;
	
	// 授权角色
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addRole("bd");
		info.addStringPermission("/manager/store/index");
		return info;
	}

	// 认证登录
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 利用token传入的信息查询数据库得到其对应的记录
		UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
		SysUser user=userService.getUserByUsername(token.getUsername());
		if (user==null) {
			throw new UnknownAccountException("用户名:"+token.getUsername()+"不存在");
		} // 查看用户是否可用
		if (!user.isEnabled()) {
			throw new LockedAccountException("账户:"+user.getUsername()+"不可用");
		}
		// 数据库的密码
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.getId(), user.getPwd(), getName());
		return info;
	}

}
