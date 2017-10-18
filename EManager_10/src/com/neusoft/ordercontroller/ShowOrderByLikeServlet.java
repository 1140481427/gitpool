package com.neusoft.ordercontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.Orderinfo;
import com.neusoft.orderservice.OrderService;
import com.neusoft.utils.PageModel;

/**
 * Servlet implementation class ShowOrderByLikeServlet
 */
@WebServlet("/showorderbylike")
public class ShowOrderByLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrderByLikeServlet() {
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
		int pageSize = 3;
		
		String SpageNo = request.getParameter("pageNo");
		String SpageSize = request.getParameter("pageSize");
		String likeName = request.getParameter("likename");
		if(SpageNo != null && !SpageNo.equals("")){
			pageNo = Integer.parseInt(SpageNo);
		}
		
		if(SpageSize != null && !SpageSize.equals("")){
			pageSize = Integer.parseInt(SpageSize);
		}
		OrderService ps = OrderService.getInstance();
		PageModel<Orderinfo> pm = ps.showOrderByLike(pageNo, pageSize, likeName);
		int totalPageNo = (pm.getTotalRecords()%pageSize==0?pm.getTotalRecords()/pageSize:pm.getTotalRecords()/pageSize+1);
		pm.setTotalPageNo(totalPageNo);
		pm.setPageNo(pageNo);
		pm.setPageSize(pageSize);
		request.setAttribute("pm", pm);
		request.getRequestDispatcher("order_information.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
