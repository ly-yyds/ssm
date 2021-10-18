<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<title>易买网 - ${pageName }</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="position" class="wrap">
			<!-- 文字的导航 -->
			您现在的位置：<a href="index">首页</a>&nbsp;&gt;&nbsp;${pageName }详情
		</div>
		<div id="main" class="wrap">
			<div class="lefter">
				<jsp:include page="common/left.jsp" />
			</div>

			<!-- 动态包含页面（公告详情、新闻详情、商品详情） -->
			<jsp:include page="${changePage }"></jsp:include>

			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
</html>