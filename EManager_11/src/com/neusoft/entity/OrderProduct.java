package com.neusoft.entity;

import java.io.Serializable;

public class OrderProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7921097605171728784L;

	
	private int pid;
	private String pname;
	private String pro_item_no;
	

	private int num;
	private double price;
	
	public OrderProduct(){
		
	}
	
	

	public OrderProduct(int pid, String pname, String pro_item_no, int num, double price) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pro_item_no = pro_item_no;
		this.num = num;
		this.price = price;
	}



	public int getPid() {
		return pid;
	}
	
	public String getPro_item_no() {
		return pro_item_no;
	}



	public void setPro_item_no(String pro_item_no) {
		this.pro_item_no = pro_item_no;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderProduct [pid=" + pid + ", pname=" + pname + ", num=" + num + ", price=" + price + "]";
	}
	
	
}
