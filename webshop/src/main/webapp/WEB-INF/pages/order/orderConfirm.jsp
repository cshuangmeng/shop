<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/orderConfirm.css"/>
		<%@include file="../util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/orderConfirm.js"></script>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<div class="commonTop">
			<%@include file="../util/head.jsp" %>
		</div>
		<div class="commomNavigation">
			<%@include file="../util/menu.jsp" %>
		</div>
		<!--body-->
		<div class="shoppingCartMiddle">
			<div class="shoppingCartMiddleCon">
				<div class="middleAddress">
					<p class="address_txt1">请确认收货地址</p>
					<ul class="address_con">
						<c:if test="${not empty result.data.addresses[0] }">
						<li class="address_con1" addressId=${result.data.addresses[0].id }>
							<div class="address_con_txt1">
								<p class="address_con_txt_left">${result.data.addresses[0].address }</p>
								<p class="address_con_txt_right">${result.data.addresses[0].consigner }</p>
							</div>
							<p class="address_con_txt2">
								${result.data.addresses[0].mobile }
							</p>
							<div class="address_con_txt3">
								<p class="address_con_txt3_l">修改</p>
							</div>
							<img class="corner" src="${pageContext.servletContext.contextPath }/resources/img/right.png"/>
						</li>
						</c:if>
						<c:if test="${not empty result.data.addresses[1] }">
						<li class="address_con1">
							<div class="address_con_txt1">
								<p class="address_con_txt_left">${result.data.addresses[1].address }</p>
								<p class="address_con_txt_right">${result.data.addresses[1].consigner }</p>
							</div>
							<p class="address_con_txt2">
								${result.data.addresses[1].mobile }
							</p>
							<div class="address_con_txt3">
								<p class="address_con_txt3_l">修改</p>
							</div>
						</li>
						</c:if>
						<li class="address_con2">
							<img src="${pageContext.servletContext.contextPath }/resources/img/addjpg.png" />
						</li>
						<c:forEach items="${result.data.addresses }" var="address" varStatus="state">
						<c:if test="${state.count>2 }">
						<li name="display" class="address_con1 display_none">
							<div class="address_con_txt1">
								<p class="address_con_txt_left">${address.address }</p>
								<p class="address_con_txt_right">${address.consigner }</p>
							</div>
							<p class="address_con_txt2">
								${address.mobile }
							</p>
							<div class="address_con_txt3">
								<p class="address_con_txt3_l">修改</p>
							</div>
						</li>
						</c:if>
						</c:forEach>
						<div class="clear"></div>
					</ul>
					<div class="addressShow">
						<p class="addressShow_l">
							<span id="showAllAddress">显示全部地址</span>
							<img src="${pageContext.servletContext.contextPath }/resources/img/boder.jpg"/>
						</p>
						<p class="addressShow_r">
							<span><a href="${pageContext.servletContext.contextPath }/address/list">管理收货地址</a></span>
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
					<input type="hidden" name="totalMiniPrice" value="${result.data.totalMiniPrice }"/>
					<input type="hidden" name="coinRate" value="${result.data.coinRate }"/>
					<input type="hidden" name="pointRate" value="${result.data.pointRate }"/>
					<c:forEach items="${result.data.goods }" var="shop">
					<c:forEach items="${shop.goods }" var="goods">
					<li goodsId="${goods.goodsId }">
						<img style="margin-left: 30px;" class="goodsImage" src="${goods.headImg }" />
						<p class="goods_introduce">${goods.name }</p>
						<p class="goods_price">¥${goods.price }</p>
						<div class="count" style="margin: 16px 120px 0 120px;">
							<b class="numberCode">${goods.amount }</b>
						</div>
						<div class="preferential">
							<!-- <p>省66</p> -->
							<p>${result.data.freight==0?"商品免运费":"运费："+result.data.freight }</p>
						</div>
						<p class="goods_price_end">¥${goods.price*goods.amount }</p>
						<input type="hidden" name="pointEnable" value="${goods.pointEnable }"/>
						<input type="hidden" name="coinEnable" value="${goods.coinEnable }"/>
						<input type="hidden" name="typeId" value="${goods.typeId }"/>
					</li>
					</c:forEach>
					</c:forEach>
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
							<p><input type="text" name="tribeId"/></p>
						</li>
						<li class="cupon_con_c">
							<p>本次使用</p>
							<p><input type="text" name="coin"/></p>
							<p>部落币（${result.data.coin }）</p>
						</li>
						<li class="cupon_con_c">
							<p>本次使用</p>
							<p><input type="text" name="point"/></p>
							<p>部落分（${result.data.point }）</p>
						</li>
					</ul>
					<ul class="priceAllCon">
						<li>
							<span id="pointDeduct" style="color: #333;">¥0</span>
							<span>部落分抵用</span>
						</li>
						<li>
							<span id="coinDeduct" style="color: #333;">¥0</span>
							<span>部落币抵用</span>
						</li>
						<li>
							<span id="totalPrice" style="color: #333;">¥442</span>
							<span>商品金额</span>
						</li>
						<li>
							<span id="payPrice" style="color: #d15553;">¥442</span>
							<span style="width: 110px;">总金额${result.data.freight==0?"（已免运费）":"" }</span>
						</li>
					</ul>
				</div>
				<div class="payAll">
					<p class="payAllLeft">
						<span><a href="${pageContext.servletContext.contextPath }/user/car">返回购物车</a></span>
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
		<div class="commonBot">
			<%@include file="../util/foot.jsp" %>
		</div>
	</body>
</html>