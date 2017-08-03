$(function(){
	
	//第一步
	var one=$("div.con:eq(0)");
	var two=$("div.con:eq(1)");
	var three=$("div.con:eq(2)");
	var four=$("div.con:eq(3)");
	one.show();
	//获取图形验证码
	function getVerifyCode(){
		$("img[name='verifyCode']").attr("src",contextPath+"/user/verify?"+(new Date()));
	}
	getVerifyCode();
	//换一张
	$("span.cursor").click(function(){getVerifyCode();});
	//下一步
	one.find("button").click(function(){
		var cellphone=$.trim(one.find(":text.name").val());
		var code=$.trim(one.find(":text.code").val());
		if(cellphone==""){
			alert("请输入登录名");
			return;
		}
		var exp=new RegExp("^1[0-9]{10}$")
		if(!exp.test(cellphone)){
			alert("请输入正确的登录名");
			return;
		}
		if(code==""){
			alert("请输入验证码");
			return;
		}
		$.post(contextPath+"/user/code",{"cellphone":cellphone,"code":code},function(data){
			if(data.code==0){
				one.hide();
				two.find("p:eq(0)").text("短信验证码已发送到 "+cellphone.substring(0,3)+"****"+cellphone.substring(7)+"，请注意查收。并在下方输入短信中验证码：");
				two.show();
			}else{
				alert(data.msg);
			}
		})
	});
	//获取验证码
	$("p.telCode b").click(function(){
		two.hide();
		getVerifyCode();
		one.show();
	});
	//下一步
	two.find("p:eq(2)").click(function(){
		var code=$.trim(two.find(":text.code").val());
		var cellphone=$.trim(one.find(":text.name").val());
		if(code==""){
			alert("请输入验证码");
			return;
		}
		$.post(contextPath+"/user/reset",{"code":code,"cellphone":cellphone},function(data){
			if(data.code==0){
				two.hide();
				three.show();
			}else{
				alert(data.msg);
			}
		});
	});
	//重置密码
	//下一步
	three.find("p:eq(2)").click(function(){
		var cellphone=$.trim(one.find(":text.name").val());
		var code=$.trim(two.find(":text.code").val());
		var newPass=$.trim(three.find(":password.newPass").val());
		var newPassAgain=$.trim(three.find(":password.newPassAgain").val());
		if(newPass==""){
			alert("请输入密码");
			return;
		}
		var reg=new RegExp("[a-z|A-z|0-9|_]{6,12}");
		if(!reg.test(newPass)){
			alert("密码不符合要求");
			return;
		}
		if(newPassAgain==""){
			alert("请输入确认密码");
			return;
		}
		if(newPass!=newPassAgain){
			alert("两次输入的密码不一致");
			return;
		}
		$.post(contextPath+"/user/reset",{"code":code,"cellphone":cellphone,"password":newPass},function(data){
			if(data.code==0){
				three.hide();
				four.show();
			}else{
				alert(data.msg);
			}
		});
	});
	
});

