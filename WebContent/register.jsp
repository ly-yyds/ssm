<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<title>易买网 - 注册</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="register" class="wrap">
			<div class="shadow" style="margin: 20px auto;">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<h1>欢迎注册易买网</h1>
					<ul class="steps clearfix">
						<li class="current"><em></em>填写注册信息</li>
						<li class="last"><em></em>注册成功</li>
					</ul>
					<form id="regForm" method="post" action="user">
						<input type="hidden" name="action" value="register" />
						<table>				
							<tr>
								<td class="field">用户名(*)：</td>
								<td>
									<input class="text" type="text" id="userName" name="userName"/>
									&nbsp;<font id="userErrorInfo" color="red"></font>
								</td>
							</tr>
							<tr>
								<td class="field">登录密码(*)：</td>
								<td>
									<input class="text" type="password" id="password" name="password"/>
								</td>
							</tr>
							<tr>
								<td class="field">确认密码(*)：</td>
								<td>
									<input class="text" type="password" id="rePassWord" name="rePassWord"/>
								</td>
							</tr>
							<tr>
								<td class="field">性别：</td>
								<td>
							    	<input type="radio" name="sex" value="男" checked="checked"/>男&nbsp;&nbsp;
							    	<input type="radio" name="sex" value="女"/>女					    					    
							    </td>						
							</tr>
							<tr>
								<td class="field">出生日期：</td>
								<td>
									<input type="text" id="birthday" name="birthday" class="Wdate" onClick="WdatePicker()"/>	
								</td> 
							</tr>
							<tr>
								<td class="field">身份证号：</td>
								<td>
									<input class="text" type="text" id="dentityCode" name="dentityCode"/>
								</td>
							</tr>
							<tr>
								<td class="field">Email：</td>
								<td>
									<input class="text" type="text" id="email" name="email"/>
								</td>
							</tr>
							<tr>
								<td class="field">手机号码(*)：</td>
								<td>
									<input class="text" type="text" id="mobile" name="mobile" />
								</td>
							</tr>
							<tr>
								<td class="field">收获地址(*)：</td>
								<td>
									<input class="text" type="text" id="address" name="address" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td>
									<label class="ui-green">
										<input style="cursor: pointer;" type="button" id="regBtn" value="提交注册" />
									</label>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>
									<font id="error" color="red">${msg }</font>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
	<script type="text/javascript">
		// 给用户名输入框绑定失焦事件
		$("#userName").blur(function(){
			// 接收参数
			var userName = $(this).val().trim();
			// 非空判断
			if(userName == ""){
				// 提示用户不能为空
				$("#userErrorInfo").html("不能为空！");
				return;
			}
			// ajax发出请求到后台，通过回调函数接收的后台数据进行判断
			$.ajax({
			   type: "POST",
			   url: "user",
			   data: {
				   "action":"checkName",
				   "userName":userName
			   },
			   success: function(result){
			   		if(result == 0){
			   			// 已存在
						$("#userErrorInfo").html("对不起，用户名已存在！");
			   		}else if(result == 1){
			   			$("#userErrorInfo").html("");
			   		}else{
			   			$("#userErrorInfo").html("不能为空！");
			   		}
			   }
			});
		});
		// 给注册按钮绑定点击事件
		$("#regBtn").click(function(){
			// 接收参数
			var userName = $("#userName").val();
			var password = $("#password").val();
			var rePassWord = $("#rePassWord").val();
			var mobile = $("#mobile").val();
			var address = $("#address").val();
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
			if(rePassWord!=password){
				// 提示用户不能为空
				$("#error").html("确认密码和密码不一致！");
				return;
			}
			if(mobile.trim() == ""){
				// 提示用户不能为空
				$("#error").html("手机号不能为空！");
				return;
			}
			if(address.trim() == ""){
				// 提示用户不能为空
				$("#error").html("地址不能为空！");
				return;
			}
			// 提交表单
			$("#regForm").submit();
		});
	</script>
</html>