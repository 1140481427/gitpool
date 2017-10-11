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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addpro")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("pro_name");
		String region = request.getParameter("region");
		String pro_no = request.getParameter("pro_no");
		String pro_spec = request.getParameter("pro_spec");
		String pro_img_src = request.getParameter("pro_img_src");
		int pro_price =Integer.parseInt(request.getParameter("pro_price"));
		int pro_hits = Integer.parseInt(request.getParameter("pro_hits"));
		int pro_rem = Integer.parseInt(request.getParameter("pro_rem"));
		int pro_tj = Integer.parseInt(request.getParameter("pro_tj"));
		int pro_sx = Integer.parseInt(request.getParameter("pro_sx"));
		String edit = request.getParameter("edit");
		
		T_Product_List pro = new T_Product_List(pro_no,name,region,pro_spec,pro_img_src,pro_price,pro_hits,pro_rem,pro_tj,pro_sx,edit);
		
		ProductService ps = new ProductService();
		try{
			if(ps.addPro(pro)) {
				System.out.println("添加成功");
				response.sendRedirect("PageModelShowPro");
			} else {
				System.out.println("添加失败");
				response.sendRedirect("PageModelShowPro");
			}
		}catch(DaoException e) {
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
