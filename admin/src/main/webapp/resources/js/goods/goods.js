//全局变量
var addNumsAtOnce=5;
var pIndex=1;

//表单校验
function checkGoodsForm(){
	var typeId=$("select[name='typeId']").val();
	var shopId=$("select[name='shopId']").val();
	var price=$(":text[name='price']").val();
	var cashDiscount=$(":text[name='cashDiscount']").val();
	var coinEnable=$(":radio[name='coinEnable']").val();
	var pointEnable=$(":radio[name='pointEnable']").val();
	alert(cashDiscount);
	if(cashDiscount==''){
		alert("请输入正确的最低现金折扣1");
		return false;
	}
	return true;
}

//加载城市列表
function loadGoodsTypeList(){
	$.post(contextPath + "/gtype/list",function(data){
	    	$.each(data.data, function (index, val) {
	    		$("select[name='typeId']").append("<option value='"+val.id+"'>"+val.name+"</option>")
		});
	    	$("select[val]").each(function(i,v){
	    		$(v).val($(v).attr("val"));
	    	});
    });
}

//加载店铺列表
function loadShopList(){
	$.post(contextPath + "/shop/list",function(data){
	    	$.each(data.data, function (index, val) {
	    		$("select[name='shopId']").append("<option value='"+val.shopId+"'>"+val.shopName+"</option>")
		});
	    	$("select[val]").each(function(i,v){
	    		$(v).val($(v).attr("val"));
	    	});
    });
}

//动态添加商品参数
function addGoodsParam(data){
	var html="<div p_index="+pIndex+" class=\"mws-form-row\">"+
				"<div name='param'>"+
					"参数名：<input name='label' type=\"text\" class=\"mws-textinput\" value='"+(undefined!=data?data.split("=")[0]:"")+"'/>"+
					"参数值：<input name='value' type=\"text\" class=\"mws-textinput\" value='"+(undefined!=data?data.split("=")[1]:"")+"'/>"+
					"&nbsp;&nbsp;&nbsp;<a href=\"javascript:delGoodsParam("+pIndex+");\">删除</a>"+
				"</div>"+
			 "</div>";
	$("#edit_goods_param_div div.mws-form-inline").append(html);
	pIndex=pIndex+1;
}

//回填商品信息
function initGoodsOtherInfo(){
	var val=$(":radio[name='coinEnable'][val]").attr("val");
	if(val!=''){
		$(":radio[name='coinEnable'][value='"+val+"']").attr("checked","checked");
	}else{
		$(":radio[name='coinEnable'][value='1']").attr("checked","checked");
	}
	val=$(":radio[name='pointEnable'][val]").attr("val");
	if(val!=''){
		$(":radio[name='pointEnable'][value='"+val+"']").attr("checked","checked");
	}else{
		$(":radio[name='pointEnable'][value='1']").attr("checked","checked");
	}
}

//一次添加多个参数
function addMoreParam(){
	for(var i=0;i<addNumsAtOnce;i++){
		addGoodsParam();
	}
}

//提交参数
function submitNewParams(){
	var params="";
	$("div[name='param']").each(function(i,v){
		var label=$(v).find("input[name='label']").val();
		var value=$(v).find("input[name='value']").val();
		value=value.replace(/,/g,"，");
		if(label!=""&&value!=""){
			params+=params!=""?","+label+"="+value:label+"="+value;
		}
	});
	$(":hidden[name='params']").val(params);
	$("#edit_goods_param_div").dialog("close");
	//设置Cookie
	setCookie("goods_params",params);
}

//初始化参数
function initGoodsParams(){
	var params=$(":hidden[name='params']").val();
	if(params==''){
		//设置Cookie
		params=getCookie("goods_params");
	}
	if(undefined!=params&&params!=""){
		for(var i=0;i<params.split(",").length;i++){
			addGoodsParam(params.split(",")[i]);
		}
	}else{
		addMoreParam();
	}
}

//删除参数
function delGoodsParam(index){
	$("div[p_index='"+index+"']").remove();
}

