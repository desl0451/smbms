<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>订单管理页面 >> 订单添加页面</span>
	</div>
	<div class="providerAdd">
		<form id="billForm" name="billForm" method="post"
			action="${pageContext.request.contextPath }/sys/bill/insert.html">
			<!--div的class 为error是验证错误，ok是验证成功-->
			<input type="hidden" name="method" value="add">
			<div class="">
				<label for="billCode">订单编码：</label> <input type="text"
					name="billCode" class="text" id="billCode" value="BILL2016_020">
				<!-- 放置提示信息 -->
				<font color="red"></font>
			</div>
			<div>
				<label for="productName">商品名称：</label> <input type="text"
					name="productName" id="productName" value="龙井"> <font
					color="red"></font>
			</div>
			<div>
				<label for="productUnit">商品单位：</label> <input type="text"
					name="productUnit" id="productUnit" value="艺福堂"> <font
					color="red"></font>
			</div>
			<div>
				<label for="productCount">商品数量：</label> <input type="text"
					name="productCount" id="productCount" value="30"> <font
					color="red"></font>
			</div>
			<div>
				<label for="totalPrice">总金额：</label> <input type="text"
					name="totalPrice" id="totalPrice" value="1500"> <font
					color="red"></font>
			</div>
			<div>
				<label>供应商：</label> <select name="providerId" id="providerId">
				</select> <font color="red"></font>
			</div>
			<div>
				<label>是否付款：</label> <input type="radio" name="isPayment" value="1"
					checked="checked">未付款 <input type="radio" name="isPayment"
					value="2">已付款
			</div>
			<div class="providerAddBtn">
				<input type="button" name="add" id="add" value="保存"> <input
					type="button" id="back" name="back" value="返回">
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
            <p>你确定要添加该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/bill/billadd.js"></script>