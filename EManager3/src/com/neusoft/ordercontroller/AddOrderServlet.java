package com.neusoft.ordercontroller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neusoft.con_addservice.ConsumerAddrService;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Consumer_Addr;
import com.neusoft.entity.Orderinfo;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.orderservice.OrderService;
import com.neusoft.shop_consumerservice.ShopCsmService;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取地址信息
		HttpSession session = request.getSession();
		Account_Consumer ac = (Account_Consumer)session.getAttribute("user");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String s1 = request.getParameter("sel1");
		String s2 = request.getParameter("sel2");
		String s3 = request.getParameter("sel3");
		String order = request.getParameter("order");
		
		//买家收货地址
		Consumer_Addr ca = new Consumer_Addr();
		ca.setName(name);
		ca.setPhone(phone);
		ca.setProvice(s1);
		ca.setCity(s2);
		ca.setArea(s3);
		ca.setAddr(order);
		ConsumerAddrService cads = ConsumerAddrService.getInstance();
		if (cads.addConsumerAddr(ca)) {
			System.out.println("买家收货地址添加成功!");
			
			List<Consumer_Addr> list = cads.showConsumerAddrList();
			
			
			//订单信息
			String payment = request.getParameter("payment"); //支付方式  
			String remark = request.getParameter("remark");	//备注
			int num = Integer.parseInt(request.getParameter("num"));
			double totalprice = Double.parseDouble(request.getParameter("totalprice"));
			
			
			String[] pid =  request.getParameterValues("pid");
			System.out.println(pid);
			
			
			Orderinfo oi = new Orderinfo();
			UUID uuid = UUID.randomUUID();
			oi.setOrderno(uuid.toString());		//订单编号
			oi.setOrderstatus(2);
			oi.setPaystatus(Integer.parseInt(payment));
			oi.setAddrinfo(list.get(0).getId());
			oi.setMask(remark);
			oi.setNum(num);
			oi.setTotalprice(totalprice);
			
			
			OrderService os = OrderService.getInstance();
			if (os.addOrder(oi)) {
				System.out.println("订单添加成功!");
				//下单成功后  修改买家购物信息
				Shoppinginfo_Consumer sc = null;
				ShopCsmService scs = ShopCsmService.getInstance();
				sc = scs.showShopCsmByAid(ac.getId());
				//sc.setMoney(sc.getMoney());
				sc.setCount(sc.getCount() + num);
				scs.modiflyShopCsm(sc);
				response.sendRedirect("ordersucc.jsp");
			} else {
				System.out.println("订单添加失败");
				String err = "订单添加失败";
				request.setAttribute("err", err);
				request.getParameter("order.jsp");
			}
			
			
		} else {
			System.out.println("买家地址添加失败!");
			String err = "买家地址添加失败!";
			request.setAttribute("err", err);
			request.getRequestDispatcher("order.jsp").forward(request, response);
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
