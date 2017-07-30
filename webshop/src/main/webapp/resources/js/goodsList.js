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
    	var total=parseInt($("#pageTool").find("b").text().split("/")[1]);
    	param.page=page;
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
	    $.post(contextPath+"/goods/search",param,function(data){
	    	$("#goodsResult").html(data);
	    	var page=$(":hidden[name='page']").val();
	    	var total=$(":hidden[name='total']").val();
	    	$("#pageTool").find("b").text(page+"/"+total);
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
	//商品列表定位
	position();
	function position(){
		$('.goodslist .goodslistCon .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
	}
	//选定门店
	$("img[shopId]").click(function(){
		if($(this).parent().find("span").length>0){
			$(this).parent().find("span").remove();
			$(this).parent().removeClass("active");
		}else{
			$(this).parent().append("<span>X</span>");
			$(this).parent().addClass("active");
		}
	});
	//搜索
	$(".enture,.price button").click(function(){
		var shopIds="";
		delete param["shopIds"];
		$("div.active").each(function(i,v){
			shopIds+=shopIds.length>0?","+$(v).find("img").attr("shopId"):$(v).find("img").attr("shopId");
		});
		if(shopIds!=""){
			param.shopIds=shopIds;
		}
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
	$(".cancel").click(function(){
		$("img[shopId]").each(function(i,v){
			$(v).parent().find("span").remove();
			$(v).parent().removeClass("active");
		});
	});
	
});

