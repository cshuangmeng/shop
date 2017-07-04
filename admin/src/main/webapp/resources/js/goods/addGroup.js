//加载城市列表
function loadCityList(){
	$.post(contextPath + "/manager/area/cityList",function(data){
    	$.each(data.data, function (index, val) {
    		$("select[name='city']").append("<option value='"+(val.areacode+"").substring(0,2)+"'>"+val.city+"</option>")
		});
    });
}

$(function(){

	//设置datepicker的全局变量信息
	$.datepicker.setDefaults({"dateFormat":"yy-mm-dd"});
	//将制定的输入框应用datepicker
	$(".mws-datepicker").datepicker({showOtherMonths:true});
	
	//初始化操作系统下拉列表
	initOptionsOfSelect("os","mobile_platform_type");
	//初始化广告类型下拉列表
	initOptionsOfSelect("groupType","ad_group_type");
	//初始化城市下拉列表
	loadCityList();
	
});

