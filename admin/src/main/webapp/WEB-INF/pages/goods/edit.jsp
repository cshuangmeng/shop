<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp"></script>
<%@include file="../common/head.jsp" %>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goods/goods.js"></script>
<title>新增商品</title>

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
                    	<span class="mws-i-24 i-graph">新增商户</span>
                    </div>
                    <div class="mws-panel-body">
	                    	<div class="mws-panel-content">
	                    		<form id="update-form" action="submit" enctype="multipart/form-data" method="post">
	                    		<input type="hidden" name="id" value="${empty storeInfo.id?0:storeInfo.id }"/>
	                    		<input type="hidden" name="examineState" value="${empty storeInfo.examineState?'EXAMINING':storeInfo.examineState }"/>
	                    		<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">名称</span>
	                    				<span><input type="text" name="storeName" class="edit-div-input1" value="${storeInfo.storeName }"/></span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">详细地址</span>
	                    				<span><input type="text" name="address" class="edit-div-input1" value="${storeInfo.address }"/></td>
	                    			</div>
                    			</div>
	                    		<div class="edit-div-row">
	                    			<div class="edit-div-column-float">
	                    				<span class="edit-div-label">城市</span>
	                    				<span>
	                    					<select name="city" class="edit-div-select" val="${areaInfo.id }">
	                    					<c:forEach var="city" items="${cityList }">
	                    						<option value="${city.areacode }">${city.city }</option>
	                    					</c:forEach>
	                    					</select>
	                    				</span>
	                    			</div>
	                    			<div>
	                    				<span class="edit-div-label">区划</span>
	                    				<span>
	                    					<select name="areacode" class="edit-div-select" val="${storeInfo.areacode }"></select>
	                    				</span>
	                    			</div>
	                    		</div>
                    			<div class="edit-div-row">
                    				<div class="edit-div-column-float">
	                    				<span class="edit-div-label">BD人员</span>
	                    				<span><select name="bdId" class="edit-div-select" val="${storeInfo.bdId }">
	                    					<c:forEach var="bd" items="${bdList }">
	                    						<option value="${bd.id }">${bd.fullname }</option>
	                    					</c:forEach>
	                    				</select></span>
	                    			</div>
	                    			<div>
                    					<span class="edit-div-label">类型</span>
                    					<span><select name="typeId" class="edit-div-select" val="${storeInfo.typeId }">
                    					<c:forEach var="storeType" items="${storeTypeList }">
                    						<option value="${storeType.dictValue }">${storeType.dictName }</option>
                    					</c:forEach>
                    					</select></span>
                    				</div>
	                    		</div>
	                    		<div class="edit-div-row">
	                    			<div class="edit-div-column-float">
	                    				<span class="edit-div-label">客服电话</span>
	                    				<span><input type="text" name="servicePhone" class="query-div-input" value="${storeDetail.servicePhone }"/></span>
	                    			</div>
	                    			<div>
	                    				<span class="edit-div-label">营业时间</span>
	                    				<span>
	                    					<input type="text" name="businessHours" class="query-div-input" value="${storeDetail.businessHours }"/>
	                    				</span>
	                    			</div>
	                    		</div>
	                    		<div class="edit-div-row">
	                    			<div class="edit-div-column-float">
	                    				<span class="edit-div-label">经度</span>
	                    				<span><input type="text" name="lon" class="query-div-input" value="${empty storeInfo.lon?0:storeInfo.lon }"/></span>
	                    			</div>
	                    			<div>
	                    				<span class="edit-div-label">纬度</span>
	                    				<span><input type="text" name="lat" class="query-div-input" value="${empty storeInfo.lat?0:storeInfo.lat }"/></span>
	                    			</div>
	                    		</div>
	                    		<table id="file-upload-table">
	                    			<tr>
	                    				<td class="edit-file-table-label">商户LOGO</td>
	                    				<td class="edit-file-padding-bottom"><input type="file" name="logoFile" class="edit-div-file"/></td>
	                    			</tr>
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