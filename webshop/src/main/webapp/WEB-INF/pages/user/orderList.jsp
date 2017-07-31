<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/mySpace.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/orderList.js"></script>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<div class="commonTop">
			<%@include file="../util/head.jsp" %>
		</div>
		<div class="commomNavigation">
			<%@include file="../util/menu.jsp" %>
		</div>
		<!--body-->
		<div class="mySpace">
			<%@include file="../util/navigate.jsp" %>
			<!--订单管理-->
			<div class="mySpace_right">
				<div class="mySpace_right_title">
					<p style="margin: 0 380px 0 34px;">商品</p>
					<p>单价</p>
					<p style="margin: 0 93px 0 103px;">数量</p>
					<p>操作</p>
				</div>
				<div id="orderResult">
				<%@include file="orderResult.jsp" %>
				</div>
				<div class="mySpace_right_page">
					<input type="hidden" id="pageTool" value="${result.data.page }/${result.data.total}"/>
					<p class="last_page">上一页</p>
					<c:forEach begin="1" end="${result.data.total }" step="1" varStatus="state">
						<p num>${state.count }</p>
					</c:forEach>
					<p class="next_page">下一页</p>
				</div>
				<img src="${pageContext.servletContext.contextPath }/resources/img/border_page.png"/>
			</div>	
		</div>
		<!--footer-->
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>