package com.neusoft.acc_csmservlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.shop_consumerservice.ShopCsmService;
import com.neusoft.utils.MD5Utils;

/**
 * Servlet implementation class AddCsmServlet
 */
@WebServlet("/addcsm")
public class AddCsmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCsmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Acc_CsmService ac = Acc_CsmService.getInstance();
		Account_Consumer acm = new Account_Consumer();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		acm.setLoginname(username);
		acm.setPassword(MD5Utils.GetMD5Code(password));
		//acm.setRegistertime(new Date(System.currentTimeMillis()));
		//acm.setLastlogintime(new Date(System.currentTimeMillis()));
		
		acm.setIp(request.getRemoteAddr());
		if (ac.addAcc_Csm(acm)) {
			System.out.println("×¢²á³É¹¦");
			Shoppinginfo_Consumer sc = new Shoppinginfo_Consumer();
			sc.setAid(ac.showAcc_CsmList().get(0).getId());
			sc.setCount(0);
			sc.setMoney(0.0);
			sc.setNickname("XXX");
			ShopCsmService scs = ShopCsmService.getInstance();
			scs.addShopCsm(sc);
			response.sendRedirect("tiaozhuan.jsp");
		} else {
			System.out.println("×¢²áÊ§°Ü!");
			String err = "×¢²áÊ§°Ü!";
			request.setAttribute("err", err);
			request.getRequestDispatcher("csmregister.jsp").forward(request, response);;
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
