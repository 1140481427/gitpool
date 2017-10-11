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
import javax.servlet.http.HttpSession;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.entity.Account;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.loginservice.LoginService;

/**
 * Servlet Filter implementation class CsmLoginFilter
 */
@WebFilter("/CsmLoginFilter")
public class CsmLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CsmLoginFilter() {
        // TODO Auto-generated constructor stub
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

		// pass the request along the filter chain
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
				if(name != null && name.equals("csmusername")){
					username = URLDecoder.decode(value, "utf-8");
					use_ck = s;
				}
				if(name != null && name.equals("csmpassword")){
					password = value;
					psw_ck = s;
				}
			}
		}
			
		if(username != null && password != null){
			Acc_CsmService ac = Acc_CsmService.getInstance();
			Account_Consumer acm = null;
			acm = ac.showAcc_CsmByUser(username);
			 
			if (password.equals(acm.getPassword())){
				System.out.println("登陆成功");
				HttpSession session = req.getSession();
			  	session.setAttribute("user", acm);
				request.getRequestDispatcher("shop_index.jsp").forward(request, response);	
			}else{
				System.out.println("删除cookie,重新登陆");
				use_ck.setMaxAge(0);
				psw_ck.setMaxAge(0);
				resp.addCookie(use_ck);
				resp.addCookie(psw_ck);
				resp.sendRedirect("csmlogin.jsp");
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
