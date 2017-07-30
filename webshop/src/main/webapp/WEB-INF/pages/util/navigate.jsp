<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="mySpace_left">
	<div class="personImg">
	</div>
	<div class="mySpace_left_con_t">
		<ul class="mySpace_left_con">
			<li class="mySpace_left_con_1">
				<img src="${sessionScope.user.headImg }1"/>
				<p>我的账户</p>
				
			</li>
			<li style="color: #DB3E40;" class="mySpace_left_con_2"><a href="${pageContext.servletContext.contextPath }/order/list">订单管理</a></li>
			<li class="mySpace_left_con_3"><a href="${pageContext.servletContext.contextPath }/address/list">收货地址</a></li>
		</ul>
		<ul style="margin: 20px 0;" class="mySpace_left_con">
			<li class="mySpace_left_con_1">
				<img src="${pageContext.servletContext.contextPath }/resources/img/5_03.png"/>
				<p>我的资产</p>
			</li>
			<li class="mySpace_left_con_2"><a href="${pageContext.servletContext.contextPath }/user/account">钱包</a></li>
		</ul>
		<ul class="mySpace_left_con">
			<li class="mySpace_left_con_1">
				<img src="${pageContext.servletContext.contextPath }/resources/img/3_03.png"/>
				<p>我的部落</p>
			</li>
			<li class="mySpace_left_con_2"><a href="${pageContext.servletContext.contextPath }/tribe/info">成员</a></li>
		</ul>
	</div>
</div>