<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/aboutUs.css"/>
		<%@include file="util/script.jsp" %>
		<title>关于我们</title>
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
			<div class="aboutUs_top"></div>
			<!--body-->
			<div class="aboutUs">
			<div class="aboutUs_txt">
				<p class="aboutUs_txt01">About us</p>
				<p class="aboutUs_txt02">关于我们</p>
				<p class="aboutUs_line"></p>
				<p class="aboutUs_txt03"></p>
				<p class="aboutUs_txt01">Our idea</p>
				<p class="aboutUs_txt02">我们的理念</p>
				<p class="aboutUs_line"></p>
			</div>
			<ul class="aboutUs_list">
				<li>
					<div class="aboutUs_list_l">
						<p class="aboutUs_list_img"></p>
						<p class="aboutUs_list_title">品牌共振</p>
						<p class="aboutUs_list_con">以各产业龙头企业的品牌影响力形成聚合效应，去伪存真,解决当下电商购物难题</p>
					</div>
					<div class="aboutUs_list_r">
						<p class="aboutUs_list_img_2"></p>
						<p class="aboutUs_list_title">品牌共振</p>
						<p class="aboutUs_list_con">追求卓越品质，提升生活质量,致力于打造家庭健康消费首选平台</p>
					</div>
				</li>
				<li class="aboutUs_list_bottom">
					<div class="aboutUs_list_l">
						<p class="aboutUs_list_img_3"></p>
						<p class="aboutUs_list_title">资源共享</p>
						<p class="aboutUs_list_con">提高资源的利用效率,搭建社群经济完整体系，促使社群价值最大化</p>
					</div>
					<div class="aboutUs_list_r">
						<p class="aboutUs_list_img_4"></p>
						<p class="aboutUs_list_title">联合共赢</p>
						<p class="aboutUs_list_con">围绕一线品牌，联合品牌专卖店众企联动，打造地域旗舰体验中心，搭建共享经济模型，开启品牌年度大型回馈活动</p>
					</div>
				</li>
			</ul>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>