<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<title>易买网 - 注册</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="register" class="wrap">
			<div class="shadow">
				<em class="corner lb"></em>
				<em class="corner rt"></em>
				<div class="box">
					<h1>欢迎注册易买网</h1>
					<ul class="steps clearfix">
						<li class="finished"><em></em>填写注册信息</li>
						<li class="last-current"><em></em>注册成功</li>
					</ul>
					<div class="msg">
						<p>恭喜：注册成功！</p>
						<p>正在跳转登录页面...</p>
						<script type="text/javascript">
							setTimeout("location.href='login.jsp'", 3000);
						</script>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
</html>