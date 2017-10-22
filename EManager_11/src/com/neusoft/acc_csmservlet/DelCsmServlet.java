package com.neusoft.acc_csmservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.shop_consumerservice.ShopCsmService;

/**
 * Servlet implementation class DelCsmServlet
 */
@WebServlet("/delcsm")
public class DelCsmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCsmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int aid = Integer.parseInt(request.getParameter("aid"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		//ɾ����Ա������Ϣͬʱɾ����Ա���ڻ�Ա��Ϣ
		
		ShopCsmService scs = ShopCsmService.getInstance();
		if (scs.removeShopCsm(id)) {
			System.out.println("ɾ����Ա������Ϣ�ɹ�!");
		} else {
			System.out.println("ɾ����Ա������Ϣʧ��!");
		}
		
		Acc_CsmService acs = Acc_CsmService.getInstance();
		if (acs.removeAcc_Csm(aid)) {
			System.out.println("ɾ����Ա��Ϣ�ɹ�!");
		} else {
			System.out.println("ɾ����Ա��Ϣʧ��!");
		}
		
		response.sendRedirect("pageshop");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
