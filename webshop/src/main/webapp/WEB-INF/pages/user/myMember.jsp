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
						<img src="${pageContext.servletContext.contextPath }/resources/img/person.png"/>
					</li>
					<li style="margin: 0 80px 0 28px;">
						<p>部落名称：王者部落</p>
						<p>部落币：200</p>
						<p>勇士：6</p>
					</li>
					<li>
						<p>部落ID：123456</p>
						<p>征战天数：100</p>
					</li>
				</ul>
				<ul class="member_list">
					<li class="member_list_title">
						<p>成员昵称</p>
						<p style="margin:0 236px;">成员ID</p>
						<p>加入时间</p>
					</li>
					<li class="member_list_con_1">
						<p>成员1</p>
						<p style="margin: 0 242px;">1234455</p>
						<p>2017-03-26</p>
					</li>
					<li class="member_list_con_1">
						<p>成员1</p>
						<p style="margin: 0 242px;">1234455</p>
						<p>2017-03-26</p>
					</li>
					<li class="member_list_con_1">
						<p>成员1</p>
						<p style="margin: 0 242px;">1234455</p>
						<p>2017-03-26</p>
					</li>
					<li class="member_list_con_1">
						<p>成员1</p>
						<p style="margin: 0 242px;">1234455</p>
						<p>2017-03-26</p>
					</li>
					<li class="member_list_con_1">
						<p>成员1</p>
						<p style="margin: 0 242px;">1234455</p>
						<p>2017-03-26</p>
					</li>
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