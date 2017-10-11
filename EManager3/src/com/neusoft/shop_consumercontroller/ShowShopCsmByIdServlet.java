package com.neusoft.shop_consumercontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.shop_consumerservice.ShopCsmService;

/**
 * Servlet implementation class ShowShopCsmByIdServlet
 */
@WebServlet("/showshopcsmbyid")
public class ShowShopCsmByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowShopCsmByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ShopCsmService scs = ShopCsmService.getInstance();
		HttpSession session = request.getSession();
		Account_Consumer ac = (Account_Consumer) session.getAttribute("user");
		Shoppinginfo_Consumer sc = scs.showShopCsmByAid(ac.getId());
		request.setAttribute("sc", sc);
		request.getRequestDispatcher("nikename.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
