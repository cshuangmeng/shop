<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/resources/css/store.css" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/data/storeDetail.js"></script>
<title>商户信息</title>

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
            <%@include file="../common/navigation.jsp" %>
            <!-- End Navigation -->
            
        </div>
        
        
        <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">
        
        	<!-- Main Container -->
            <div class="container">

				<div class="mws-panel grid_8 mws-collapsible">
					<div class="mws-panel-header">
						<span class="mws-i-24 i-graph">商户信息</span> <input type="hidden"
							name="storeId" value="${storeMap.sid}" /> <input
							type="hidden" name="status" value="${storeMap.examineState}" />
					</div>
					<div class="mws-panel-body">
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>名称</b></span> <span id="store_name_span">${not empty storeMap.storeName?storeMap.storeName:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>详细地址</b></span> <span>${not empty storeMap.address?storeMap.address:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float pb12">
									<span class="info-div-label"><b>经度</b></span> <span id="lnglatY">${not empty storeMap.lon?storeMap.lon:'N/A' }</span>
								</div>
								<div class="pb12">
									<span class="info-div-label"><b>纬度</b></span> <span id="lnglatX">${not empty storeMap.lat?storeMap.lat:'N/A' }</span>
								</div>
								<div id="mapContainer"></div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>城市</b></span> <span>${not empty storeMap.city?storeMap.city:'N/A' }</span>
								</div>
								<div>
									<span class="info-div-label"><b>区划</b></span> <span>${not empty storeMap.region?storeMap.region:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>BD人员</b></span> <span>${not empty storeMap.bdName?storeMap.bdName:'N/A' }</span>
								</div>
								<div>
									<span class="info-div-label"><b>商户类型</b></span> <span>${not empty storeMap.typeName?storeMap.typeName:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>客服电话</b></span> <span>${not empty storeMap.servicePhone?storeMap.servicePhone:'N/A' }</span>
								</div>
								<div>
									<span class="info-div-label"><b>营业时间</b></span> <span>${not empty storeMap.businessHours?storeMap.businessHours:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row reason">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>审核状态</b></span> <span id="span_state_name">${not empty storeMap.stateName?storeMap.stateName:'N/A' }</span>
								</div>
								<span class="info-div-textarea-label"><b>拒绝原因</b></span>
								<div class="reason">${not empty storeMap.reason?storeMap.reason:'N/A' }</div>
							</div>
							<div class="edit-div-row">
								<span class="info-div-textarea-label"><b>描述</b></span>
								<div>${not empty storeMap.description?storeMap.description:'N/A' }</div>
							</div>
							<div class="edit-div-row">
								<span class="info-div-label"><b>商户LOGO</b></span> <span class="logo_img_class"><img src="${not empty storeMap.logoImg?storeMap.logoImg:'' }" /></span>
							</div>
							<div class="mws-panel-body" style="clear: both">
								<div class="mws-panel-toolbar top clearfix">
									<ul>
										<li onclick="approveStoreInfo('PASSED')" class="li01"><input class="mws-button green large" type="button" value="审核通过"></li>
										<li onclick="approveStoreInfo('REFUSED')" class="li02"><input class="mws-button green large" type="button" value="审核拒绝"></li>
									</ul>
								</div>
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
	<div id="refuse-reason-dialog">
		<form class="mws-form">
		<div class="mws-form-inline">
			<div class="mws-form-row">
				<label>拒绝理由</label>
				<div class="mws-form-item large">
					<input type="text" name="reason" class="mws-textinput" />
				</span>
			</div>
		</div>
		</form>
	</div>
        
    
    <!-- End Main Wrapper -->
    <!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
    <!-- wifi dialog -->
</body>
</html>