package com.neusoft.orderservice;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.con_addservice.ConsumerAddrService;
import com.neusoft.entity.Orderinfo;
import com.neusoft.orderdao.OrderDao;
import com.neusoft.utils.PageModel;

/**
 * ¶©µ¥ÒµÎñ²ã
 * @author Administrator
 *
 */
public class OrderService {
	
	private static OrderService instance = new OrderService();
	
	private OrderService(){}
	
	public static OrderService getInstance(){
		return instance;
	}

	OrderDao od = DaoFactory.getInstance("orderdaoimpl", OrderDao.class);
	
	public boolean addOrder(Orderinfo oi){
		return od.insertOrder(oi);
	}
	
	public boolean modiflyOrder(Orderinfo oi){
		return od.updateOrder(oi);
	}
	
	public boolean removeOrde(int id){
		return od.delOrder(id);
	}
	
	public PageModel<Orderinfo> showOrderByPageModel(int pageNo,int pageSize){
		return od.selectOrder(pageNo, pageSize);
	}
	
	public Orderinfo showOrderById(int id){
		return od.selectOrder(id);
	}
}
