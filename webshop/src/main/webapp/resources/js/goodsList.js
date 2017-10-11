$(function(){
	var $more = $('#js-more'),$moreList = $('#js-moreList');
	//显示隐藏更多
	$more.mouseenter(function(){
		$moreList.css("display","block");
	});
    $more.mouseleave(function(){
		$moreList.css("display","none");
	});
    //商品分页
    var param=parseQueryString(window.location);
    param.sort=1;
    function loadPageData(page){
    		param.page=page;
	    $.post(contextPath+"/goods/search",param,function(data){
		    	$("#goodsResult").html(data);
		    	var page=$(":hidden[name='page']").val();
		    	var total=$(":hidden[name='total']").val();
		    	$("#pageTool").find("b").text(page+"/"+total);
		    	if(page<=1){
		    		$("#pageTool").find("span").eq(0).hide();
		    	}else{
		    		$("#pageTool").find("span").eq(0).show();
		    	}
		    	if(page>=total){
		    		$("#pageTool").find("span").eq(1).hide();
		    	}else{
		    		$("#pageTool").find("span").eq(1).show();
		    	}
	    });
    }
    loadPageData(1);
    //绑定分页
    $("#pageTool").find("span").eq(0).click(function(){
    	var tool=$("#pageTool").find("b");
    	var page=parseInt(tool.text().split("/")[0])-1;
    	tool.text(page+"/"+tool.text().split("/")[1]);
    	loadPageData(page);
    });
    $("#pageTool").find("span").eq(1).click(function(){
    	var tool=$("#pageTool").find("b");
    	var page=parseInt(tool.text().split("/")[0])+1;
    	tool.text(page+"/"+tool.text().split("/")[1]);
    	loadPageData(page);
    });
	
	//选定门店
	$("span[shopId]").click(function(){
		$("span[shopId]").removeClass("all");
		$(this).addClass("all");
		if($(this).attr("shopId")!=0){
			param.shopId=$(this).attr("shopId");
		}else{
			delete param["shopId"];
		}
		var page=parseInt($("#pageTool").find("b").text().split("/")[0])
		loadPageData(page);
	});
	//预设类别
	if(param.typeId!=undefined&&param.typeId!=null){
		$("span[typeId]").removeClass("all");
		$("span[typeId="+param.typeId+"]").addClass("all");
	}
	//选定类别
	$("span[typeId]").click(function(){
		$("span[typeId]").removeClass("all");
		$(this).addClass("all");
		if($(this).attr("typeId")!=0){
			param.typeId=$(this).attr("typeId");
		}else{
			delete param["typeId"];
		}
		var page=parseInt($("#pageTool").find("b").text().split("/")[0])
		loadPageData(page);
	});
	//搜索
	$(".enture,.price button").click(function(){
		var minPrice=$(".price").find(":text:eq(0)").val();
		var maxPrice=$(".price").find(":text:eq(1)").val();
		var exp=new RegExp("^[1-9][0-9]*$");
		if(exp.test(minPrice)){
			param.minPrice=minPrice;
		}else{
			delete param["minPrice"]
		}
		if(exp.test(maxPrice)){
			param.maxPrice=maxPrice;
		}else{
			delete param["maxPrice"]
		}
		var page=parseInt($("#pageTool").find("b").text().split("/")[0])
		loadPageData(page);
	});
	//排序
	$("div.left:eq(1)").find("span").click(function(){
		$(this).parent().find("span").removeClass("active");
		$(this).addClass("active");
		param.sort=$(this).attr("sort");
		if(undefined==$(this).attr("rule")||$(this).attr("rule")=="0"){
			$(this).attr("rule",1);
		}else{
			$(this).attr("rule",0);
		}
		param.rule=$(this).attr("rule");
		var page=parseInt($("#pageTool").find("b").text().split("/")[0])
		loadPageData(page);
	});
	
});

