package com.neusoft.entity;

/**
 * о╣ап
 * @author Administrator
 *
 */
public class Set {
	private int id;
	private int pid;
	private String name;
	private double price;
	
	
	public Set(int id, double price) {
		this.id = id;
		this.price = price;
	}
	
	public Set(int id, int pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public Set() {
		super();
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Set [id=" + id + ", pid=" + pid + ", name=" + name + "]";
	}
	
	
}
