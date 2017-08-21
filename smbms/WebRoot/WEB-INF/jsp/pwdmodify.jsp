<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>密码修改页面</span>
	</div>
	<div class="providerAdd">
		<form id="userForm" name="userForm" method="post"
			action="${pageContext.request.contextPath }/sys/user/pwdsave.html">
			<input type="hidden" name="method" value="savepwd">
			<!--div的class 为error是验证错误，ok是验证成功-->
			<div class="info">${message}</div>
			<div class="">
				<label for="oldPassword">旧密码：</label> <input type="password"
					name="oldpassword" id="oldpassword" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for="newPassword">新密码：</label> <input type="password"
					name="newpassword" id="newpassword" value=""> <font
					color="red"></font>
			</div>
			<div>
				<label for="reNewPassword">确认新密码：</label> <input type="password"
					name="rnewpassword" id="rnewpassword" value=""> <font
					color="red"></font>
			</div>
			<div class="providerAddBtn">
				<!--<a href="#">保存</a>-->
				<input type="button" name="save" id="save" value="保存"
					class="input-button">
			</div>
		</form>
	</div>
</div>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要修改密码吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/pwdmodify.js"></script>