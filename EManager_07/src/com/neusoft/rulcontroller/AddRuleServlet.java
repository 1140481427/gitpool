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
 * Servlet implementation class AddRuleServlet
 */
@WebServlet("/addrule")
public class AddRuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRuleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RuleService rs = RuleService.getInstacne();
		Rule rl = new Rule();
		int pid = Integer.parseInt(request.getParameter("pid"));
		String pm_size = request.getParameter("pm_size");
		rl.setPid(pid);
		rl.setPm_size(pm_size);
		if (rs.addRule(rl)) {
			System.out.println("添加成功!");
			response.sendRedirect("showrule");
		} else {
			System.out.println("添加失败!");
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
