<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/head.jsp"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/data/store.js"></script>
<title>商户列表</title>
</head>

<body>
	<input type="hidden" name="contextPath"
		value="${pageContext.servletContext.contextPath }" />
	<!-- Themer -->
	<!-- Themer End -->


	<!-- Header Wrapper -->
	<div id="mws-header" class="clearfix">

		<!-- Logo Wrapper -->
		<div id="mws-logo-container">
			<div id="mws-logo-wrap">
				<img
					src="${pageContext.servletContext.contextPath }/resources/images/mws-logo.png"
					alt="fms" />
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
			<%@include file="../common/navigation.jsp"%>
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
						<div id="query-div">
							<div class="query-div-row">
								<span class="query-div-label">商户名称</span> <span><input
									type="text" class="edit-div-input1" name="fullName" /></span>
							</div>
							<div class="query-div-row">
								<div class="query-div-column-float">
									<span class="query-div-label">城市</span> <span><select
										class="query-div-select" name="city">
											<option value="0">全部</option>
											<c:forEach var="city" items="${cityList }">
												<option value="${city.areacode }">${city.city }</option>
											</c:forEach>
									</select> </span>
								</div>
								<div class="query-div-column-float">
									<span class="query-div-label">区划</span> <span><select
										name="areacode" class="query-div-select"></select></span>
								</div>
								<div>
									<span class="query-div-label">类型</span> <span><select
										name="typeId" class="query-div-select">
											<option value="">全部</option>
											<c:forEach var="storeType" items="${storeTypeList }">
												<option value="${storeType.dictValue }">${storeType.dictName }</option>
											</c:forEach>
									</select></span>
								</div>
							</div>
							<div class="query-div-row">
								<div class="query-div-column-float">
									<span class="query-div-label">BD&nbsp;&nbsp;&nbsp;</span> <span><select
										name="bdId" class="query-div-select">
											<option value="0">全部</option>
											<c:forEach var="bd" items="${bdList }">
												<option value="${bd.id }">${bd.fullname }</option>
											</c:forEach>
									</select> </span>
								</div>
								<div class="query-div-column-float">
									<span class="query-div-label">状态</span> <span><select
										name="status" class="query-div-select">
											<option value="">全部</option>
											<c:forEach var="state" items="${stateList }">
												<option value="${state.dictValue }">${state.dictName }</option>
											</c:forEach>
									</select> </span>
								</div>
							</div>
							<div class="query-div-row">
								<div class="query-div-column-float">
									<span class="query-div-label">注册时间</span> <span> <input
										type="text" name="createTimeB"
										class="mws-textinput mws-datepicker query-div-input1" /> - <input
										type="text" name="createTimeE"
										class="mws-textinput mws-datepicker query-div-input1" />
									</span>
								</div>
								<div>
									<span class="query-div-label">充电宝数</span> <span> <input
										type="text" name="pbTotalNoB" class="query-div-input1" /> - <input
										type="text" name="pbTotalNoE" class="query-div-input1" />
									</span>
								</div>
							</div>
							<div class="query-div-row">
								<span class="query-div-submit-span"><input name="submit"
									type="button" value="查  询" class="mws-button green large" /></span>
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
								<li onclick="editStoreInfo('add')"><a
									href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">新增</a></li>
								<li onclick="overduedStore('PASSED')"><a
									href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">审核通过</a></li>
								<li onclick="overduedStore('REFUSED')"><a
									href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">审核拒绝</a></li>
								<li onclick="overduedStore('OVERDUED')"><a
									href="javascript:void(0);" class="mws-ic-16 ic-arrow-refresh">下线</a></li>
								<li onclick="editStoreInfo('edit')"><a href="javascript:void(0);"
									class="mws-ic-16 ic-edit">编辑</a></li>
								<li onclick="exportStoreQueryResult()"><a
									href="javascript:void(0);" class="mws-ic-16 ic-printer">导出</a></li>
								<li onclick="importData()"><a 
									href="javascript:void(0);" class="mws-ic-16 ic-printer" >Excel导入</a></li>
							</ul>
						</div>
						<table id="store-result-table" class="mws-datatable mws-table">
							<thead>
								<th><input type="checkbox" name="checkAll" onclick="selectAll(this)" />&nbsp;&nbsp;&nbsp;全选</th>
								<th>名称</th>
								<th>类型</th>
								<th>地区</th>
								<th>负责人</th>
								<th>充电宝数</th>
								<th>状态</th>
								<th>创建时间</th>
							</thead>
							<tbody>
							</tbody>
						</table>
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

	<!-- alert dialog -->
	<div id="mws-jui-dialog"></div>
	<div id="mws-confirm-dialog"></div>
	<div id="add-excel-dialog">
		<form name="addExcel" action="store/import" method="post" enctype="multipart/form-data" id="addExcel">
			<div class="modal-body">
				<div class="row gap">
					<label class="col-sm-7 control-label">请选择Excel文件：<input
						class="btn btn-default" id="excel_file" type="file"
						accept="xls" name="excelFile" />
					</label>
					<span><a style="color: blue;" href="../file/商户录入模板.xls">下载模板</a></span>
				</div>
			</div> 
		</form>
	</div>	
</body>
</html>