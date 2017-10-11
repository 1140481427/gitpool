package com.neusoft.acc_csmservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.cateservice.Categroy;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.utils.PageModel;

/**
 * Servlet implementation class ShowAccCsmServlet
 */
@WebServlet("/showcsm")
public class ShowAccCsmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAccCsmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pageNo = 1;
		int pageSize = 5;
		
		String SpageNo = request.getParameter("pageNo");
		String SpageSize = request.getParameter("pageSize");
		
		if(SpageNo != null && !SpageNo.equals("")){
			pageNo = Integer.parseInt(SpageNo);
		}
		
		if(SpageSize != null && !SpageSize.equals("")){
			pageSize = Integer.parseInt(SpageSize);
		}
		
		Acc_CsmService ac = Acc_CsmService.getInstance();
		PageModel<Account_Consumer> pm = ac.showAcc_CsmAll(pageNo,pageSize);
		int totalPageNo = (pm.getTotalRecords()%pageSize==0?pm.getTotalRecords()/pageSize:pm.getTotalRecords()/pageSize+1);
		pm.setTotalPageNo(totalPageNo);
		pm.setPageNo(pageNo);
		pm.setPageSize(pageSize);
		
		request.setAttribute("pm", pm);
		request.getRequestDispatcher("member_mag.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
