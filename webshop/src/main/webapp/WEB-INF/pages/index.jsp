<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/index.css"/>
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/index.js"></script>
		<title>首页</title>
	</head>
	<body>
		<div class="p-index-main">
			<!--navigation-->
			<div class="commonTop">
				<%@include file="util/head.jsp" %>
			</div>
			<div class="commomNavigation">
				<%@include file="util/menu.jsp" %>
			</div>
			<!--banner-->
			<div class="banner">
				<div id="myCarousel" class="carousel slide list">
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner" id="js-banner">
						<c:forEach items="${result.data.bottomBanners }" var="banner">
						<div class="item">
							<c:choose>
								<c:when test="${not empty banner.url }">
									<a href="${banner.url }"><img src="${banner.img }" alt=""/></a>
								</c:when>
								<c:otherwise>
									<img src="${banner.img }" alt=""/>
								</c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
					<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
				</div>
			</div>
			<!--欢迎大礼包-->
			<div class="hydlb">
				<div class="tit">
					<b></b>
					<span class="line"></span>
					<span class="titCon">欢迎大礼包
						<span class="smalltit">Welcome spree</span>
					</span>
					<span class="line"></span>
				</div>
				<div class="clear"></div>
				<div class="list">
					<c:forEach items="${result.data.welcomes }" var="welcome">
					<div class="li">
						<div class="img">
							<a href="${pageContext.servletContext.contextPath }/goods/info?id=${welcome.id}"><img src="${welcome.img }"/></a>
						</div class="tit">
						<p class="con">
							<span class="money">
								<b>${welcome.price }</b>元
							</span>
							<span class="name">${welcome.name }</span>
						</p>
					</div>
					</c:forEach>
					<div class="clear"></div>
				</div>
			</div>
			<!--品牌旗舰体验中心-->
			<div class="tyzx">
				<div class="tit">
					<span class="con">
						<span class="titCon">品牌旗舰体验中心</span>
						<span class="smalltit">Welcome spree</span>
					</span>
					<span class="all">
						查看全部
						<img src="${pageContext.servletContext.contextPath }/resources/img/goAll.png"/>
					</span>
				</div>
				<div class="list">
					<c:forEach items="${result.data.shops }" var="shop">
					<div class="li">
						<a href="${pageContext.servletContext.contextPath }/shop/info?id=${shop.id}"><img src="${shop.img }"/></a>
					</div>
					</c:forEach>
					<div class="clear"></div>
				</div>
			</div>
			<!--banner01-->
			<div class="banner01">
				<div id="myCarousel01" class="carousel slide">
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner">
						<c:forEach items="${result.data.topBanners }" var="banner">
						<div class="item item01">
							<c:choose>
								<c:when test="${not empty banner.url }">
									<a href="${banner.url }"><img src="${banner.img }" alt=""/></a>
								</c:when>
								<c:otherwise>
									<img src="${banner.img }" alt=""/>
								</c:otherwise>
							</c:choose>
						</div>
						</c:forEach>
					</div>
					<!-- 轮播（Carousel）导航 -->
					<a class="carousel-control left" href="#myCarousel01" 
					    data-slide="prev">&lsaquo;</a>
					<a class="carousel-control right" href="#myCarousel01" 
	   				    data-slide="next">&rsaquo;</a>
				</div>
			</div>
			<!--品质生活-->
			<div class="pzsh">
				<div class="tit">
					<span class="con">
						<span class="titCon">品质生活</span>
						<span class="smalltit">Welcome spree</span>
					</span>
					<span class="all">
						查看全部
						<img src="${pageContext.servletContext.contextPath }/resources/img/goAll.png"/>
					</span>
				</div>
				<div class="list">
					<c:forEach items="${result.data.lives }" var="live">
					<div class="li">
						<div class="img">
							<a href="${pageContext.servletContext.contextPath }/goods/info?id=${live.id}"><img src="${live.img }"/></a>
						</div class="tit">
						<p class="con">
							<span class="money">
								<b>${live.price }</b>元
							</span>
							<span class="name">${live.name }</span>
						</p>
					</div>
					</c:forEach>
					<div class="clear"></div>
				</div>
			</div>
			<!--养生尚品-->
			<div class="yssp">
				<div class="tit">
					<span class="con">
						<span class="titCon">养生尚品</span>
						<span class="smalltit">Welcome spree</span>
					</span>
					<span class="all">
						查看全部
						<img src="${pageContext.servletContext.contextPath }/resources/img/goAll.png"/>
					</span>
				</div>
				<div class="list">
					<c:forEach items="${result.data.healths }" var="health">
					<div class="li">
						<div class="img">
							<a href="${pageContext.servletContext.contextPath }/goods/info?id=${health.id}"><img src="${health.img} "/></a>
						</div class="tit">
						<p class="con">
							<span class="money">
								<b>${health.price }</b>元
							</span>
							<span class="name">${health.name }</span>
						</p>
					</div>
					</c:forEach>
					<div class="clear"></div>
				</div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>