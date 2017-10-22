package com.neusoft.rulcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.neusoft.entity.Rule;
import com.neusoft.rulservice.RuleService;

/**
 * Servlet implementation class AjaxShowRuleServlet
 */
@WebServlet("/ajaxrule")
public class AjaxShowRuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxShowRuleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cid = Integer.parseInt(request.getParameter("cid"));
		RuleService rs = RuleService.getInstacne();
		List<Rule> list = rs.selectRuleByCid(cid);
		PrintWriter pw = response.getWriter();
		Gson gs = new Gson();
		pw.println(gs.toJson(list));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
