<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
	<title>易买网 - 首页</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="main" class="wrap">
			<div class="lefter">
				<jsp:include page="common/left.jsp" />
			</div>
			<div class="main">
				<div class="price-off">
					<h2>今日特价</h2>
					<ul class="product clearfix">
						<c:forEach items="${specialProductList }" var="specialProduct" end="7">
							<li>
								<dl>
									<dt>
										<a href="product?id=${specialProduct.id }" target="_blank"><img src="${specialProduct.proPic }" /></a>
									</dt>
									<dd class="title">
										<a href="product?id=${specialProduct.id }" target="_blank">${specialProduct.name }</a>
									</dd>
									<dd class="price">￥${specialProduct.price }</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="side">
					<div class="news-list">
						<h4>最新公告</h4>
						<ul>
							<c:forEach items="${noticeList }" var="notice" end="6">
								<li>
									<a href="notice?id=${notice.id }">${notice.title }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="spacer"></div>
					<div class="news-list">
						<h4>新闻动态</h4>
						<ul>
							<c:forEach items="${newsList }" var="news" end="6">
								<li>
									<a href="news?id=${news.id }">${news.title }</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="spacer clear"></div>
				<div class="hot">
					<h2>热卖推荐</h2>
					<ul class="product clearfix">
						<c:forEach items="${hotProductList }" var="hotProduct" end="5">
							<li>
								<dl>
									<dt>
										<a href="product?id=${hotProduct.id }" target="_blank"><img src="${hotProduct.proPic}" /></a>
									</dt>
									<dd class="title">
										<a href="product?id=${hotProduct.id }" target="_blank">${hotProduct.name}</a>
									</dd>
									<dd class="price">￥${hotProduct.price }</dd>
								</dl>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
</html>