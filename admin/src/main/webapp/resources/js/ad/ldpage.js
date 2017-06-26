//加载城市列表
function loadCityList(){
	$.post(contextPath + "/manager/area/cityList",function(data){
    	$.each(data.data, function (index, val) {
    		$("select[name='city']").append("<option value='"+(val.areacode+"").substring(0,2)+"'>"+val.city+"</option>")
		});
    });
}



//加载符合条件的广告组信息
var params;
function queryAdgroups(){
	var groupName=$(":text[name='groupName']").val();
	var groupType=$("select[name='groupType']").val();
	var os=$("select[name='os']").val();
	var city=$("select[name='city']").val();
	var beginDate=$(":text[name='beginDate']").val();
	var endDate=$(":text[name='endDate']").val();
	var beginTime=$(":text[name='beginTime']").val();
	var endTime=$(":text[name='endTime']").val();
	var status=$("select[name='status']").val();
	//设置默认值
	params={"groupName":groupName,"groupType":groupType,"beginDate":beginDate,"endDate":endDate
			,"beginTime":beginTime,"endTime":endTime,"os":os,"city":city,"status":status};
	//发送请求获取数据更新到表格中
	$.post("/fms/manager/ad/queryGroup",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='storeId' value='"+v.id+"'/>"+"</td>"
					+"<td><a href='/fms/manager/ad/groupDetail?gid="+v.id+"'>" + (v.groupName!=undefined&&v.groupName!=''?v.groupName:'N/A') + "</a></td>"
					+"<td>" + (v.cityName!=undefined&&v.cityName!=''?v.cityName:'N/A') + "</td>"
					+"<td>" + (v.os!=undefined&&v.os!=''?v.os:'N/A') + "</td>"
					+"<td>" + (v.groupTypeName!=undefined&&v.groupTypeName!=''?v.groupTypeName:'N/A') + "</td>"
					+"<td>" + (v.statusName!=undefined&&v.statusName!=''?v.statusName:'N/A') + "</td>"
					+"<td>" + (v.creativeCount!=undefined&&v.creativeCount!=''?v.creativeCount:'N/A') + "</td>"
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
	$(":checkbox[name='storeId']").each(function(i,v){
		if($(checkAll).attr("checked")!=undefined){
			$(v).attr("checked","checked");
		}else{
			$(v).removeAttr("checked");
		}
	});
}

//编辑广告组
function editAddGroupInfo(){
	var gid = $(":checkbox[name='storeId']:checked").val();
	if($(":checkbox[name='storeId']:checked").length==1){
		var storeId=$(":checkbox[name='storeId']:checked").val();
		location.href="/fms/manager/ad/editGroup?gid="+gid;
	}else{
		$("#mws-jui-dialog").text("请先选择一个广告组").dialog("open");
	}
}

//删除广告组
function deleteAddGroup(){
	if($(":checkbox[name='storeId']:checked").length==0){
		$("#mws-jui-dialog").text("请先选择一个广告组").dialog("open");
		return;
	}
	var gid=$(":checkbox[name='storeId']:checked").val();
	//弹出确认提示框
	$("#mws-jui-dialog").text("确定删除该版本信息吗？").dialog("option",{title:"确认",buttons:[{
		text:"确认",
		click:function(){
			//发送审核请求
			$.post("/fms/manager/ad/delGroup",{"gid":gid},function(response){
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


//上线下线
function approveAdInfo(status){
	var gid = $(":checkbox[name='storeId']:checked").val();
	if($(":checkbox[name='storeId']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个商户").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='storeId']").each(function(i,v){
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
					$.post("/fms/manager/ad/groupStatus",{"gid":gid,"status":status},function(response){
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
	initOptionsOfSelect("os","mobile_platform_type");
	//初始化广告类型下拉列表
	initOptionsOfSelect("groupType","ad_group_type");
	//初始化审核状态下拉列表
	initOptionsOfSelect("status","auditing_status");
	//初始化城市下拉列表
	loadCityList();
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryAdgroups();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryAdgroups);
});

