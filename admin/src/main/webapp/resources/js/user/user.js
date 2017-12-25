//加载符合条件的广告组信息
var params;
function queryOrderList(){
	var nickname=$(":text[name='nickname']").val();
	var cellphone=$(":text[name='cellphone']").val();
	//设置默认值
	params={"nickname":nickname,"cellphone":cellphone};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/user/list",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var stateName="N/A";
			if(v.state==0){
				stateName="未激活";
			}else if(v.state==1){
				stateName="已激活";
			}else if(v.state==2){
				stateName="已禁用";
			}
			var cashExchangeEnableName="N/A";
			if(v.cashExchangeEnable==0){
				cashExchangeEnableName="否";
			}else if(v.cashExchangeEnable==1){
				cashExchangeEnableName="是";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='goodsId' value='"+v.id+"'/>"+"</td>"
					+"<td>" + (v.nickname!=undefined&&v.nickname!=''?v.nickname:'N/A') + "</td>"
					+"<td>" + (v.cellphone!=undefined&&v.cellphone!=''?v.cellphone:'N/A') + "</td>"
					+"<td>" + v.coin + "</td>"
					+"<td>" + v.point + "</td>"
					+"<td>" + stateName + "</td>"
					+"<td state="+v.cashExchangeEnable+">" + cashExchangeEnableName + "</td>"
					+"<td>" + (v.createTime!=undefined&&v.createTime!=''?v.createTime:'N/A') + "</td>"
					+"<td>" + (v.loginTime!=undefined&&v.loginTime!=''?v.loginTime:'N/A') + "</td></tr>"
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

//上线下线
function approveAdInfo(status){
	var gid = $(":checkbox[name='goodsId']:checked").val();
	if($(":checkbox[name='goodsId']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个用户").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='goodsId']").each(function(i,v){
			if($(v).attr("checked")!=undefined){
				storeIds+=storeIds.length>0?","+$(v).val():$(v).val();
			}
		});
		if(storeIds.length>0){
			//弹出确认提示框
			$("#mws-jui-dialog").text("确定执行该操作吗？").dialog("option",{title:"确认",buttons:[{
				text:"确认",
				click:function(){
					//发送审核请求
					$.post(contextPath+"/user/right",{"userIds":storeIds,"right":status},function(response){
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
			$("#mws-jui-dialog").text("请选择至少一个用户").dialog("open");
		}
	}
}

$(function(){
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryOrderList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryOrderList);
})

