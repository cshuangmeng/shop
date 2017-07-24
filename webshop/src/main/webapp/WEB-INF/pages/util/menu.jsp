<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="NAVIGATION_commom">
	<div class="navigation">
		<div class="navigationCon">
			<div class="list">
				<div><a href="${pageContext.servletContext.contextPath }/index">首页</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=2">欢迎大礼包</a></div>
				<div>品牌旗舰体验中心</div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=1">品质生活</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=3">养生尚品</a></div>
				<div class="js-more" id="js-more">更多
					<span id="js-moreList">
						<b>关于我们</b>
						<b>联系我们</b>
						<b>加入我们</b>
						<b>申请加入</b>
						<b>品牌旗舰</b>
						<b>商城介绍</b>
						<b>企业家专访</b>
						<img src="${pageContext.servletContext.contextPath }/resources/img/daosanjiao.png"/>
					</span>
				</div>
			</div>
			<div class="search">
				<input type="text" placeholder="搜索商品" />
				<img src="${pageContext.servletContext.contextPath }/resources/img/search.png"/>
			</div>
		</div>
	</div>
</div>