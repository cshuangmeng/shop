<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="TOP">
	<div  class="top">
		<div class="topCon">
			<span><a href="${pageContext.servletContext.contextPath }/user/car">购物车</a></span>
			<b>/</b>
			<c:choose>
			<c:when test="${not empty sessionScope.user }">
				<span><a href="${pageContext.servletContext.contextPath }/user/logout">退出</a></span>
			</c:when>
			<c:otherwise>
				<span><a href="${pageContext.servletContext.contextPath }/register">注册</a></span>				
			</c:otherwise>
			</c:choose>
			<b>/</b>
			<c:choose>
			<c:when test="${not empty sessionScope.user }">
				<span class="login">欢迎您，<a href="${pageContext.servletContext.contextPath }/user/account">${sessionScope.user.nickname }</a></span>
			</c:when>
			<c:otherwise>
				<span class="login"><a href="${pageContext.servletContext.contextPath }/login">登录</a></span>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<div class="header">
		<div class="headerCon">
			<div class="logo">
				<a href="${pageContext.servletContext.contextPath }/index"><img src="${pageContext.servletContext.contextPath }/resources/img/logo.png"/></a>
			</div>
			<div class="icon">
				<div>
					<span class="img">
						<img src="${pageContext.servletContext.contextPath }/resources/img/zhongqiliandong.png"/>
					</span>
					<span class="con">众企联动</span>
				</div>
				<div>
					<span class="img">
						<img src="${pageContext.servletContext.contextPath }/resources/img/didaopinzhi.png"/>
					</span>
					<span class="con">地道品质</span>
				</div>
				<div>
					<span class="img">
						<img src="${pageContext.servletContext.contextPath }/resources/img/pinpaizhigong.png"/>
					</span>
					<span class="con">品牌直供</span>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>