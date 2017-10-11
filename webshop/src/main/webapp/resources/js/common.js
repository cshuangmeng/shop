
	////////////////////////////////////////
	//全局变量
	////////////////////////////////////////
	var contextPath;
	
	////////////////////////////////////////
	//初始化页面提醒弹出窗口
	////////////////////////////////////////
	
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
	
	//监听401响应码完成页面跳转到登录页面
	$(document).ajaxError(function (event, jqXHR, ajaxSettings, thrownError) {
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
	
	//把url的参数部分转化成json对象
	function parseQueryString(url) {
		var reg_url = /^[^\?]+\?([\w\W]+)$/,
		reg_para = /([^&=]+)=([\w\W]*?)(&|$|#)/g,
		arr_url = reg_url.exec(url),
		ret = {};
		if (arr_url && arr_url[1]) {
			var str_para = arr_url[1], result;
			while ((result = reg_para.exec(str_para)) != null) {
				ret[result[1]] = result[2];
			}
		}
		return ret;
	}
	
	//通过key获取url中的参数值
	function getQueryString (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return unescape(r[2]);
		}
		return null;
	}
	
	//搜索栏目监听回车事件
	function doSearchByEnter(){
		var key=$.trim($("div.search").find(":text").val());
		if(key!=""){
			location.href=contextPath+"/goods/list?name="+encodeURI(encodeURI(key));
		}
	}
	
	////////////////////////////////////////
	//页面加载完毕后调用
	////////////////////////////////////////

	$(function() {
		//初始化菜单
		contextPath=$("meta[name='contextPath']").attr("content");
		//绑定用户登出事件
		$("#logout").click(logout);
		//商品搜索
		var key=decodeURI(null!=getQueryString("name")?getQueryString("name"):"");
		$("div.search").find(":text").val(key);
		$("div.search").find("img").click(function(){
			key=$.trim($("div.search").find(":text").val());
			if(key!=""){
				location.href=contextPath+"/goods/list?name="+encodeURI(encodeURI(key));
			}
		});
		//更多栏目
		var $more = $('#js-more'),
		$moreList = $('#js-moreList');
		//显示隐藏更多
		$more.mouseenter(function(){
			$moreList.css("display","block");
		});
	    $more.mouseleave(function(){
			$moreList.css("display","none");
		});
	    if($("div.personImg").length>0&&$("div.personImg").attr("headImg")!=""){
	    		$("div.personImg").css("background",'url('+$("div.personImg").attr("headImg")+')');
	    }
	    $(".navigationCon :text").keydown(function(event){
		    	if(event.keyCode==13){
		    		doSearchByEnter();
		    	}
	    	}); 
	});