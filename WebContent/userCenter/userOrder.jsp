<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="main">
	<h2>订单管理</h2>
	<div class="manage">
	<div class="spacer"></div>
	<div class="search">
		<form action="order?action=searchOrder" method="post">
			订单查询：&nbsp;&nbsp;<input  type="text" id="textSearch" name="orderNo" value="${msg }" autocomplete="off"/>
			<label class="ui-blue"><input  type="submit" name="submit" value="查询"/></label>
		</form>
	</div>
		<table class="list">
			<c:forEach items="${orderList }" var="order">
				<tr style="background-color:#F7F4EB">
					<td colspan="4">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						单号:${order.orderNo }
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						下单时间:<fmt:formatDate value="${order.createTime }" type="date" pattern="yyyy-MM-dd"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						状态:<!-- 1待审核 2待发货 3确认收货 4交易完毕 -->
						<c:choose>
							<c:when test="${order.status == 1 }">
								待审核
							</c:when>
							<c:when test="${order.status == 2 }">
								待发货
							</c:when>
							<c:when test="${order.status == 3 }">
								<input type="button" class="ui-blue" value="确认收货" onclick="confirmReceive(${order.id})"/>
							</c:when>
							<c:when test="${order.status == 4 }">
								交易完毕
							</c:when>
						</c:choose>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						金额:${order.cost }&nbsp; (元)
					</td>
				</tr>	
				<c:forEach items="${order.orderProductList }" var="orderProduct">
					<tr>
						<td width="55%">
							<a href="product?id=${orderProduct.product.id }">
								<img src="${orderProduct.product.proPic }" width="70" height="70" />
							</a>
							&nbsp;&nbsp;&nbsp;
							<a href="product?id=${orderProduct.product.id }">
								 ${orderProduct.product.name }
							</a>
						</td>
						<td width="15%">
							&nbsp;&nbsp;&nbsp;
							${orderProduct.product.price }(元)
						</td>
						<td width="15%">
							&nbsp;&nbsp;&nbsp;
							${orderProduct.num }
						</td>
						<td width="15%">
							&nbsp;&nbsp;&nbsp;小计:
							${orderProduct.product.price * orderProduct.num }&nbsp;(元)
						</td>
					</tr>
				</c:forEach>				  
			</c:forEach>
		</table>
	</div>
</div>
<script type="text/javascript">
	function confirmReceive(orderId){
		if(confirm("确定收货？")){
			$.ajax({
			   type: "POST",
			   url: "order",
			   data: {
				   "action":"confirmReceive",
				   "orderId":orderId
			   },
			   success: function(result){
			   		if(result == 0){
			   			alert("网络异常");
			   		}else{
			   			window.location.reload();
			   		}
			   }
			});
		}
	}
</script>
</html>