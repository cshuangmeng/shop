<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goodsList.js"></script>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<%@include file="util/head.jsp" %>
		<!--body-->
		<div class="mySpace">
			<div class="mySpace_left">
				<div class="personImg">
				</div>
				<div class="mySpace_left_con_t">
					<ul class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/1_03.png"/>
							<p>我的账户</p>
							<img style="margin-top: 4px;" src="img/corner_01.png"/>
						</li>
						<li style="color: #DB3E40;" class="mySpace_left_con_2">订单管理</li>
						<li class="mySpace_left_con_3">收货地址</li>
					</ul>
					<ul style="margin: 20px 0;" class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/5_03.png"/>
							<p>我的资产</p>
						</li>
						<li class="mySpace_left_con_2">钱包</li>
					</ul>
					<ul class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/3_03.png"/>
							<p>我的部落</p>
						</li>
						<li class="mySpace_left_con_2">成员</li>
					</ul>
				</div>
			</div>
			<!--我的钱包-->
			<div class="member">
				<ul class="member_con">
					<li>
						<img src="img/person.png"/>
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
		<%@include file="util/foot.jsp" %>
	</body>
</html>