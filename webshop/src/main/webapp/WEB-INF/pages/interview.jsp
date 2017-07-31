<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/businessManInterview.css"/>
		<%@include file="util/script.jsp" %>
		<title>企业家专访</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<div class="commonTop">
				<%@include file="util/head.jsp" %>
			</div>
			<div class="commomNavigation">
				<%@include file="util/menu.jsp" %>
			</div>
			<div class="interview_top"></div>
		<div class="interview_top_txt">
			<p>他们，是一个时代的标杆</p>
			<p>是一个伟大的族群</p>
			<p>是梦想和使命的化身！是拼搏进取的代言人！</p>
			<p>是为后代栽下参天大树的前辈！是我们奔驰路上的引路人......</p>
		</div>
		<ul class="interview_person">
			<li class="interview_person_l">
				<p class="left_person"></p>
				<p class="left_corner"></p>
				<div  style="background: url(img/intrduce01.png) no-repeat center bottom;" class="interview_person_l_txt">
					<span>朱慧秋</span>
					<p class="interview_person_l_txt1">安徽省华信生物药业股份有限公司董事长</p>
				</div>
			</li>
			
			<li class="interview_person_l">
				<div  style="background: url(img/intrduce03.png) no-repeat center bottom;background-position-y:110px ;" class="interview_person_l_txt">
					<span>曾立品</span>
					<p class="interview_person_l_txt1">安徽省华信生物药业股份有限公司董事长</p>
					<p class="interview_person_l_txt1">云南省总商会副会长、云南创立生物医药集团股份有限公司董事长</p>
					
				</div>
				<p class="right_corner"></p>
				<p class="right_person"></p>
			</li>
			
			<li class="interview_person_l">
				<p class="left_person02"></p>
				<p class="left_corner"></p>
				<div  style="background: url(img/intrduce04.png) no-repeat center bottom;" class="interview_person_l_txt">
					<span>徐桂芬</span>
					<p class="interview_person_l_txt1">煌上煌集团有限公司董事局主席</p>
				</div>
			</li>
			
			<li class="interview_person_l">
				<div  style="background: url(img/intrduce02.png) no-repeat center bottom;background-position-y:100px ;" class="interview_person_l_txt">
					<span>吴文中</span>
					<p class="interview_person_l_txt1">中国科学院博士后导师</p>
					<p class="interview_person_l_txt1">大连医诺生物有限公司董事长</p>
				</div>
				<p class="right_corner"></p>
				<p class="right_person02"></p>
			</li>
			</ul>
			<div class="interview_bottom_txt">
				<p>他们，是一个时代的标杆</p>
				<p>是一个伟大的族群</p>
				<p>是梦想和使命的化身！是拼搏进取的代言人！</p>
				<p>是为后代栽下参天大树的前辈！是我们奔驰路上的引路人......</p>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
	</body>
</html>