package com.neusoft.entity;

import java.io.Serializable;

/**
 * ������Ʒ��
 * @author Administrator
 *
 */
public class ProductOrderInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2540691459120819701L;
	
	private int productid;	//��Ʒid
	private int orderid;	//����id
	private int num;		//����
	
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
