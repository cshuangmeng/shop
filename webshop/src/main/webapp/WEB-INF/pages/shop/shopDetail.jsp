<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/dianpuzhuye.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/dianpuzhuye.js"></script>
		<title>首页</title>
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
			<div class="dianpujieshao">
			<div class="img">
				<img src="${result.data.fullHeadImg }"/>
			</div>
			<div class="name">${result.data.name }</div>
			<c:choose>
				<c:when test="${result.data.extras.isFollowed>0 }">
					<div class="guanzhu">已关注</div>
				</c:when>
				<c:otherwise>
					<div class="guanzhu" id="doFollowed">关注</div>
				</c:otherwise>
			</c:choose>
			<div class="fensishu">
				<p class="num">${result.data.flowers }</p>
				<p class="tit">粉丝数</p>
			</div>
			</div>
			<!--xinpinTit-->
			<div class="xinpinTit">
				<div class="tit">新品上架</div>
			</div>
			<div class="xinpinList">
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="clear"></div>
			</div>
			<div class="rexaioTit">
				<div class="tit">销售明星</div>
			</div>
			<div class="rexaioList">
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="li">
					<img src="${pageContext.servletContext.contextPath }/resources/img/FX.jpg" alt="" />
					<p class="tit">即使商品质量好，也不能漫天要价.</p>
					<p class="money">
						<span class="truemoney">￥123</span>
						<span class="minmoney">最低实付价￥<b>50</b></span>
					</p>
				</div>
				<div class="clear"></div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="../util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>