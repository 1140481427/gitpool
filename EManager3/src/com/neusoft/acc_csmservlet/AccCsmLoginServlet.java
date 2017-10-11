package com.neusoft.acc_csmservlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.entity.Account;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.loginservice.LoginService;
import com.neusoft.utils.MD5Utils;

/**
 * Servlet implementation class AccCsmLoginServlet
 */
@WebServlet("/acccsmlogin")
public class AccCsmLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccCsmLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doLogin(request,response);
	}
	
	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isCheck = request.getParameter("autologin");
		
		Acc_CsmService ac = Acc_CsmService.getInstance();
		Account_Consumer acm = null;
		
		String command = request.getParameter("command");
		if("login".equals(command)){
			String _username = request.getParameter("username");
			String _password = request.getParameter("password");
			
			if(_username !=null || _username != "" && _password !=null || _password != ""){
				 
				  acm = ac.showAcc_CsmByUser(_username);
				  if (MD5Utils.GetMD5Code(_password).equals(acm.getPassword())){
					  if(isCheck != null && isCheck.equals("1")){
							//编码
							Cookie use_ck = new Cookie("csmusername",URLEncoder.encode(_username,"utf-8"));
							use_ck.setMaxAge(7*24*60*60);
							//加密
							Cookie psw_ck = new Cookie("csmpassword",MD5Utils.GetMD5Code(_password));
							System.out.println(MD5Utils.GetMD5Code(_password));
							psw_ck.setMaxAge(7*24*60*60);
							response.addCookie(use_ck);
							response.addCookie(psw_ck);
						}	
					  
					  	HttpSession session = request.getSession();
					  	session.setAttribute("user", acm);
						request.getRequestDispatcher("shop_index.jsp").forward(request, response);	
				  } else {
					  	System.out.println("============用户名密码错误!==========");
					  	String error = "用户名或密码错误!";
					  	request.setAttribute("error", error);
					  	request.getRequestDispatcher("csmlogin.jsp").forward(request, response);
				  }
			}
			
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
