package com.mage.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.Notice;
import com.mage.service.NoticeService;
import com.mage.service.impl.NoticeServiceImpl;
import com.mage.util.StringUtil;

/**
 * 公告
 */
@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NoticeService noticeService = new NoticeServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收参数 id
		String id = request.getParameter("id");
		//非空判断
		if(StringUtil.isEmpty(id)){
			//	空，return
			return;
		}
		//调用noticeService查询公告信息notice
		Notice notice = noticeService.findNoticeById(Integer.parseInt(id));
		//存作用域（notice、包含页、pageName）
		request.setAttribute("pageName", "公告");
		request.setAttribute("notice", notice);
		request.setAttribute("changePage", "notice/notice.jsp");
		//请求转发跳转公告详情页面
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}

}
