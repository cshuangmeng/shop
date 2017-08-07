$(function(){
	//登录
	$(".loginCon").find("form").submit(function(){
		var cellphone=$(".loginCon").find("input[name='cellphone']").val();
		var password=$(".loginCon").find("input[name='password']").val();
		var code=$(":hidden[name='code']").val();
		var flag=true;
		if(code==""){
			if(cellphone==""){
				$(".loginCon").find("input[name='cellphone'] ~ span").css({"visibility":"visible"});
				flag=false;
			}
			if(password==""){
				$(".loginCon").find("input[name='password'] ~ span").css({"visibility":"visible"});
				flag=false;
			}
		}
		return flag;
	});
	//绑定微信登录二维码
	$('.loginWx').click(function(){
		var url="https://open.weixin.qq.com/connect/qrconnect?appid=wx80d859c110cefa54" +
				"&redirect_uri=http%3A%2F%2Fwww.tangseng.shop%2Fweb%2Fuser%2Flogin" +
				"&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
		location.href=url;
	});
	/*var obj = new WxLogin({
		 id:"login_container", 
		 appid: "wx80d859c110cefa54", 
		 scope: "snsapi_login", 
		 redirect_uri: "http%3A%2F%2Fwww.tangseng.shop%2Fweb%2Flogin",
		 state: "STATE",
		 style: "white",
		 href: ""
	});*/
	$('#login_container').click(function(){
		$(this).hide();
	});
	//扫描二维码登录成功
	var param=parseQueryString(location.href);
	if(param.code!=undefined&&param.code!=null){
		$(":hidden[name='code']").val(param.code);
		//$("form").submit();
	}
});

