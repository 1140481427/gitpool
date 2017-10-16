package com.neusoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.Account;
import com.neusoft.loginservice.LoginService;
import com.neusoft.utils.MD5Utils;

/**
 * µÇÂ½¿ØÖÆÆ÷
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
		
		String erro = (String) request.getAttribute("erro");
		String isCheck = request.getParameter("check");
		System.out.println(isCheck);
		String command = request.getParameter("command");
		if("login".equals(command)){
			String _username = request.getParameter("username");
			String _password = request.getParameter("password");
			Account mAcc = new Account();
			mAcc.setUsername(_username);
			mAcc.setPassword(MD5Utils.GetMD5Code(_password));
			mAcc.setIp(request.getRemoteAddr());
			
			if(_username !=null || _username != "" && _password !=null || _password != ""){
				 mAcc = new LoginService().login(mAcc);
			}
			if(mAcc!=null){
				if(isCheck != null && isCheck.equals("1")){
					//±àÂë
					Cookie use_ck = new Cookie("username",URLEncoder.encode(_username,"utf-8"));
					use_ck.setMaxAge(7*24*60*60);
					//¼ÓÃÜ
					Cookie psw_ck = new Cookie("password",MD5Utils.GetMD5Code(_password));
					System.out.println(MD5Utils.GetMD5Code(_password));
					psw_ck.setMaxAge(7*24*60*60);
					response.addCookie(use_ck);
					response.addCookie(psw_ck);
				}	
				request.setAttribute("usernae", _username);
				request.getRequestDispatcher("index.jsp").forward(request, response);		
			}else{
				System.out.println("============ÓÃ»§ÃûÃÜÂë´íÎó!==========");
				//request.setAttribute("erro", "ÓÃ»§Ãû»òÃÜÂë´íÎó,µÇÂ½Ê§°Ü");
				//request.getRequestDispatcher("LoginServlet").forward(request, response);
				response.sendRedirect("login.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
