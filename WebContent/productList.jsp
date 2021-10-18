<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
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
				<jsp:include page="common/left.jsp" />
			</div>

			<div class="main">
				<div class="product-list">
					<ul class="product clearfix">
						<c:forEach items="${productList }" var="product">
							<li>
								<dl>
									<dt>
										<a href="product?id=${product.id }" ><img src="${product.proPic }"/></a>
									</dt>
									<dd class="title">
										<a href="product?id=${product.id }" >${product.name }</a>
									</dd>
									<dd class="price">
										￥${product.price }
									</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
					<div class="pager">
						<ul class="clearfix">${pageCode}</ul>
					</div>
				</div>
			</div>

			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
</html>