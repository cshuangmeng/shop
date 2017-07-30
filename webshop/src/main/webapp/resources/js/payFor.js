$(function(){
	var codeUrl=$(":hidden[name='codeUrl']").val();
	//生成二维码
	var qrcode = new QRCode("qrcode", {
	    text: codeUrl,
	    width: 128,
	    height: 128,
	    colorDark : "#000000",
	    colorLight : "#ffffff",
	    correctLevel : QRCode.CorrectLevel.H
	});
})