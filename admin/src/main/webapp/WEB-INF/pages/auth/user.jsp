<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/auth/user.js"></script>

<title>后台人员列表</title>

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
                    <div id="mws-username">您好, ${user.username  }</div>
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
            
            	<div id="user_list_div" class="mws-panel grid_8">
                	<div class="mws-panel-header">
                    	<span class="mws-i-24 i-graph">后台人员</span>
                    	<div class="mws-collapse-button">
                    		<span> </span>
                    	</div>
                    </div>
                    <div class="mws-panel-body">
                    	<div class="mws-panel-toolbar top clearfix">
							<ul>
								<li onclick="addRoleForUser()"><a
									href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">分配角色</a></li>
							</ul>
						</div>
	                   	<table id="user_list_table" class="mws-datatable mws-table">
	                        <thead>
	                        	<th style="width:50px;"></th>
	                        	<th>用户名</th>
	                        	<th>密码</th>
	                        	<th>是否可用</th>
	                        	<th>角色</th>
	                        	<th>创建时间</th>
	                        	<th></th>
	                        </thead>
	                        <tbody>
	                        </tbody>
	                    </table>
                    </div>
                </div>
                
            </div>
            <!-- End Main Container -->
            
            <!-- Footer -->
            <div id="mws-footer">Copyright &copy; 2015-2018 futeplus.com , All Rights Reserved</div>
            <!-- End Footer -->
            
        </div>
        <!-- End Container Wrapper -->
        
    </div>
    <!-- End Main Wrapper -->
    
    <!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
	<!-- 用户信息编辑表单 -->
    <div id="edit_user_info_div">
    	<form class="mws-form" action="" method="post">
    		<input type="hidden" name="id"/>
    		<div class="mws-form-inline">
    			<div class="mws-form-row">
    				<label>用户名</label>
    				<div class="mws-form-item small">
    					<input type="text" class="mws-textinput" name="username" />
    				</div>
    			</div>
    			<div class="mws-form-row">
    				<label>密码</label>
    				<div class="mws-form-item small">
    					<input type="text" class="mws-textinput" name="pwd" />
    				</div>
    			</div>
    			<div class="mws-form-row">
    				<label>当前状态</label>
    				<div class="mws-form-item small">
    					<select name="enabled">
    						<option value="true">可用</option>
    						<option value="false">不可用</option>
    					</select>
    				</div>
    			</div>
    		</div>
    	</form>
    </div>
    <!-- 角色选择弹出窗口 -->
    <div id="role_list_dialog">
    	<table id="role_list_table" class="mws-datatable mws-table">
            <thead>
            	<th><input type="checkbox" name="checkAll" onclick="selectAll(this)" />&nbsp;&nbsp;&nbsp;全选</th>
            	<th>角色名</th>
            	<th>描述</th>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</body>
</html>