<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/shoppingCar.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/login.js"></script>
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
		<div class="shoppingCartMiddle">
			<div class="shoppingCartMiddleCon">
				<p class="MiddleConTitle">
					<span style="font-weight: 800;font-size: 20px;font-family: '微软雅黑';color: #404040;margin-right: 34px;">
					购物袋</span>请在<span style="color: #cf443f;">19分32秒</span>19分32秒内提交订单，下单后你另有30分钟的支付时间。
				</p>
				<div class="MiddleConTitle_T">
					<p class="MiddleConTitle_1">
						产品
					</p>
					<p class="MiddleConTitle_2" >
						单价
					</p>
					<p class="MiddleConTitle_3" >
						数量
					</p>
					<p class="MiddleConTitle_4" >
						金额
					</p>
					<p class="MiddleConTitle_5" >
						操作
					</p>
				</div>
				<ul class="MiddleConGoods">
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count">
							<span class="add">+</span>
							<span class="numberCode">1</span>
							<!--<input  class="numberCode" type="text" value="1" />-->
							<span class="minus">-</span>
						</div>
						<p class="goods_price_end">¥3402</p>
						<img class="delectImg" src="${pageContext.servletContext.contextPath }/resources/img/delect.png"/>
					</li>
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count">
							<span class="add">+</span>
							<span class="numberCode">1</span>
							<span class="minus">-</span>
						</div>
						<p class="goods_price_end">¥3402</p>
						<img class="delectImg" src="${pageContext.servletContext.contextPath }/resources/img/delect.png"/>
					</li>
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count">
							<span class="add">+</span>
							<span class="numberCode">1</span>
							<span class="minus">-</span>
						</div>
						<p class="goods_price_end">¥3402</p>
						<img class="delectImg" src="${pageContext.servletContext.contextPath }/resources/img/delect.png"/>
					</li>
				</ul>
				<div class="fare">
					<button class="fareBtn">运费</button>
					<p class="fareTxt">本组商品已免运费</p>
				</div>
				<div class="fareBlank">
					
				</div>
				<div class="priceAll">
					<ul class="priceAllCon">
						<li>
							<span style="color: #333;">¥442</span>
							<span>商品金额</span>
						</li>
						<li>
							<span style="color: #d15553;">¥442</span>
							<span style="width: 110px;">总金额（已免运费）</span>
						</li>
					</ul>
				</div>
				<div class="payAll">
					<p class="payAllLeft">请在倒计时结束前结算</p>
					<p class="payAllRight">
						<span style="margin-left: 50px;">立即结算</span>
						<span style="margin-left: 10px;">19:21</span>
					</p>
				</div>
				<div class="bottomBlank">
					
				</div>
				
				<!--footer-->
				<div class="commonBot"></div>
			</div>
		</div>
		<!--footer-->
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>