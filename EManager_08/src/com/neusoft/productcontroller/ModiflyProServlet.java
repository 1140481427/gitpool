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
 * Servlet implementation class ModiflyProServlet
 */
@WebServlet("/modiflypro")
public class ModiflyProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiflyProServlet() {
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
	
	protected void modifly(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pro_no = request.getParameter("pro_no");
		String name = request.getParameter("pro_name");
		String region = request.getParameter("region");
		String pro_spec = request.getParameter("pro_spec");
		String pro_img_src = request.getParameter("pro_img_src");
		double pro_price = Double.parseDouble(request.getParameter("pro_price"));
		int pro_hits = Integer.parseInt(request.getParameter("pro_hits"));
		int pro_rem = Integer.parseInt(request.getParameter("pro_rem"));
		int pro_tj = Integer.parseInt(request.getParameter("pro_tj"));
		int pro_sx = Integer.parseInt(request.getParameter("pro_sx"));
		String edit = request.getParameter("edit");
		
		T_Product_List pd = new T_Product_List(pid, pro_no, name, region, pro_spec, pro_img_src, pro_price, pro_hits, pro_rem, pro_tj, pro_sx, edit);
		
		try {
			ProductService ps = new ProductService();
			if(ps.modiflyPro(pd)) {
				System.out.println("修改成功");
				response.sendRedirect("PageModelShowPro");
			} else {
				String error = "修改失败";
				request.setAttribute("error", error);
				request.getRequestDispatcher("showmodifly?pid="+pid).forward(request, response);;
			}
		} catch (DaoException e) {
			System.out.println(e.getMessage());
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
