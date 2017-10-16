package com.neusoft.entity;

import java.io.Serializable;

/**
 * 类别实体类
 * @author Administrator
 *
 */
public class T_Pro_Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5590991318541459743L;

	private int cate_id;	//类别id
	private String cate_name; 	//类别名称
	private int cate_pid;	//父id
	
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public int getCate_pid() {
		return cate_pid;
	}
	public void setCate_pid(int cate_pid) {
		this.cate_pid = cate_pid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "T_Pro_Category [cate_id=" + cate_id + ", cate_name=" + cate_name +  ", cate_pid=" + cate_pid + "]";
	}
	
	
}
