<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/main.css" media="screen" />
<script type="text/javascript" src="../resources/js/shop/stat.js"></script>
<title>商户概括</title>
</head>

<body>
	<!-- Themer -->
	<!-- Themer End -->


	<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">

		<!-- Logo Wrapper -->
		<div id="mws-logo-container">
			<div id="mws-logo-wrap">
				<img src="../resources/images/mws-logo.png" alt="statweb" />
			</div>
		</div>

		<!-- User Area Wrapper -->
		<div id="mws-user-tools" class="clearfix">

			<!-- User Notifications -->

			<!-- User Messages -->

			<!-- User Functions -->
			<div id="mws-user-info" class="mws-inset">
				<div id="mws-user-photo">
					<img src="../resources/images/example/scottwills_cat.jpg"
						alt="User Photo" />
				</div>
				<div id="mws-user-functions">
					<div id="mws-username">您好, ${userData.username  }</div>
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

				<div class="mws-report-container clearfix">
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-battery-full"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title">总商品数</span>
                            <span class="mws-report-value"></span>
                        </span>
                    </a>

                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-battery"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title">待审核商品</span>
                            <span class="mws-report-value"></span>
                        </span>
                    </a>

                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-battery-half"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title">在售商品</span>
                            <span class="mws-report-value"></span>
                        </span>
                    </a>
                    
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-battery-low"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title">下架商品</span>
                            <span class="mws-report-value"></span>
                        </span>
                    </a>
                    
                	<a class="mws-report" href="#">
                    	<span class="mws-report-icon mws-ic ic-light-circle-green"></span>
                        <span class="mws-report-content">
                        	<span class="mws-report-title">今日添加</span>
                            <span class="mws-report-value"></span>
                        </span>
                    </a>
                </div>

				<div class="mws-panel grid_8 mws-collapsible">
					<div class="mws-panel-header">
						<span class="mws-i-24 i-graph"><span class="doubt">订单走势
						</span> </span>
					</div>
					<div class="mws-panel-body">
						<div class="mws-panel-content">
							<table class='fc-header' style='width: 100%'>
								<tbody>
									<tr></tr>
								</tbody>
							</table>
							<div id="mws-dashboard-chart" style="width: 100%; height: 400px;"></div>
						</div>
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