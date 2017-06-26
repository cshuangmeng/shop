  	
	////////////////////////////////////////
	//地区相关
	////////////////////////////////////////
	
	//加载区划列表
	function loadRegionSelect(cityId,firstItemName){
		$("select[name='areacode']").empty();
		// 地市默认选择全部
		if(firstItemName!=undefined){
			if($("select[name='areacode']").attr("val")==undefined||$("select[name='areacode']").attr("val")==''){
				$("select[name='areacode']").append("<option value='0' selected='selected'>"+firstItemName+"</option>");
			}else{
				$("select[name='areacode']").append("<option value='0'>"+firstItemName+"</option>");
			}
		}
		if(cityId>0){
			$.post(contextPath+"/area/regionList",{"cityCode":cityId},function(response){
				// 装载数据
				$(response.data).each(function(i,v){
					if($("select[name='areacode']").attr("val")==v.areacode){
						$("select[name='areacode']").append("<option value='"+v.areacode+"' selected='selected'>"+v.region+"</option>");
					}else{
						$("select[name='areacode']").append("<option value='"+v.areacode+"'>"+v.region+"</option>");
					}
				});
			});
		}
	}
	
	// //////////////////////////////////////
	// 商户相关
	// //////////////////////////////////////
	
	// 加载符合条件的商户信息
	var params;
	function loadStoreQueryResult(){
		var cityCode=$("select[name='city']").val();
		var areacode=$("select[name='areacode']").val();
		areacode=areacode.length>2?areacode:cityCode.substr(0,2);
		var typeId=$("select[name='typeId']").val();
		var storeName=$(":text[name='fullName']").val();
		var bdId=$("select[name='bdId']").val();
		var status=$("select[name='status']").val();
		var createTimeB=$(":text[name='createTimeB']").val();
		var createTimeE=$(":text[name='createTimeE']").val();
		var pbTotalNoB=$(":text[name='pbTotalNoB']").val();
		var pbTotalNoE=$(":text[name='pbTotalNoE']").val();
		// 设置默认值
		pbTotalNoB=pbTotalNoB!=""?pbTotalNoB:"-1";
		pbTotalNoE=pbTotalNoE!=""?pbTotalNoE:"-1";
		params={"areacode":areacode,"typeId":typeId,"storeName":storeName
				,"bdId":bdId,"examineState":status,"createTimeB":createTimeB,"createTimeE":createTimeE
				,"pbTotalNoB":pbTotalNoB,"pbTotalNoE":pbTotalNoE};
		// 发送请求获取数据更新到表格中
		$.post("store/query",params,function(response){
			$(".mws-datatable").dataTable().fnDestroy();
			$(".mws-datatable").find("tbody").empty();
			$(response.data).each(function(k, v) {
				var css = "even";
				if (k % 2 == 1) {
					css = "odd";
				}
				var html = "<tr class='" + css + "'><td><input type='checkbox' name='storeId' value='"+v.id+"'/>"
				+"</td><td><a href='store/info?storeId="+v.id+"'>" + (v.storeName!=undefined&&v.storeName!=''?v.storeName:'N/A') + "</a></td>"
				+"<td>" + (v.typeName!=undefined&&v.typeName!=''?v.typeName:'N/A') + "</a></td><td>"
				+ (v.city!=undefined&&v.city!=''?v.city:'N/A') + " － " 
				+ (v.region!=undefined&&v.region!=''?v.region:'N/A') 
				+ "</td><td>" + (v.bdName!=undefined&&v.bdName!=''?v.bdName:'N/A') + "</td>"
				+"<td>" + v.pbTotalNo + "</td><td status='"+v.examineState+"'>" 
				+ (v.stateName!=undefined&&v.stateName!=''?v.stateName:'N/A') + "</td><td>" + v.createTime + "</td></tr>";
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
			// 取消全选排序功能
			$(".mws-datatable").find("th.sorting_asc").toggleClass("sorting_asc").unbind("click");
		});
	}
	
	// 导出商户查询结果
	function exportStoreQueryResult(){
		if($("form[name='export']").length==0){
			var token=$("meta[name='_csrf']").attr("content");
			var parameterName=$("meta[name='_csrf.parameterName']").attr("content");
			var exportForm="<form name=\"export\" action=\"store/export\" method=\"POST\">"
						+"<input type='hidden' name='"+parameterName+"' value='"+token+"'/>";
			for(var key in params){
				exportForm+="<input type=\"hidden\" name=\""+key+"\" value=\""+eval('params.'+key)+"\" /><br />";
			}
			exportForm+="</form>";
			$("body").append(exportForm);
		}
		// 将本次的查询条件组装并提交给服务器获取查询结果
		for(var key in params){
			$("form[name='export']").find(":hidden[name='"+key+"']").val(eval('params.'+key));
		}
		$("form[name='export']").submit();
	}
	
	//弹出excel导入窗口
	function importData(){
		$("#add-excel-dialog").dialog("open");
	}	
	
	// 全选
	function selectAll(checkAll){
		$(":checkbox[name='storeId']").each(function(i,v){
			if($(checkAll).attr("checked")!=undefined){
				$(v).attr("checked","checked");
			}else{
				$(v).removeAttr("checked");
			}
		});
	}
	
	// 编辑商户信息
	function editStoreInfo(type){
		if(type=="edit"){
			if($(":checkbox[name='storeId']:checked").length==1){
				var storeId=$(":checkbox[name='storeId']:checked").val();
				location.href="store/update?storeId="+storeId;
			}else{
				$("#mws-jui-dialog").text("请先选择一个商户").dialog("open");
			}
		}else{
			location.href="store/update";
		}
	}
	
	// 商户基本信息预设
	function setStoreInfo(){
		// 绑定商户列表页面查询事件
		$("#query-div").find(":button[name='submit']").click(loadStoreQueryResult);
		// 商户查询页面 － 下拉框默认选择全部
		var firstItemName="请选择";
		if($("#query-div").length>0){
			firstItemName="全部";
		}
		// 绑定城市选择事件
		$("select[name='city']").change(function(){
			loadRegionSelect($(this).val(),firstItemName);
		});
		// 判断下拉框是否存在预设值
		$("select[val!='']").each(function(i,v){
			$(v).val('');
			$(v).find("option").each(function(ii,vv){
				if($(v).attr("val")==$(vv).val()){
					$(vv).attr("selected","selected");
				}
			});
		});
		loadRegionSelect($("select[name='city']").val(),firstItemName);
	}
	
	// //////////////////////////////////////
	// 提示框相关
	// //////////////////////////////////////
	
	//绑定导入excel事件
	function initImportData(){
		$("#add-excel-dialog").dialog({
			autoOpen: false,
			title: "导入Excel", 
			modal: true,
			width: 500,
			buttons: [{
				text: "导入",
				click: function(){
					var file=$("#add-excel-dialog").find(":file[name='excelFile']").val();
					var index = file.lastIndexOf(".");
					if(index < 0) {
						$("#mws-jui-dialog").text("上传的文件格式不正确，请选择Excel文件(*.xls)！").dialog("open");
					} else {
						var ext = file.substring(index + 1, file.length);
						if(ext != "xls") {
							$("#mws-jui-dialog").text("上传的文件格式不正确，请选择Excel文件(*.xls)！").dialog("open");
						} else {
							$("#addExcel").submit();
						}
					}
				}
			},{
				text: "关闭", 
				click: function() {
					$(this).dialog("close");
				}
			}]
		});
	}
	
	//审核商户信息
	function overduedStore(status){
		//获取选中的商户
		var storeIds="";
		if($(":checkbox[name='storeId']:checked").length>0){
			// 获取选中的商户
			$(":checkbox[name='storeId']:checked").each(function(i,v){
				var currStatus=$.trim($(v).parent().parent().find("td:eq(6)").attr("status"));
				if(status=="PASSED"||status=="REFUSED"){
					if(currStatus=="EXAMINING"){
						storeIds+=storeIds.length>0?","+$(v).val():$(v).val();
					}
				}else if(status=="OVERDUED"){
					if(currStatus=="PASSED"){
						storeIds+=storeIds.length>0?","+$(v).val():$(v).val();
					}
				}
			});
		}else{
			$("#mws-jui-dialog").text("请至少选择一个商户").dialog("open");
			return;
		}
		if(storeIds.length==0&&(status=="PASSED"||status=="REFUSED")){
			$("#mws-jui-dialog").text("请选择待审核商户").dialog("open");
			return;
		}else if(storeIds.length==0&&status=="OVERDUED"){
			$("#mws-jui-dialog").text("请选择审核通过商户").dialog("open");
			return;
		}
		$("#mws-confirm-dialog").text("确定执行该操作吗？").dialog("option",{title:"确认",buttons:[{
			text:"确认",
			click:function(){
				//发送审核请求
				$.post("store/approve",{"storeIds":storeIds,"status":status},function(response){
					if(response.result==0){
						loadStoreQueryResult();
						$("#mws-confirm-dialog").dialog("close");
						$("#mws-jui-dialog").text("操作成功").dialog("open");
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
		}
		]}).dialog("open");
	}
	
	// //////////////////////////////////////
	// 页面加载完毕后调用
	// //////////////////////////////////////
	
	$(function(){
		// 设置datepicker的全局变量信息
		$.datepicker.setDefaults({"dateFormat":"yy-mm-dd"});
		// 将制定的输入框应用datepicker
		$(".mws-datepicker").datepicker({showOtherMonths:true});
		//初始化导入Excel窗口
		initImportData();
		// 商户基本信息预设
		setStoreInfo();
		// 进入查询页面默认自动加载数据
		if($("#store-result-table").length>0){
			loadStoreQueryResult();
		}
	});
	
