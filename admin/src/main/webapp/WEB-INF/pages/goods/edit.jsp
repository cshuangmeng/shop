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
	                    		<input type="hidden" name="id" value="${empty goodsInfo.id?0:goodsInfo.id }"/>
	                    		<input type="hidden" name="state" value="${empty goodsInfo.state?0:goodsInfo.state }"/>
	                    		<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">商品名称</span>
	                    				<span><input type="text" name="goodsName" class="edit-div-input1" value="${goodsInfo.goodsName }"/></span>
	                    			</div>
                    			</div>
	                    		<div class="edit-div-row">
	                    			<div class="edit-div-column-float">
	                    				<span class="edit-div-label">商品类型</span>
	                    				<span>
	                    					<select name="typeId" class="edit-div-select" val="${goods.typeId }">
	                    					<c:forEach var="type" items="${goodsTypes }">
	                    						<option value="${type.id }">${type.name }</option>
	                    					</c:forEach>
	                    					</select>
	                    				</span>
	                    			</div>
	                    		</div>
	                    		<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">店铺名称</span>
	                    				<span>
	                    					<select name="shopId" class="edit-div-select" val="${goods.shopId }">
	                    					<c:forEach var="shop" items="${shops }">
	                    						<option value="${shop.id }">${shop.name }</option>
	                    					</c:forEach>
	                    					</select>
	                    				</span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">价格</span>
	                    				<span><input type="text" name="price" class="edit-div-input1" value="${goodsInfo.price }"/></span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">最低现金折扣</span>
	                    				<span><input type="text" name="cashDiscount" class="edit-div-input1" value="${goodsInfo.cashDiscount }"/></span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">商品参数</span>
	                    				<span><a href="">点击添加</a></span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">是否可以使用部落币</span>
	                    				<span><input type="radio" name="coinEnable" class="edit-div-input1"/>是
	                    				<input type="radio" name="coinEnable" class="edit-div-input1"/>否</span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">是否可以使用部落分</span>
	                    				<span><input type="radio" name="pointEnable" class="edit-div-input1"/>是
	                    				<input type="radio" name="pointEnable" class="edit-div-input1"/>否</span>
	                    			</div>
                    			</div>
                    			<div class="edit-div-row">
	                    			<div>
	                    				<span class="edit-div-label">当前状态</span>
	                    				<span>
	                    					<select name="state" class="edit-div-select" val="${goods.state }">
	                    					<c:forEach var="state" items="${states }">
	                    						<option value="${shop.code }">${shop.value }</option>
	                    					</c:forEach>
	                    					</select>
	                    				</span>
	                    			</div>
                    			</div>
	                    		<table id="file-upload-table">
	                    			<tr>
	                    				<td class="edit-file-table-label">商品头像</td>
	                    				<td class="edit-file-padding-bottom"><input type="file" name="logoFile" class="edit-div-file"/></td>
	                    				<tr><td colspan="3"><input type="button" value="点击添加"/></td></tr>
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