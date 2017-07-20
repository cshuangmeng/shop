<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/orderConfirm.css"/>
		<%@include file="../util/script.jsp" %>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<%@include file="../util/head.jsp" %>
		<!--body-->
		<div class="shoppingCartMiddle">
			<div class="shoppingCartMiddleCon">
				<div class="middleAddress">
					<p class="address_txt1">请确认收货地址</p>
					<ul class="address_con">
						<li class="address_con1">
							<div class="address_con_txt1">
								<p class="address_con_txt_left">上海市普陀区兰溪路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;君悦苑3号楼</p>
								<p class="address_con_txt_right">&nbsp;&nbsp;(陈陈陈)</p>
							</div>
							<p class="address_con_txt2">
								15201010001
							</p>
							<div class="address_con_txt3">
								<p class="address_con_txt3_l">修改</p>
								<p class="address_con_txt3_r">#282727</p>
							</div>
							<img class="corner" src="${pageContext.servletContext.contextPath }/resources/img/right.png"/>
						</li>
						<li class="address_con1" style="margin: 0 64px;">
							<div class="address_con_txt1">
								<p class="address_con_txt_left">上海市普陀区兰溪路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;君悦苑3号楼</p>
								<p class="address_con_txt_right">&nbsp;&nbsp;(陈陈陈)</p>
							</div>
							<p class="address_con_txt2">
								15201010001
							</p>
							<div class="address_con_txt3">
								<p class="address_con_txt3_l">修改</p>
								<p class="address_con_txt3_r">#282727</p>
							</div>
						</li>
						<li class="address_con2">
							<img src="${pageContext.servletContext.contextPath }/resources/img/addjpg.png" />
						</li>
					</ul>
					<div class="addressShow">
						<p class="addressShow_l">
							<span>显示全部地址</span>
							<img src="${pageContext.servletContext.contextPath }/resources/img/boder.jpg"/>
						</p>
						<p class="addressShow_r">
							<span>管理收货地址</span>
							<img src="${pageContext.servletContext.contextPath }/resources/img/boder.jpg"/>
						</p>
					</div>
					<p class="address_txt2">请确认收货地址</p>
					
					
				</div>
				<div class="MiddleConTitle">
					<p class="MiddleConTitle_1">
						产品
					</p>
					<p class="MiddleConTitle_2" >
						单价
					</p>
					<p class="MiddleConTitle_3" >
						数量
					</p>
					<p class="MiddleConTitle_4" >
						优惠方式
					</p>
					<p class="MiddleConTitle_5" >
						小计
					</p>
					<!--<p class="MiddleConTitle_6" >
						删除
					</p>-->
					
				</div>
				<ul class="MiddleConGoods">
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count" style="margin: 16px 120px 0 120px;">
							<b class="numberCode">1</b>
						</div>
						<div class="preferential">
							<p>省66</p>
							<p>商品免运费</p>
						</div>
						<p class="goods_price_end">¥3402</p>
					</li>
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count" style="margin: 16px 120px 0 120px;">
							<b class="numberCode">1</b>
						</div>
						<div class="preferential">
							<p>省66</p>
							<p>商品免运费</p>
						</div>
						<p class="goods_price_end">¥3402</p>
					</li>
					<li>
						<img style="margin-left: 30px;" class="goodsImage" src="${pageContext.servletContext.contextPath }/resources/img/goods.png" />
						<p class="goods_introduce">尚可茶品精选名茶专场-青云碧螺春两罐10g</p>
						<p class="goods_price">¥3402</p>
						<div class="count" style="margin: 16px 120px 0 120px;">
							<b class="numberCode">1</b>
						</div>
						<div class="preferential">
							<p>省66</p>
							<p>商品免运费</p>
						</div>
						<p class="goods_price_end">¥3402</p>
					</li>
				</ul>
				<div class="fare">
					<button class="fareBtn">运费</button>
					<p class="fareTxt">本组商品已经免运费</p>
				</div>
				<div class="fareBlank" style="height: 12px;">
					
				</div>
				<div class="priceAll" style="height: 210px;">
					<ul class="coupon">
						<li style="margin-left: 30px;" class="cupon_con_l">
							<p>填写部落番号</p>
							<p class="cupon_con_l_1"></p>
						</li>
						<li style="margin: 0 160px;" class="cupon_con_c">
							<p>本次使用</p>
							<p class="cupon_con_c_1"></p>
							<p>部落币（100）</p>
						</li>
						<li class="cupon_con_c">
							<p>本次使用</p>
							<p class="cupon_con_c_1"></p>
							<p>部落币（100）</p>
						</li>
					</ul>
					<ul class="priceAllCon">
						<li>
							<span style="color: #333;">¥20</span>
							<span>部落分抵用</span>
						</li>
						<li>
							<span style="color: #333;">¥20</span>
							<span>部落币抵用</span>
						</li>
						<li>
							<span style="color: #333;">¥442</span>
							<span>商品金额</span>
						</li>
						<li>
							<span style="color: #d15553;">¥442</span>
							<span style="width: 110px;">总金额（已免运费）</span>
						</li>
					</ul>
				</div>
				<div class="payAll">
					<p class="payAllLeft">
						<span>返回购物车</span>
						<img src="${pageContext.servletContext.contextPath }/resources/img/back.png" />
					</p>
					<p class="payAllRight">
						提交订单
					</p>
				</div>
				<div class="bottomBlank">
					
				</div>
				
				<!--footer-->
				<div class="commonBot"></div>
			</div>
		</div>
		<!--footer-->
		<%@include file="../util/foot.jsp" %>
	</body>
</html>