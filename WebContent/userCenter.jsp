<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<title>易买网 - ${pageName }</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="position" class="wrap">
			<!-- 文字的导航 -->
			您现在的位置：<a href="index">首页</a>&nbsp;&gt;&nbsp;${pageName }
		</div>
		<div id="main" class="wrap">
			<div class="lefter">
				<div class="box">
					<dl>
						<dt>用户管理</dt>
						<dd><a href="user?action=userCenter">个人信息管理</a></dd>
						<dt>订单管理</dt>
						<dd><a href="order?action=userOrder">个人订单管理</a></dd>
					</dl>
				</div>
			</div>
			
			<!-- 动态包含页面 -->
			<jsp:include page="${changePage }"></jsp:include>
			
			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
</html>