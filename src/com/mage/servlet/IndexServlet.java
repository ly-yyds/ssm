package com.mage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mage.po.BigType;
import com.mage.po.News;
import com.mage.po.Notice;
import com.mage.po.Product;
import com.mage.po.Tag;
import com.mage.service.BigTypeService;
import com.mage.service.NewsService;
import com.mage.service.NoticeService;
import com.mage.service.ProductService;
import com.mage.service.TagService;
import com.mage.service.impl.BigTypeServiceImpl;
import com.mage.service.impl.NewsServiceImpl;
import com.mage.service.impl.NoticeServiceImpl;
import com.mage.service.impl.ProductServiceImpl;
import com.mage.service.impl.TagServiceImpl;

/**
 * 首页模块
 * 	1、导航栏
 * 	2、标签栏
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BigTypeService bigTypeService = new BigTypeServiceImpl();
	private TagService tagService = new TagServiceImpl();
	private ProductService productService = new ProductServiceImpl();
	private NoticeService noticeService = new NoticeServiceImpl();
	private NewsService newsService = new NewsServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用service查大类别集合
		List<BigType> bigTypeList = bigTypeService.findAll();
		// 调用service查标签集合
		List<Tag> tagList = tagService.findAll();
		// 调用service查特价商品集合
		List<Product> specialProductList = productService.findSpecialProduct();
		// 调用service查公告集合
		List<Notice> noticeList = noticeService.findAll();
		// 调用service查新闻集合
		List<News> newsList = newsService.findAll();
		// 调用service查热卖商品集合
		List<Product> hotProductList = productService.findhotProduct();
		// 存session域对象中
		HttpSession session = request.getSession();
		session.setAttribute("bigTypeList", bigTypeList);
		session.setAttribute("tagList", tagList);
		session.setAttribute("specialProductList", specialProductList);
		session.setAttribute("noticeList", noticeList);
		session.setAttribute("newsList", newsList);
		session.setAttribute("hotProductList", hotProductList);
		// 请求转发跳转index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
