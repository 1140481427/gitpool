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
 * Servlet implementation class ModiflyCateServlet
 */
@WebServlet("/modiflyCate")
public class ModiflyCateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiflyCateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		modifly(request,response);
	}

	private void modifly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Categroy cg = new Categroy();
		T_Pro_Category tc = new T_Pro_Category();
		
		tc = cg.showCate(cid);
		
		String cname = request.getParameter("cname");
		String sele = request.getParameter("selepro"); 
		
		if(cname == null || cname.equals("")){
			throw new DaoException("分类名称不能为空!");
		}
		
		tc.setCate_name(cname);
		tc.setCate_pid(Integer.parseInt(sele));
		if(cg.modiflyCate(tc)){
			System.out.println("分类修改成功");
			response.sendRedirect("showcate");
		} else {
			System.out.println("分类修改失败");
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
