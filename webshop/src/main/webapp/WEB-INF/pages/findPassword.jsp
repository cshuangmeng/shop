<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/findPassword.css"/>
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/findPassword.js"></script>
		<title>首页</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<%@include file="util/head.jsp" %>
			<!--goodsDetail-->
			<div class="pass">
				<div class="passcon">
					<div class="tit">
						<span>首页 > 找回密码</span>
					</div>
					<!--第一步-->
					<div class="con">
						<img class="passImg" src="${pageContext.servletContext.contextPath }/resources/img/pass01.jpg"/>
						<p>
							<span>登录名：</span>
							<input class="name" type="text" placeholder="手机号/用户名/邮箱" name="" id="" value="" />
						</p>
						<p>
							<span>验证码：</span>
							<input class="code" type="text" name="" id="" value="" />
							<b>2341</b>
							<span class="cursor">换一张</span>
						</p>
						<p>
							<button>下一步</button>
						</p>
					</div>
					<!--第二步-->
					<div class="con">
						<img class="passImg" src="${pageContext.servletContext.contextPath }/resources/img/pass02.png"/>
						<p>正发送到 157*****311，请注意查收。并在下方输入短信中验证码：</p>
						<p class="telCode">
							<span>验证码：</span>
							<input class="code" type="text" name="" id="" value="" />
							<b>获取验证码</b>
						</p>
						<p>
							<button>确定</button>
						</p>
					</div>
					<!--第三步-->
					<div class="con">
						<img class="passImg" src="${pageContext.servletContext.contextPath }/resources/img/pass03.png"/>
						<p>
							<span>新密码：</span>
							<input class="newPass" type="text" placeholder="由6-20位字母、数字和符号组成" name="" id="" value="" />
						</p>
						<p>
							<span>确认密码：</span>
							<input class="newPassAgain" type="text" placeholder="请再次输去上面的密码" name="" id="" value="" />
						</p>
						<p>
							<button>下一步</button>
						</p>
					</div>
					<!--第四步-->
					<div class="con" style="padding-bottom: 180px;">
						<img class="passImg" style="margin-bottom: 20px;" src="${pageContext.servletContext.contextPath }/resources/img/pass04.png"/>
						<p class="getPssSuccess">恭喜您，您的新密码已设置成功，为保证您的购物安全， </p>
						<p class="getPssSuccess">建议您定期更改密码以保护账户安全。</p>
					</div>
					
					<p class="botTit">如果您忘记登录名，将无法找回您的账户信息，您可以点击这里<span>
					<a href="${pageContext.servletContext.contextPath }/register">重新注册</a></span>
					 。如果您有任何疑问，请随时拨打服务热线：400-6789-8</p>
				</div>
			</div>
			<!--footer-->
			<%@include file="util/foot.jsp" %>
		</div>
	</body>
</html>