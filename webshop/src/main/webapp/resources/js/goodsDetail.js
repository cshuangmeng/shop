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
	
	//商品是否可购买检查
	function checkEnableBuy(){
		//检查是否可以购买此商品
		var enable=parseInt($(".btn").attr("enable"));
		var enableAmount=parseInt($(".btn").attr("amount"));
		var amount=parseInt($(".numchange :text").val());
		if(enable<=0){
			alert("每人限购1次,只限新人购买");
			return false;
		}else if(enableAmount>=0&&amount>enableAmount){
			alert("该商品最多只能购买"+enableAmount+"件");
			return false;
		}
		return true;
	}
	
	//加入购物车
	function joinToShoppingCar(async){
		var goodsId=$("#goodsId").val();
		var amount=$(".numchange :text").val();
		if(checkEnableBuy()){
			$.ajax({url:contextPath+"/user/addGoods",
				    data:{"goodsId":goodsId,"amount":amount},
				    async:async,
				    dataType:"json",
				    type:"post",
				    success:function(data){
				    	if(async){
							if(data.code==0){
								alert("添加成功");
							}else{
								alert(data.msg);
							}
				    	}
					}
			});
		}
	}
	//立即购买
	function buyNow(){
		var goodsId=$("#goodsId").val();
		var amount=$(".numchange :text").val();
		joinToShoppingCar(false);
		location.href=contextPath+"/order/confirm?itemIds="+goodsId+","+amount;
	}
	//绑定事件
	$(".shopcar").click(function(){joinToShoppingCar(true);});
	$(".pay").click(function(){buyNow(true);});
});

