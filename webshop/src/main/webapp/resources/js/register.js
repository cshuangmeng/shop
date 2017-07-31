$(function(){
	//数据校验
	function checkForm(){
		var cellphone=$(":text[name='cellphone']");
		var password=$(":password[name='password']");
		var confirm=$(":password[name='confirm']");
		var code=$(":text[name='code']");
		var result=true;
		$(".registerCon").find(":text,:password").each(function(i,v){
			if($(v).val()==""){
				$(v).css({"border-color":"red","color":"red"});
				result=false;
			}
		});
		if(result&&password.val()!=confirm.val()){
			confirm.css({"border-color":"red","color":"red"});
			result=false;
		}
		return result;
	}
	$(".registerCon").find(":text,:password").focus(function(){
		$(this).css({"border-color":"#bcbcbc","color":"#bcbcbc"});
	});
	$("#submitBtn").click(function(){
		if(checkForm()){
			var cellphone=$(":text[name='cellphone']").val();
			var password=$(":password[name='password']").val();
			var confirm=$(":password[name='confirm']").val();
			var code=$(":text[name='code']").val();
			$.post(contextPath+"/user/register",{"cellphone":cellphone,"password":password,"code":code},function(data){
				if(data.code==0){
					alert("注册成功,现在去登录");
					location.href=contextPath+"/user/login";
				}else{
					alert(data.msg);
				}
			});
		}
	});
	//下发验证码
	$("#getCodeBtn").click(function(){
		var cellphone=$(":text[name='cellphone']");
		if(cellphone.val()==""){
			cellphone.css({"border-color":"red","color":"red"});
		}else{
			$.ajax({url:contextPath+"/user/code",async:false,data:{"cellphone":cellphone.val()},success:function(data){
				if(data.code==0){
					alert("验证码下发成功");
				}else{
					alert(data.msg);
				}
			}});
		}
	});
});