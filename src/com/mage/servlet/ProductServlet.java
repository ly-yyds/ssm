package com.mage.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.Product;
import com.mage.service.ProductService;
import com.mage.service.impl.ProductServiceImpl;
import com.mage.util.PageUtil;
import com.mage.util.StringUtil;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService = new ProductServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断用户行为
		String action = request.getParameter("action");
		if("findProductListBySmallType".equals(action)){
			// 小类别搜索
			findProductListBySmallType(request,response);
		}else if("findProductListByBigType".equals(action)){
			// 大类别搜索
			findProductListByBigType(request,response);
		}else if("search".equals(action)){
			// 关键字搜索
			findProductListByName(request,response);
		}else{
			// 商品详情
			findProductById(request,response);
		}
	}

	/**
	 * 大类别搜索
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findProductListByBigType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String bigTypeIdStr = request.getParameter("bigTypeId");
		String pageStr = request.getParameter("page");
		//非空判断
		if(StringUtil.isEmpty(bigTypeIdStr)){
			//	空，return
			return;
		}
		if(StringUtil.isEmpty(pageStr)){
			pageStr = "1";
		}
		Integer bigTypeId = Integer.parseInt(bigTypeIdStr);
		Integer page = Integer.parseInt(pageStr);
		//调用service通过当前页、每页数量、smallTypeId查询商品集合
		List<Product> list = productService.findProductListByBigType(page,8,bigTypeId);
		//调用service通过id查询商品总数 
		int total = productService.findProductAmountByBigType(bigTypeId);
		//使用PageUtil生成分页代码
		String pageCode = PageUtil.getPageCode("product?action=findProductListByBigType&bigTypeId="+bigTypeIdStr, total, page, 8);
		//存作用域（商品集合，分页代码，pageName）
		request.setAttribute("productList", list);
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("pageName", "商品列表");
		//请求转发跳转productList.jsp
		request.getRequestDispatcher("productList.jsp").forward(request, response);
	}

	/**
	 * 关键字搜索
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findProductListByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String keyword = request.getParameter("keyword");
		String pageStr = request.getParameter("page");
		//非空判断
		if(StringUtil.isEmpty(pageStr)){
			pageStr = "1";
		}
		Integer page = Integer.parseInt(pageStr);
		//调用service通过当前页、每页数量、关键字查询商品集合
		List<Product> list = productService.findProductListByName(page,8,keyword);
		//调用service通过关键字查询商品总数 
		int total = productService.findProductAmountByName(keyword);
		//	使用PageUtil生成分页代码
		String pageCode = PageUtil.getPageCode("product?action=search&keyword="+keyword, total, page, 8);
		//存作用域（商品集合，分页代码，pageName）
		request.setAttribute("productList", list);
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("pageName", "商品搜索");
		//请求转发跳转productList.jsp
		request.getRequestDispatcher("productList.jsp").forward(request, response);
	}

	/**
	 * 小类别搜索
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findProductListBySmallType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String smallTypeIdStr = request.getParameter("smallTypeId");
		String pageStr = request.getParameter("page");
		//非空判断
		if(StringUtil.isEmpty(smallTypeIdStr)){
			//	空，return
			return;
		}
		if(StringUtil.isEmpty(pageStr)){
			pageStr = "1";
		}
		Integer smallTypeId = Integer.parseInt(smallTypeIdStr);
		Integer page = Integer.parseInt(pageStr);
		//调用service通过当前页、每页数量、smallTypeId查询商品集合
		List<Product> list = productService.findProductListBySmallType(page,8,smallTypeId);
		//调用service通过id查询商品总数 
		int total = productService.findProductAmountBySmallType(smallTypeId);
		//使用PageUtil生成分页代码
		String pageCode = PageUtil.getPageCode("product?action=findProductListBySmallType&smallTypeId="+smallTypeIdStr, total, page, 8);
		//存作用域（商品集合，分页代码，pageName）
		request.setAttribute("productList", list);
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("pageName", "商品列表");
		//请求转发跳转productList.jsp
		request.getRequestDispatcher("productList.jsp").forward(request, response);
	}

	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数
		String id = request.getParameter("id");
		//非空判断
		if(StringUtil.isEmpty(id)){
			//	空，return
			return;
		}
		//调用service通过id查询商品对象 Product
		Product product = productService.findProductById(Integer.parseInt(id));
		
		
		// 最近浏览功能
		// 获取session中的最近浏览商品集合
		List<Product> recentProductList = (List<Product>) request.getSession().getAttribute("recentProductList");
		if(recentProductList == null){
			recentProductList = new LinkedList<Product>();
		}
		// 来个标记
		boolean flag = true;
		// 循环遍历最近浏览商品集合，判断是不是浏览同一个商品
		for (Product recentProduct : recentProductList) {
			// 是同一个商品
			if(Integer.parseInt(id) == recentProduct.getId()){
				// 修改标记状态，说明是同一个商品的情况
				flag = false;
				// 先移除
				recentProductList.remove(recentProduct);
				// 再添加，放到最前面
				recentProductList.add(0,recentProduct);
				// 跳出循环
				break;
			}
		}
		// 如果不是前面的情况再进行别的操作
		if(flag){
			if(recentProductList.size()==4){
				// 移除最后一个
				recentProductList.remove(3);
				// 添加新的到最前面
				recentProductList.add(0,product);
			}else{
				// 添加新的到最前面
				recentProductList.add(0,product);
			}
		}
		// 存session，覆盖最近浏览商品
		request.getSession().setAttribute("recentProductList", recentProductList);
		
		
		//存作用域（Product、包含页、pageName）
		request.setAttribute("product", product);
		request.setAttribute("changePage", "product/product.jsp");
		request.setAttribute("pageName", "商品");
		//跳转details.jsp
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
