	
	//统计指定时间段内订单数
	function showShopOrderStatChart() {
		$.post(contextPath+"/shop/summary",function(response) {
			//填充汇总数据
			$(".mws-report:eq(0)").find(".mws-report-value").text(response.data.goodsSum);
			$(".mws-report:eq(1)").find(".mws-report-value").text(response.data.submittedSum);
			$(".mws-report:eq(2)").find(".mws-report-value").text(response.data.passedSum);
			$(".mws-report:eq(3)").find(".mws-report-value").text(response.data.deletedSum);
			$(".mws-report:eq(4)").find(".mws-report-value").text(response.data.todaySum);
			$('#mws-dashboard-chart').highcharts({
				exporting : {
					enabled : false
				},
				credits : {
					enabled : false
				},
				tooltip : {
					shared : true
				},
				title : {
					text : '订单变化趋势',
				},
				xAxis : {
					categories : response.data.line1.categories
				},
				yAxis : {
					title : {
						text : '数目'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				series : [ {
					name : '日期',
					data : response.data.line1.series1
				} ]
			});
		});
	}
	
	$(function() {
		showShopOrderStatChart();
	});
