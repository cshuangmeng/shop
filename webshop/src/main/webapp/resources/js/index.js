$(function(){
	
	//轮播
	$("#js-banner").find(".item:eq(1)").addClass('active');
	$("#js-banner").find(".item:eq(0)").remove();
	$("#js-banner1").find(".item:eq(1)").addClass('active');
	$("#js-banner1").find(".item:eq(0)").remove();
	$('.carousel').carousel();
	
	
	//商品列表定位
	position();
	function position(){
		$('.hydlb .list .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
		$('.tyzx .list .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
		$('.pzsh .list .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
		$('.yssp .list .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
	}
	
});

