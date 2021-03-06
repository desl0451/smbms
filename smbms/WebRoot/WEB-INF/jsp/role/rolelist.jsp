<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>角色管理页面</span>
	</div>
	<div class="search">
		<form>
			<a href="${pageContext.request.contextPath }/sys/role/roleadd.html">添加角色</a>
		</form>
	</div>
	<table class="providerTable" cellpadding="0" cellspacing="0">
		<tr class="firstTr">
			<th width="25%">角色编码</th>
			<th width="20%">角色名称</th>
			<th width="25%">创建时间</th>
			<th width="30%">操作</th>
		</tr>
		<c:forEach var="role" items="${roleList}" varStatus="status">
			<tr>
				<td><span>${role.roleCode}</span></td>
				<td><span>${role.roleName}</span></td>
				<td><span> <fmt:formatDate value="${role.creationDate}"
							pattern="yyyy-MM-dd" />
				</span></td>

				<td>
				<span><a class="viewUser" href="javascript:;" id=${role.id } roleName=${role.roleName }><img src="${pageContext.request.contextPath }/statics/images/read.png" alt="查看" title="查看"/></a></span>
				<span><a class="modifyUser" href="javascript:;" id=${role.id } roleName=${role.roleName }><img src="${pageContext.request.contextPath }/statics/images/xiugai.png" alt="修改" title="修改"/></a></span>
				<span><a class="deleteUser" href="javascript:;" id=${role.id } roleName=${role.roleName }><img src="${pageContext.request.contextPath }/statics/images/schu.png" alt="删除" title="删除"/></a></span>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" id="totalPageCount" value="${totalPageCount}" />
	<c:import url="../common/rollpage.jsp">
		<c:param name="totalCount" value="${totalCount}" />
		<c:param name="currentPageNo" value="${currentPageNo}" />
		<c:param name="totalPageCount" value="${totalPageCount}" />
	</c:import>
	<div class="providerAdd">
			   <div>
                    <label for="roleCode">角色编码：</label>
                    <input type="text" name="roleCode" readonly id="roleCode" value="${role.roleCode }"> 
					<font color="red"></font>
               </div>
		       <div>
                    <label for="roleName">角色名称：</label>
                    <input type="text" name="roleName" id="roleName" readonly value="${role.roleName }"> 
                    <font color="red"></font>
               </div>
                <div>
                    <label >创建时间：</label>
					<input type="text"  id="creationDate" readonly name="creationDate" value="<fmt:formatDate
					value="${role.creationDate}" pattern="yyyy-MM-dd" />"
					readonly="readonly" >					
        			<font color="red"></font>
                </div>
        </div>
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该用户吗？</p>
			<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
		</div>
	</div>
</div>

<%@include file="/WEB-INF/jsp/common/foot.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/role/rolelist.js"></script>
