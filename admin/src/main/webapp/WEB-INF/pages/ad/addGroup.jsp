<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp" %>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath }/resources/css/ldpage.css" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/ldpage/addGroup.js"></script>
<title>新增广告组</title>
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
                    	<span class="mws-i-24 i-graph">新增广告组</span>
                    </div>
                    <div class="mws-panel-body">
	                    	<div class="mws-panel-content">
	                    		<form id="update-form" action="updateGroup?${_csrf.parameterName}=${_csrf.token}" method="post">
	                    		<input type="hidden" name="adverId" value="${not empty adgroup.adverId?adgroup.adverId:1 }"/>
	                    		<input type="hidden" name="id" value="${not empty adgroup.id?adgroup.id:0 }"/>
	                    		<input type="hidden" name="status" value="${not empty adgroup.status?adgroup.status:0 }"/>
	                    		<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">广告组名称</span>
	                    				<span><input type="text" name="groupName" class="edit-div-input" value="${adgroup.groupName }"/></span>
	                    			</div>
                    			</div>
								<div class="edit-div-row">
									<div class="query-div-column-float">
										<span class="query-div-label">日期</span> 
										<span> 
											<input type="text" name="beginDate" class="mws-textinput mws-datepicker query-div-input1" value="${adgroup.beginDate }"/> - 
											<input type="text" name="endDate" class="mws-textinput mws-datepicker query-div-input1" value="${adgroup.endDate }"/>
										</span>
									</div>
									<div>
										<span class="edit-div-label">时段</span> 
										<span> 
											<select name="beginTime" class="query-div-select" value="${adgroup.beginTime }">
												<option value="">全部</option>
												<option value="00:00">00:00</option>
												<option value="01:00">01:00</option>
												<option value="02:00">02:00</option>
												<option value="03:00">03:00</option>
												<option value="04:00">04:00</option>
												<option value="05:00">05:00</option>
												<option value="06:00">06:00</option>
												<option value="07:00">07:00</option>
												<option value="08:00">08:00</option>
												<option value="09:00">09:00</option>
												<option value="10:00">10:00</option>
												<option value="11:00">11:00</option>
												<option value="12:00">12:00</option>
												<option value="13:00">13:00</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>
												<option value="20:00">20:00</option>
												<option value="21:00">21:00</option>
												<option value="22:00">22:00</option>
												<option value="23:00">23:00</option>
											</select>
											<span> - </span> 
											<select name="endTime" class="query-div-select" value="${adgroup.endTime }">
												<option value="">全部</option>
												<option value="00:00">00:00</option>
												<option value="01:00">01:00</option>
												<option value="02:00">02:00</option>
												<option value="03:00">03:00</option>
												<option value="04:00">04:00</option>
												<option value="05:00">05:00</option>
												<option value="06:00">06:00</option>
												<option value="07:00">07:00</option>
												<option value="08:00">08:00</option>
												<option value="09:00">09:00</option>
												<option value="10:00">10:00</option>
												<option value="11:00">11:00</option>
												<option value="12:00">12:00</option>
												<option value="13:00">13:00</option>
												<option value="14:00">14:00</option>
												<option value="15:00">15:00</option>
												<option value="16:00">16:00</option>
												<option value="17:00">17:00</option>
												<option value="18:00">18:00</option>
												<option value="19:00">19:00</option>
												<option value="20:00">20:00</option>
												<option value="21:00">21:00</option>
												<option value="22:00">22:00</option>
												<option value="23:00">23:00</option>
										</select>
										</span>
									</div>
								</div>
								<div class="edit-div-row">
									<div class="query-div-column-float pl46">
										<span class="query-div-label">操作系统</span>
	                 					<span>
	                 						<select class="query-div-select" name="os" value="${adgroup.os }">
	              								<option value="">全部</option>
	              							</select>
	              						</span>
									</div>
									<div>
										<span class="query-div-label">城市</span>
	                 					<span>
	                 						<select class="query-div-select" name="city" value="${adgroup.city }">
	              								<option value="">全部</option>
	              							</select>
	              						</span>
									</div>
								</div>
								<div class="edit-div-row">
									<div class="query-div-column-float pl46">
										<span class="query-div-label">广告组类型</span>
	                 					<span>
	                 						<select class="query-div-select" name="groupType" value="${adgroup.groupType }">
	              							</select>
	              						</span>
									</div>
								</div>
								</table>
	                    		</div>
	                    		<div class="edit-mws-button-row">
	                    			<input type="submit" value="提  交" class="mws-button black"/>
	                    		</div>
	                    		</form>
                        </div>
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

</body>
</html>