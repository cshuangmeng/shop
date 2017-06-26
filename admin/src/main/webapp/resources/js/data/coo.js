	
	//加载合作请求列表
	function loadCooRequestList(){
		$("#coo_list_table").dataTable({
			"sAjaxSource": "coo/list",
			"sAjaxDataProp": "data",
			"bServerSide":true,
			'bPaginate': true,
			"aoColumnDefs" : [{"bSortable":false,"aTargets":[0,1,2,3,4]}],
			"oLanguage": {
	          "sLengthMenu": "每页显示 _MENU_ 条记录",
	          "sZeroRecords": "对不起，查询不到相关数据！",
	          "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	          "sInfoFiltered": "",
	          "sInfoEmpty": "",
	          "sSearch": "搜索",
			},
			"aoColumns": [
			   {"mDataProp":"fullname","sTitle":"商户名称","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"address","sTitle":"地址","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"linkman","sTitle":"联系人","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"telephone","sTitle":"联系电话","sClass":"center","sWidth":"20%"},
			   {"mDataProp":"createTime","sTitle":"申请时间","sClass":"center","sWidth":"20%"}
		    ],
		    "fnServerData": function(sSource, aoData, fnCallback){
		    	var sSearch;
		    	$(aoData).each(function(i,v){
					if(v.name=="sSearch"){
						$(":hidden[name='sSearch']").val(v.value);
					}
				});
		    	$.post(sSource,aoData,fnCallback);
		    }}
		).css("width","100%");
	}
	
	//导出Excel
	function exportCooRequestList(){
		var token=$("meta[name='_csrf']").attr("content");
		var parameterName=$("meta[name='_csrf.parameterName']").attr("content");
		$("form[name='coo_list_form']").append("<input type='hidden' name='"+parameterName+"' value='"+token+"'/>");
		$("form[name='coo_list_form']").attr("action","coo/export").submit();
	}
	
	$(function(){
		//加载合作请求数据
		loadCooRequestList();
	});
	