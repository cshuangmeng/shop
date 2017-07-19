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
			<%@include file="../util/head.jsp" %>
			<!--goodsDetail-->
			<div class="goodsDetail">
				<div class="goods">
					<div class="left">
						<img src="img/FX.jpg"/>
					</div>
					<div class="right">
						<p class="tit">
							<span class="con">
								云南白药创可贴
							</span>
							<span class="shop">
								<img src="img/zhongqiliandong.png"/>
								云南白药旗舰店 
							</span>
						</p>
						<div class="clear"></div>
						<div class="price">
							￥<b>199</b>
							<span>最低实付价￥50</span>
						</div>
						<p class="freight">运费：<span>￥23</span></p>
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
							购买最多可获得<b>100</b>部落分！
						</p>
					</div>
				</div>
				<div class="clear"></div>
				<div class="title">
					<span class="titlecon">
						<img src="img/didaopinzhi.png" alt="" />
						<b>商品参数</b>
					</span>
				</div>
				<div class="detailList">
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="li">
						<span class="tit">品牌名称：</span>
						<span class="con">云南白药</span>
					</div>
					<div class="clear"></div>
				</div>
				<div class="title">
					<span class="titlecon">
						<img src="img/didaopinzhi.png" alt="" />
						<b>商品详情</b>
					</span>
				</div>
				<div class="introduce">
					<img src="img/FX.jpg"/>
					<img src="img/FX.jpg"/>
					<img src="img/qr.png"/>
					<img src="img/FX.jpg"/>
				</div>
				<div class="clear"></div>
			</div>
			<!--footer-->
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>