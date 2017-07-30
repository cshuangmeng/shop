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
			<div class="member">
				<ul class="member_con">
					<li>
						<img src="${sessionScope.user.headImg }"/>
					</li>
					<li style="margin: 0 80px 0 28px;">
						<p>部落名称：${result.data.tribe.nickname }</p>
						<p>部落币：${result.data.tribe.coin }</p>
						<p>勇士：${result.data.size }</p>
					</li>
					<li>
						<p>部落ID：${result.data.tribe.id }</p>
						<p>征战天数：${result.data.tribe.extras.days }</p>
					</li>
				</ul>
				<ul class="member_list">
					<c:forEach items="${result.data.members }" var="member">
					<li class="member_list_title">
						<p>${member.nickname }</p>
						<p style="margin:0 236px;">${member.tribeId }</p>
						<p>${member.joinTime }</p>
					</li>
					</c:forEach>
				</ul>
				<div class="member_page">
					<p style="margin-left: 200px;">上一页</p>
					<p class="member_page_number">1</p>
					<p>下一页</p>
				</div>
			</div>
		</div>
		<!--footer-->
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>