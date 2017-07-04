<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/resources/css/store.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/ldpage/groupDetail.js"></script>
<title>广告组详情</title>

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
                    <div id="mws-username">您好, <sec:authentication property="name"/></div>
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
						<span class="mws-i-24 i-graph">广告组信息</span>
					</div>
					<div class="mws-panel-body">
						<div class="mws-panel-content">
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>广告主</b></span> <span>${not empty adgroup.adverName?adgroup.adverName:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>广告组名称</b></span> <span>${not empty adgroup.groupName?adgroup.groupName:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div>
									<span class="info-div-label"><b>广告类型</b></span> <span>${not empty adgroup.groupTypeName?adgroup.groupTypeName:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float pb12">
									<span class="info-div-label"><b>开始日期</b></span> <span>${not empty adgroup.beginDate?adgroup.beginDate:'N/A' }</span>
								</div>
								<div class="pb12">
									<span class="info-div-label"><b>结束日期</b></span> <span>${not empty adgroup.endDate?adgroup.endDate:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float pb12">
									<span class="info-div-label"><b>开始时间</b></span> <span>${not empty adgroup.beginTime?adgroup.beginTime:'N/A' }</span>
								</div>
								<div class="pb12">
									<span class="info-div-label"><b>结束时间</b></span> <span>${not empty adgroup.endTime?adgroup.endTime:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>城市</b></span> <span>${not empty adgroup.cityName?adgroup.cityName:'N/A' }</span>
								</div>
								<div>
									<span class="info-div-label"><b>平台</b></span> <span>${not empty adgroup.os?adgroup.os:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>状态</b></span> <span>${not empty adgroup.statusName?adgroup.statusName:'N/A' }</span>
								</div>
								<div>
									<span class="info-div-label"><b>创建时间</b></span> <span>${not empty adgroup.createTime?adgroup.createTime:'N/A' }</span>
								</div>
							</div>
							<div class="edit-div-row">
								<div class="info-div-column-float">
									<span class="info-div-label"><b>修改时间</b></span> <span>${not empty adgroup.updateTime?adgroup.updateTime:'N/A' }</span>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="mws-panel grid_8 mws-collapsible">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-list">查询结果</span>
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-toolbar top clearfix">
                        	<ul>
                        		<li onclick="showWiFiAddDialog()"><a href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">添加</a></li>
                        		<li onclick="deleteAddGroup()"><a href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">删除</a></li>
                        		<li onclick="adUpdateDialog()"><a href="javascript:void(0);" class="mws-ic-16 ic-edit">编辑</a></li>
                            </ul>
                        </div>
						<table id="store-result-table" class="mws-datatable mws-table">
							<thead>
								<th style="width: 93px"><input type="checkbox" name="checkAll" onclick="selectAll(this)" />&nbsp;&nbsp;&nbsp;全选</th>
								<th style="width: 152px">广告标题</th>
								<th>广告地址</th>
								<th>跳转地址</th>
								<th>跳转方式</th>
								<th>创建时间</th>
							</thead>
							<tbody>
							</tbody>
						</table>
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
    <!-- 添加广告  -->
	<div id="ad-add-dialog">
		<form class="mws-form" id="add-form" name="ad-add-form" action="updateCreative?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="groupId" value="${adgroup.id}" />
			<input type="hidden" name="adverId" value="${adgroup.adverId}" />
			<div class="mws-form-inline">
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">标题</span> <span> <input
							type="text" name="title" class="edit-div-input" style="width:200px" /></span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">图片地址</span> <span>
						<input type="file" name="imgFile" class="edit-div-file"/>
						</span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">点击跳转地址</span> <span> <input
							type="text" name="actionTarget" class="edit-div-input" style="width:200px" /></span>
					</div>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">跳转地址打开方式</span> <span> <select
						class="query-div-select" name="actionType">
							<option value="0">全部</option>
					</select>
					</span>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">是否需要登录</span> <span> <select
						class="query-div-select" name="needLogin" >
					</select>
					</span>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">分享内容</span> <span> <input
							type="text" name="shareContent" class="edit-div-input" style="width:400px" />
					</span>
				</div>
			</div>
		</form>
	</div>
	 <!-- 修改广告  -->
	<div id="ad-update-dialog">
		<form class="mws-form" id="adcreative-update-form" name="adcreative-update-form" action="updateCreative?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
			<input type="hidden" name="id"/>
			<input type="hidden" name="groupId" value="${adgroup.id}" />
			<input type="hidden" name="adverId" value="${adgroup.adverId}" />
			<input type="hidden" name="status" value="1" />
			<input type="hidden" name="imgUrl" value="" />
			<div class="mws-form-inline">
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">标题</span> <span> <input
							type="text" name="title" class="edit-div-input" style="width:200px" /></span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">图片地址</span> <span>
						<input type="file" name="imgFile" class="edit-div-file"/>
						</span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="edit-div-label">点击跳转地址</span> <span> <input
							type="text" name="actionTarget" class="edit-div-input" style="width:200px" /></span>
					</div>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">跳转地址打开方式</span> <span> <select
						class="query-div-select" name="actionType" >
							<option value="0">全部</option>
					</select>
					</span>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">是否需要登录</span> <span> <select
						class="query-div-select" name="needLogin" >
					</select>
					</span>
				</div>
				<div class="mws-form-row">
					<span class="query-div-label">分享内容</span> <span> <input
							type="text" name="shareContent" class="edit-div-input" style="width:400px" />
					</span>
				</div>
			</div>
		</form>
	</div>
	 <!-- 广告详情  -->
	 <div id="ad-detail-dialog">
	 	<form class="mws-form" id="adcreative-detail-form" name="ad-update-form" action="updateCreative?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" method="post">
			<div class="mws-form-inline">
				<div class="mws-form-row">
					<div>
						<span class="info-div-label"><b>标题</b></span> <span class="title"></span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="info-div-label"><b>图片</b></span> <span><img class="pic"/></span>
					</div>
				</div>
				<div class="mws-form-row">
					<div>
						<span class="info-div-label"><b>点击跳转地址</b></span> <span class="actionTarget"></span>
					</div>
				</div>
				<div class="mws-form-row">
					<span class="info-div-label"><b>跳转地址打卡方式</b></span> <span class="actionType"></span>
				</div>
				<div class="mws-form-row">
					<span class="info-div-label"><b>是否需要登录</b></span> <span class="needLogin"></span>
				</div>
				<div class="mws-form-row">
					<span class="info-div-label"><b>分享内容</b></span> <span class="shareContent"></span>
				</div>
			</div>
		</form>
	</div>
	</body>
</html>