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
import com.neusoft.entity.ProductOrderInfo;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.orderservice.OrderService;
import com.neusoft.product_orderservice.ProductOrderService;
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
		//��ȡ��ַ��Ϣ
		HttpSession session = request.getSession();
		Account_Consumer ac = (Account_Consumer)session.getAttribute("user");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String s1 = request.getParameter("sel1");
		String s2 = request.getParameter("sel2");
		String s3 = request.getParameter("sel3");
		String order = request.getParameter("order");
		
		//����ջ���ַ
		Consumer_Addr ca = new Consumer_Addr();
		ca.setName(name);
		ca.setPhone(phone);
		ca.setProvice(s1);
		ca.setCity(s2);
		ca.setArea(s3);
		ca.setAddr(order);
		ConsumerAddrService cads = ConsumerAddrService.getInstance();
		if (cads.addConsumerAddr(ca)) {
			System.out.println("����ջ���ַ��ӳɹ�!");
			
			List<Consumer_Addr> list = cads.showConsumerAddrList();
			
			
			//������Ϣ
			String payment = request.getParameter("payment"); //֧����ʽ  
			String remark = request.getParameter("remark");	//��ע
			int num = Integer.parseInt(request.getParameter("num"));
			String[] totalPriceArr = request.getParameterValues("totalprice");
			//��ȡ��Ʒ�۸�����
			
			double priceNum = 0.0;
			
			for(int i=0; i<totalPriceArr.length; i++) {
				//ѭ��  �ó���Ʒ�ܼ�
				priceNum += Double.parseDouble(totalPriceArr[i]);
			}
			String[] pid =  request.getParameterValues("pid");
			System.out.println(pid);
			
			
			Orderinfo oi = new Orderinfo();
			UUID uuid = UUID.randomUUID();
			oi.setOrderno(uuid.toString());		//�������
			oi.setOrderstatus(2);
			oi.setPaystatus(Integer.parseInt(payment));
			oi.setAddrinfo(list.get(0).getId());
			oi.setMask(remark);
			oi.setNum(num);
			oi.setTotalprice(priceNum);
			
			OrderService os = OrderService.getInstance();
			if (os.addOrder(oi)) {
				System.out.println("������ӳɹ�!");
				Orderinfo oo = os.showOrderByDesc();
				//�õ���Ʒ�������ҵ������
				ProductOrderService pos = ProductOrderService.getInstance();
				
				//������Ʒ��Ϣ
				String[] proid = request.getParameterValues("pid");
				System.out.println(proid.toString());
				String[] pnum = request.getParameterValues("num");
				
				ProductOrderInfo[] poiArry = new ProductOrderInfo[pnum.length];
				//��������Ʒ��Ϣ
				for(int i=0; i<proid.length; i++) {
					ProductOrderInfo poi = new ProductOrderInfo();
					poi.setProductid(Integer.parseInt(proid[i]));
					poi.setNum(Integer.parseInt(pnum[i]));
					poi.setOrderid(oo.getId());
					poiArry[i] = poi;
					
					
				}
				
				
				//��������Ϣ
				pos.addProductOrder(poiArry);
				
				
				//�µ��ɹ���  �޸���ҹ�����Ϣ
				Shoppinginfo_Consumer sc = null;
				ShopCsmService scs = ShopCsmService.getInstance();
				sc = scs.showShopCsmByAid(ac.getId());
				//sc.setMoney(sc.getMoney());
				sc.setCount(sc.getCount() + num);
				sc.setMoney(sc.getMoney() + priceNum);
				scs.modiflyShopCsm(sc);
				response.sendRedirect("ordersucc.jsp");
			} else {
				System.out.println("�������ʧ��");
				String err = "�������ʧ��";
				request.setAttribute("err", err);
				request.getParameter("order.jsp");
			}
			
			
		} else {
			System.out.println("��ҵ�ַ���ʧ��!");
			String err = "��ҵ�ַ���ʧ��!";
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
