$(function(){
	var codeUrl=$(":hidden[name='codeUrl']").val();
	var orderId=$(":hidden[name='orderId']").val();
	//生成二维码
	var qrcode = new QRCode("qrcode", {
	    text: codeUrl,
	    width: 128,
	    height: 128,
	    colorDark : "#000000",
	    colorLight : "#ffffff",
	    correctLevel : QRCode.CorrectLevel.H
	});
	//循环获取订单状态
	function checkOrderStatus(){
		$.post(contextPath+"/order/info",{"orderId":orderId},function(data){
			if(data.code==0&&data.data.state==1){
				location.href=contextPath+"/tip?callback=paySuccess&orderId="+orderId;
			}
		})
	}
	window.setInterval(function(){checkOrderStatus();},5000);
})