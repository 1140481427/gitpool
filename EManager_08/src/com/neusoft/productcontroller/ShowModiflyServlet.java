package com.neusoft.productcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.T_Product_List;
import com.neusoft.productservice.ProductService;

/**
 * Servlet implementation class ShowModiflyServlet
 */
@WebServlet("/showmodiflypro")
public class ShowModiflyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowModiflyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			try {
				showmodifly(request,response);
			} catch (DaoException e){
				System.out.println(e.getMessage());
				response.sendRedirect("PageModelShowPro");
			}
		} catch (DaoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	protected void showmodifly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		
		if(pid == null || pid.equals("")){
			throw new DaoException("pid²»ÄÜÎª¿Õ!");
		}
		
		//response.sendRedirect("PageModelShowPro");
		
		int _pid = Integer.parseInt(pid);	
		
		ProductService ps = new ProductService();
		T_Product_List pro = ps.showProById(_pid);
		request.setAttribute("pro", pro);
		request.getRequestDispatcher("modiflyproduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
