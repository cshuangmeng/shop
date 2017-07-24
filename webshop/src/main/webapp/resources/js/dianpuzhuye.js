$(function(){
	//商品列表定位
	position();
	function position(){
		$('.xinpinList .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
		$('.rexaioList .li').each(function(){
			var _this = $(this);
			var index = _this.index()+1;
			if(index % 4 == 0){
				_this.css('margin-right','0px');
			}
		});
	}
})
