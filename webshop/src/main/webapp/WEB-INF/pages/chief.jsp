<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/css/chiefRoad.css"/>
		<%@include file="util/script.jsp" %>
		<title>酋长之路</title>
	</head>
	<body>
		<div class="p-login-main">
			<!--navigation-->
			<div class="commonTop">
				<%@include file="util/head.jsp" %>
			</div>
			<div class="commomNavigation">
				<%@include file="util/menu.jsp" %>
			</div>
			<div class="chiefRoad">
			<div class="chiefRoad_introduce">
				<ul class="chiefRoad_introduce_con">
					<li class="chiefRoad_introduce_con_title">
						<p>很高兴在这里遇见你</p>
					</li>
					<li class="chiefRoad_introduce_con_txt">
						<p>
						 	<span>唐僧商城</span>一直立志成为家庭健康消费的首选平台，在家庭日常消费的过程中，
						     如何优化消费者和厂家的常态化供需链条，是作为酋长的您和您治理下的部落
						     即将和我们一道来面临的问题
						</p>
					</li>
					<li class="chiefRoad_introduce_con_name">
						<p>唐僧商城 &nbsp;&nbsp;tangsheng.shop</p>
					</li>
				</ul>
			</div>
			<div class="chiefRoad_step">
				<ul class="chiefRoad_step_con">
					<li class="chiefRoad_step_con_one">
						<span>酋长之路</span>
						<p>
						     是您第一次在唐僧商城消费时与唐僧所结下的善缘，也是在产品质量保证的前提下
						     让您及您的“族群”始终享受实惠的一条必经之路，来到这里您正式开启了在我们商城的
						     “成长”之路，如果可以，我们希望以您为纽带，将这份善缘传承下去，给您及身边
						     的朋友带来健康、安全的购物体验，这将会是我们一成不变的宗旨。
						</p>
					</li>
					<li class="chiefRoad_step_con_two">
						<span>建立自己的“领地”</span>
						<p>
						     发展自己的部落成员，也可以透过自己喜欢的方式DIY自己的族群。 期待有朝一日，
						     您能亲率您的族群在互联网这个大千世界开疆辟土、独领风骚、挥斥方遒
						</p>
					</li>
					<li class="chiefRoad_step_con_three">
						<span>无与伦比的魅力 至高无上的权利</span>
						<p>
					              当然，作为酋长的您，只要你愿意，您随时可以招募自己的“后宫佳丽”
					              亦可以“母仪天下”您每招募一名自己的部落成员，可以奖励其50部落币
					        用于在商城消费；每一位成员的加入将为您创造50部落币的“财富”
						</p>
					</li>
				</ul>
			</div>
			
			<div class="chiefRoad_activity">
				<ul class="chiefRoad_activity_img">
					<li>
						<img class="one hover" src="${pageContext.servletContext.contextPath }/resources/img/1_01_on.png"/>
					</li>
					<li>
						<img src="${pageContext.servletContext.contextPath }/resources/img/2_01.png"/>
					</li>
					<li>
						<img src="${pageContext.servletContext.contextPath }/resources/img/3_01.png"/>
					</li>
				</ul>
				<ul class="chiefRoad_activity_txt">
					<li class="chiefRoad_activity_txt_l">
						<p class="chiefRoad_activity_txt_c_title01">部落分的获取</p>
						<div class="chiefRoad_activity_txt_c_title01_con">
							<p>1.酋长奖励</p>
							<p>2.族员在商城购买产品，每消费1人民币积1部落分（实付现金）</p>
						</div>
						<p class="chiefRoad_activity_txt_c_title01">部落分的使用</p>
						<div style="width: 190px;" class="chiefRoad_activity_txt_c_title01_con">
							<p>1.部落分可用于商城购物抵扣消费</p>
							<p>2.部落分抵扣（3部落分=1人民币）</p>
						</div>
					</li>
					<li class="chiefRoad_activity_txt_c">
						<p class="chiefRoad_activity_txt_c_title01">部落币的获取</p>
						<div class="chiefRoad_activity_txt_c_title01_con">
							<p>1.酋长招募族员填写部落ID并成功购买欢迎大礼包，获得酋长封赏</p>
							<p>2.每位族员成功加入后，为酋长部落创造50部落币财富</p>
							<p>3.酋长可任命优秀族员成为部落首领，协助酋长管理其部落</p>
						</div>
						<p class="chiefRoad_activity_txt_c_title01">部落币的使用</p>
						<div style="width: 190px;" class="chiefRoad_activity_txt_c_title01_con">
							<p>1.部落币可用于商城购物抵扣消费</p>
							<p>2.部落币抵扣（2部落币=1人民币）</p>
						</div>
					</li>
					<li class="chiefRoad_activity_txt_r">
						<p class="chiefRoad_activity_txt_c_title01">每个消费者都可以晋升酋长，而每位酋长亦可以构建自己的部落</p>
						<p class="chiefRoad_activity_txt_c_title01">酋长权利</p>
						<div style="width: 190px;" class="chiefRoad_activity_txt_c_title01_con">
							<p>1.开创自己的个性部落</p>
							<p>2.新品上线的总和评审</p>
							<p>3.封赏自己的族员</p>
							<p>4.任命部落的首领</p>
							<p>5.DIY自己的族群</p>
							<p>6.代表部落出席 品牌峰会 拷贝</p>
						</div>
					</li>
				</ul>
			</div>
			</div>
			<!--footer-->
			<div class="commonBot">
				<%@include file="util/foot.jsp" %>
			</div>
		</div>
		<script type="text/javascript">
			$('.chiefRoad_activity_img li').each(function(){
		    	$(this).mouseover(function(){
		    		var index = $(this).index();
		    		var num = $(this).index()+1;
		    		$(this).find('img').addClass('hover');
		    		$(this).find('img').attr('src',contextPath+"/resources/img/"+num+"_01_on.png");
		    		$(this).siblings().find('img').removeClass('hover');
		    		$(this).parent().find('.one').attr('src',contextPath+"/resources/img/1_01.png");
		    		$(this).find('img').siblings().removeClass('hover');
		    		$('.chiefRoad_activity_txt').find('li').eq(index).show();
		    		$('.chiefRoad_activity_txt').find('li').eq(index).siblings().hide();
		    	});
		    	$(this).mouseout(function(){
		    		var index = $(this).index();
		    		var num = $(this).index()+1;
		    		$(this).find('img').attr('src',contextPath+"/resources/img/"+num+"_01.png");
		    		$(this).find('img').removeClass('hover');
		    		$('.chiefRoad_activity_txt').find('li').eq(index).hide();
		    	});
		    });
		    $('.chiefRoad_activity_img').mouseout(function(){
	    		$(this).find('.one').addClass('hover');
	    		$(this).find('.one').attr('src',contextPath+"/resources/img/1_01_on.png");
	    		$('.chiefRoad_activity_txt').find('li').eq(0).show();
	    	});
		</script>
	</body>
</html>