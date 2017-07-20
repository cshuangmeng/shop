$(function(){
	var $more = $('#js-more'),
		$moreList = $('#js-moreList');
	
	//显示隐藏更多
	$more.mouseenter(function(){
		$moreList.css("display","block");
	});
    $more.mouseleave(function(){
		$moreList.css("display","none");
	});
	
	//轮播
	$('.item:eq(1)').addClass('active');
	$('.item:eq(0)').remove();
	$('.carousel').carousel();
	$('.item01:eq(1)').addClass('active');
	$('.item01:eq(0)').remove();
	
	
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

