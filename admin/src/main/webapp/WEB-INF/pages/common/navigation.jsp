<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="mws-navigation">
	<ul>
		<c:forEach items="${userData.menus }" var="menu" varStatus="status">
			<c:if test="${menu.parentId==0 }">
				<c:if test="${!status.first }">
					</ul>
					</li>
				</c:if>
				<li>
				<a href="${menu.url!='#'?pageContext.servletContext.contextPath:'' }${menu.url }" class="mws-i-24 ${menu.icon }">${menu.name }</a>
				<ul class="closed">
			</c:if>
			<c:if test="${menu.parentId>0 }">
				<li><a href="${pageContext.servletContext.contextPath }${menu.url }">${menu.name }</a></li>
			</c:if>
			<c:if test="${status.last }">
				</ul>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>