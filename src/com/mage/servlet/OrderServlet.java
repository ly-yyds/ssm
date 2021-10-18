package com.mage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mage.po.Order;
import com.mage.po.OrderProduct;
import com.mage.po.Product;
import com.mage.po.ShoppingCart;
import com.mage.po.ShoppingCartItem;
import com.mage.po.User;
import com.mage.service.OrderProductService;
import com.mage.service.OrderService;
import com.mage.service.impl.OrderProductServiceImpl;
import com.mage.service.impl.OrderServiceImpl;
import com.mage.util.StringUtil;

/**
 * 订单
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderServiceImpl();
	private OrderProductService orderProductService = new OrderProductServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("userOrder".equals(action)){
			// 进入个人订单页面
			userOrder(request,response);
		}else if("searchOrder".equals(action)){
			// 查询订单
			searchOrder(request,response);
		}else if("confirmReceive".equals(action)){
			// 确认收货
			confirmReceive(request,response);
		}else if("confirmReceive".equals(action)){
			// 确认收货
			confirmReceive(request,response);
		}else if("saveOrder".equals(action)){
			// 订单结算
			saveOrder(request,response);
		}
	}

	/**
	 * 订单结算
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过session获取shoppingCart
		HttpSession session = request.getSession();
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		//创建Order，设置userId、status、orderNo、cost
		Order order = new Order();
		order.setUserId(shoppingCart.getUserId());
		order.setStatus(1);
		order.setOrderNo(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
		//手动获取总价cost
		//得到shoppingCartItems
		List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
		//float cost
		float cost = 0;
		//new OrderProductList
		List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
		//循环遍历，
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			//	设置OrderProduct  productId num
			OrderProduct orderProduct = new OrderProduct();
			Integer count = shoppingCartItem.getCount();
			orderProduct.setNum(count);
			Product product = shoppingCartItem.getProduct();
			Integer price = product.getPrice();
			orderProduct.setProductId(product.getId());
			orderProductList.add(orderProduct);
			//	拿到每一个商品单元的商品数量以及商品单价，商品数量*单价
			cost += count*price;
		}
		order.setCost(cost);
		//调用service添加订单（Order），返回orderId
		Integer orderId = orderService.saveOrder(order);
		//调用service添加订单中间表（OrderProductList循环遍历添加）
		for (OrderProduct orderProduct : orderProductList) {
			orderProduct.setOrderId(orderId);
			orderProductService.saveOrderProduct(orderProduct);
		}
		//移除session中的购物车数据
		session.removeAttribute("shoppingCart");
		//跳转结算结果页面
		request.getRequestDispatcher("shopping-result.jsp").forward(request, response);
	}

	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void confirmReceive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收参数
		String orderId = request.getParameter("orderId");
		if(StringUtil.isEmpty(orderId)){
			response.getWriter().write("0");
			return;
		}
		// 调用service通过id修改status
		int row = orderService.confirmReceive(Integer.parseInt(orderId));
		// 判断是否成功
		if(row <= 0){
			// 失败
			response.getWriter().write("0");
			return;
		}
		response.getWriter().write("1");
	}

	/**
	 * 查询订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void searchOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String orderNo = request.getParameter("orderNo");
		//非空判断
		if(StringUtil.isEmpty(orderNo)){
			//	空，查询所有
			userOrder(request,response);
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		Integer userId = user.getId();
		//调用service通过关键字查询订单集合
		List<Order> list = orderService.findOrderListByOrderNo(orderNo,userId);
		if(list.isEmpty()){
			request.setAttribute("msg", "暂未查询到记录");
		}
		request.setAttribute("orderList", list);
		request.setAttribute("changePage", "userCenter/userOrder.jsp");
		request.setAttribute("pageName", "个人中心");
		//请求转发跳转userCenter.jsp
		request.getRequestDispatcher("userCenter.jsp").forward(request, response);
	}

	private void userOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从session中获取用户id
		User user = (User) request.getSession().getAttribute("user");
		Integer userId = user.getId();
		//调用service通过用户id查询订单集合
		//（查询到订单集合时遍历通过订单id查询orderProductList
		//查询到orderProductList时遍历通过productId查询Product）
		List<Order> orderList = orderService.findOrderListByUserId(userId);
		//存作用域（订单集合、changePage、pageName）
		request.setAttribute("orderList", orderList);
		request.setAttribute("changePage", "userCenter/userOrder.jsp");
		request.setAttribute("pageName", "个人中心");
		//请求转发跳转userCenter.jsp
		request.getRequestDispatcher("userCenter.jsp").forward(request, response);
	}

}
