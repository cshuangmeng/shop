<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="common/head.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		$("div#mws-login .mws-login-back").mouseover(function(event) {
			$(this).find("a").animate({'left':0})
		}).mouseout(function(event) {
			$(this).find("a").animate({'left':70})
		});
	});
</script>
<title>登录</title>
</head>
</body>
	<div id="mws-login">
    	<h1>伏特加管理后台</h1>
        <div class="mws-login-lock"><img src="resources/css/icons/24/locked-2.png" alt="" /></div>
    	<div id="mws-login-form">
        	<form class="mws-form" action="loginSubmit" method="post">
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<input type="text" class="mws-login-username mws-textinput" placeholder="username" name="username"/>
                    </div>
                </div>
                <div class="mws-form-row">
                	<div class="mws-form-item large">
                    	<input type="password" class="mws-login-password mws-textinput" placeholder="password" name="pwd"/>
                    </div>
                </div>
                <div class="mws-form-row">
                	<input type="submit" value="登 录" class="mws-button green mws-login-button" />
                	<div style="color:red;margin-top:5px;text-align:center;">${message }</div>
                </div>
            </form>
        </div>
    </div>
</html>