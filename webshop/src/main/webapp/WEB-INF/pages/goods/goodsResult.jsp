<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty result.data.goods }">
<c:forEach items="${result.data.goods }" var="g">
<a href="${pageContext.servletContext.contextPath }/goods/info?id=${g.id}">
<div class="li">
	<img src="${g.fullHeadImg }" alt="" />
	<p class="tit">${g.name }</p>
	<p class="money">
		<span class="truemoney">￥${g.price }</span>
		<span class="minmoney">最低实付价￥<b>${g.extras.miniPrice }</b></span>
	</p>
</div>
</a>
</c:forEach>
</c:if>
