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
		<div class="p-login-main">
			<!--navigation-->
			<%@include file="util/head.jsp" %>
			<!--goodsDetail-->
			<div class="goodslist">
				<div class="brand">
					<div>
						<div class="left">
							品牌
						</div>
						<div class="right">
							<div><img src="img/qr.png"/></div>
							<div class="active">
								<img src="img/jinpaifuwu.png"/>
								<span>X</span>
							</div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
							<div><img src="img/qr.png"/></div>
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
					<div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
						<div class="li">
							<img src="img/FX.jpg" alt="" />
							<p class="tit">即使商品质量好，也不能漫天要价.</p>
							<p class="money">
								<span class="truemoney">￥123</span>
								<span class="minmoney">最低实付价￥<b>50</b></span>
							</p>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!--footer-->
			<%@include file="util/foot.jsp" %>
		</div>
	</body>
</html>