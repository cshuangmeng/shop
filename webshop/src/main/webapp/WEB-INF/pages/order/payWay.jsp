<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/payFor.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/payFor.js"></script>
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
					<p>应付金额：<span>¥${result.data.totalPayPrice }</span></p>
				</li>
			</ul>
			<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
			<ul class="message_cue_con2">
				<li class="message_cue_con2_top">
					<div class="message_cue_con2_title1">订单信息：</div>
					<c:forEach items="${result.data.orders }" var="order">
					<p>${order.extras.goods.name }</p>
					</c:forEach>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
				<li class="message_cue_con2_bottom">
					<div class="message_cue_con2_title2">收货信息：</div>
					<span style="margin-left: 30px;">联系电话：${result.data.address.mobile }</span>
					<span style="margin: 0 100px 0 70px;">收货人：${result.data.address.consigner }</span>
					<span>${result.data.address.address } </span>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
				<li class="message_cue_con2_xia">
					<div class="message_cue_con2_title4">订单信息：</div>
					<p>商品运费：${(not empty result.data.freight&&result.data.freight==0)?"包邮":"¥"+result.data.freight }</p>
					<p>部落币抵扣：¥${result.data.coin/result.data.coinRate }</p>
					<p>部落分抵扣：¥${result.data.point/result.data.pointRate }</p>
				</li>
				<img src="${pageContext.servletContext.contextPath }/resources/img/pay_border.png" />
			</ul>
			<ul class="message_cue_con3">
				<li class="message_cue_con2_title3">
					<p>在线支付</p>
				</li>
				<li class="select">
					<!-- <input type="radio" name="Sex" value="male" />
					<img src="${pageContext.servletContext.contextPath }/resources/img/payfor1 (3).gif" /> -->
					<input type="radio" checked="checked" name="Sex" value="male" />
					<img src="${pageContext.servletContext.contextPath }/resources/img/payfor1 (2).gif" />
				</li>
			</ul>
			<input type="hidden" name="codeUrl" value="${result.data.payInfo.codeUrl }"/>
			<input type="hidden" name="orderId" value="${result.data.orderId }"/>
			<div id="qrcode">
				
			</div>
			<div class="clear"></div>
		</div>
		<!--footer-->
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>