package com.mage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.Comment;
import com.mage.service.CommentService;
import com.mage.service.impl.CommentServiceImpl;
import com.mage.util.PageUtil;
import com.mage.util.StringUtil;

/**
 *  留言板
 *  1、查询当前页留言
 *  2、发表留言
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService commentService = new CommentServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if("findCommentList".equals(action)){
			// 查询当前页留言
			findCommentListByPage(request,response);
		}else if("addComment".equals(action)){
			// 发表留言
			addComment(request,response);
		}
	}

	/**
	 * 发表留言
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收参数
		String nickName = request.getParameter("nickName");
		String content = request.getParameter("content");
		// 创建对象存储数据
		Comment comment = new Comment(content, nickName);
		// 非空判断
		if(StringUtil.isEmpty(nickName)){
			request.setAttribute("error", "昵称不能为空！");
			request.setAttribute("comment", comment);
			findCommentListByPage(request,response);
			return;
		}
		if(StringUtil.isEmpty(content)){
			request.setAttribute("error", "留言不能为空！");
			request.setAttribute("comment", comment);
			findCommentListByPage(request,response);
			return;
		}
		// 调用service添加留言
		int row = commentService.addComment(comment);
		// 判断是否添加成功
		if(row <= 0){
			request.setAttribute("error", "发表留言失败！");
			request.setAttribute("comment", comment);
			findCommentListByPage(request,response);
			return;
		}
		findCommentListByPage(request,response);
	}

	/**
	 * 查询当前页留言
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findCommentListByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取当前页参数 currentPage
		String pageStr = request.getParameter("page");
		//非空判断
		if(StringUtil.isEmpty(pageStr)){
			//	空，给个默认值
			pageStr = "1";
		}
		Integer page = Integer.parseInt(pageStr);
		//调用service查询当前页集合（当前页，每页数量）
		List<Comment> list = commentService.findCommentListByPage(page,4);
		//sql：select * from t_comment order by createTime desc limit (当前页-1)*4,每页数量4;
		//查询总数
		int total = commentService.findCommentAmount();
		//使用PageUtil生成分页代码（comment?action=findCommentList，留言总数，currentPage，4）
		String pageCode = PageUtil.getPageCode("comment?action=findCommentList", total, page, 4);
		//存作用域（当前页留言集合、分页代码）
		request.setAttribute("commentList", list);
		request.setAttribute("pageCode", pageCode);
		//请求转发跳转comment.jsp
		request.getRequestDispatcher("comment.jsp").forward(request, response);
	}

}
