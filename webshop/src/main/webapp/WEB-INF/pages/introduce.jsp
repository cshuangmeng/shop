<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/mallItroduction.css"/>
		<%@include file="util/script.jsp" %>
		<title>商城介绍</title>
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
			<div class="mallItroduction_top"></div>
		<div class="mallItroduction">
			<ul class="mallItroduction_top_txt">
				<li class="line01"><p></p> </li>
				<li class="mallItroduction_top_txt_con"><p>我们一直引以为豪的，是一直以来的专注</p></li>
				<li class="line01"><p></p> </li>
			</ul>
			<ul class="mallItroduction_list">
				<li>
					<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction01.png"/>
					<div class="mallItroduction_list_txt">
						<p class="line02"></p>
						<p class="mallItroduction_list_txt_con">专注品质</p>
						<p class="line02"></p>
					</div>
					<p class="english_txt">Dedicated quality</p>
				</li>
				<li style="margin: 0 12px;">
					<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction02.png"/>
					<div class="mallItroduction_list_txt">
						<p class="line02"></p>
						<p class="mallItroduction_list_txt_con">专注品牌</p>
						<p class="line02"></p>
					</div>
					<p class="english_txt">  Focus brand</p>
				</li>
				<li>
					<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction03.png"/>
					<div class="mallItroduction_list_txt">
						<p class="line02"></p>
						<p class="mallItroduction_list_txt_con">专注企业</p>
						<p class="line02"></p>
					</div>
					<p class="english_txt">Focus on Enterprises</p>
				</li>
			</ul>
			<div class="mallItroduction_c_txt">
				<span>选择我们的8大优势</span>
				<p>健康升级，优化家庭健康所需产品</p>
			</div>
			<ul class="goodness_list">
				<li>
					<div class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_01.png"/>
						<p>
							<span>理性消费，去伪存真</span>
							<span>精益求精</span>
						</p>
					</div>
					<div style="margin-left: 26px;" class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_02.png"/>
						<p>
							<span>所销售商品经过严格筛选</span>
							<span>来自各行、各业、各地域领导品牌</span>
						</p>
					</div>
				</li>
				<li>
					<div class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_03.png"/>
						<p>
							<span>众多企业、企业家</span>
							<span>垂直参与市场营销，高效协同</span>
						</p>
					</div>
					<div style="margin-left: 26px;" class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_04.png"/>
						<p>
							<span>酋长部落自定义</span>
							<span>让你的优惠炫动起来</span>
						</p>
					</div>
				</li>
				<li>
					<div class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_05.png"/>
						<p>
							<span>所有产品均发自产地</span>
							<span>真正做到完全去除中间环节</span>
						</p>
					</div>
					<div style="margin-left: 26px;" class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_06.png"/>
						<p>
							<span>“客户放心      企业用心</span>
							<span>团队诚心      服务贴心”</span>
						</p>
					</div>
				</li>
				<li>
					<div class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_07.png"/>
						<p>
							<span>双品牌运营品质不打折</span>
							<span>真正享受日常消费的健康实惠</span>
						</p>
					</div>
					<div style="margin-left: 26px;" class="goodness_list_l">
						<img src="${pageContext.servletContext.contextPath }/resources/img/mallintroduction_08.png"/>
						<p>
							<span>以电商之名助力匠心回归</span>
							<span>造福百万家庭</span>
							
						</p>
					</div>
				</li>
			</ul>
			<div class="goodness_img">
				
			</div>
			<p class="goodness_title">合作品牌</p>
			<ul style="margin-bottom: 126px;" class="brandItroduction_logo">
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
			</ul>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>