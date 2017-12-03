<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goods/goods.js"></script>
<style>
	::-webkit-scrollbar {
		width: 16px;
		height: 16px;
		background-color: #F5F5F5;
	}
	/*定义滚动条轨道 内阴影+圆角*/
	
	::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
		border-radius: 10px;
		background-color: #F5F5F5;
	}
	/*定义滑块 内阴影+圆角*/
	
	::-webkit-scrollbar-thumb {
		border-radius: 10px;
		-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
		background-color: #555;
	}
	
	.divcss5-a {
		width: 500px;
		height: 270px;
		overflow-y: scroll;
	}
</style>
<title>新增商品</title>

</head>

<body>
	<input type="hidden" name="contextPath"
		value="${pageContext.servletContext.contextPath }" />
	<!-- Themer -->
	<!-- Themer End -->


	<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">

		<!-- Logo Wrapper -->
		<div id="mws-logo-container">
			<div id="mws-logo-wrap">
				<img
					src="${pageContext.servletContext.contextPath }/resources/images/mws-logo.png"
					alt="fms" />
			</div>
		</div>

		<!-- User Area Wrapper -->
		<div id="mws-user-tools" class="clearfix">

			<!-- User Notifications -->

			<!-- User Messages -->

			<!-- User Functions -->
			<div id="mws-user-info" class="mws-inset">
				<div id="mws-user-photo">
					<img
						src="${pageContext.servletContext.contextPath }/resources/images/example/scottwills_underwater2.jpg"
						alt="User Photo" />
				</div>
				<div id="mws-user-functions">
					<div id="mws-username">您好, ${user.username }</div>
					<ul>
						<li><a id="logout" href="javascript:void(0);">退出</a></li>
					</ul>
				</div>
			</div>
			<!-- End User Functions -->

		</div>
	</div>

	<!-- Main Wrapper -->
	<div id="mws-wrapper">
		<!-- Necessary markup, do not remove -->
		<div id="mws-sidebar-stitch"></div>
		<div id="mws-sidebar-bg"></div>

		<!-- Sidebar Wrapper -->
		<div id="mws-sidebar">

			<!-- Search Box -->

			<!-- Main Navigation -->
			<%@include file="../common/navigation.jsp"%>
			<!-- End Navigation -->

		</div>


		<!-- Container Wrapper -->
		<div id="mws-container" class="clearfix">

			<!-- Main Container -->
			<div class="container">

				<div class="mws-panel grid_8 mws-collapsible">
					<div class="mws-panel-header">
						<span class="mws-i-24 i-graph">新增商品</span>
					</div>
					<div class="mws-panel-body">
						<form id="update-form" action="submit" enctype="multipart/form-data" method="post" class="mws-form">
							<input type="hidden" name="id" value="${empty goods.id?0:goods.id }" /> 
							<input type="hidden" name="state" value="${empty goods.state?0:goods.state }" />
							<input type="hidden" name="params" value="${goods.extras.params }" />
							<div class="mws-form-inline">
								<div class="mws-form-row">
									<label>商品名称</label>
									<div class="mws-form-item small">
										<input type="text" name="name" value="${goods.name }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品头像</label>
									<div class="mws-form-item small">
										<input type="file" name="logoImg" class="edit-div-file" />
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品类型</label>
									<div class="mws-form-item small">
										<select name="typeId" val="${goods.typeId }">
											<c:forEach var="type" items="${goodsTypes }">
												<option value="${type.id }">${type.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="mws-form-row">
									<label>店铺名称</label>
									<div class="mws-form-item small">
										<select name="shopId" class="edit-div-select"
											val="${goods.shopId }">
											<c:forEach var="shop" items="${shops }">
												<option value="${shop.id }">${shop.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="mws-form-row">
									<label>价格</label>
									<div class="mws-form-item small">
										<input type="text" name="price" value="${goods.price }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>最低现金折扣</label>
									<div class="mws-form-item small">
										<input type="text" name="cashDiscount" value="${goods.cashDiscount }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品参数</label>
									<div class="mws-form-item small">
										<a href="javascript:void(0)" id="paramInput">点击添加</a>
									</div>
								</div>
								<div class="mws-form-row">
									<label>是否可以使用部落币</label>
									<div class="mws-form-item small">
										<input type="radio" name="coinEnable" val="${goods.coinEnable }" value="1" />是
										<input type="radio" name="coinEnable" value="0"/>否
									</div>
								</div>
								<div class="mws-form-row">
									<label>是否可以使用部落分</label>
									<div class="mws-form-item small">
										<input type="radio" name="pointEnable" val="${goods.pointEnable }" value="1" />是 
										<input type="radio" name="pointEnable" value="0" />否
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品简介</label>
									<div class="mws-form-item small">
										<input type="file" name="infoImg" multiple="multiple" class="edit-div-file" />
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品详情</label>
									<div class="mws-form-item small">
										<input type="file" name="detailImg" multiple="multiple" class="edit-div-file" />
									</div>
								</div>
							</div>
							<div class="edit-mws-button-row">
								<input type="submit" value="提  交" class="mws-button black" />
							</div>
						</form>
					</div>
				</div>
				<!-- 添加商品参数 -->
			    <div id="edit_goods_param_div">
			    	<form class="mws-form" action="" method="post">
		    		<div class="mws-form-inline divcss5-a">
						
		    		</div>
		    		</form>
			    </div>
			</div>

		</div>
		<!-- End Main Container -->

		<!-- Footer -->
		<div id="mws-footer">Copyright &copy; 2015-2018 futeplus.com ,
			All Rights Reserved</div>
		<!-- End Footer -->

	</div>
	<!-- End Container Wrapper -->
	</div>
	<!-- End Main Wrapper -->
	
</body>
</html>