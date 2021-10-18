package com.mage.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.po.User;
import com.mage.service.UserService;
import com.mage.service.impl.UserServiceImpl;
import com.mage.util.StringUtil;

/**
 * 用户模块
 * 1、用户登录
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置服务器默认编码   不设置  默认是  ISO-8859-1 不支持中文，会乱码，放在使用request对象之前
		request.setCharacterEncoding("UTF-8");
		// 判断用户行为
		String action = request.getParameter("action");
		if("login".equals(action)){
			// 登录
			login(request,response);
		}else if("register".equals(action)){
			// 注册
			try {
				register(request,response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("logout".equals(action)){
			// 注销
			logout(request,response);
		}else if("checkName".equals(action)){
			// 验证用户名是否唯一
			checkName(request,response);
		}else if("userCenter".equals(action)){
			// 个人中心
			userCenter(request,response);
		}else if("userEdit".equals(action)){
			// 个人信息修改页面
			userEdit(request,response);
		}else if("updateUser".equals(action)){
			// 个人信息修改
			try {
				updateUser(request,response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else{
			response.sendRedirect("index");
		}
	}

	/**
	 * 修改个人信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws ParseException 
	 */
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		// 接收参数
		String trueName = request.getParameter("trueName");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String birthdayStr = request.getParameter("birthday");
		Date birthday = null;
		if(StringUtil.isNotEmpty(birthdayStr)){
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
		}
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String dentityCode = request.getParameter("dentityCode");
		String email = request.getParameter("email");
		
		// 非空判断
		if(StringUtil.isEmpty(password)){
			request.setAttribute("error", "密码不能为空！");
			userEdit(request,response);
			return;
		}
		if(StringUtil.isEmpty(mobile)){
			request.setAttribute("error", "手机号不能为空！");
			userEdit(request,response);
			return;
		}
		if(StringUtil.isEmpty(address)){
			request.setAttribute("error", "地址不能为空！");
			userEdit(request,response);
			return;
		}
		User user = (User) request.getSession().getAttribute("user");
		Integer id = user.getId();
		// 创建对象存储数据
		User u = new User(id,trueName,password,sex,birthday,dentityCode,email,mobile,address);
		// 调用service执行更新，返回受影响行
		int row = userService.updateUser(u);
		if(row <= 0){
			request.setAttribute("error", "修改失败，请重试！");
			userEdit(request,response);
			return;
		}
		u.setUserName(user.getUserName());
		u.setStatus(user.getStatus());
		request.getSession().setAttribute("user", u);
		userCenter(request,response);
	}

	/**
	 * 个人信息修改页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageName", "修改当前用户");
		request.setAttribute("changePage", "userCenter/userEdit.jsp");
		request.getRequestDispatcher("userCenter.jsp").forward(request, response);
	}

	/**
	 * 个人中心
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void userCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pageName", "个人中心");
		request.setAttribute("changePage", "userCenter/userInfo.jsp");
		request.getRequestDispatcher("userCenter.jsp").forward(request, response);
	}

	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws ParseException 
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		//接收参数
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String rePassWord = request.getParameter("rePassWord");
		String sex = request.getParameter("sex");
		String birthdayStr = request.getParameter("birthday");
		// 将String转换成Date
		Date birthday = null;
		if(StringUtil.isNotEmpty(birthdayStr)){
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);			
		}
		String dentityCode = request.getParameter("dentityCode");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		//非空判断
		//空，跳转提示用户并回显
		
		
		
		//判断密码和确认密码是否一致
		if(!password.equals(rePassWord)){
			//不一致，跳转提示用户并回显
			request.setAttribute("msg", "确认密码和密码不一致！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		//判断用户名唯一性
		User u = userService.findUserByName(userName);
		//判断user是否为空
		if(u != null){
			//不唯一，跳转提示用户并回显
			request.setAttribute("msg", "用户名已存在！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		// 创建用户对象存储前台的数据
		User user = new User(userName,password,sex,birthday,dentityCode,email,mobile,address);
		//调用userService添加用户，得到受影响行数row
		int row = userService.addUser(user);
		if(row <= 0){
			//row <=0，失败，跳转提示用户并回显
			//不唯一，跳转提示用户并回显
			request.setAttribute("msg", "注册失败！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		//跳转注册成功页面
		request.getRequestDispatcher("reg-result.jsp").forward(request, response);
	}

	/**
	 * 验证用户名是否唯一
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void checkName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//接收参数
		String userName = request.getParameter("userName");
		//非空判断
		if(StringUtil.isEmpty(userName)){
			// 空，响应2
			response.getWriter().write("2");
			return;
		}
		//调用service通过用户名查询用户对象user
		User user = userService.findUserByName(userName);
		//判断user是否为空
		if(user != null){
			//非空，响应0
			response.getWriter().write("0");
			return;
		}
		response.getWriter().write("1");
	}

	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 清session  三种方法  invalidate();【session销毁】   清除指定：  setAttribute("user", null)  removeAttribute("user");
		request.getSession().removeAttribute("user");
		// 清cookie
		Cookie cookie = new Cookie("user",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		// 跳转登录页面
		response.sendRedirect("login.jsp");
	}

	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String imageCode = request.getParameter("imageCode");
		//创建回显对象
		User u = new User(userName,password);
		//非空判断
		if(StringUtil.isEmpty(userName)){
			//空，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "用户名不能为空！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(StringUtil.isEmpty(password)){
			//空，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "密码不能为空！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(StringUtil.isEmpty(imageCode)){
			//空，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "验证码不能为空！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//判断验证码是否正确
		//获取session(生成的)中的验证码
		String sRand = (String) request.getSession().getAttribute("sRand");
		if(!imageCode.equals(sRand)){
			//	不正确，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "验证码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//调用service通过用户名查询用户对象user
		User user = userService.findUserByName(userName);
		//判断user是否为空
		if(user == null){
			//	空，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "用户不存在！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//比较密码是否正确
		if(!password.equals(user.getPassword())){
			//	不正确，跳转登录页面并提示用户还要回显数据
			request.setAttribute("user", u);
			request.setAttribute("msg", "密码错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//把user存到session
		request.getSession().setAttribute("user", user);
		//把用户名和密码存cookie
		Cookie cookie = new Cookie("user",userName + "-" + password);
		//设置过期时间 单位 s
		cookie.setMaxAge(7*24*60*60);
		response.addCookie(cookie);
		//重定向跳转首页
		response.sendRedirect("index");
	}

}
