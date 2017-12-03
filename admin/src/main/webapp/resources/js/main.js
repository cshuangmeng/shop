
	////////////////////////////////////////
	//全局变量
	////////////////////////////////////////
	var contextPath;
	
	////////////////////////////////////////
	//初始化页面提醒弹出窗口
	////////////////////////////////////////
	
	function initComTipDialogs(){
		//初始化提示弹出窗口
		$("#mws-jui-dialog").dialog({
			autoOpen: false, 
			title: "提示", 
			modal: true, 
			width: "250", 
			buttons: [{
				text: "关闭", 
				click: function() {
					$(this).dialog("close");
				}
			}]
		});
		//初始化确认弹出窗口
		$("#mws-confirm-dialog").dialog({
			autoOpen: false, 
			title: "提示", 
			modal: true, 
			width: "250", 
			buttons: [{
				text: "关闭", 
				click: function() {
					$(this).dialog("close");
				}
			}]
		});
	}
	
	////////////////////////////////////////
	//登陆相关
	////////////////////////////////////////
	
	//用户登出系统
	function logout(){
		var token=$("meta[name='_csrf']").attr("content");
		var parameterName=$("meta[name='_csrf.parameterName']").attr("content");
		var html="<form name='logout' action='"+contextPath+"/logout' method='POST'>"
				+"<input type='hidden' name='"+parameterName+"' value='"+token+"'/></form>";
		$("body").append(html);
		$("form[name='logout']").submit();
	}
	
	//在所有POST异步请求之前设置Token
	function setRequestHeader(){
		var token=$("meta[name='_csrf']").attr("content");
		var header=$("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function(evt, request, settings) {
			request.setRequestHeader(header, token);
		});
	}
	
	//初始化下拉列表
	function initOptionsOfSelect(select,code,value){
		$.post(contextPath + "/util/dicts",{"parentName":code},function(data){
	        	$.each(data.data, function (index, val) {
	        		$("select[name='"+select+"']").append("<option value='"+val.code+"'>"+val.value+"</option>")
			});
	        	if(undefined!=value){
	        		setSelectedOption(select,value);
	        	}
	    });
	}
	
	//设置下拉默认值
	function setSelectedOption(select,value){
		$("select[name='"+select+"'] > option").each(function(i,v){
			if($(v).attr("value")==(value+"")){
				$(v).attr("selected","selected");
			}
		});
	}
	
	//监听401响应码完成页面跳转到登录页面
	$(document).ajaxError(function (event, jqXHR, ajaxSettings, thrownError) {
		alert(jqXHR.status);
		if (jqXHR.status == "401") {
			window.location.replace(contextPath+"/login");
		}
	});
	
	//判断子字符串是否在父字符串中
	function containsStr(str1,str2){
		var strs=str1.split(",");
		for(var i=0;i<strs.length;i++){
			if(strs[i]==str2){
				return true;
			}
		}
		return false;
	}
	
	//设置Cookie
	function setCookie(name,value){
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	
	//读取Cookie
	function getCookie(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return "";
	}

	////////////////////////////////////////
	//页面加载完毕后调用
	////////////////////////////////////////

	$(function() {
		//初始化菜单
		contextPath=$("meta[name='contextPath']").attr("content");
		//初始化菜单
		var location=window.location;
		$("#mws-navigation").find("a").each(function(i,v){
			var regs=new RegExp(".*"+$(v).attr("href")+".*");
			if(regs.test(location)){
				$(v).parent().parent().parent().find("ul").toggleClass("closed");
				$(v).parent().parent().parent().toggleClass("active");
				$(v).parent().toggleClass("active");
			}
		});
		//初始化提醒弹出窗口
		initComTipDialogs();
		//绑定用户登出事件
		$("#logout").click(logout);
	});