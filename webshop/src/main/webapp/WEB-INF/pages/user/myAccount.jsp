<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/mySpace.css"/>
		<%@include file="../util/script.jsp" %>
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
			<!--我的钱包-->
			<div class="wallet">
				<div class="wallet_top">
					<span style="margin:0 224px 0 30px;">部落币：${sessionScope.user.coin }</span>
					<span>部落分：${sessionScope.user.point }</span>
				</div>
				<div class="wallet_bottom">
					<p class="wallet_bottom_1">
						交易记录
					</p>
					<div class="wallet_bottom_2">
						<div class="wallet_bottom_2_title">
							<span style="margin-left: 20px;">交易编号</span>
							<span style="margin-left: 177px;">事件</span>
							<span>全部类型</span>
							<span>金额</span>
						</div>
						<c:choose>
							<c:when test="${not empty result.data }">
								<c:forEach items="${result.data }" var="log">
								<div class="wallet_bottom_2_title">
									<span style="margin-left: 20px;">${log.tradeNo }</span>
									<span class="eve">${log.item }</span>
									<span>${log.desc }</span>
									<span>${log.amount }</span>
								</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="wallet_bottom_2_con">
									<p>当前没有交易记录，先去逛会吧...</p>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<!--footer-->
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>