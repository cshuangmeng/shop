$(function(){
	//修改地址
	$("ul[addressId]").click(function(){
		var addressId=$(this).attr("addressId");
		var consigner=$(this).find("li:eq(0) p").text();
		var address=$(this).find("li:eq(1) p").text();
		var mobile=$(this).find("li:eq(2) p").text();
		var isDefault=$(this).find("li.corner_top").length;
		$(":text[name='consigner']").val(consigner);
		$(":text[name='address']").val(address);
		$(":text[name='mobile']").val(mobile);
		$(":hidden[name='addressId']").val(addressId);
		if(isDefault>0){
			$(":checkbox[name='isDefault']").prop("checked",true);
		}else{
			$(":checkbox[name='isDefault']").removeAttr("checked");
		}
	});
	//添加或修改地址
	$("p.keep_address").click(function(){
		var addressId=$(":hidden[name='addressId']").val();
		var consigner=$(":text[name='consigner']").val();
		var address=$(":text[name='address']").val();
		var mobile=$(":text[name='mobile']").val();
		var isDefault=$(":checkbox[name='isDefault']:checked").length;
		if(addressId!=""){
			var data={"id":addressId,"consigner":consigner,"mobile":mobile,"address":address,"isDefault":isDefault};
			$.post(contextPath+"/address/update",data,function(data){
				if(data.code==0){
					location.href=location.href;
				}else{
					alert(data.msg);
				}
			});
		}else{
			var data={"consigner":consigner,"mobile":mobile,"address":address,"isDefault":isDefault,"areaId":0,"areaName":""
				,"postCode":0};
			$.post(contextPath+"/address/save",data,function(data){
				if(data.code==0){
					location.href=location.href;
				}else{
					alert(data.msg);
				}
			});
		}
	});
	$("ul.add_goods_address_con :text").val('');
})