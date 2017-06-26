	
	////////////////////////////////////////
	//user相关
	////////////////////////////////////////

	//加载用户列表
	function loadUserListTable(){
		$.post("user/list",function(response){
			$("#user_list_table").dataTable().fnDestroy();
			$("#user_list_table").find("tbody").empty();
			$(response.data).each(function(k, v) {
				var css = "even";
				if (k % 2 == 1) {
					css = "odd";
				}
				var roles="";
				var rids="";
				var buttons="<span><a href='javascript:edituserInfo("+v.id+");'>"
				   		  +"<img src='"+contextPath+"/resources/css/icons/16/application_edit.png' alt='编辑' /></a>&nbsp;&nbsp;" +
				   		  		"<span><a href='javascript:deleteUser("+v.id+");'>"
				   		  +"<img src='"+contextPath+"/resources/css/icons/16/cancel.png' alt='删除' /></a>&nbsp;&nbsp;";
			   $(v.roles).each(function(i,v){
				   roles+=roles.length>0?","+v.roleName:v.roleName;
				   rids+=rids.length>0?","+v.id:v.id;
			   });
				var html = "<tr class='" + css + "'><td><input type='radio' name='uid' value='"+v.id+"'/>"
				+"</td><td>" + v.username + "</td><td>" + v.pwd + "</td><td>"
				+(v.enabled?"可用":"不可用")+"</td><td rids='"+rids+"'>"+roles+"</td><td>"+v.createTime+"</td><td>"+buttons+"</td></tr>";
				$("#user_list_table").find("tbody").append(html);
			});
			$("#user_list_table").dataTable({
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
			$("#user_list_table").find("th.sorting_asc").toggleClass("sorting_asc").unbind("click");
		});
	}
	
	//加载角色列表
	function loadRoleListTable(){
		var rids=$(":radio[name='uid']:checked").parent().parent().find("td:eq(4)").attr("rids");
		$.post("role/list",{"enabled":1},function(response){
			$("#role_list_table").dataTable().fnDestroy();
			$("#role_list_table").find("tbody").empty();
			$(response.data).each(function(k, v) {
				var css = "even";
				if (k % 2 == 1) {
					css = "odd";
				}
				var html = "<tr class='" + css + "'><td>" +
				"<input type='checkbox' name='rid' value='"+v.id+"'"
				+(containsStr(rids,""+v.id)?" checked='checked'/>":"/>")
				+"</td><td>" + v.roleName + "</td><td>" + v.description + "</td></tr>";
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
	
	//删除用户
	function deleteUser(id){
		//弹出确认提示框
		$("#mws-jui-dialog").text("确定删除该用户吗？").dialog("option",{title:"确认",buttons:[{
			text:"确认",
			click:function(){
				//发送审核请求
				$.post("user/del",{"uid":id},function(response){
					//重置提示框
					initComTipDialogs();
					if(response.result==0){
						$("#mws-jui-dialog").text("操作成功").dialog("open");
						loadUserListTable();
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
	
	// 全选
	function selectAll(checkAll){
		$(":checkbox[name='rid']").each(function(i,v){
			if($(checkAll).attr("checked")!=undefined){
				$(v).attr("checked","checked");
			}else{
				$(v).removeAttr("checked");
			}
		});
	}
	
	//编辑用户信息
	function edituserInfo(userId){
		$.post("user/info",{"uid":userId},function(response){
			//设置用户信息
			$("#edit_user_info_div").find(":hidden[name='id']").val(response.data.id);
			$("#edit_user_info_div").find(":text[name='username']").val(response.data.username);
			$("#edit_user_info_div").find(":text[name='pwd']").val(response.data.pwd);
			$("#edit_user_info_div").find("select[name='enabled']").val(""+response.data.enabled);
			$("#edit_user_info_div").dialog("open");
		});
	}
	
	//获取角色列表
	function queryRoleList(){
		$.post("role/list",{"enabled":true},function(data){
        	$("select[name='roleId']").empty();
        	$.each(data.data, function (index, val) {
        		$("select[name='roleId']").append("<option value='"+val.id+"' selected=''>"+val.roleName+"</option>")
			});
	    });
	}
	
	//为用户分配角色
	var uid;
	function addRoleForUser(){
		if($(":radio[name='uid']:checked").length==1){
			loadRoleListTable();
			uid=$(":radio[name='uid']:checked").val();
			$("#role_list_dialog").dialog("open");
		}else{
			$("#mws-jui-dialog").text("请先选择一个用户").dialog("open");
		}
	}
	
	//绑定用户新增按钮事件
	function bindAdduserEvent(){
		//绑定新增中间数据文件按钮事件
		$("#user_list_div").find("div.mws-panel-header > div > span")
		.css("backgroundImage","url("+contextPath+"/resources/css/icons/16/add.png)")
		.click(function(){
			$("#edit_user_info_div").find(":text").val('');
			$("#edit_user_info_div").find("select").val('');
			$("#edit_user_info_div").find(":hidden[name='id']").val("0");
			$("#edit_user_info_div").dialog("open");
		});
	}
	
	////////////////////////////////////////
	//弹出窗口相关
	////////////////////////////////////////
	
	//初始化用户信息录入弹出窗口，新增
	function initEdituserInfoDialog(){
		$("#edit_user_info_div").dialog({
			autoOpen: false, 
			title: "用户信息", 
			modal: true, 
			width: "500", 
			buttons: [{
				text: "保存",
				click: function(){
					var params=$("#edit_user_info_div").find("form").serialize();
					$.post("user/update",params,function(response){
						if(response.result==0){
							$("#mws-jui-dialog").text("保存成功").dialog("open");
							$("#edit_user_info_div").dialog("close");
							loadUserListTable();
						}else if(response.result==2){
							$("#mws-jui-dialog").text("用户名已存在，请重新输入").dialog("open");
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
	
	//初始化角色选择弹出窗口
	function initRoleListDialog(){
		$("#role_list_dialog").dialog({
			autoOpen: false, 
			title: "分配角色", 
			modal: true, 
			width: 700,
			buttons: [{
				text: "保存",
				click: function(){
					var rids="";
					$(":checked[name='rid']").each(function(i,v){
						rids+=rids.length>0?","+$(v).val():$(v).val();
					});
					$.post("user/roles",{"uid":uid,"rids":rids},function(response){
						if(response.result==0){
							loadUserListTable();
							$("#role_list_dialog").dialog("close");
							$("#mws-jui-dialog").text("分配成功").dialog("open");
						}else{
							$("#mws-jui-dialog").text("分配失败，请重试").dialog("open");
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
		//初始化角色选择弹出窗口
		initRoleListDialog();
		//初始化用户信息录入弹出窗口
		initEdituserInfoDialog();
		//绑定用户新增按钮事件
		bindAdduserEvent();
		//加载用户列表
		loadUserListTable();
	});
	