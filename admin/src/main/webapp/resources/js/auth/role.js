//加载请求列表
function loadRoleList() {
	$.post("role/list",function(response){
		$("#role_list_table").dataTable().fnDestroy();
		$("#role_list_table").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var buttons="<span><a href='javascript:editRoleInfo("+v.id+");'>"
			   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;" +
			   		  		"<span><a href='javascript:deleteRole("+v.id+");'>"
			   		  +"<img src='"+contextPath+"/resources/css/icons/16/cancel.png' alt='删除' /></a>&nbsp;&nbsp;";
			var html = "<tr class='" + css + "'><td><input type='radio' name='rid' value='"+v.id+"'/>"
			+"</td><td>" + v.roleName + "</td><td>" + (v.enabled?"可用":"不可用") + "</td><td>"
			+v.description+"</td><td>"+v.createTime+"</td><td>"+buttons+"</td></tr>";
			$("#role_list_table").find("tbody").append(html);
		});
		$("#role_list_table").dataTable({
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
		// 取消全选排序功能
		$("#role_list_table").find("th.sorting_asc").toggleClass("sorting_asc").unbind("click");
	});
}
// 绑定角色新增按钮事件
function bindAddRoleEvent() {
	// 绑定新增中间数据文件按钮事件
	$("#role_list_div").find("div.mws-panel-header > div > span").css("backgroundImage",
		"url(" + contextPath + "/resources/css/icons/16/add.png)").click(function(){
		$("#edit_role_info_div").find(":text").val('');
		$("#edit_role_info_div").find("select").val('');
		$("#edit_role_info_div").find(":hidden[name='id']").val("0");
		$("#edit_role_info_div").dialog("open");
	});
}

// 初始化角色信息录入弹出窗口，新增
function initEditRoleInfoDialog() {
	$("#edit_role_info_div").dialog({
		autoOpen : false,
		title : "角色信息",
		modal : true,
		width : "500",
		buttons : [{
			text : "保存",
			click : function() {
				var params = $("#edit_role_info_div").find("form").serialize();
				$.post("role/update",params, function(response) {
					if (response.result == 0) {
						$("#mws-jui-dialog").text("保存成功").dialog("open");
						$("#edit_role_info_div").dialog("close");
						loadRoleList();
					} else if (response.result == 2) {
						$("#mws-jui-dialog").text("角色已存在，请重新输入").dialog("open");
					} else {
						$("#mws-jui-dialog").text("保存失败，请重试").dialog("open");
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

// 删除用户
function deleteRole(id) {
	// 弹出确认提示框
	$("#mws-jui-dialog").text("确定删除该角色吗？").dialog("option", {
		title : "确认",
		buttons : [ {
			text : "确认",
			click : function() {
				// 发送审核请求
				$.post("role/del", {"rid" : id}, function(response) {
					// 重置提示框
					initComTipDialogs();
					if (response.result == 0) {
						$("#mws-jui-dialog").text("操作成功").dialog("open");
						loadRoleList();
					} else {
						$("#mws-jui-dialog").text("操作失败，请重试").dialog("open");
					}
				});
			}
		}, {
			text : "取消",
			click : function() {
				$(this).dialog("close");
			}
		} ]
	}).dialog("open");
}

//初始化分配菜单弹出窗口
function initAddMenuDialog(){
	$("#menu_tree_div").tree({
		animate:true,
		checkbox:true
	});
	$("#menu_tree_div").dialog({
		autoOpen: false, 
		title: "分配菜单", 
		modal: true, 
		width: 500,
		buttons: [{
			text: "保存",
			click: function(){
				var mids="";
				var parentId="";
				var nodes = $('#menu_tree_div').tree('getChecked');
				for(var i=0;i<nodes.length;i++){
					mids+=mids.length>0?","+nodes[i].id:nodes[i].id;
					if(null!=$('#menu_tree_div').tree('getParent',nodes[i].target)){
						parentId=$('#menu_tree_div').tree('getParent',nodes[i].target).id;
						if(!containsStr(mids,parentId)){
							mids+=","+parentId;
						}
					}
				}
				$.post("role/menus",{"rid":rid,"mids":mids},function(response){
					if(response.result==0){
						$("#menu_tree_div").dialog("close");
						$("#mws-jui-dialog").text("操作成功").dialog("open");
					}else{
						$("#mws-jui-dialog").text("操作失败").dialog("open");
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

// 编辑角色信息
function editRoleInfo(roleId) {
	$.post("role/info",{"rid":roleId}, function(response) {
		// 设置角色信息
		$("#edit_role_info_div").find(":hidden[name='id']").val(response.data.id);
		$("#edit_role_info_div").find(":text[name='roleName']").val(response.data.roleName);
		$("#edit_role_info_div").find("select[name='enabled']").val(response.data.enabled);
		$("#edit_role_info_div").find(":text[name='description']").val(response.data.description);
		$("#edit_role_info_div").dialog("open");
	});
}

//为角色分配菜单
var rid;
function addMenuForRole(){
	rid=$(":radio[name='rid']:checked").val();
	$.post("menu/role",{"rid":rid},function(response){
		$("#menu_tree_div").tree("loadData",response.data);
	});
	$("#menu_tree_div").dialog("open");
}

// 页面加载完毕后执行
$(function() {
	// 初始化菜单分配窗口
	initAddMenuDialog();
	// 编辑角色列表
	initEditRoleInfoDialog();
	// 绑定添加角色事件
	bindAddRoleEvent();
	// 加载角色列表
	loadRoleList();
});
