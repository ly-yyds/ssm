<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<title>易买网 - 登录</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="register" class="wrap">
			<div class="shadow">
				<em class="corner lb"></em> <em class="corner rt"></em>
				<div class="box">
					<h1>欢迎回到易买网</h1>
					<form id="loginForm" method="post" action="user">
						<input type="hidden" name="action" value="login">
						<table>
							<tr>
								<td class="field">用户名：</td>
								<td>
									<input class="text" type="text" id="userName"
									name="userName" value="admin" />
								</td>
							</tr>
							<tr>
								<td class="field">登录密码：</td>
								<td>
									<input class="text" type="password" id="password"
									name="password" value="123" />
								</td>
							</tr>
							<tr>
								<td class="field">验证码：</td>
								<td>
									<input class="text" style="width: 60px; margin-right: 10px;" 
									type=text name="imageCode" id="imageCode">
								 	<img title="换一张试试" name="randImage" id="randImage"
								 	src="image.jsp" width="60" height="22" border="1" align="absmiddle">
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="ui-green">
										<input style="cursor: pointer;" type="button" id="loginBtn" value="立即登录" />
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<font id="error" color="red">${msg }</font>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
	<script type="text/javascript">
		// 点击切换验证码
		// 绑定点击事件，改变src属性
		$("#randImage").click(function(){
			$(this).attr("src","image.jsp?" + Math.random());
		});
		
		// 给登录按钮绑定点击事件
		$("#loginBtn").click(function(){
			// 接收参数
			var userName = $("#userName").val();
			var password = $("#password").val();
			var imageCode = $("#imageCode").val();
			// 非空判断
			if(userName.trim() == ""){
				// 提示用户不能为空
				$("#error").html("用户名不能为空！");
				return;
			}
			if(password.trim() == ""){
				// 提示用户不能为空
				$("#error").html("密码不能为空！");
				return;
			}
			if(imageCode.trim() == ""){
				// 提示用户不能为空
				$("#error").html("验证码不能为空！");
				return;
			}
			// 提交表单
			$("#loginForm").submit();
		});
	</script>
</html>