//加载符合条件的广告组信息
var params;
function queryOrderList(){
	var goodsName=$(":text[name='goodsName']").val();
	var typeId=$("select[name='typeId']").val();
	var state=$("select[name='state']").val();
	var startDate=$(":text[name='startDate']").val();
	var endDate=$(":text[name='endDate']").val();
	var shopName=$(":text[name='shopName']").val();
	//设置默认值
	params={"goodsName":goodsName,"typeId":typeId,"startDate":startDate,"endDate":endDate
			,"state":state,"shopName":shopName};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/goods/list",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td><input type='checkbox' name='goodsId' value='"+v.goodsId+"'/>"+"</td>"
					+"<td><a href='"+contextPath+"/goods/detail?goodsId="+v.goodsId+"'>" 
					+ (v.goodsName!=undefined&&v.goodsName!=''?v.goodsName:'N/A') + "</a></td>"
					+"<td>" + (v.typeName!=undefined&&v.typeName!=''?v.typeName:'N/A') + "</td>"
					+"<td state="+v.state+">" + (v.stateName!=undefined&&v.stateName!=''?v.stateName:'N/A') + "</td>"
					+"<td>" + (v.shopName!=undefined&&v.shopName!=''?v.shopName:'N/A') + "</td>"
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
	$(":checkbox[name='goodsId']").each(function(i,v){
		if($(checkAll).attr("checked")!=undefined){
			$(v).attr("checked","checked");
		}else{
			$(v).removeAttr("checked");
		}
	});
}

//编辑广告组
function editAddGroupInfo(){
	var gid = $(":checkbox[name='goodsId']:checked").val();
	if($(":checkbox[name='goodsId']:checked").length==1){
		var storeId=$(":checkbox[name='goodsId']:checked").val();
		location.href=contextPath+"/goods/edit?goodsId="+gid;
	}else{
		$("#mws-jui-dialog").text("请先选择一个商品").dialog("open");
	}
}

//上线下线
function approveAdInfo(status){
	var gid = $(":checkbox[name='goodsId']:checked").val();
	if($(":checkbox[name='goodsId']:checked").length==0){
		$("#mws-jui-dialog").text("请选择至少一个商品").dialog("open");
	}else{
		//获取选中的商户
		var storeIds="";
		$(":checkbox[name='goodsId']").each(function(i,v){
			if($(v).attr("checked")!=undefined){
				var currStatus=$.trim($(v).parent().parent().find("td:eq(3)").attr("state"));
				if((status==1||status==2)&&currStatus==0){
					storeIds=storeIds.length>0?","+$(v).val():$(v).val();
				}else if(status==3){
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
					$.post(contextPath+"/goods/examine",{"goodsIds":storeIds,"state":status},function(response){
						//重置提示框
						initComTipDialogs();
						if(response.code==0){
							$("#mws-jui-dialog").text("操作成功").dialog("open");
							queryOrderList();
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

//初始化添加商品参数
function initAddGoodsParamDialog(){
	$("#edit_goods_param_div").dialog({
		autoOpen: false, 
		title: "添加商品参数", 
		modal: true, 
		width: "550", 
		buttons: [{
			text: "再添加五个参数",
			click: function(){
				addMoreParam();
			}
		},{
			text: "提交", 
			click: function() {
				submitNewParams();
			}
		}]
	});
}

//初始化弹窗
function initShowScanDialog(){
	$("#show_scan_dialog").dialog({
		autoOpen: false, 
		title: "二维码", 
		modal: true, 
		width: "340", 
		buttons: [{
			text:"取消",
			click:function(){
				$(this).dialog("close");
			}
		}]
	});
}

//展示二维码
function showQrcode(){
	//获取选中的商户
	var storeIds="";
	$(":checkbox[name='goodsId']").each(function(i,v){
		if($(v).attr("checked")!=undefined){
			storeIds=storeIds.length>0?","+$(v).val():$(v).val();
		}
	});
	if(storeIds==''||storeIds.split(",").length!=1){
		$("#mws-jui-dialog").text("请选择一个商品").dialog("open");
	}else{
		$.post(contextPath+"/goods/qrcode",{"goodsId":storeIds},function(data){
			$("#showImg").attr("src",data.data.img);
			$("#show_scan_dialog").dialog("open");
		});
	}
}

$(function(){
	//设置datepicker的全局变量信息
	$.datepicker.setDefaults({"dateFormat":"yy-mm-dd"});
	//将制定的输入框应用datepicker
	$(".mws-datepicker").datepicker({showOtherMonths:true});
	
	//初始化操作系统下拉列表
	initOptionsOfSelect("state","goods_state");
	//初始化城市下拉列表
	loadGoodsTypeList();
	//初始化店铺列表
	loadShopList();
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryOrderList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryOrderList);
	//添加商品参数
	initAddGoodsParamDialog();
	initShowScanDialog();
	$("#paramInput").click(function(){
		$("#edit_goods_param_div div.mws-form-inline").empty();
		initGoodsParams();
		$("#edit_goods_param_div").dialog("open");
	});
	//回填商品其他信息
	initGoodsOtherInfo();
    $("#update-form").validate();
})

