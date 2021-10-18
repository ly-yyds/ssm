<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<title>易买网 - 购物车</title>
	</head>
	<body>
		<!-- 头部 -->
		<div id="header" class="wrap">
			<jsp:include page="common/top.jsp" /> 
		</div>
		<div id="position" class="wrap">
			<!-- 文字的导航 -->
			您现在的位置：<a href="index">首页</a>&nbsp;&gt;&nbsp;购物车
		</div>
		<div id="main" class="wrap">
			<div id="shopping">
				<form action="order?action=saveOrder" method="post">
					<table>
						<tr>
							<th>商品名称</th>
							<th>商品单价</th>
							<th>金额</th>
							<th>购买数量</th>
							<th>操作</th>
						</tr>
						<!-- 展示已添加的商品信息 -->
						<c:forEach items="${shoppingCart.shoppingCartItems }" var="shoppingCartItem">
							<tr class="productTr">
								<td class="thumb"><!-- 产品图片与名称 -->
									<img src="${shoppingCartItem.product.proPic }" class="imgs"/>
									<a href="product?id=${shoppingCartItem.product.id }" >${shoppingCartItem.product.name }</a>
								</td>
								<td class="price"><!-- 单价 -->
									<span>￥<label class="price_one" id="price_${shoppingCartItem.product.id }">${shoppingCartItem.product.price }</label></span>						
								</td>
								<td class="price"><!-- 总价= 单价* 个数 -->
									<span>￥<label id="productItem_total_${shoppingCartItem.product.id }">${shoppingCartItem.product.price * shoppingCartItem.count }</label></span>					
								</td>
								<td class="number"><!--数量   -->
									<input type="hidden" id="productId" value="${shoppingCartItem.product.id }"/>
									<input type="button" value="&nbsp;&nbsp;-&nbsp;&nbsp;" class="min"/>
									<input type="text" id="count" value="${shoppingCartItem.count }" class="text_box" style="width:30px;text-align:center"/>
									<input type="button" value="&nbsp;&nbsp;+&nbsp;&nbsp;" class="add" />
								</td>
								<td class="delete"><!-- 删除 -->
									<a href="javascript:removeShopping(${shoppingCartItem.product.id })">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="button">
						<input type="submit"  value=""/>
					</div>
				</form>
			</div>
			
			<!-- 订单的内容显示 -->
			<div class="shopping_list_end">
				<ul>
					<li class="shopping_list_end_2">
						￥<label id="product_total"></label>
					</li>
					<li class="shopping_list_end_3">商品金额总计 : </li>
				</ul>
			</div>
			
			<!-- 个人信息的显示 -->
			<div style="background-color:#CDDBB8;margin-top:10px;text-align:center;font-size:20px;">
				<div style="padding:7px">
					<b>个人信息:</b>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>收件人:</b>${user.userName}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>收货地址:</b>${user.address}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<b>联系电话:</b>${user.mobile}
				</div>
			</div>
			
			<div class="clear"></div>
		</div>
		<div id="footer">
			<jsp:include page="common/footer.jsp" />
		</div>
	</body>
	<script type="text/javascript">
		$(function(){//自动加载
			
			setTotal();//每次刷新页面都会调用此方法
		
			function setTotal(){//商品的总价
				var sub = 0;
				$(".productTr").each(function(){
					//获取商品的数量
					var number = $(this).find('input[class=text_box]').val();
					//获取商品的价钱
					var price = $(this).find('label[class=price_one]').html();
					//计算   数量* 价钱 =总价钱
					sub += number * price;
				});
				//把总数显示到 总计处
				$("#product_total").html(sub);
			}
		
			//对 " + "按钮进行操作
			$(".add").click(function(){
				//数量+1
				var t= $(this).parent().find('input[class=text_box]');
				t.val(parseInt(t.val()) + 1);
				//获取td隐藏域的id的值
				var product_id = $(this).parent().find('input[id=productId]').val();
				//获取对应ID的单价
				var price = $("#price_" + product_id).html();
				//修改该商品总价
				$("#productItem_total_" + product_id).html(price * t.val());
				//重新计算全部商品的总价
				setTotal();
				// ajax访问后台更新session
				updateShopping(1,t.val(),product_id);
			});
		
			//对 " - "按钮进行操作
			$(".min").click(function(){
				//数量-1
				var t= $(this).parent().find('input[class=text_box]');
				t.val(parseInt(t.val()) - 1);
				if(parseInt(t.val()) < 1){//不允许减少到1以下！
					t.val(1);
				}
				//获取td隐藏域的id的值
				var product_id = $(this).parent().find('input[id=productId]').val();
				//获取对应ID的单价
				var price = $("#price_" + product_id).html();
				//修改该商品总价
				$("#productItem_total_" + product_id).html(price * t.val());
				//重新计算全部商品的总价
				setTotal();
				if(parseInt(t.val()) > 1){
					updateShopping(0,t.val(),product_id);
				}
			});
		
		});
		
		function updateShopping(addOrCut,count,productId){
			$.ajax({
				type:"post",
				url:"shopping",
				data:{
					"action":"updateShoppingCart",
					"productId":productId,
					"count":count
				},
				success:function(result){
					// 判断是否更新成功
					if(result == 0){
						// 把数量变回去
						var t= $("#count");
						if(addOrCut == 1){
							t.val(parseInt(t.val()) - 1);
						}else{
							t.val(parseInt(t.val()) + 1);
						}
						alert("网络异常");
					}
				}
			});
		}
		
		function removeShopping(productId){
			if(confirm("您确定要删除这个商品吗？")){
				$.ajax({
					type:"post",
					url:"shopping",
					data:{
						"action":"deleteShoppingCart",
						"productId":productId
					},
					success:function(result){
						// 判断是否更新成功
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