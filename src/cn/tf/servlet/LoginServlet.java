package cn.tf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tf.domain.User;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.util.SecurityUtil;

public class LoginServlet extends HttpServlet {

	private BusinessService s= new BusinessServiceImpl();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		//验证用户名和密码
		User user = s.login(username, SecurityUtil.md5(password));
		if(user==null){
			out.write("错误的用户名或密码,1秒后转向登陆页面");
			response.setHeader("Refresh", "1;URL="+request.getContextPath()+"/Login.jsp");
			return;
		}
		
		request.getSession().setAttribute("user", user);
		out.write("登陆成功,1秒后转向主页");
		response.setHeader("Refresh", "1;URL="+request.getContextPath()+"/index.jsp");
		//记住用户名和密码:cookie   loginInfo=用户名（Base64）_密码（MD5）
		
		if(remember!=null){
			Cookie cookie = new Cookie("loginInfo", SecurityUtil.base64Encode(username)+"_"+SecurityUtil.md5(password));
			cookie.setPath(request.getContextPath());
			cookie.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(cookie);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}