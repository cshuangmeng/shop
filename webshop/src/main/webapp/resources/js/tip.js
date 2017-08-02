$(function(){
	//判断回调类型
	var param=parseQueryString(location.href);
	if(param.callback=="paySuccess"){
		$.post(contextPath+"/order/info",{"orderId":param.orderId},function(data){
			var address=data.data.extras.address.address;
			$("div.money").html("付款金额：<span>￥"+data.data.price+"</span>");
			$("div.address").html("收货地址："+address);
			$("div.con:eq(1)").show();
		});
		$("div.yesBtn").click(function(){
			location.href=contextPath+"/order/list";
		});
		$("div.noBtn").click(function(){
			location.href=contextPath+"/user/account";
		});
	}
})