<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/login.css"/>
		<%@include file="util/script.jsp" %>
		<script src="${pageContext.servletContext.contextPath }/resources/js/login.js"></script>
		<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
		<title>首页</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<div class="commonTop">
				<%@include file="util/head.jsp" %>
			</div>
			<div class="commomNavigation">
				<%@include file="util/menu.jsp" %>
			</div>
			<!--body-->
			<div class="p-login">
				<div class="loginImg">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg"/>
				</div>
				<div class="loginCon">
					<form action="${pageContext.servletContext.contextPath }/user/login" method="post">
					<p class="p01">如有帐号，请登录</p>
					<p>
						<input name="cellphone" type="text" placeholder="手机号/用户名/邮箱" value="${cellphone }" />
						<span class="error">请输入用户名</span>
					</p>
					<p>
						<input name="password" type="password" placeholder="密码" value="${password }"/>
						<c:choose>
							<c:when test="${not empty result.msg }">
								<span class="error">${result.msg }</span>
							</c:when>
							<c:otherwise>
								<span class="error">请输入密码</span>
							</c:otherwise>
						</c:choose>
					</p>
					<p>
						<button>登录</button>
					</p>
					<p>
						<span class="remberUserName"><b></b>记住用户名</span>
						<span class="forgetReg">
						<b class="forget"><a href="${pageContext.servletContext.contextPath }/findPassword">忘记密码？</a></b>
						 | <b class="register"><a href="${pageContext.servletContext.contextPath }/register">免费注册</a></b></span>
					</p>
					<p class="cooperate">合作网站账号登录</p>
					<p><img class="loginWx" src="${pageContext.servletContext.contextPath }/resources/img/loginWx.png"/></p>
					</form>
				</div>
				<div class="clear"></div>
			</div>
			<div class="logincontainer" id="login_container">
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>