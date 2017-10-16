package com.neusoft.catecontroller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.cateservice.Categroy;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.productservice.ProductService;

/**
 * Servlet implementation class ShowModiflyCate
 */
@WebServlet("/showmodifly")
public class ShowModiflyCate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowModiflyCate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cid = Integer.parseInt(request.getParameter("cid"));
		Categroy cg = new Categroy();
		T_Pro_Category tc = cg.showCate(cid);
		
		//ÏÂÀ­¿òÄÚÈÝ
		ProductService ps = new ProductService();
		Map<Integer, String> map = ps.showProOneCate();
		request.setAttribute("map", map);
		request.setAttribute("tc",tc);
		request.getRequestDispatcher("modiflycategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
