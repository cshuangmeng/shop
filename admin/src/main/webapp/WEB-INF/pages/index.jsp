<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="common/head.jsp"%>
<title>后台管理平台 － 首页</title>

</head>

<body>
	<!-- Themer -->
	<!-- Themer End -->


	<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">

		<!-- Logo Wrapper -->
		<div id="mws-logo-container">
			<div id="mws-logo-wrap">
				<img src="${pageContext.servletContext.contextPath }/resources/images/mws-logo.png" alt="vms" />
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
			<%@include file="common/navigation.jsp"%>
			<!-- End Navigation -->

		</div>


		<!-- Container Wrapper -->
		<div id="mws-container" class="clearfix">

			<!-- Main Container -->
			<div class="container">
				<p>Hello</p>
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

	<!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
</body>
</html>