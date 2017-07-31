<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/register.css"/>
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/register.js"></script>
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
			<div class="p-register">
				<div class="registerImg">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg"/>
				</div>
				<div class="registerCon">
					<p class="p01"><span>欢迎注册</span><span class="gologin">已注册可<b><a href="${pageContext.servletContext.contextPath }/login">直接登录</a></b></span></p>
					<p>
						<input type="hidden" name="openId" value="${openId }" />
						<input type="text" placeholder="请输入手机号" name="cellphone" />
					</p>
					<p>
						<input type="password" placeholder="密码由6-20位字母，数字和符号组成" name="password"/>
					</p>
					<p>
						<input type="password" placeholder="请再次确认密码" name="confirm"/>
					</p>
					<p class="verificationCode">
						<input type="text" placeholder="短信验证码" name="code"/>
						<button id="getCodeBtn">获取验证码</button>
					</p>
					<p>
						<button id="submitBtn">注册</button>
					</p>
					<p>
						<span class="acceptanceAgreement"><b></b>我已阅读并接受<i>唐僧商城服务条款</i></span>
					</p>
				</div>
				<div class="clear"></div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>