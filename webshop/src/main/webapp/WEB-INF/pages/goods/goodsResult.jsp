<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="page" value="${result.data.page }"/>
<input type="hidden" name="total" value="${result.data.total }"/>
<c:if test="${not empty result.data.goods }">
<c:forEach items="${result.data.goods }" var="g">
<div class="li">
	<a href="${pageContext.servletContext.contextPath }/goods/info?id=${g.id}"><img src="${g.fullHeadImg }" alt="" /></a>
	<a href="${pageContext.servletContext.contextPath }/goods/info?id=${g.id}"><p class="tit">${g.name }</p></a>
	<p class="money">
		<span class="truemoney">￥${g.price }</span>
		<span class="minmoney">最低实付价￥<b>${g.extras.miniPrice }</b></span>
	</p>
</div>
</c:forEach>
</c:if>
<script type="text/javascript">
position();
//商品列表定位
function position(){
	$('#goodsResult').find("div").each(function(){
		var _this = $(this);
		var index = _this.index()+1;
		if(index % 4 == 0){
			_this.css('margin-right','0px');
		}
	});
}
</script>