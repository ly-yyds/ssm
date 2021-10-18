<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
	<h2>用户管理</h2>
	<div class="manage">
		<table class="list">
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>性别</th>
				<th>Email</th>
				<th>电话号</th>
				<th>操作</th>
			</tr>
			<tr>
				<td class="first w4 c">${user.id }</td>
				<td class="w1 c">${user.trueName }</td>
				<td class="w2 c">${user.sex }</td>
				<td class="c">${user.email }</td>
				<td class="w4 c">${user.mobile }</td>
				<td class="w1 c"><a href="user?action=userEdit">修改</a></td>
			</tr>
		</table>
	</div>
</div>