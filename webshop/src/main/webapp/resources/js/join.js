$(function(){
	$("p.joinUs_txt").click(function(){
		var company=$.trim($("div.joinUs_con :text:eq(0)").val());
		var website=$.trim($("div.joinUs_con :text:eq(1)").val());
		var product=$.trim($("div.joinUs_con :text:eq(2)").val());
		var advantage=$.trim($("div.joinUs_con :text:eq(3)").val());
		var contact=$.trim($("div.joinUs_con :text:eq(4)").val());
		var telephone=$.trim($("div.joinUs_con :text:eq(5)").val());
		if(company==""||website==""||product==""||advantage==""||contact==""||telephone==""){
			alert("请将信息填写完整");
			return;
		}
		$.post(contextPath+"/shop/cooperate",{"company":company,"website":website
			,"product":product,"advantage":advantage,"contact":contact,"telephone":telephone},function(data){
			if(data.code==0){
				alert("提交成功，我们将尽快与您取得联系！");
			}else{
				alert(data.msg);
			}
		});
	});
})