<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<c:forEach items="${bigTypeList }" var="bigType">
					<dt>${bigType.name }</dt>
					<!-- 拿到大类别下所有的子类别 -->
					<c:forEach items="${bigType.smallTypeList }" var="smallType">
						<dd><a href="product?action=findProductListBySmallType&smallTypeId=${smallType.id }">${smallType.name }</a></dd>
					</c:forEach>
				</c:forEach>
			</dl>
		</div>
		
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<c:forEach items="${recentProductList }" var="recentProduct">
				 	<dt>
				 		<a href="product?id=${recentProduct.id }" target="_blank"><img src="${recentProduct.proPic }" class="imgs" style="height: 50px;width: 50px;"></a>
				 	</dt>
				 	<dd>
				 		<a href="product?id=${recentProduct.id }" target="_blank">${recentProduct.name }</a>
				 	</dd>
				</c:forEach>
			</dl>
		</div>
	</body>
</html>