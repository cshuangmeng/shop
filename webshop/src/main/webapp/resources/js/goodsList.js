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
	
	//商品列表定位
	position();
	function position(){
		$('.goodslist .goodslistCon .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
	}
	
	
});

