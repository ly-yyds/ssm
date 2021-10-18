<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<title>易买网 - 留言</title>
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
				<div class="guestbook">
					<h2>全部留言</h2>
					<ul>
						<c:forEach items="${commentList}" var="comment">
							<li>
								<dl>
									<dt>${comment.content}</dt>
									<dd class="author">
										网友：${comment.nickName }
										<span class="timer">
											<fmt:formatDate value="${comment.createTime}" type="date" pattern="yyyy-MM-dd"/>
										</span>
									</dd>
									<c:if test="${not empty comment.replyContent }">
										<dd>
											官方回复：${comment.replyContent }&nbsp;&nbsp;
											<span class="timer">
												<fmt:formatDate value="${comment.replyTime }" type="date" pattern="yyyy-MM-dd"/>
											</span>
										</dd>
									</c:if>
								</dl>
							</li><br/>
						</c:forEach>
					</ul>
					<div class="clear"></div>
					<div class="pager">
						<ul class="clearfix">${pageCode }</ul>
					</div>
					<!-- 写留言 -->
					<div id="reply-box">
						<form id="comFrom" action="comment" method="post">
							<input type="hidden" name="action" value="addComment"/>
							<table>
								<tr>
									<td class="field">昵称：</td>
									<td><input type="text"  id="nickName" name="nickName" value="${comment.nickName }"/></td>
								</tr>
								<tr>
									<td class="field">留言内容：</td>
									<td><textarea id="content" name="content">${comment.content }</textarea></td>
								</tr>
								<tr>
									<td class="field"></td>
									<td><label class="ui-blue">
									 	<input type="button" id="comBtn" value="提交留言"/></label>
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	<font id="error" color="red">${error}</font>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
	<script type="text/javascript">
		$("#comBtn").click(function(){
			// 接收参数
			var content = $("#content").val();
			var nickName = $("#nickName").val();
			if(nickName.trim() == ""){
				$("#error").html("昵称不能为空！")
				return;
			}
			if(content.trim() == ""){
				$("#error").html("留言不能为空！")
				return;
			}
			$("#comFrom").submit();
		});
	</script>
</html>