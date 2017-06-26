//加载字典列表
function loadDictListTable(){
	if($("#dict_list_table").find("tr").length>0){
		$("#dict_list_table").dataTable().fnDestroy();
		$("#dict_list_table").find("tbody").empty();
	}
	$("#dict_list_table").dataTable({
		"sAjaxSource": "dict/list",
		"sAjaxDataProp": "data",
		"oLanguage": {
		  "sProcessing": "正在加载中......",
          "sLengthMenu": "每页显示 _MENU_ 条记录",
          "sZeroRecords": "对不起，查询不到相关数据！",
          "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
          "sInfoFiltered": "",
          "sInfoEmpty": "",
          "sSearch": "搜索"
		},
		"aoColumnDefs" : [{"bSortable":false,"aTargets":[1,2,3,4]}],
		"aoColumns": [
		   {"mDataProp":"dictCode","sTitle":"字典代码","sClass":"center","sWidth":"20%"},
		   {"mDataProp":"dictName","sTitle":"字典名称","sClass":"center","sWidth":"10%"},
		   {"mDataProp":"dictValue","sTitle":"字典值","sClass":"center","sWidth":"20%"},
		   {"mDataProp":"orderIndex","sTitle":"排列序号","sClass":"center","sWidth":"20%"},
		   {"sTitle":"","sClass":"center","bSortable": false,"sWidth":"10%","fnRender": function(obj){
			   var buttons="<span><a href='javascript:editDictInfo("+obj.aData.id+");'>"
			   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;"
			   		  +"<a href='javascript:delDictInfo("+obj.aData.id+");'>"
			   		  +"<img src='"+contextPath+"/resources/css/icons/16/cancel.png' alt='编辑' /></a>&nbsp;&nbsp;";
			   return buttons;
		   }}
	    ]}
	).css("width","100%");
}

//绑定字典新增按钮事件
function bindAddDictEvent(){
	//绑定新增中间数据文件按钮事件
	$("#dict_list_div").find("div.mws-panel-header > div > span")
	.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
	.click(function(){
		$("#edit_dict_info_div").find(":text").val('');
		$("#edit_dict_info_div").find("select").val('');
		$("#edit_dict_info_div").find(":hidden[name='id']").val("0");
		$("#edit_dict_info_div").dialog("open");
	});
}

//编辑字典信息
function editDictInfo(id){
	$.post("dict/info",{"id":id},function(response){
		//设置地推信息
		$("#edit_dict_info_div").find(":hidden[name='id']").val(response.data.id);
		$("#edit_dict_info_div").find(":text[name='dictCode']").val(response.data.dictCode);
		$("#edit_dict_info_div").find(":text[name='dictName']").val(response.data.dictName);
		$("#edit_dict_info_div").find(":text[name='dictValue']").val(response.data.dictValue);
		$("#edit_dict_info_div").find(":text[name='orderIndex']").val(response.data.orderIndex);
		$("#edit_dict_info_div").dialog("open");
	});
}

//绑定地推新增按钮事件
function bindAddDictEvent(){
	//绑定新增中间数据文件按钮事件
	$("#dict_list_div").find("div.mws-panel-header > div > span")
	.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
	.click(function(){
		$("#edit_dict_info_div").find(":text").val('');
		$("#edit_dict_info_div").find(":hidden[name='id']").val("0");
		$("#edit_dict_info_div").dialog("open");
	});
}

// 删除字典信息
function delDictInfo(id){
	dictId=id;
	$("#del-dict-dialog").text("确定要删除该项吗？").dialog("open");
}

////////////////////////////////////////
//弹出窗口相关
////////////////////////////////////////

//初始化字典信息录入弹出窗口
function initEditDictInfoDialog(){
	$("#edit_dict_info_div").dialog({
		autoOpen: false, 
		title: "字典信息", 
		modal: true, 
		width: "500", 
		buttons: [{
			text: "保存",
			click: function(){
				var params=$("#edit_dict_info_div").find("form").serialize();
				$.post("dict/update",params,function(response){
					if(response.result==0){
						$("#mws-jui-dialog").text("保存成功").dialog("open");
						$("#edit_dict_info_div").dialog("close");
						loadDictListTable();
					}else if(response.result==2){
						$("#mws-jui-dialog").text("该字典已存在，请重新输入").dialog("open");
					}else{
						$("#mws-jui-dialog").text("保存失败，请重试").dialog("open");
					}
				});
			}
		},{
			text: "关闭", 
			click: function() {
				$(this).dialog("close");
			}
		}]
	});
}

//初始化删除字典信息弹出窗口
var dictId;
function initDelDictDialog(){
	$("#del-dict-dialog").dialog({
		autoOpen: false, 
		title: "确认删除", 
		modal: true, 
		width: 300, 
		buttons: [{
			text: "确定",
			click: function(){
				$.post("dict/del",{"id":dictId},function(response){
					if(response.result==0){
						$("#del-dict-dialog").dialog("close");
						$("#mws-jui-dialog").text("删除成功").dialog("open");
						loadDictListTable();
					}else{
						$("#mws-jui-dialog").text("删除失败，请重试").dialog("open");
					}
				});
			}
		},{
			text: "取消", 
			click: function() {
				$(this).dialog("close");
			}
		}]
	});
}

////////////////////////////////////////
//页面加载完毕后调用
////////////////////////////////////////

$(function(){
	//绑定字典新增按钮事件
	bindAddDictEvent();
	//初始化确认删除字典弹出窗口
	initDelDictDialog();
	//初始化字典信息录入弹出窗口
	initEditDictInfoDialog();
	//绑定字典新增按钮事件
	bindAddDictEvent();
	//加载字典列表
	loadDictListTable();
});
