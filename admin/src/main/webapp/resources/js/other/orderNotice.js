//加载符合条件的广告组信息
var params;
function queryNewOrderNoticePhoneList(){
	var contact=$(":text[name='contact']").val();
	var telephone=$("select[name='telephone']").val();
	//设置默认值
	params={"contact":contact,"telephone":telephone};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/dict/listNoticePhone",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='telephone' value='"+v.telephone+"'/>"+"</td>"
					+"<td>" + (v.contact!=undefined&&v.contact!=''?v.contact:'N/A') + "</a></td>"
					+"<td>" + (v.telephone!=undefined&&v.telephone!=''?v.telephone:'N/A') + "</td>"
					+"</tr>"
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
	$(":checkbox[name='telephone']").each(function(i,v){
		if($(checkAll).attr("checked")!=undefined){
			$(v).attr("checked","checked");
		}else{
			$(v).removeAttr("checked");
		}
	});
}

//删除通知手机号
function delNoticePhone(){
	var gid = $(":checkbox[name='telephone']:checked").val();
	if($(":checkbox[name='telephone']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个联系人").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='telephone']").each(function(i,v){
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
					$.post(contextPath+"/dict/delNoticePhone",{"telephone":storeIds},function(response){
						//重置提示框
						initComTipDialogs();
						if(response.code==0){
							$("#mws-jui-dialog").text("操作成功").dialog("open");
							queryNewOrderNoticePhoneList();
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

//初始化编辑号码弹出窗口
function initEditNoticePhoneDialog() {
	$("#edit_notice_phone_div").dialog({
		autoOpen : false,
		title : "通知信息",
		modal : true,
		width : "500",
		buttons : [{
			text : "保存",
			click : function() {
				var params = $("#edit_notice_phone_div").find("form").serialize();
				$.post(contextPath+"/dict/addNoticePhone",params, function(response) {
					if (response.code == 0) {
						$("#edit_notice_phone_div").dialog("close");
						$("#mws-jui-dialog").text("保存成功").dialog("open");
						queryNewOrderNoticePhoneList();
					} else {
						$("#mws-jui-dialog").text(response.msg).dialog("open");
					}
				});
			}}, {
				text : "关闭",
				click : function() {
					$(this).dialog("close");
				}
			} 
		]
	});
}

//增加通知手机号
function addNoticePhone() {
	$("#edit_notice_phone_div").find(":text").val('');
	$("#edit_notice_phone_div").dialog("open");
}

$(function(){
	//初始化编辑号码弹出窗口
	initEditNoticePhoneDialog();
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryNewOrderNoticePhoneList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryNewOrderNoticePhoneList);
});

