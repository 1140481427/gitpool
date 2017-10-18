package com.neusoft.ordercontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neusoft.con_addservice.ConsumerAddrService;
import com.neusoft.entity.Consumer_Addr;
import com.neusoft.entity.OrderProduct;
import com.neusoft.entity.Orderinfo;
import com.neusoft.entity.ProductOrderInfo;
import com.neusoft.entity.T_Product_List;
import com.neusoft.orderservice.OrderService;
import com.neusoft.product_orderservice.ProductOrderService;
import com.neusoft.productservice.ProductService;

/**
 * Servlet implementation class ShowOrderByIdServlet
 */
@WebServlet("/showorderbyid")
public class ShowOrderByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOrderByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		//根据id查询订单信息
		OrderService os = OrderService.getInstance();
		Orderinfo oi = os.showOrderById(id);
		//查询买家信息
		ConsumerAddrService cs = ConsumerAddrService.getInstance();
		Consumer_Addr ca = cs.showConsumerAddrById(oi.getAddrinfo());
		ProductOrderService pos = ProductOrderService.getInstance();
		List<ProductOrderInfo> poi = pos.showPOByOid(id);
		//新建类  保存 俩个表的信息
		List<OrderProduct> proList = new ArrayList<OrderProduct>();
		ProductService ps = new ProductService();
		for(int i=0; i<poi.size(); i++) {
			ProductOrderInfo po = poi.get(i);
			T_Product_List p = ps.showProById(po.getProductid()) ;
			
			OrderProduct op = new OrderProduct(p.getPid(),p.getPro_name(),p.getPro_item_no(),poi.get(i).getNum(),p.getPro_price());
			proList.add(op);
		}
		
		request.setAttribute("proList", proList);
		request.setAttribute("oi", oi);
		request.setAttribute("ca", ca);
		request.getRequestDispatcher("order_details.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
