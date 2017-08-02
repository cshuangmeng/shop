$(function(){
	//显示所有地址
	function showAllAddress(){
		$("li[name='display']").removeClass("display_none");
		$("#showAllAddress").text("隐藏全部地址");
		$("#showAllAddress").unbind("click");
		$("#showAllAddress").click(function(){
			displayAllAddress();
		});
	}
	//隐藏所有地址
	function displayAllAddress(){
		$("li[name='display']").addClass("display_none");
		$("#showAllAddress").text("显示全部地址");
		$("#showAllAddress").unbind("click");
		$("#showAllAddress").click(function(){
			showAllAddress();
		});
	}
	//显示全部
	$("#showAllAddress").click(function(){
		showAllAddress();
	});
	//抵扣
	//设置总价
	var totalPrice=0;
	$(".goods_price_end").each(function(i,v){
		totalPrice+=parseInt($(v).text().replace("¥",""));
	});
	function setPayPrice(){
		var pointRate=$(":hidden[name='pointRate']").val();
		var coinRate=$(":hidden[name='coinRate']").val();
		var point=$(":text[name='point']").val()!=""?$(":text[name='point']").val():0;
		var coin=$(":text[name='coin']").val()!=""?$(":text[name='coin']").val():0;
		var totalDeduct=0;
		totalDeduct+=Math.round(point/pointRate);
		$("#pointDeduct").text("¥"+Math.round(point/pointRate));
		totalDeduct+=Math.round(coin/coinRate);
		$("#coinDeduct").text("¥"+Math.round(coin/coinRate));
		$("#payPrice").text("¥"+(totalPrice-totalDeduct));
		return totalPrice-totalDeduct;
	}
	function deductible(){
		var welcome=2;
		//部落番号是否可以填写
		$(":text[name='tribeId']").parent().parent().hide();
		$(":hidden[name='typeId']").each(function(i,v){
			if($(v).val()==welcome){
				$(":text[name='tribeId']").parent().show();
			}
		});
		//部落币是否可以填写
		$(":text[name='coinEnable']").parent().parent().hide();
		$(":hidden[name='coinEnable']").each(function(i,v){
			if($(v).val()==1){
				$(":text[name='coinEnable']").parent().show();
			}
		});
		//部落分是否可以填写
		$(":text[name='pointEnable']").parent().parent().hide();
		$(":hidden[name='pointEnable']").each(function(i,v){
			if($(v).val()==1){
				$(":text[name='pointEnable']").parent().show();
			}
		});
		$("#totalPrice").text("¥"+totalPrice);
		$("#payPrice").text("¥"+totalPrice);
		//绑定折扣检查
		$(":text[name='coin'],:text[name='point']").blur(function(){
			var totalMiniPrice=$(":hidden[name='totalMiniPrice']").val();
			var pointRate=$(":hidden[name='pointRate']").val();
			var coinRate=$(":hidden[name='coinRate']").val();
			var point=$(":text[name='point']").val()!=""?$(":text[name='point']").val():0;
			var coin=$(":text[name='coin']").val()!=""?$(":text[name='coin']").val():0;
			var exp=new RegExp("^[0-9]*$");
			var totalDeduct=0;
			if(!exp.test(point)){
				alert("请输入正确的部落分数量");
				return;
			}
			if(point%pointRate>0){
				alert("部落分数量请输入"+pointRate+"的倍数");
				return;
			}
			if(!exp.test(coin)){
				alert("请输入正确的部落币数量");
				return;
			}
			if(coin%coinRate>0){
				alert("部落币数量请输入"+coinRate+"的倍数");
				return;
			}
			if(setPayPrice()<totalMiniPrice){
				alert("最低实付现金金额不能低于"+totalMiniPrice);
				return;
			}
		});
	}
	deductible();
	//提交订单
	$(".payAllRight").click(function(){
		$(":text[name='coin']").trigger("blur");
		var itemIds="";
		$("li[goodsId]").each(function(i,v){
			itemIds+=itemIds.length>0?","+$(this).attr("goodsId"):$(this).attr("goodsId");
		});
		if($(".corner").length<=0){
			alert("请先选择收货地址");
			return;
		}
		var orderId=0;
		$.ajax({url:contextPath+"/order/new",
			    data:{"itemIds":itemIds,
			    	  "coin":$(":text[name='coin']").val()!=""?$(":text[name='coin']").val():0,
			    	  "point":$(":text[name='point']").val()!=""?$(":text[name='point']").val():0,
			    	  "tribeId":$(":text[name='tribeId']").val()!=""?$(":text[name='tribeId']").val():0,
			    	  "addressId":$(".corner").parent().attr("addressId"),
			    	  "price":setPayPrice()
			    	 },
			    type:"post",
			    dataType:"json",
			    success:function(data){
			    	if(data.code==0){
			    		orderId=data.data.orderId;
			    		location.href=contextPath+"/order/payWay?orderId="+orderId;
			    	}else{
			    		alert(data.msg);
			    	}
			    }
		});
	});
	//绑定地址选择
	$("li.address_con1").click(function(){
		$("li.address_con1 img").remove();
		$(this).append("<img class=\"corner\" src=\""+contextPath+"/resources/img/right.png\"/>");
	});
	
});