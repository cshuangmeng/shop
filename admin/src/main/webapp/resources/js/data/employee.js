//加载联盟列表
function loadEmpListTable(){
	if($("#emp_list_table").find("tr").length>0){
		$("#emp_list_table").dataTable().fnDestroy();
		$("#emp_list_table").find("tbody").empty();
	}
	$("#emp_list_table").dataTable({
		"sAjaxSource": "emp/list",
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
		"aoColumnDefs" : [ {
	        sDefaultContent : '',
	        aTargets : [ '_all' ]
	    } ],
		"aoColumns": [
		   {"mDataProp":"storeName","sTitle":"商户","sClass":"center","sWidth":"20%"},
		   {"mDataProp":"unionName","sTitle":"帐号","sClass":"center","sWidth":"10%"},
		   {"mDataProp":"unionPwd","sTitle":"密码","sClass":"center","sWidth":"20%"},
		   {"mDataProp":"createTime","sTitle":"创建时间","sClass":"center","sWidth":"20%"},
		   {"mDataProp":"unionState","sTitle":"当前状态","sClass":"center","sWidth":"10%"},
		   {"sTitle":"","sClass":"center","bSortable": false,"sWidth":"10%","fnRender": function(obj){
			   var buttons="<span><a href='javascript:editEmpInfo("+obj.aData.id+");'>"
			   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;";
			   return buttons;
		   }}
	    ]}
	).css("width","100%");
}

//绑定地推新增按钮事件
function bindAddEmpEvent(){
	//绑定新增中间数据文件按钮事件
	$("#emp_list_div").find("div.mws-panel-header > div > span")
	.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
	.click(function(){
		$("#edit_emp_info_div").find(":text").val('');
		$("#edit_emp_info_div").find("select").val('');
		$("#edit_emp_info_div").find(":hidden[name='id']").val("0");
		$("#edit_emp_info_div").dialog("open");
	});
}

//编辑地推信息
function editEmpInfo(empId){
	$.post("emp/info",{"empId":empId},function(response){
		//设置地推信息
		$("#edit_emp_info_div").find(":hidden[name='id']").val(response.data.id);
		$("#edit_emp_info_div").find(":text[name='unionName']").val(response.data.unionName);
		$("#edit_emp_info_div").find(":text[name='unionPwd']").val(response.data.unionPwd);
		$("#edit_emp_info_div").find("select[name='unionState']").val(response.data.unionState);
		$("#edit_emp_info_div").dialog("open");
	});
}

////////////////////////////////////////
//弹出窗口相关
////////////////////////////////////////

//初始化联盟帐号信息录入弹出窗口
function initEditEmpInfoDialog(){
	$("#edit_emp_info_div").dialog({
		autoOpen: false, 
		title: "联盟帐号", 
		modal: true, 
		width: "500", 
		buttons: [{
			text: "保存",
			click: function(){
				var params=$("#edit_emp_info_div").find("form").serialize();
				$.post("emp/update",params,function(response){
					if(response.result==0){
						$("#mws-jui-dialog").text("保存成功").dialog("open");
						$("#edit_emp_info_div").dialog("close");
						loadEmpListTable();
					}else if(response.result==2){
						$("#mws-jui-dialog").text("帐号已存在，请重新输入").dialog("open");
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

////////////////////////////////////////
//页面加载完毕后调用
////////////////////////////////////////

$(function(){
	//初始化负责人下拉框
	initOptionsOfSelect('unionState','bd_account_state');
	//初始化地推信息录入弹出窗口
	initEditEmpInfoDialog();
	//绑定地推新增按钮事件
	bindAddEmpEvent();
	//加载地推列表
	loadEmpListTable();
});
