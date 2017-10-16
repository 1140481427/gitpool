package com.neusoft.entity;

import java.io.Serializable;

public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6189206554937960564L;
	
	private int pro_id;
	private String pname;
	private String prc_src;
	private double price;
	private int num;
	
	public String getPrc_src() {
		return prc_src;
	}
	public void setPrc_src(String prc_src) {
		this.prc_src = prc_src;
	}
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
