package com.neusoft.rulcontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.entity.Rule;
import com.neusoft.rulservice.RuleService;

/**
 * Servlet implementation class ModiflyRuleServlet
 */
@WebServlet("/modiflyrule")
public class ModiflyRuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModiflyRuleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pm_size = request.getParameter("pm_size");
		
		Rule r = new Rule();
		r.setId(id);
		r.setPid(pid);
		r.setPm_size(pm_size);
		
		RuleService rs = RuleService.getInstacne();
		
		if (rs.modiflyRule(r)) {
			System.out.println("修改成功!");
			response.sendRedirect("showrule");
		} else {
			System.out.println("修改失败!");
			response.sendRedirect("showrule");
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
