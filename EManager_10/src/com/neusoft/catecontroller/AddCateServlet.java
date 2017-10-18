package com.neusoft.catecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.accountdao.DaoException;
import com.neusoft.cateservice.Categroy;
import com.neusoft.entity.T_Pro_Category;

/**
 * Servlet implementation class AddCateServlet
 */
@WebServlet("/addcate")
public class AddCateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			addCate(request,response);
			response.sendRedirect("showcate");
		}catch(DaoException e){
			System.out.println(e.getMessage());
		}
			
	}

	
	private void addCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname = request.getParameter("cname");
		String sele = request.getParameter("selepro"); 
		
		if(cname == null || cname.equals("")){
			throw new DaoException("分类名称不能为空!");
		}
		
		System.out.println(cname + " " + sele);
		Categroy cg = new Categroy();
		T_Pro_Category tc = new T_Pro_Category();
		
		tc.setCate_name(cname);
		tc.setCate_pid(Integer.parseInt(sele));
		cg.addCate(tc);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
