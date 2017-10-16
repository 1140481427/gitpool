package com.neusoft.entity;

import java.io.Serializable;

/**
 * 订单商品表
 * @author Administrator
 *
 */
public class ProductOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540691459120819701L;
	
	private int productid;	//商品id
	private int orderid;	//订单id
	private int num;		//数量
	
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "ProductOrderInfo [productid=" + productid + ", orderid=" + orderid + ", num=" + num + "]";
	}
	

}
