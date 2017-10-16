package com.neusoft.entity;

import java.io.Serializable;

public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6189206554937960564L;
	
	private int pro_id;
	private String pname;
	private double price;
	private int num;
	
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public double getTotalPrice(){
		return price*num;
	}
}
