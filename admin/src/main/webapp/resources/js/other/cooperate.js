//加载符合条件的合作申请信息
var params;
function queryCooperationList(){
	var company=$(":text[name='company']").val();
	var contact=$(":text[name='contact']").val();
	//设置默认值
	params={"company":company,"contact":contact};
	//发送请求获取数据更新到表格中
	$.post(contextPath+"/coo/list",params,function(response){
		$(".mws-datatable").dataTable().fnDestroy();
		$(".mws-datatable").find("tbody").empty();
		$(response.data).each(function(k, v) {
			var css = "even";
			if (k % 2 == 1) {
				css = "odd";
			}
			var html = "<tr class='" + css + "'>"
					+"<td>"+v.company+"</td>"
					+"<td>"+v.website+"</td>" 
					+"<td>"+v.product+"</td>"
					+"<td>"+v.advantage+"</td>"
					+"<td>"+v.contact+"</td>"
					+"<td>"+v.telephone+"</td>"
					+"<td>" + v.formatTime + "</td></tr>"
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

$(function(){
	//进入查询页面默认自动加载数据
	if($("#store-result-table").length>0){
		queryCooperationList();
	}
	//绑定查询按钮事件
	$(":button[name='submit']").click(queryCooperationList);
});

