package com.neusoft.test;

import com.neusoft.catedaoimpl.CateDaoImpl;
import com.neusoft.entity.T_Pro_Category;

public class CateTest {

	public static void main(String[] args) {
		delCate();
		//insert();
		//selectById();
		//update();
		//selectCate();
	}
	
	public static void insert(){
		T_Pro_Category c = new T_Pro_Category();
		c.setCate_id(4444);
		c.setCate_name("FFFF");
		c.setCate_pid(1111);
		new CateDaoImpl().insertCate(c);
	}
	
	public static void update(){
		T_Pro_Category c = new CateDaoImpl().selectCateById(302);
		c.setCate_name("´÷¶ûÓÎÏ»±Ê¼Ç±¾");
		new CateDaoImpl().updateCate(c);
	}
	
	public static void selectById(){
		System.out.println(new CateDaoImpl().selectCateById(102));
	}
	
	public static void selectCate(){
		System.out.println(new CateDaoImpl().selectCateList(1,5));
	}
	
	public static void delCate(){
		new CateDaoImpl().delCate(22222);
	}
	
}
