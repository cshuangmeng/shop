<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/resources/css/store.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/ldpage/groupDetail.js"></script>
<title>订单详情</title>

</head>

<body>
	<input type="hidden" name="contextPath" value="${pageContext.servletContext.contextPath }"/>
	<!-- Themer -->  
    <!-- Themer End -->

	<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">
    
    	<!-- Logo Wrapper -->
    	<div id="mws-logo-container">
        	<div id="mws-logo-wrap">
            	<img src="${pageContext.servletContext.contextPath }/resources/images/mws-logo.png" alt="fms" />
			</div>
        </div>
        
        <!-- User Area Wrapper -->
        <div id="mws-user-tools" class="clearfix">
        
        	<!-- User Notifications -->
            
            <!-- User Messages -->
            
            <!-- User Functions -->
            <div id="mws-user-info" class="mws-inset">
            	<div id="mws-user-photo">
                	<img src="${pageContext.servletContext.contextPath }/resources/images/example/scottwills_underwater2.jpg" alt="User Photo" />
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
            <%@include file="../common/navigation.jsp" %>
            <!-- End Navigation -->
            
        </div>
        
        
        <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">
        
        	<!-- Main Container -->
            <div class="container">

				<div class="mws-panel grid_8 mws-collapsible">
					<div class="mws-panel-header">
						<span class="mws-i-24 i-graph">订单信息</span>
					</div>
					<div class="mws-panel-body">
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>商户单号</b></span> <span id="store_name_span">${order.tradeNo }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>支付单号</b></span> <span id="store_name_span">${order.outTradeNo }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>商品名称</b></span> <span id="store_name_span">${order.goodsName }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>下单用户</b></span> <span id="store_name_span">${order.cellphone }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>支付方式</b></span> <span id="store_name_span">${order.payWay }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>订单金额</b></span> <span id="store_name_span">${order.price }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>商品数量</b></span> <span id="store_name_span">${order.amount }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>部落币</b></span> <span id="store_name_span">${order.coin }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>部落分</b></span> <span id="store_name_span">${order.point }</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>收货人</b></span>
									<span id="store_name_span">
										${order.consigner }
									</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>收货地址</b></span>
									<span id="store_name_span">
										${order.areaName }${order.address }
									</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>收货电话</b></span>
									<span id="store_name_span">
										${order.mobile }
									</span>
								</div>
							</div>
						</div>
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>下单时间</b></span>
									<span id="store_name_span">
										${order.createTime }
									</span>
								</div>
							</div>
						</div>
					</div>

            <!-- End Main Container -->
            
            <!-- Footer -->
            <div id="mws-footer">Copyright &copy; 2015-2018 futeplus.com , All Rights Reserved</div>
            <!-- End Footer -->
            
        <!-- End Container Wrapper -->
     <!-- confirm dialog -->

        
    
    <!-- End Main Wrapper -->
    <!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
	</body>
</html>