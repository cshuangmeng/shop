<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/payFor.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/login.js"></script>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<%@include file="../util/head.jsp" %>
		<!--body-->
		<div class="message_cue">
			<ul class="message_cue_con1">
				<li class="message_cue_con1_l">
					<img src="${pageContext.servletContext.contextPath }/resources/img/payfor1 (1).gif" />
				</li>
				<li class="message_cue_con1_c">
					<p>订单提交成功</p>
					<p>等待买家付款</p>
				</li>
				<li class="message_cue_con1_r">
					<p>应付金额：<span>¥4923</span></p>
				</li>
			</ul>
			<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
			<ul class="message_cue_con2">
				<li class="message_cue_con2_top">
					<div class="message_cue_con2_title1">订单信息：</div>
					<p>尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
					<p>尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
				<li class="message_cue_con2_bottom">
					<div class="message_cue_con2_title2">收货信息：</div>
					<span style="margin-left: 30px;">联系电话：152121010101</span>
					<span style="margin: 0 100px 0 70px;">收货人：陈陈陈</span>
					<span>上海市普陀区兰溪路   君悦苑3号楼   </span>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
				<li class="message_cue_con2_xia">
					<div class="message_cue_con2_title4">订单信息：</div>
					<p>商品运费：包邮</p>
					<p>部落币抵扣：¥20</p>
					<p>部落分抵扣：¥20</p>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
			</ul>
			<ul class="message_cue_con3">
				<li class="message_cue_con2_title3">
					<p>在线支付</p>
				</li>
				<li class="select">
					<input type="radio" checked="checked" name="Sex" value="male" />
					<img src="${pageContext.servletContext.contextPath }/resources/img/payfor1 (3).gif" />
					<input type="radio" name="Sex" value="male" style="margin-left: 54px;" />
					<img src="${pageContext.servletContext.contextPath }/resources/img/payfor1 (2).gif" />
				</li>
			</ul>
			<div class="payForMoney">
				<p>立即付款</p>
			</div>
		</div>
		<!--footer-->
		<%@include file="../util/foot.jsp" %>
	</body>
</html>