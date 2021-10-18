package com.mage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.News;
import com.mage.service.NewsService;
import com.mage.service.impl.NewsServiceImpl;
import com.mage.util.StringUtil;

/**
 * 新闻
 */
@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NewsService newsService = new NewsServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数 id
		String id = request.getParameter("id");
		//非空判断
		if(StringUtil.isEmpty(id)){
			//	空，return
			return;
		}
		//调用newsService查询新闻信息news
		News news = newsService.findNewsById(Integer.parseInt(id));
		//存作用域（notice、包含页、pageName）
		request.setAttribute("pageName", "新闻");
		request.setAttribute("news", news);
		request.setAttribute("changePage", "news/news.jsp");
		//请求转发跳转公告详情页面
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

}
