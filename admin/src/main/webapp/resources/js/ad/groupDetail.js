	  //获取url的
		function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

	//加载符合条件的广告素材信息
	var params;
	function queryQroupDetail(cid){
	 gid = getUrlParam('gid');
	//发送请求获取数据更新到表格中
	$.post("/fms/manager/ad/groupCreative",{"gid":gid},function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			cid = v.id;
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='storeId' value='"+cid+"'/>"+"</td>"
					+"<td><a href='javascript:adDetailDialog("+cid+")'>" + (v.title!=undefined&&v.title!=''?v.title:'N/A') + "</a></td>"
					+"<td>" + (v.imgUrl!=undefined&&v.imgUrl!=''?v.imgUrl:'N/A') + "</td>"
					+"<td>" + (v.actionTarget!=undefined&&v.actionTarget!=''?v.actionTarget:'N/A') + "</td>"
					+"<td>" + (v.actionTypeName!=undefined&&v.actionTypeName!=''?v.actionTypeName:'N/A') + "</td>"
					+"<td>" + (v.createTime!=undefined&&v.createTime!=''?v.createTime:'N/A') + "</td>"
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
	
	//删除广告素材
	function deleteAddGroup(){
		if($(":checkbox[name='storeId']:checked").length==0){
			$("#mws-jui-dialog").text("请先选择一个广告组").dialog("open");
			return;
		}
		var cid=$(":checkbox[name='storeId']:checked").val();
		//弹出确认提示框
		$("#mws-jui-dialog").text("确定删除该版本信息吗？").dialog("option",{title:"确认",buttons:[{
			text:"确认",
			click:function(){
				//发送审核请求
				$.post("/fms/manager/ad/delCreative",{"cid":cid},function(response){
					//重置提示框
					initComTipDialogs();
					if(response.result==0){
						$("#mws-jui-dialog").text("操作成功").dialog("open");
						queryQroupDetail();
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

    ////////////////////////////////////////
	//弹出窗口相关
	////////////////////////////////////////
	//初始化修改广告素材弹出窗口
	function initUpdateAdMaterialDialogs(){
		$("#ad-update-dialog").dialog({
			autoOpen: false,
			title: "修改广告素材", 
			modal: true,
			width: 600
		});
	}
	
	
	//初始化新增广告素材弹出窗口
	function initAddAdMaterialDialog(){
		$("#ad-add-dialog").dialog({
			autoOpen: false,
			title: "添加广告素材", 
			modal: true,
			width: 600,
			buttons: [{
				text: "保存",
				click: function(){
					$("form[name='ad-add-form']").submit();
				}
			},
			{
				text: "关闭", 
				click: function() {
					$(this).dialog("close");
				}
			}]
		});
	}
	//初始化详情广告素材弹出窗口
	function initDetailAdMaterialDialogs(){
		$("#ad-detail-dialog").dialog({
			autoOpen: false,
			title: "修改广告素材", 
			modal: true,
			width: 700
		});
	}
	////////////////////////////////////////
	//广告素材详情相关
	////////////////////////////////////////
	//弹出广告素材添加窗口
	function showWiFiAddDialog(){
		$("#ad-add-dialog").dialog("open");
	}
	
	//修改广告素材
	function adUpdateDialog(){
		var cid = $(":checkbox[name='storeId']:checked").val();
		if($(":checkbox[name='storeId']:checked").length==1){
			$("#ad-update-dialog").find(":text").val('');
			//加载广告素材信息
			$.post(contextPath+"/manager/ad/creativeDetail",{"cid":cid},function(response){
				$("#ad-update-dialog").find(":text[name='groupId']").val(response.data.id);
				$("#ad-update-dialog").find(":hidden[name='imgUrl']").val(response.data.imgUrl);
				$("#ad-update-dialog").find(":hidden[name='id']").val(response.data.id);
				$("#ad-update-dialog").find(":text[name='title']").val(response.data.title);
				$("#ad-update-dialog").find(":text[name='actionTarget']").val(response.data.actionTarget);
				$("#ad-update-dialog").find(":text[name='shareContent']").val(response.data.shareContent);
				setSelectedOption("actionType",response.data.actionType);
				setSelectedOption("needLogin",response.data.needLogin);
				$("#ad-update-dialog").dialog("option",{buttons: [{
					text: "保存",
					click: function(){
						//修改广告素材信息
						$("form[name='adcreative-update-form']").submit();
					}},
					{
						text: "关闭", 
						click: function() {
							$(this).dialog("close");
						}
					}]
				}).dialog("open");
			});
		}else{
			$("#mws-jui-dialog").text("请先选择一个广告素材").dialog("open");
		}
	}
	
	//修改广告素材
	function adDetailDialog(cid){
		$("#ad-detail-dialog").find(":text").val('');
		//加载广告素材信息
		$.post(contextPath+"/manager/ad/creativeDetail",{"cid":cid},function(response){
			$("#ad-detail-dialog .mws-form-row .title").html(response.data.title);
			$("#ad-detail-dialog .mws-form-row .pic").attr('src',response.data.imgUrl);
			$("#ad-detail-dialog .mws-form-row .actionTarget").html("<a href='"+response.data.actionTarget+"' target='_blank'>"+response.data.actionTarget+"</a>");
			$("#ad-detail-dialog .mws-form-row .actionType").html(response.data.actionTypeName);
			$("#ad-detail-dialog .mws-form-row .needLogin").text(response.data.needLogin);
			$("#ad-detail-dialog .mws-form-row .shareContent").text(response.data.shareContent);
			$("#ad-detail-dialog").dialog("option",{buttons: [{
				text: "关闭", 
				click: function() {
					$(this).dialog("close");
				}
			}]
			}).dialog("open");
		});
		
	}
	
$(function(){
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryQroupDetail();
	}
	//初始化新增广告素材弹出窗口
	initAddAdMaterialDialog();
	//初始化修改WiFi弹出窗口
	initUpdateAdMaterialDialogs();
	initDetailAdMaterialDialogs();
	//初始化审核状态下拉列表
	initOptionsOfSelect("needLogin","yes_or_no");
	//初始化跳转方式下拉列表
	initOptionsOfSelect("actionType","ad_click_open_type");
})