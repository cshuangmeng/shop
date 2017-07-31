//删除订单
function deleteOrder(orderId){
	if(confirm("确定要删除该订单吗？")){
		$.post(contextPath+"/order/delete",{"orderId":orderId},function(data){
			if(data.code==0){
				$(a).parent().parent().parent().remove();
			}else{
				alert(data.msg);
			}
		});
	}
}
//再次支付
function payOrder(orderId){
	location.href=contextPath+"/order/payWay?orderId="+orderId;
}
$(function(){
	//分页
	function loadPageData(page){
    	var total=parseInt($("#pageTool").val().split("/")[1]);
    	if(page<=1){
    		$("p.last_page").hide();
    	}else{
    		$("p.last_page").show();
    	}
    	if(page>=total){
    		$("p.next_page").hide();
    	}else{
    		$("p.next_page").show();
    	}
	    $.post(contextPath+"/order/search",{"page":page},function(data){
	    	$("#orderResult").html(data);
	    });
    }
	$("p.last_page").hide();
	//绑定分页
    $("p.last_page").click(function(){
    	var tool=$("#pageTool").val();
    	var page=parseInt(tool.split("/")[0])-1;
    	$("#pageTool").val(page+"/"+tool.split("/")[1]);
    	loadPageData(page);
    });
    $("p.next_page").click(function(){
    	var tool=$("#pageTool").val();
    	var page=parseInt(tool.split("/")[0])+1;
    	$("#pageTool").val(page+"/"+tool.split("/")[1]);
    	loadPageData(page);
    });
    $("div.mySpace_right_page").find("p[num]").click(function(){
    	var page=parseInt($(this).text());
    	var tool=$("#pageTool").val();
    	$("#pageTool").val(page+"/"+tool.split("/")[1]);
    	loadPageData(page);
    });
})