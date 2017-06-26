	
	////////////////////////////////////////
	//BD相关
	////////////////////////////////////////

	//加载地推列表
	function loadBdListTable(){
		if($("#bd_list_table").find("tr").length>0){
			$("#bd_list_table").dataTable().fnDestroy();
			$("#bd_list_table").find("tbody").empty();
		}
		$("#bd_list_table").dataTable({
			"sAjaxSource": "bd/list",
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
			   {"mDataProp":"boss","sTitle":"负责人","sClass":"center","sWidth":"10%"},
			   {"mDataProp":"fullname","sTitle":"姓名","sClass":"center","sWidth":"10%"},
			   {"mDataProp":"username","sTitle":"帐号","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"pwd","sTitle":"密码","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"createTime","sTitle":"创建时间","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"accountState","sTitle":"当前状态","sClass":"center","sWidth":"10%"},
			   {"sTitle":"","sClass":"center","bSortable": false,"sWidth":"10%","fnRender": function(obj){
				   var buttons="<span><a href='javascript:editBdInfo("+obj.aData.id+");'>"
				   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;";
				   return buttons;
			   }}
		    ]}
		).css("width","100%");
	}
	
	//绑定地推新增按钮事件
	function bindAddBdEvent(){
		//绑定新增中间数据文件按钮事件
		$("#bd_list_div").find("div.mws-panel-header > div > span")
		.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
		.click(function(){
			$("#edit_bd_info_div").find(":text").val('');
			$("#edit_bd_info_div").find("select").val('');
			$("#edit_bd_info_div").find(":hidden[name='id']").val("0");
			$("#edit_bd_info_div").dialog("open");
		});
	}
	
	//编辑地推信息
	function editBdInfo(bdId){
		$.post("bd/info",{"bdId":bdId},function(response){
			//设置地推信息
			$("#edit_bd_info_div").find(":hidden[name='id']").val(response.data.id);
			$("#edit_bd_info_div").find("select[name='boss']").val(response.data.boss);
			$("#edit_bd_info_div").find(":text[name='fullname']").val(response.data.fullname);
			$("#edit_bd_info_div").find(":text[name='username']").val(response.data.username);
			$("#edit_bd_info_div").find(":text[name='pwd']").val(response.data.pwd);
			$("#edit_bd_info_div").find("select[name='accountState']").val(response.data.accountState);
			$("#edit_bd_info_div").dialog("open");
		});
	}
	
	////////////////////////////////////////
	//弹出窗口相关
	////////////////////////////////////////
	
	//初始化地推信息录入弹出窗口
	function initEditBdInfoDialog(){
		$("#edit_bd_info_div").dialog({
			autoOpen: false, 
			title: "地推信息", 
			modal: true, 
			width: "500", 
			buttons: [{
				text: "保存",
				click: function(){
					var params=$("#edit_bd_info_div").find("form").serialize();
					$.post("bd/update",params,function(response){
						if(response.result==0){
							$("#mws-jui-dialog").text("保存成功").dialog("open");
							$("#edit_bd_info_div").dialog("close");
							loadBdListTable();
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
		initOptionsOfSelect('boss','bd_boss');
		//初始化帐号状态下拉框
		initOptionsOfSelect('accountState','bd_account_state');
		//初始化地推信息录入弹出窗口
		initEditBdInfoDialog();
		//绑定地推新增按钮事件
		bindAddBdEvent();
		//加载地推列表
		loadBdListTable();
	});
	