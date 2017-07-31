<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="NAVIGATION_commom">
	<div class="navigation">
		<div class="navigationCon">
			<div class="list">
				<div><a href="${pageContext.servletContext.contextPath }/index">首页</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=2">欢迎大礼包</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/colony">品牌旗舰体验中心</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=1">品质生活</a></div>
				<div><a href="${pageContext.servletContext.contextPath }/goods/list?typeId=3">养生尚品</a></div>
				<div class="js-more" id="js-more">更多
					<span id="js-moreList">
						<a href="${pageContext.servletContext.contextPath }/about"><b>关于我们</b></a>
						<a href="${pageContext.servletContext.contextPath }/join"><b>联系我们</b></a>
						<a href="${pageContext.servletContext.contextPath }/introduce"><b>商城介绍</b></a>
						<a href="${pageContext.servletContext.contextPath }/interview"><b>企业家专访</b></a>
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