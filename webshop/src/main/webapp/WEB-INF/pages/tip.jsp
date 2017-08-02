<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/payS.css"/>
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/tip.js"></script>
		<title>提示</title>
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
			<div class="con" style="display: none;">
			<div class="headImg">
				<img src="${pageContext.servletContext.contextPath }/resources/img/payR.png"/>
				<div class="headtit">
					<p>无法完成支付</p>
					<p style="color: #ddd;">卡内余额不足</p>
				</div>
			</div>
			<div class="gerenzhongxin">
				您可以选择<span>重新支付</span>或<span><a href="${pageContext.servletContext.contextPath }/user/account">个人中心</a></span>查看交易记录，建议去看看
			</div>
			<div class="btn">
				<div class="yesBtn">重新支付</div>
				<div class="noBtn">继续逛逛</div>
			</div>
			</div>
			
			<div class="con" style="display: none;">
			<div class="headImg">
				<img src="${pageContext.servletContext.contextPath }/resources/img/payS.png"/>
				<div class="headtit">
					<p>您已付款成功</p>
					<p>等待商家发货</p>
				</div>
			</div>
			<div class="money">
				付款金额：<span>￥1234</span>
			</div>
			<div class="address">
				收货地址：上海市闵行区38号院7834室
			</div>
			<div class="gerenzhongxin">
				您可以再<span>个人中心</span>查看交易记录，建议去看看
			</div>
			<div class="btn">
				<div class="yesBtn">去看看</div>
				<div class="noBtn">继续逛逛</div>
			</div>
			</div>
			
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>