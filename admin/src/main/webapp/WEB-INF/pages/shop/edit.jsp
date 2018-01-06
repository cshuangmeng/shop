<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goods/goods.js"></script>
<title>新增店铺</title>

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
					<div id="mws-username">您好, ${userData.username }</div>
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
							<input type="hidden" name="id" value="${empty shop.id?0:shop.id }" /> 
							<div class="mws-form-inline">
								<div class="mws-form-row">
									<label>店铺名称</label>
									<div class="mws-form-item small">
										<input type="text" name="name" value="${shop.name }" class="mws-textinput"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>店铺头像</label>
									<div class="mws-form-item small">
										<input type="file" name="logoImg" class="edit-div-file" />
									</div>
								</div>
								<div class="mws-form-row">
									<label>联系人</label>
									<div class="mws-form-item small">
										<input type="text" name="contact" class="edit-div-file" value="${shop.contact }"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>联系电话</label>
									<div class="mws-form-item small">
										<input type="text" name="telephone" class="edit-div-file" value="${shop.telephone }"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>所在地</label>
									<div class="mws-form-item small">
										<input type="text" name="address" class="edit-div-file" value="${shop.address }"/>
									</div>
								</div>
								<div class="mws-form-row">
									<label>简介</label>
									<div class="mws-form-item small">
										<textarea name="introduction" class="mws-textinput">${shop.introduction }</textarea>
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