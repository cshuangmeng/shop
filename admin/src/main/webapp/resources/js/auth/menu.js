	
	////////////////////////////////////////
	//菜单相关
	////////////////////////////////////////

	//加载权限列表
	function loadMenuListTable(){
		if($("#menu_list_table").find("tr").length>0){
			$("#menu_list_table").dataTable().fnDestroy();
			$("#menu_list_table").find("tbody").empty();
		}
		$("#menu_list_table").dataTable({
			"sAjaxSource": "menu/list",
			"sAjaxDataProp": "data",
			"bSort":false,
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
			   {"mDataProp":"name","sTitle":"菜单名","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"url","sTitle":"页面地址","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"parentName","sTitle":"父菜单","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"state","sTitle":"是否可用","sClass":"center","sWidth":"10%"},
			   {"mDataProp":"sort","sTitle":"排列序号","sClass":"center","sWidth":"10%"},
			   {"sTitle":"操作","sClass":"center","bSortable": false,"sWidth":"20%","fnRender": function(obj){
				   var buttons="<span><a href='javascript:editMenuInfo("+obj.aData.id+");'>"
				   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;" +
				   		  		"<a href='javascript:deleteMenu("+obj.aData.id+");'>"
				   		  +"<img src='"+contextPath+"/resources/css/icons/16/cancel.png' alt='删除' /></a>&nbsp;&nbsp;";
				   return buttons;
			   }}
		    ]}
		).css("width","100%");
	}
	
	function deleteMenu(id){
		//弹出确认提示框
		$("#mws-jui-dialog").text("确定删除该菜单信息吗？").dialog("option",{title:"确认",buttons:[{
			text:"确认",
			click:function(){
				//发送审核请求
				$.post("menu/del",{"mid":id},function(response){
					//重置提示框
					initComTipDialogs();
					if(response.result==0){
						$("#mws-jui-dialog").text("操作成功").dialog("open");
						loadMenuListTable();
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
	
	//绑定权限新增按钮事件
	function bindAddMenuEvent(){
		//绑定新增中间数据文件按钮事件
		$("#menu_list_div").find("div.mws-panel-header > div > span")
		.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
		.click(function(){
			initParentMenus();
			$("#edit_menu_info_div").find(":text").val('');
			$("#edit_menu_info_div").find("select").val('');
			$("#edit_menu_info_div").find(":hidden[name='id']").val("0");
			$("#edit_menu_info_div").dialog("open");
		});
	}
	
	//初始化父菜单选中列表
	function initParentMenus(defaultValue){
		$("select[name='parentId']").empty();
		$.post("menu/son",function(data){
			$("select[name='parentId']").append("<option value='0'>无</option>");
			$(data.data).each(function(i,v){
				if (v.id==defaultValue) {
					$("select[name='parentId']").append("<option value='"
							+v.id+"' selected='selected'>"+v.menuName+"</option>");
				}else{
					$("select[name='parentId']").append("<option value='"+v.id+"'>"+v.menuName+"</option>");
				}
					
			});
		});
	}
	
	//编辑权限信息
	function editMenuInfo(mid){
		$.post("menu/info",{"mid":mid},function(response){
			//加载父权限
			initParentMenus(response.data.parentId);
			//设置权限信息
			$("#edit_menu_info_div").find(":hidden[name='id']").val(response.data.id);
			$("#edit_menu_info_div").find(":text[name='name']").val(response.data.name);
			$("#edit_menu_info_div").find(":text[name='url']").val(response.data.url);
			$("#edit_menu_info_div").find("select[name='state']").val(""+response.data.state);
			$("#edit_menu_info_div").find(":text[name='orderIndex']").val(response.data.orderIndex);
			$("#edit_menu_info_div").find(":text[name='icon']").val(response.data.icon);
			$("#edit_menu_info_div").dialog("open");
		});
	}
	
	////////////////////////////////////////
	//弹出窗口相关
	////////////////////////////////////////
	
	//初始化权限信息录入弹出窗口
	function initEditMenuInfoDialog(){
		$("#edit_menu_info_div").dialog({
			autoOpen: false, 
			title: "权限信息", 
			modal: true, 
			width: "500", 
			buttons: [{
				text: "保存",
				click: function(){
					var params=$("#edit_menu_info_div").find("form").serialize();
					$.post("menu/update",params,function(response){
						if(response.result==0){
							$("#mws-jui-dialog").text("保存成功").dialog("open");
							$("#edit_menu_info_div").dialog("close");
							loadMenuListTable();
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
		initEditMenuInfoDialog();
		bindAddMenuEvent();
		loadMenuListTable();
	});
	