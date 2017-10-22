package com.neusoft.catecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.cateservice.Categroy;

/**
 * Servlet implementation class DelCate
 */
@WebServlet("/del")
public class DelCate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelCate() {
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
		if(cg.removeCate(cid)){
			System.out.println("É¾³ý³É¹¦!");
			response.sendRedirect("showcate");
		}else{
			System.out.println("É¾³ýÊ§°Ü!");
			response.sendRedirect("showcate");
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
