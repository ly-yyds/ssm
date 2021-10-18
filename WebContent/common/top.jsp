<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
	</head>
	<body>
		<div id="logo">
			<img src="images/logo.gif" />
		</div>
		<div class="help">
			<!-- 用户没有登录 -->
			<c:if test="${empty user }">
				<a href="javascript:checkLogin();" class="shopping">购物车</a>
				<a href="login.jsp">登录</a>
				<a href="register.jsp">注册</a>
				<a href="javascript:checkLogin();">留言板</a>	
			</c:if>
			<!-- 用户已登录 -->
			<c:if test="${!empty user }">
				<a href="shopping.jsp" class="shopping">购物车(${shoppingCart.shoppingCartItems == null ? 0 : shoppingCart.shoppingCartItems.size()})件商品</a>
				<a href="user?action=userCenter">${user.userName }</a>
				<a href="javascript:logout();">注销</a>
				<a href="register.jsp">注册</a>
				<a href="comment?action=findCommentList">留言板</a>	
			</c:if>
			<form action="product?action=search" method="post">
				<input type="text" name="keyword" value="" />
				<input type="submit" value="搜索" /><br />
				<div id="suggest" style="width: 200px"></div>
			</form>
		</div>
	
		<!-- 导航栏 -->
		<div class="navbar">
			<ul class="clearfix">
				<li class="current"><a href="index">首页</a></li>
				<c:forEach items="${bigTypeList }" var="bigType">
					<li>
						<a href="product?action=findProductListByBigType&bigTypeId=${bigType.id }">${bigType.name }</a>
					</li> 
				</c:forEach>
			</ul>
		</div>

		<!-- 标签栏 -->
		<div id="childNav">
			<div class="wrap">
				<ul class="clearfix">
					<c:forEach items="${tagList }" var="tag" varStatus="status">
						<c:choose>
							<c:when test="${status.first==true }">
								<li class="first">
									<a href="" target="_blank">${tag.name }</a>
								</li>
							</c:when>
							<c:when test="${status.last==true }">
								<li class="last">
									<a href="" target="_blank">${tag.name }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="" target="_blank">${tag.name }</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		function checkLogin(){
			alert("请先登录！");
		}
		function logout(){
			if(confirm("您确认要注销吗？")){
				// 点确认时执行
				window.location.href="user?action=logout";
			}
		}
	</script>
</html>