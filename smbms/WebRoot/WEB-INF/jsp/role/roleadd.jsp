<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>角色管理页面 >> 角色添加页面</span>
	</div>
	<div class="providerAdd">
		<form id="roleForm" name="roleForm" method="post"
			action="${pageContext.request.contextPath }/sys/role/insert.html">
			<input type="hidden" name="method" value="add">
			<div class="">
				<label for="roleCode">角色编码:</label> 
				<input type="text" name="roleCode" id="roleCode" value="SMBMS_GOD">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="roleName">角色名称:</label> 
				<input type="text" name="roleName" id="roleName" value="大神"> 
				<font color="red"></font>
			</div>
			<div class="providerAddBtn">
				<input type="button" name="add" id="add" value="保存">
				<input type="button" name="back" id="back" value="返回">
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
            <p>你确定要添加该角色吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/role/roleadd.js"></script>