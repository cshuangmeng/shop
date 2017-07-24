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
			<!--订单管理-->
			<div class="mySpace_right">
				<div class="mySpace_right_title">
					<p style="margin: 0 380px 0 34px;">商品</p>
					<p>单价</p>
					<p style="margin: 0 93px 0 103px;">数量</p>
					<p>操作</p>
				</div>
				<div class="mySpace_right_title_01">
					<span style="margin:0 197px 0 20px;">订单编号：12345678</span>
					<span>成交时间：2017-7-13</span>
					
				</div>
				<ul class="mySpace_right_title_01_con">
					<li style="margin:0 52px 0 14px;">
						<img  src="${pageContext.servletContext.contextPath }/resources/img/goods.png"/>
					</li>
					<li>
						<p>尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p>属性：精品名茶</p>
					</li>
					<li style="margin: 0 110px 0 88px;">
						<p>¥4302</p>
					</li>
					<li>
						<p>1</p>
					</li>
					<li style="margin-left: 94px;">
						<p>立即付款</p>
						<p>删除订单</p>
					</li>
				</ul>
				
				<div class="mySpace_right_title_01">
					<span style="margin:0 197px 0 20px;">订单编号：12345678</span>
					<span>成交时间：2017-7-13</span>
					<span style="margin-left: 226px;">待收货</span>
				</div>
				<ul class="mySpace_right_title_01_con">
					<li style="margin:0 52px 0 14px;">
						<img  src="${pageContext.servletContext.contextPath }/resources/img/goods.png"/>
					</li>
					<li>
						<p>尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p>属性：精品名茶</p>
					</li>
					<li style="margin: 0 110px 0 88px;">
						<p>¥4302</p>
					</li>
					<li>
						<p>1</p>
					</li>
					<li style="margin-left: 94px;">
						<p>确认收货</p>
					</li>
				</ul>
				
				<div class="mySpace_right_title_01">
					<span style="margin:0 197px 0 20px;">订单编号：12345678</span>
					<span>成交时间：2017-7-13</span>
					<span style="margin-left: 226px;">待评价</span>
				</div>
				<ul class="mySpace_right_title_01_con">
					<li style="margin:0 52px 0 14px;">
						<img  src="${pageContext.servletContext.contextPath }/resources/img/goods.png"/>
					</li>
					<li>
						<p>尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p>属性：精品名茶</p>
					</li>
					<li style="margin: 0 110px 0 88px;">
						<p>¥4302</p>
					</li>
					<li>
						<p>1</p>
					</li>
					<li style="margin-left: 94px;">
						<p>再来一单</p>
						<p>删除订单</p>
					</li>
				</ul>
				
				<div class="mySpace_right_page">
					<p class="last_page">上一页</p>
					<p style="opacity: 0;">1</p>
					<p>2</p>
					<p>3</p>
					<p>4</p>
					<p>5</p>
					<p>6</p>
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