$(function(){
	//增加商品数量
	$(".add").click(function(){
		var num=$(this).parent().find(".numberCode");
		num.text(parseInt(num.text())+1);
		$("#payPrice").text("¥"+setPayPrice());
	});
	//减少商品数量
	$(".minus").click(function(){
		var num=$(this).parent().find(".numberCode");
		if(num.text()=="1"){
			if(confirm("确定要删除该商品吗？")){
				removeGoods($(this).parent().parent());
			}
		}else{
			num.text(parseInt(num.text())-1);
			$("#payPrice").text("¥"+setPayPrice());
		}
	});
	//删除商品
	function removeGoods(li){
		var goodsId=li.attr("goodsId");
		$.post(contextPath+"/user/dropGoods",{"goodsId":goodsId},function(data){
			if(data.code==0){
				li.remove();
				$("#payPrice").text("¥"+setPayPrice());
			}else{
				alert(data.msg);
			}
		})
	}
	$(".delectImg").click(function(){
		if(confirm("确定要删除该商品吗？")){
			removeGoods($(this).parent());
		}
	});
	//计算应付金额
	function setPayPrice(){
		var payPrice=0;
		$(".goods_price_end").each(function(i,v){
			var price=parseInt($(v).parent().find(".goods_price").text().replace("¥",""));
			var amount=parseInt($(v).parent().find(".numberCode").text());
			$(this).text("¥"+(amount*price));
			payPrice+=parseInt($(v).text().replace("¥",""));
		});
		return payPrice;
	}
	$("#payPrice").text("¥"+setPayPrice());
	//结算订单
	$(".payAllRight").click(function(){
		var itemIds="";
		var items="";
		$("li[goodsId]").each(function(i,v){
			var goodsId=$(v).attr("goodsId");
			var amount=$(v).find(".numberCode").text();
			itemIds+=itemIds.length>0?","+$(v).attr("goodsId"):$(v).attr("goodsId");
			items+=items.length>0?"_"+goodsId+","+amount:goodsId+","+amount;
		});
		//保存商品数量
		$.post(contextPath+"/user/addMultiGoods",{"items":items},function(data){
			if(data.code==0){
				location.href=contextPath+"/order/confirm?itemIds="+itemIds;
			}else{
				alert(data.msg);
			}
		})
	});
})