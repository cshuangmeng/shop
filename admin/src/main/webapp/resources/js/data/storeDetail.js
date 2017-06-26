	//审核商户信息
	function approveStoreInfo(status){
		//获取选中的商户
		var storeIds=$(":hidden[name='storeId']").val();
		var currStatus=$(":hidden[name='status']").val();
		var reason = $(".query-div-column-float .query-div-input1").val();
		if(currStatus=='EXAMINING'){
			if(storeIds.length>0){
				//弹出确认提示框
				if(status == 'PASSED'){
					$("#mws-jui-dialog").text("确定执行该操作吗？").dialog("option",{title:"确认",buttons:[{
						text:"确认",
						click:function(){
							//发送审核请求
							$.post("approve",{"storeIds":storeIds,"status":status},function(response){
								//重置提示框
								initComTipDialogs();
								if(response.result==0){
									$(".mws-panel-toolbar").hide();
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
				}else if(status == 'REFUSED'){
					$("#refuse-reason-dialog").dialog("open");
				}
		}
	}
}
	// 初始化审核拒绝理由输入窗口
	function initRefuseDialog() {
		$("#refuse-reason-dialog").dialog({
			autoOpen : false,
			title : "修改WiFi",
			modal : true,
			width : 500,
			buttons : [ {
				text : "确认",
				click : function() { // 发送审核请求
					$.post("approve", {
						"storeIds" : $(":hidden[name='storeId']").val(),
						"status" : 'REFUSED',
						"reason" : $("#refuse-reason-dialog").find(":text[name='reason']").val()
					}, function(response) {
						// 重置提示框
						if (response.result == 0) {
							$(".mws-panel-content .reason").show();
							$(".mws-panel-toolbar").hide();
							$("#refuse-reason-dialog").dialog("close");
							$("#mws-jui-dialog").text("操作成功").dialog("open");
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
		});
	}
	
	////////////////////////////////////////
	//页面加载完毕后调用
	////////////////////////////////////////
	
$(function(){
	var lat = $("#lnglatX").text(); // 获取纬度
    var lon = $("#lnglatY").text(); // 获取经度
    initRefuseDialog();
    //隐藏审核按钮
    if($(":hidden[name='status']").val()!='EXAMINING'){
    	$(".mws-panel-toolbar").hide();
    }
    if($(":hidden[name='status']").val()=='PASSED' || $(":hidden[name='status']").val()=='EXAMINING'){
    	$(".mws-panel-content .reason").hide();
    }
    //初始化腾讯地图
    var myLatlng = new qq.maps.LatLng(lat, lon);
    var myOptions = {
      zoom: 15,
      center: myLatlng,
    }
    var map = new qq.maps.Map(document.getElementById("mapContainer"), myOptions);
	//创建marker
    var marker = new qq.maps.Marker({
        position: myLatlng,
        map: map
    });
});