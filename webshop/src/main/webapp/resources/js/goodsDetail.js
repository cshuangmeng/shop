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
	
	//数量加减
	$(".changenum").blur(function(){
		var _this = $(this);
        if(_this.val() > 1){
        	_this.prev().css('color','#333');
			_this.prev().removeAttr('disabled');
        }
    });
    //减
    reduce();
    function reduce(){
    	$('.reduce').click(function(){
    		console.log('--');
    		var _this = $(this);
    		var val = _this.next().val();
    		val--;
    		if(val >= 1){
				_this.next().val(val);
    		}
    		if(val == 1){
    			_this.css('color','#ccc');
				_this.attr('disabled','disabled');
    		}
		});
    }
	//加
	increase();
	function increase(){
		$('.increase').click(function(){
			console.log('++');
			var _this = $(this);
			_this.prev().prev().css('color','#333');
			var val = _this.prev().val();
			val++;
			_this.prev().val(val);
		});
	}
	
	
});

