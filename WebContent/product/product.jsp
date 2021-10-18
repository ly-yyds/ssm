<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="product"  class="main">
	<h1>${product.name }</h1>
	<div class="infos">
		<div class="thumb">
			<img class="img" src="${product.proPic }" />
		</div>
		<div class="buy">
			<br/>
			<p>
				商城价：<span class="price">￥${product.price }</span>
			</p>
			<p>库 存：${product.stock }</p>
			<br/>
			<div class="button">
				<input type="button" name="button" value="" onclick="buy(${product.id });"/><br/>
				<a href="javascript:addShoppingCart(${product.id })">放入购物车</a>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="introduce">
		<h2>
			<strong>商品详情</strong>
		</h2>
		<div class="text">
			${product.description }
		</div>
	</div>
</div>
<script type="text/javascript">
	function addShoppingCart(productId){
		if('${user.userName }' == ''){
			// 没登录
			alert("要想购物请先登录！")
		}else{
			$.ajax({
				type:"post",
				url:"shopping",
				data:{
					"action":"addShoppingCart",
					"productId":productId
				},
				success:function(result){
					// 判断是否添加成功
					if(result == 0){
						alert("添加失败");
					}else{
						window.location.reload();
					}
				}
			});
		}
	}
	function buy(productId){
		if('${user.userName }' == ''){
			// 没登录
			alert("要想购物请先登录！")
		}else{
			$.ajax({
				type:"post",
				url:"shopping",
				data:{
					"action":"buy",
					"productId":productId
				},
				success:function(result){
					// 判断是否添加成功
					if(result == 0){
						alert("网络异常，请检查您的网络！");
					}else{
						window.location.href="shopping.jsp";
					}
				}
			});
		}
	}
</script>