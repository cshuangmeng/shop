<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goodsList.js"></script>
		<title>首页</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<%@include file="../util/head.jsp" %>
			<!--goodsDetail-->
			<div class="goodslist">
				<div class="brand">
					<div>
						<div class="left">
							品牌
						</div>
						<div class="right">
							<div><img src="${pageContext.servletContext.contextPath }/resources/img/qr.png"/></div>
							<div class="active">
								<img src="${pageContext.servletContext.contextPath }/resources/img/jinpaifuwu.png"/>
								<span>X</span>
							</div>
							<c:forEach items="${result.data.shops }" var="shop">
							<div><img src="${shop.fullHeadImg }"/></div>
							</c:forEach>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="btn">
					<button class="enture">确定</button>
					<button class="cancel">取消</button>
				</div>
				<div class="line"></div>
				<div class="screen">
					<div class="left">
						<span class="active">综合</span>
						<b></b>
						<span>价格</span>
						<b></b>
						<span>折扣</span>
						<b></b>
						<div class="price">
							<input type="text" placeholder="￥"/>
							<span>-</span>
							<input type="text" placeholder="￥"/>
							<button>确定</button>
						</div>
					</div>
					<div  class="right">
						<b>1/25</b>
						<span><</span>
						<span>下一页 ></span>
					</div>
				</div>
				<div class="goodslistCon">
					<div id="goodsResult">
						
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!--footer-->
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>