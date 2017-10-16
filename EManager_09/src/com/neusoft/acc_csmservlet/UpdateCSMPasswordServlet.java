package com.neusoft.acc_csmservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.acc_csmservice.Acc_CsmService;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.utils.MD5Utils;

/**
 * Servlet implementation class UpdateCSMPasswordServlet
 */
@WebServlet("/updatecsmpassword")
public class UpdateCSMPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCSMPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String passowrd = request.getParameter("newpassword");
		Acc_CsmService ac = Acc_CsmService.getInstance();
		
		Account_Consumer acm =  ac.showAcc_CsmById(id);
		acm.setPassword(MD5Utils.GetMD5Code(passowrd));
		if (ac.modiflyAcc_Csm(acm)) {
			System.out.println("–ﬁ∏ƒ√‹¬Î≥…π¶!");
			response.sendRedirect("modiflytiaozhuan.jsp");
			//response.sendRedirect("csmlogin.jsp");
		} else {
			System.out.println("–ﬁ∏ƒ√‹¬Î ß∞‹!");
			String err = "–ﬁ∏ƒ√‹¬Î ß∞‹!";
			request.setAttribute("err", err);
			request.getRequestDispatcher("modiflypassword.jsp").forward(request, response);;
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
