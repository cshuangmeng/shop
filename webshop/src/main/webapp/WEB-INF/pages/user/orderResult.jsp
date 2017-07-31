<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${result.data.orders }" var="order">
	<div class="mySpace_right_title_01">
		<span style="margin:0 197px 0 20px;">订单编号：${order.tradeNo }</span>
		<span>成交时间：${order.createTime }</span>
		
	</div>
	<c:forEach items="${order.goods }" var="goods">
	<ul class="mySpace_right_title_01_con">
		<li style="margin:0 52px 0 14px;">
			<img  src="${goods.headImg }"/>
		</li>
		<li style="width:210px;">
			<p>${goods.name }</p>
			<p>${goods.typeName }</p>
		</li>
		<li style="margin: 0 110px 0 88px;">
			<p>¥${goods.price }</p>
		</li>
		<li>
			<p>${goods.amount }</p>
		</li>
		<li style="margin-left: 94px;">
			<p><a href="javascript:payOrder(${order.orderId });">立即付款</a></p>
			<c:if test="${order.state=='待付款' }">
				<p><a href="javascript:deleteOrder(${order.orderId });">删除订单</a></p>
			</c:if>
		</li>
	</ul>
	</c:forEach>
</c:forEach>