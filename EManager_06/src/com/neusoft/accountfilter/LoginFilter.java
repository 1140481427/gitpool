package com.neusoft.accountfilter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.Account;
import com.neusoft.loginservice.LoginService;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
    	System.out.println("==========创建loginFilter=========");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		Cookie[] cks = req.getCookies();
		String username = null;
		String password = null;
		Cookie use_ck = null;
		Cookie psw_ck = null;
		if(cks != null && cks.length>1) {
			for(Cookie s : cks){
				System.out.println(s.getName());
				System.out.println(s.getValue());
				String name = s.getName();
				String value = s.getValue();
				if(name != null && name.equals("username")){
					username = URLDecoder.decode(value, "utf-8");
					use_ck = s;
				}
				if(name != null && name.equals("password")){
					password = value;
					psw_ck = s;
				}
			}
		}
			
		if(username != null && password != null){
			Account mAcc = new Account();
			mAcc.setUsername(username);
			mAcc.setPassword(password);
			mAcc.setIp(request.getRemoteAddr());
			 mAcc = new LoginService().login(mAcc);
			
			if(mAcc != null){
				System.out.println("登陆成功");
				req.setAttribute("usernae", username);
				req.getRequestDispatcher("index.jsp").forward(req, resp);		
			}else{
				System.out.println("删除cookie,重新登陆");
				use_ck.setMaxAge(0);
				psw_ck.setMaxAge(0);
				resp.addCookie(use_ck);
				resp.addCookie(psw_ck);
				resp.sendRedirect("login.jsp");
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
