//加载符合条件的广告组信息
var params;
function queryOrderList(){
	var shopName=$(":text[name='shopName']").val();
	var state=$("select[name='state']").val();
	//设置默认值
	params={"name":shopName,"state":state};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/shop/list",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='shopId' value='"+v.shopId+"'/>"+"</td>"
					+"<td><a href='"+contextPath+"/shop/detail?shopId="+v.shopId+"'>" 
					+ (v.shopName!=undefined&&v.shopName!=''?v.shopName:'N/A') + "</a></td>"
					+"<td>" + (v.contact!=undefined&&v.contact!=''?v.contact:'N/A') + "</td>"
					+"<td>" + (v.telephone!=undefined&&v.telephone!=''?v.telephone:'N/A') + "</td>"
					+"<td state="+v.state+">" + (v.stateName!=undefined&&v.stateName!=''?v.stateName:'N/A') + "</td>"
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
	$(":checkbox[name='shopId']").each(function(i,v){
		if($(checkAll).attr("checked")!=undefined){
			$(v).attr("checked","checked");
		}else{
			$(v).removeAttr("checked");
		}
	});
}

//编辑广告组
function editAddGroupInfo(){
	var gid = $(":checkbox[name='shopId']:checked").val();
	if($(":checkbox[name='shopId']:checked").length==1){
		var storeId=$(":checkbox[name='shopId']:checked").val();
		location.href=contextPath+"/shop/edit?shopId="+gid;
	}else{
		$("#mws-jui-dialog").text("请先选择一个店铺").dialog("open");
	}
}

//上线下线
function approveAdInfo(status){
	var gid = $(":checkbox[name='shopId']:checked").val();
	if($(":checkbox[name='shopId']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个店铺").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='shopId']").each(function(i,v){
			if($(v).attr("checked")!=undefined){
				var currStatus=$.trim($(v).parent().parent().find("td:eq(4)").attr("state"));
				if(currStatus==0){
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
					$.post(contextPath+"/shop/examine",{"shopIds":storeIds,"state":status},function(response){
						//重置提示框
						initComTipDialogs();
						if(response.code==0){
							$("#mws-jui-dialog").text("操作成功").dialog("open");
							queryOrderList();
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
		}else{
			$("#mws-jui-dialog").text("请选择待审核商品").dialog("open");
		}
	}
}

$(function(){
	//初始化状态下拉列表
	initOptionsOfSelect("state","goods_state");
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryOrderList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryOrderList);
});

