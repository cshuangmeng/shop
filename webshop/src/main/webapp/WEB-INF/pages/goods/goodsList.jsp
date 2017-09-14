<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/goodsList.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goodsList.js"></script>
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
			<div class="goodslist">
				<div class="searchList">
					<div class="pinpai">
						<div class="tit">品牌：</div>
						<div class="list">
							<span class="all" shopId=0>全部</span>
							<c:forEach items="${result.data.shops }" var="shop">
								<span shopId=${shop.id }>${shop.name }</span>
							</c:forEach>
						</div>
					</div>
					<div class="pinpai">
						<div class="tit">品类：</div>
						<div class="list">
							<span class="all" typeId=0>全部</span>
							<span typeId=1>品质生活</span>
							<span typeId=2>欢迎大礼包</span>
							<span typeId=3>家庭健康</span>
						</div>
					</div>
					<!--<div class="line"></div>-->
				</div>
				<div class="screen">
					<div class="left">
						<span class="active" sort=1>综合</span>
						<b></b>
						<span sort=2>价格</span>
						<b></b>
						<span sort=3>折扣</span>
						<b></b>
						<div class="price">
							<input type="text" placeholder="￥"/>
							<span>-</span>
							<input type="text" placeholder="￥"/>
							<button>确定</button>
						</div>
					</div>
					<div id="pageTool" class="right">
						<b>1/25</b>
						<span>&lt; 上一页</span>
						<span>下一页 &gt;</span>
					</div>
				</div>
				<div class="goodslistCon">
					<div id="goodsResult">
						
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="../util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>