<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="main">
	<h2>修改用户</h2>
	<div class="manage">
		<form id="userForm" action="user?action=updateUser" method="post">
			<table class="form">
				<tr>
					<td class="field">用户名：</td>
					<td><input type="text" class="text" name="userName" value="${user.userName }" readonly="readonly" /></td>
				</tr>
				<tr>
					<td class="field">姓名：</td>
					<td><input type="text" class="text"  id="trueName" name="trueName" value="${user.trueName }" /></td>
				</tr>
				<tr>
					<td class="field">密码：</td>
					<td><input type="text" class="text" id="password" name="password" value="${user.password }" /></td>
				</tr>
				<tr>
					<td class="field">性别：</td>
					<td>
						<c:if test="${user.sex == '男' }">
							<input type="radio"  name="sex" value="男" checked/>男
							<input type="radio"  name="sex" value="女"/>女
						</c:if>
						<c:if test="${user.sex == '女' }">
							<input type="radio"  name="sex" value="男"/>男
							<input type="radio"  name="sex" value="女" checked/>女
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="field">出生日期：</td>
					<td>
						<input  type="text"  id="birthday"  name="birthday" value='<fmt:formatDate value="${user.birthday }" type="date" pattern="yyyy-MM-dd"/>'  class="Wdate" onClick="WdatePicker()"/>	
					</td>
				</tr>
				<tr>
					<td class="field">手机号码：</td>
					<td><input type="text" id="mobile" class="text" name="mobile" value="${user.mobile }" /></td>
				</tr>
				<tr>
					<td class="field">送货地址：</td>
					<td><input type="text" class="text"  id="address" name="address" value="${user.address }" /></td>
				</tr>
				<tr>
					<td class="field">身份证号：</td>
					<td><input class="text" type="text" id="dentityCode" name="dentityCode"  value="${user.dentityCode }" /></td>
				</tr>
				<tr>
					<td class="field">Email：</td>
					<td><input class="text" type="text" id="email" name="email"  value="${user.email }" /></td>
				</tr>
				<tr>
					<td></td>
					<td><label class="ui-blue"><input type="button" id="userBtn" value="更新" /></label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><font id="error" color="red">${error }</font> </td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script type="text/javascript">
	$("#userBtn").click(function(){
		// 接收参数
		var password = $("#password").val();
		var mobile = $("#mobile").val();
		var address = $("#address").val();
		// 非空判断
		if(password.trim() == ""){
			$("#error").html("密码不能为空！")
			return;
		}
		if(mobile.trim() == ""){
			$("#error").html("手机号不能为空！")
			return;
		}
		if(address.trim() == ""){
			$("#error").html("地址不能为空！")
			return;
		}
		// 提交表单
		$("#userForm").submit();
	});
</script>