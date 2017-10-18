package com.neusoft.shop_consumercontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.shop_consumerservice.ShopCsmService;

/**
 * Servlet implementation class ModiflyShopCsmServlet
 */
@WebServlet("/modiflyshopcsm")
public class ModiflyShopCsmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiflyShopCsmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String nickname = request.getParameter("nickname");
		double money = Double.parseDouble(request.getParameter("money"));
		int count = Integer.parseInt(request.getParameter("count"));
		
		ShopCsmService scs = ShopCsmService.getInstance();
		Shoppinginfo_Consumer sc = scs.showShopCsmByAid(id);
		sc.setNickname(nickname);
		sc.setMoney(money);
		sc.setCount(count);
		
		if(scs.modiflyShopCsm(sc)){
			if(request.getParameter("type").equals("1")){
				System.out.println("修改成功!");
				response.sendRedirect("pageshop");
			}else{
				System.out.println("修改成功!");
				response.sendRedirect("showshopcsmbyid?id=" + id +"&type=2");
			}
			
		}else{
			System.out.println("修改失败!");
			String err = "修改失败!";
			request.setAttribute("err", err);
			request.getRequestDispatcher("showshopcsmbyid?id=" + id).forward(request, response);
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
