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
	
	
	
});

