<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/joinContactUs.css"/>
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/join.js"></script>
		<title>联系我们</title>
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
			<div class="joinContactUs">
			<div class="contactUs">
				<p class="contactUs_title">联系我们</p>
				<p class="contactUs_title_bg"></p>
				<div class="contactUs_con">
					<p>联系电话：0755-8268-9595</p>
					<p>163邮箱 ： zhiyuan@163.com</p>
					<p>传真：0755-8389-6336</p>
				</div>
				<a href="http://j.map.baidu.com/0eWX9" target="blank"><div class="contactUs_map"></div></a>
				<p class="contactUs_title_bg_bottom"></p>
				<p class="contactUs_button"><a href="http://j.map.baidu.com/0eWX9" target="blank">现在就去</a></p>
			</div>
			<div class="joinUs">
				<p class="joinUs_title">申请加入</p>
				<div class="joinUs_con">
					<input type="text" placeholder="企业名称" />
					<input type="text" placeholder="企业网址" />
					<input type="text" placeholder="主打产品" />
					<input type="text" placeholder="产品优势" />
					<input type="text" placeholder="联系人"/>
					<input type="text" placeholder="联系方式" />
				</div>
				<p class="joinUs_txt">申请加入</p>
			</div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>