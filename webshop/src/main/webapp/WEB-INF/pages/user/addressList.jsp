<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<%@include file="util/script.jsp" %>
		<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/js/goodsList.js"></script>
		<title>首页</title>
	</head>
	<body>
		<!--navigation-->
		<%@include file="util/head.jsp" %>
		<!--body-->
		<div class="mySpace">
			<div class="mySpace_left">
				<div class="personImg">
				</div>
				<div class="mySpace_left_con_t">
					<ul class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/1_03.png"/>
							<p>我的账户</p>
							<img style="margin-top: 4px;" src="img/corner_01.png"/>
						</li>
						<li style="color: #DB3E40;" class="mySpace_left_con_2">订单管理</li>
						<li class="mySpace_left_con_3">收货地址</li>
					</ul>
					<ul style="margin: 20px 0;" class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/5_03.png"/>
							<p>我的资产</p>
						</li>
						<li class="mySpace_left_con_2">钱包</li>
					</ul>
					<ul class="mySpace_left_con">
						<li class="mySpace_left_con_1">
							<img src="img/3_03.png"/>
							<p>我的部落</p>
						</li>
						<li class="mySpace_left_con_2">成员</li>
					</ul>
				</div>
			</div>
			<!--收货地址-->
			<div style="display: none;" class="goods_address">
				<div class="goods_address_title">
					<p class="goods_address_title_txt1">已保存的收获地址</p>
					<p class="goods_address_title_txt2">您已经创建1个收货地址，最多可以创建10个</p>
				</div>
				<div class="goods_address_title_con">
					<ul class="goods_address_title_con_1">
						<li>
							<img src="img/1_04.png"/>
							<p>陈陈陈</p>
						</li>
						<li>
							<img src="img/2_04.png"/>
							<p>上海市普陀区兰溪路   君悦苑3号楼   </p>
						</li>
						<li>
							<img src="img/3_04.png"/>
							<p>152001010101</p>
						</li>
						<li class="corner_top">
							<p>默认地址</p>
						</li>
						<li class="corner_bottom">
							<img src="img/6_03.png" />
						</li>
					</ul>
					<ul class="goods_address_title_con_2">
						<li class="X"></li>
						<li class="Y"></li>
					</ul>
				</div>
				<p class="add_goods_address">
					新增收货地址
				</p>
				<ul class="add_goods_address_con">
					<li class="add_goods_address_con_1">
						<div class="add_goods_address_con_1_img"></div>
						<p>收货人</p>
						<input type="text" value="收货人姓名" />
					</li>
					<li class="add_goods_address_con_2">
						<div class="add_goods_address_con_2_img"></div>
						<p style="margin-right: 0;">收货地址</p>
						<input style="margin-left: 14px;" type="text" value="省/直辖市" />
						<input type="text" value="市" />
						<input type="text" value="区/县" />
						<input style="margin-left: 13px;" type="text" value="乡镇/街道" />
						<input style="background: none;" class="last_input" type="text" value="详细地址" />
					</li>
					<li class="add_goods_address_con_1">
						<div class="add_goods_address_con_1_img"></div>
						<p>手机号</p>
						<input style="width: 168px;" type="text" value="手机号" />
					</li>
				</ul>
				<ul id="radio_choice">
					<li>
						<input type="radio" checked="checked" name="choice" />
						<span>家庭地址</span>
					</li>
					<li style="margin: 0 90px;">
						<input type="radio" name="choice" />
						<span>家庭地址</span>
					</li>
					<li>
						<input type="radio" name="choice" />
						<span>其他</span>
					</li>
				</ul>
				<p class="keep_address">保存收货地址</p>
			</div>
		</div>
		<!--footer-->
		<%@include file="util/foot.jsp" %>
	</body>
</html>