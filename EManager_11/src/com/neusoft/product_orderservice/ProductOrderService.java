package com.neusoft.product_orderservice;

import java.util.List;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.ProductOrderInfo;
import com.neusoft.product_orderdao.ProductOrderDao;

public class ProductOrderService {

	private static ProductOrderService instance = new ProductOrderService();
	
	private ProductOrderService(){}
	
	public static ProductOrderService getInstance(){
		return instance;
	}
	
	ProductOrderDao pod = DaoFactory.getInstance("ProductOrderDaoImpl", ProductOrderDao.class);
	
	public boolean addProductOrder(ProductOrderInfo[] po){
		return pod.insertPo(po);
	}
	
	public List<ProductOrderInfo> showPOByOid(int oid){
		return pod.selectPo(oid);
	}
}
