<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/goodsDetail.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goodsDetail.js"></script>
		<title>商品详情</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<div class="commonTop">
				<%@include file="../util/head.jsp" %>
			</div>
			<div class="commomNavigation">
				<%@include file="../util/menu.jsp" %>
			</div>
			<!--goodsDetail-->
			<div class="goodsDetail">
				<div class="goods">
					<div class="left">
						<img src="${result.data.goods.fullHeadImg }"/>
					</div>
					<div class="right">
						<p class="tit">
							<span class="con">
								${result.data.goods.name }
							</span>
							<span class="shop">
								<img src="${result.data.shop.fullHeadImg }"/>
								${result.data.shop.name }
							</span>
						</p>
						<div class="clear"></div>
						<div class="price">
							￥<b>${result.data.goods.price }</b>
							<span>最低实付价￥${result.data.goods.extras.miniPrice }</span>
						</div>
						<p class="freight">运费：<span>￥${result.data.goods.extras.freight }</span></p>
						<div class="num">
							<div class="shuliang">数量：</div>
							<div class="numchange">
								<button class="reduce">-</button>
								<input type="text" class="changenum" value="1" class="num"/>
								<button class="increase">+</button>
							</div>
						</div>
						<div class="btn">
							<div class="shopcar">加入购物车</div>
							<div class="pay">立即购买</div>
						</div>
						<div class="clear"></div>
						<p class="buluofen">
							购买最多可获得<b>${result.data.goods.extras.backPoint }</b>部落分！
						</p>
					</div>
				</div>
				<div class="clear"></div>
				<div class="title">
					<span class="titlecon">
						<img src="${pageContext.servletContext.contextPath }/resources/img/didaopinzhi.png" alt="" />
						<b>商品参数</b>
					</span>
				</div>
				<div class="detailList">
					<c:if test="${not empty result.data.goods.extras.detailsJson }">
					<c:forEach items="${result.data.goods.extra.detailsJson }" var="detail">
					<div class="li">
						<span class="tit">${detail.label }：</span>
						<span class="con">${detail.value }</span>
					</div>
					</c:forEach>
					<div class="clear"></div>
					</c:if>
				</div>
				<div class="title">
					<span class="titlecon">
						<img src="${pageContext.servletContext.contextPath }/resources/img/didaopinzhi.png" alt="" />
						<b>商品详情</b>
					</span>
				</div>
				<c:if test="${not empty result.data.goods.extras.fullDetailImg }">
					<div class="introduce">
						<c:forEach items="${result.data.goods.extras.fullDetailImg }" var="img">
						<img src="${img }"/>
						</c:forEach>
					</div>
				</c:if>
				<div class="clear"></div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="../util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>