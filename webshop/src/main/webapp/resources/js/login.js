$(function(){
	//登录
	$(".loginCon").find("form").submit(function(){
		var cellphone=$(".loginCon").find("input[name='cellphone']").val();
		var password=$(".loginCon").find("input[name='password']").val();
		var flag=true;
		if(cellphone==''){
			$(".loginCon").find("input[name='cellphone'] ~ span").css({"visibility":"visible"});
			flag=false;
		}
		if(password==''){
			$(".loginCon").find("input[name='password'] ~ span").css({"visibility":"visible"});
			flag=false;
		}
		return flag;
	});
	//绑定微信登录二维码
	$('.loginWx').click(function(){
		$('#login_container').show();
	});
	var obj = new WxLogin({
		 id:"login_container", 
		 appid: "wx80d859c110cefa54", 
		 scope: "snsapi_login", 
		 redirect_uri: "http%3A%2F%2Fwww.tangseng.shop",
		 state: "STATE",
		 style: "white",
		 href: ""
	});
	$('#login_container').click(function(){
		$(this).hide();
	});
});

