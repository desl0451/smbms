$(function(){
	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
	/**
	 * bind、live、delegate
	 * on 
	 */
	$(".viewUser").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		//window.location.href=path+"/sys/user/view/"+ obj.attr("userid");
		$.ajax({
				type:"GET",
				url:path+"/sys/role/view.html",
				data:{id:obj.attr("id")},
				dataType:"json",
				success:function(result){
					if("failed"==result){
						alert("操作超时!");
					}else if("nodata"==result){
						alert("没有数据!");
					}else{
						$("#roleCode").val(result.roleCode);
						$("#roleName").val(result.roleName);
						$("#creationDate").val(result.creationDate);
					}
				},
				error:function(data){
					alert("error!");
				}
		});
	});
	
});