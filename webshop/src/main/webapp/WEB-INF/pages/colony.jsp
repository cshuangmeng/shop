<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/brandIntroduction.css"/>
		<%@include file="util/script.jsp" %>
		<title>品牌集群</title>
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
			<div class="brandItroduction">
			<div class="brandItroduction_bg"></div>
			<div class="brandItroduction_txt">
				<p>上海高翎实业有限公司正全速布局以“品牌ip化”和“渠道集成化”为助推力的大健康生态产业集群</p>
				<p>努力打造充满活力、开放的生态 型系统未来公司将充分借助资本市场工具加快打造生命健康生态系统</p>
			</div>
			<ul class="brandItroduction_logo">
				<li>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96735">
						<img src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-1.jpg"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96734">
						<img class="brandItroduction_logo_center" src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-2.jpg"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96733">
						<img src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-3.jpg"/>
					</a>
				</li>
				<li>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96736">
						<img src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-4.jpg"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96738">
						<img class="brandItroduction_logo_center" src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-5.jpg"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96732">
						<img src="${pageContext.servletContext.contextPath }/resources/img/brandItroduction_logo-6.jpg"/>
					</a>
				</li>
				<li>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96739">
						<img src="${pageContext.servletContext.contextPath }/resources/img/123_01.png"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96740">
						<img class="brandItroduction_logo_center" src="${pageContext.servletContext.contextPath }/resources/img/123_02.png"/>
					</a>
					<a href="${pageContext.servletContext.contextPath }/shop/info?id=96741">
						<img src="${pageContext.servletContext.contextPath }/resources/img/123_03.png"/>
					</a>
				</li>
			</ul>
			<div class="jiuli_tribe">
				<div class="jiuli_tribe_bg"></div>
				<div class="jiuli_tribe_txt">
					<p class="jiuli_tribe_txt_1">
						九黎部落品牌集群是
						<span>线下品牌旗舰体验中心</span>的重要组成部分，其集群内品牌在c端的良好口碑和市场知名度，
						将为tangsheng.shop商城带来良好的客流及口碑
					</p>
					<p>
						一线品牌的口碑效应为tangseng.shop商城实现C2B创造条件，
						<span>高翎线上线下的 运营体系</span>也将实现各品牌之间的流量互导，
						并进一步提升九黎部落品牌集群的影响力
					</p>
				</div>
			</div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>