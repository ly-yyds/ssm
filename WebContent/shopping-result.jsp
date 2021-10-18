<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
	<title>易买网 - 购物</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="position" class="wrap">
			<!-- 文字的导航 -->
			您现在的位置：<a href="index">首页</a>&nbsp;&gt;&nbsp;购物
		</div>
		<div id="main" class="wrap">
		
			<div class="wrap">
				<div id="shopping">
					<div class="shadow">
						<em class="corner lb"></em>
						<em class="corner rt"></em>
						<div class="box">
						 	<div class="msg"> 
						 		<p>恭喜:购买成功~！</p>
						 		<p>正在进入首页...</p>
						 		<script type="text/javascript">
						 			setTimeout("location.href='index.jsp'",3000);
						 		</script>
						 	</div>
						</div>
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