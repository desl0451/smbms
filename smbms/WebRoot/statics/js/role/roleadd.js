var roleCode = null;
var rolename = null;
var addBtn = null;
var backBtn = null;
var billObj;
$(function() {
	roleCode = $("#roleCode");
	roleName = $("#roleName");
	addBtn = $("#add");
	backBtn = $("#back");
	roleCode.next().html("*");
	roleName.next().html("*");

	roleCode.on("blur", function() {
		$.ajax({
			type : "POST",
			url : path + "/sys/role/roleExist.json",
			data : {roleCode : roleCode.val()},
			dataType : "json",
			success : function(data) {
				if (data.roleCode == "true") { //旧密码正确
					validateTip(roleCode.next(), {"color" : "green"}, imgYes, true);
				} else if (data.roleCode == "false") { //旧密码输入不正确
					validateTip(roleCode.next(), {"color" : "red"}, imgNo + " 角色编码已存在", false);
				} else if (data.roleCode == "error") { //旧密码输入为空
					validateTip(roleCode.next(), {"color" : "red"}, imgNo + " 角色编码错误", false);
				}
			},
			error : function(data) {
				//请求出错
				validateTip(roleCode.next(), {"color" : "red"}, imgNo + " 请求错误", false);
			}
		});
	}).on("focus", function() {
		validateTip(roleCode.next(), {"color" : "#666666"}, "* 请输入原密码", false);
	});

	roleName.on("focus", function() {
		validateTip(roleName.next(), {"color" : "#666666"}, "* 角色名必须大于2小于20", false);
	}).on("blur", function() {
		if (roleName.val() != null && roleName.val().length >=2 && roleName.val().length < 20) {
			validateTip(roleName.next(), {"color" : "green"}, imgYes, true);
		} else {
			validateTip(roleName.next(), {"color" : "red"}, imgNo + " 角色名不符合规范，请重新输入", false);
		}
	});

	addBtn.on("click", function() {
		roleCode.blur();
		roleName.blur();
		if (roleCode.attr("validateStatus") == "true"
			&& roleName.attr("validateStatus") == "true") {
			changeDLGContent("你确定添加角色吗？");
			openYesOrNoDLG();
		}

	});
	
	backBtn.on("click",function(){
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
	
	$('#no').click(function() {
		cancleBtn();
	});

	$('#yes').click(function() {
		$("#roleForm").submit();
	});
});

function openYesOrNoDLG() {
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn() {
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr) {
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}