var userObj;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(obj){
	$.ajax({
		type:"GET",
		url:path+"/sys/user/deleteuser.json",
		data:{userid:userObj.attr("userid")},
		dataType:"json",
		success:function(data){
			if(data.delResult == "true"){//删除成功：移除删除行
				cancleBtn();
				obj.parents("tr").remove();
				window.location.href=path+"/sys/user/list.html";
			}else if(data.delResult == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("对不起，删除用户【"+obj.attr("username")+"】失败");
			}else if(data.delResult == "notexist"){
				//alert("对不起，用户【"+obj.attr("username")+"】不存在");
				changeDLGContent("对不起，用户【"+obj.attr("username")+"】不存在");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

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
				url:path+"/sys/user/view.html",
				data:{id:obj.attr("userid")},
				dataType:"json",
				success:function(result){
					if("failed"==result){
						alert("操作超时!");
					}else if("nodata"==result){
						alert("没有数据!");
					}else{
						$("#userCode").val(result.userCode);
						$("#userName").val(result.userName);
						if(result.gender=="1"){
							$("#gender").val("女");
						}else{
							$("#gender").val("男");
						}
						$("#birthday").val(result.birthday);
						$("#phone").val(result.phone);
						$("#address").val(result.address);
						$("#userRole").val(result.userRoleName);
						$("#creationDate").val(result.creationDate);
						$("#idPicPath").attr('src',result.idPicPath); 
						$("#workPicPath").attr('src',result.workPicPath); 
					}
				},
				error:function(data){
					alert("error!");
				}
		});
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/sys/user/usermodify/"+ obj.attr("userid")+"/";
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		userObj = $(this);
		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
		openYesOrNoDLG();
	});
});