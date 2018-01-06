<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/shop/shop.js?v=1"></script>
<title>店铺列表</title>
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
            <%@include file="../common/navigation.jsp" %>
            <!-- End Navigation -->
            
        </div>
        
        
        <!-- Container Wrapper -->
        <div id="mws-container" class="clearfix">
        
        	<!-- Main Container -->
            <div class="container">
            
            	<div class="mws-panel grid_8 mws-collapsible">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">查询条件</span>
                    </div>
                    <div class="mws-panel-body">
                 		<form id="query-form">
                 			<div class="query-div-row">
	                 			<div class="query-div-column-float">
	                 				<span class="query-div-label">店铺名称</span>
                 					<span><input type="text" class="edit-div-input1 w400" name="shopName"/></span>
	                 			</div>
	                 			<div>
	                 				<span class="query-div-label">商品状态</span>
	                 				<span><select name="state" class="query-div-select">
	                 					<option value="">全部</option>
	                 				</select></span>
	                 			</div>
                 			</div>
               				<div class="query-div-row">
               					<span class="query-div-submit-span"><input name="submit" type="button" value="查  询" class="mws-button green large"/></span>
               				</div>
                 		</form>
                    </div>
                </div>
                
                <div class="mws-panel grid_8 mws-collapsible">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-list">查询结果</span>
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-toolbar top clearfix">
                        	<ul>
                        		<li><a href="${pageContext.servletContext.contextPath }/shop/edit" class="mws-ic-16 ic-arrow-refresh">添加</a></li>
                        		<li onclick="editAddGroupInfo()"><a href="javascript:void(0);" class="mws-ic-16 ic-edit">编辑</a></li>
                        		<li onclick="approveAdInfo('1')"><a href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">通过</a></li>
                            	<li onclick="approveAdInfo('2')"><a href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">拒绝</a></li>
                            </ul>
                        </div>
						<table id="store-result-table" class="mws-datatable mws-table">
							<thead>
								<th style="width: 93px"><input type="checkbox" name="checkAll" onclick="selectAll(this)" />&nbsp;&nbsp;&nbsp;全选</th>
								<th style="width: 152px">店铺名称</th>
								<th>联系人</th>
								<th>联系电话</th>
								<th>状态</th>
								<th>添加时间</th>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
                </div>
                
            </div>
            <!-- End Main Container -->
            
            <!-- Footer -->
            <div id="mws-footer">Copyright &copy; 2017-2020 tangseng.com , All Rights Reserved</div>
            <!-- End Footer -->
            
        </div>
        <!-- End Container Wrapper -->
        
    </div>
    <!-- End Main Wrapper -->
	
	<!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
</body>
</html>