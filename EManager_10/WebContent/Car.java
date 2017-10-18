package com.neusoft.entity;

/**
 * 汽车实体类
 * @author Administrator
 *
 */
public class Car {

	private int id;
	private String name;
	
	
	public Car() {
		super();
	}
	public Car(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
