package com.mage.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.Product;
import com.mage.po.ShoppingCart;
import com.mage.po.ShoppingCartItem;
import com.mage.po.User;
import com.mage.service.ProductService;
import com.mage.service.impl.ProductServiceImpl;
import com.mage.util.StringUtil;

/**
 * 购物车
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/shopping" })
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 判断用户行为
		String action = request.getParameter("action");
		if("addShoppingCart".equals(action)){
			// 添加购物车
			addShoppingCart(request,response);
			return;
		}else if("buy".equals(action)){
			// 购买
			buy(request,response);
			return;
		}else if("updateShoppingCart".equals(action)){
			// 当用户点击加和减的时候更新购物车
			updateShoppingCart(request,response);
			return;
		}else if("deleteShoppingCart".equals(action)){
			// 删除购物车商品
			deleteShoppingCart(request,response);
			return;
		}
	}

	/**
	 * 删除购物车商品
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void deleteShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收参数
		String productIdStr = request.getParameter("productId");
		//非空判断
		if(StringUtil.isEmpty(productIdStr)){
			//	空，响应0
			response.getWriter().write(0);
			return;
		}
		Integer productId = Integer.parseInt(productIdStr);
		//获取session中的购物车对象  ShoppingCart
		ShoppingCart shoppingCart =  (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		//获取ShoppingCartItems
		List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
		//循环遍历ShoppingCartItems
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			//	判断是否是同一个商品
			if(shoppingCartItem.getProduct().getId() == productId){
				//		移除该商品
				shoppingCartItems.remove(shoppingCartItem);
				//		break;
				break;
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItems);
		//覆盖session中的ShoppingCart
		request.getSession().setAttribute("shoppingCart",shoppingCart);
		//响应1
		response.getWriter().write(1);
	}

	/**
	 * 当用户点击加和减的时候更新购物车
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void updateShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收参数
		String productIdStr = request.getParameter("productId");
		String countStr = request.getParameter("count");
		//非空判断
		if(StringUtil.isEmpty(productIdStr)){
			//	空，响应0
			response.getWriter().write(0);
			return;
		}
		Integer productId = Integer.parseInt(productIdStr);
		Integer count = Integer.parseInt(countStr);
		//获取session中的购物车对象  ShoppingCart
		ShoppingCart shoppingCart =  (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		//获取ShoppingCartItems
		List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
		//循环遍历ShoppingCartItems
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			//	判断是否是同一个商品
			if(shoppingCartItem.getProduct().getId() == productId){
				//		是，count+1
				shoppingCartItem.setCount(count);
				//		break;
				break;
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItems);
		//覆盖session中的ShoppingCart
		request.getSession().setAttribute("shoppingCart",shoppingCart);
		//响应1
		response.getWriter().write(1);
	}

	/**
	 * 购买
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void buy(HttpServletRequest request, HttpServletResponse response) throws IOException {
		addShoppingCart(request,response);
	}

	/**
	 * 添加购物车
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void addShoppingCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收参数productId
		String productIdStr = request.getParameter("productId");
		//非空判断
		if(StringUtil.isEmpty(productIdStr)){
			//	空，响应0
			response.getWriter().write(0);
			return;
		}
		Integer productId = Integer.parseInt(productIdStr);
		//获取session中的购物车对象  ShoppingCart（新建，userId、ShoppingCartItems   新建ShoppingCartItem，Product，count）
		ShoppingCart shoppingCart =  (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		//购物车对象非空判断
		if(shoppingCart == null){
			//	空，new ShoppingCat，设置userId
			shoppingCart = new ShoppingCart();
			User user = (User) request.getSession().getAttribute("user");
			shoppingCart.setUserId(user.getId());
		}
		//获取ShoppingCartItems
		List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
		// 判断是否为空
		if(shoppingCartItems == null){
			shoppingCartItems = new ArrayList<ShoppingCartItem>();
		}
		//boolean flag = false
		boolean flag = false;
		//循环遍历ShoppingCartItems
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			//	判断是否是同一个商品
			if(shoppingCartItem.getProduct().getId() == productId){
				//		是，count+1
				shoppingCartItem.setCount(shoppingCartItem.getCount() + 1);
				//		flag = true
				flag = true;
				//		break;
				break;
			}
		}
		//通过标记判断不是同一个商品 if(!flage)
		if(!flag){
			Product product = productService.findProductById(productId);
			//	不是，new ShoppingCartItem 设置Product，count
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 1);
			//	添加到ShoppingCartItems里
			shoppingCartItems.add(shoppingCartItem);
		}
		shoppingCart.setShoppingCartItems(shoppingCartItems);
		//覆盖session中的ShoppingCart
		request.getSession().setAttribute("shoppingCart",shoppingCart);
		//响应1
		response.getWriter().write(1);
	}

}
