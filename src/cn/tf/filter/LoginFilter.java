package cn.tf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tf.domain.User;
import cn.tf.service.BusinessService;
import cn.tf.service.impl.BusinessServiceImpl;
import cn.tf.util.SecurityUtil;

public class LoginFilter implements Filter{
	private BusinessService s = new BusinessServiceImpl();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request;
		HttpServletResponse response;
		
		try {
			request=(HttpServletRequest) req;
			response=(HttpServletResponse) resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		//检测用户是否登录
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		//获取指定的cookie，解析用户名和密码
		if(user==null){
			Cookie cookies[]=request.getCookies();
			for(int i=0;cookies!=null&&i<cookies.length;i++){
				if("loginInfo".equals(cookies[i].getName())){
					//根据用户名和密码再次验证是否正确   
					String base64Username = cookies[i].getValue().split("_")[0];
					String md5Password = cookies[i].getValue().split("_")[1];
					User u1 = s.login(SecurityUtil.base64Decode(base64Username), md5Password);
					//如果正确：放入HttpSession
					if(u1!=null){
						session.setAttribute("user", u1);//自动登陆
					}
					break;
				}
			}
		}
		
		chain.doFilter(request, response);
		
		
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
