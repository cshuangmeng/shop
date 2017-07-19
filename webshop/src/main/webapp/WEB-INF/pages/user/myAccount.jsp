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
			<div style="display: none;" class="wallet">
				<div class="wallet_top">
					<span style="margin:0 224px 0 30px;">部落币：100</span>
					<span>部落分：100</span>
				</div>
				<div class="wallet_bottom">
					<p class="wallet_bottom_1">
						交易记录
					</p>
					<div class="wallet_bottom_2">
						<div class="wallet_bottom_2_title">
							<span style="margin-left: 20px;">交易编号</span>
							<span>事件</span>
							<span>全部类型</span>
							<span>金额</span>
							<span>对应订单号</span>
							<span>操作</span>
						</div>
						<div class="wallet_bottom_2_con">
							<p>当前没有交易记录，先去逛会吧...</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--footer-->
		<%@include file="util/foot.jsp" %>
	</body>
</html>