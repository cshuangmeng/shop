//加载城市列表
function loadGoodsTypeList(){
	$.post(contextPath + "/gtype/list",function(data){
	    	$.each(data.data, function (index, val) {
	    		$("select[name='typeId']").append("<option value='"+val.id+"'>"+val.name+"</option>")
		});
    });
}



//加载符合条件的广告组信息
var params;
function queryOrderList(){
	var goodsName=$(":text[name='goodsName']").val();
	var typeId=$("select[name='typeId']").val();
	var state=$("select[name='state']").val();
	var startDate=$(":text[name='startDate']").val();
	var endDate=$(":text[name='endDate']").val();
	var shopName=$(":text[name='shopName']").val();
	//设置默认值
	params={"goodsName":goodsName,"typeId":typeId,"startDate":startDate,"endDate":endDate
			,"state":state,"shopName":shopName};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/goods/list",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='goodsId' value='"+v.goodsId+"'/>"+"</td>"
					+"<td><a href='"+contextPath+"/goods/detail?goodsId="+v.goodsId+"'>" 
					+ (v.goodsName!=undefined&&v.goodsName!=''?v.goodsName:'N/A') + "</a></td>"
					+"<td>" + (v.typeName!=undefined&&v.typeName!=''?v.typeName:'N/A') + "</td>"
					+"<td>" + (v.state!=undefined&&v.state!=''?v.state:'N/A') + "</td>"
					+"<td>" + (v.shopName!=undefined&&v.shopName!=''?v.shopName:'N/A') + "</td>"
					+"<td>" + v.createTime + "</td></tr>"
			$(".mws-datatable").find("tbody").append(html);
		});
		$(".mws-datatable").dataTable({
			"bLengthChange": false,
	        "bFilter": false,
	        "oLanguage": {
	  		  "sProcessing": "正在加载中......",
	            "sLengthMenu": "每页显示 _MENU_ 条记录",
	            "sZeroRecords": "对不起，查询不到相关数据！",
	            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	            "sInfoFiltered": "",
	            "sInfoEmpty": "",
	            "sSearch": "搜索"
	  		}
		});
		//取消全选排序功能
		$(".mws-datatable").find("th.sorting_asc").toggleClass("sorting_asc").unbind("click");
	});
}

//全选
function selectAll(checkAll){
	$(":checkbox[name='goodsId']").each(function(i,v){
		if($(checkAll).attr("checked")!=undefined){
			$(v).attr("checked","checked");
		}else{
			$(v).removeAttr("checked");
		}
	});
}

//编辑广告组
function editAddGroupInfo(){
	var gid = $(":checkbox[name='goodsId']:checked").val();
	if($(":checkbox[name='goodsId']:checked").length==1){
		var storeId=$(":checkbox[name='goodsId']:checked").val();
		location.href=contextPath+"/goods/edit?goodId="+gid;
	}else{
		$("#mws-jui-dialog").text("请先选择一个商品").dialog("open");
	}
}

//上线下线
function approveAdInfo(status){
	var gid = $(":checkbox[name='goodsId']:checked").val();
	if($(":checkbox[name='goodsId']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个商品").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='goodsId']").each(function(i,v){
			if($(v).attr("checked")!=undefined){
				var currStatus=$.trim($(v).parent().parent().find("td:eq(5)").attr("status"));
				if(status==2){
					storeIds=storeIds.length>0?","+$(v).val():$(v).val();
				}else if(status==4){
					storeIds=storeIds.length>0?","+$(v).val():$(v).val();
				}
			}
		});
		if(storeIds.length>0){
			//弹出确认提示框
			$("#mws-jui-dialog").text("确定执行该操作吗？").dialog("option",{title:"确认",buttons:[{
				text:"确认",
				click:function(){
					//发送审核请求
					$.post(contextPath+"/goods/examine",{"goodId":storeIds,"status":status},function(response){
						//重置提示框
						initComTipDialogs();
						if(response.result==0){
							$("#mws-jui-dialog").text("操作成功").dialog("open");
							queryAdgroups();
						}else{
							$("#mws-jui-dialog").text("操作失败，请重试").dialog("open");
						}
					});
				}
			},{
				text:"取消",
				click:function(){
					$(this).dialog("close");
				}
			}]}).dialog("open");
		}
	}
}



$(function(){
	//设置datepicker的全局变量信息
	$.datepicker.setDefaults({"dateFormat":"yy-mm-dd"});
	//将制定的输入框应用datepicker
	$(".mws-datepicker").datepicker({showOtherMonths:true});
	
	//初始化操作系统下拉列表
	initOptionsOfSelect("state","goods_state");
	//初始化城市下拉列表
	loadGoodsTypeList();
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryOrderList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryOrderList);
});

