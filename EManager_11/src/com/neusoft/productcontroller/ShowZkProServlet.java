package com.neusoft.productcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.T_Product_List;
import com.neusoft.productservice.ProductService;
import com.neusoft.utils.PageModel;

/**
 * Servlet implementation class ShowZkProServlet
 */
@WebServlet("/showzk")
public class ShowZkProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowZkProServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		int pageNo = 1;
		int pageSize = 6;
		
		String SpageNo = request.getParameter("pageNo");
		String SpageSize = request.getParameter("pageSize");
		
		if(SpageNo != null && !SpageNo.equals("")){
			pageNo = Integer.parseInt(SpageNo);
		}
		
		if(SpageSize != null && !SpageSize.equals("")){
			pageSize = Integer.parseInt(SpageSize);
		}
		ProductService ps = new ProductService();
		PageModel<T_Product_List> pm = ps.shoZkProduct(pageNo, pageSize);
		int totalPageNo = (pm.getTotalRecords()%pageSize==0?pm.getTotalRecords()/pageSize:pm.getTotalRecords()/pageSize+1);
		pm.setTotalPageNo(totalPageNo);
		pm.setPageNo(pageNo);
		pm.setPageSize(pageSize);
		request.setAttribute("pm", pm);
		request.getRequestDispatcher("hot1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
