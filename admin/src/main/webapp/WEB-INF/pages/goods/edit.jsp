<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goods/goods.js"></script>
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
						<span class="mws-i-24 i-graph">新增商户</span>
					</div>
					<div class="mws-panel-body">
						<form id="update-form" action="submit" enctype="multipart/form-data" method="post" class="mws-form">
							<input type="hidden" name="id" value="${empty goodsInfo.id?0:goodsInfo.id }" /> 
							<input type="hidden" name="state" value="${empty goodsInfo.state?0:goodsInfo.state }" />
							<div class="mws-form-inline">
								<div class="mws-form-row">
									<label>商品名称</label>
									<div class="mws-form-item small">
										<input type="text" name="goodsName" value="${goodsInfo.goodsName }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品头像</label>
									<div class="mws-form-item small">
										<input type="file" name="logoFile" class="edit-div-file" />
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
										<input type="text" name="price" value="${goodsInfo.price }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>最低现金折扣</label>
									<div class="mws-form-item small">
										<input type="text" name="cashDiscount" value="${goodsInfo.cashDiscount }" class="mws-textinput"/>
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
										<input type="radio" name="coinEnable" />是
										<input type="radio" name="coinEnable" />否
									</div>
								</div>
								<div class="mws-form-row">
									<label>是否可以使用部落分</label>
									<div class="mws-form-item small">
										<input type="radio" name="pointEnable" />是 
										<input type="radio" name="pointEnable" />否
									</div>
								</div>
								<div class="mws-form-row">
									<label>商品详情</label>
									<div class="mws-form-item small">
										<input type="file" name="logoFile" multiple="multiple" class="edit-div-file" />
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
		    		<div class="mws-form-inline">
		    			<div class="mws-form-row">
		    				<label>参数</label>
		    				<div class="mws-form-item small">
		    					<input type="text" class="mws-textinput" />&nbsp;&nbsp;&nbsp;<a href="">删除</a>
		    				</div>
		    			</div>
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
	
	<!-- Web Uploader -->
    <script type="text/javascript">
        // 添加全局站点信息
        var BASE_URL = '${pageContext.servletContext.contextPath }/resources/plugins/webuploader';
    </script>

</body>
</html>