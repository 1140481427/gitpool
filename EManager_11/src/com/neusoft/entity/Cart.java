package com.neusoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车
 * @author Administrator
 *
 */
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3833273403775778455L;
	
	/**
	 * shop 集合
	 */
	private List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
	
	/**
	 * 添加商品
	 * @param item
	 */
	public void addItem(CartItem item) {
		
		for(CartItem cart : items){
			if(cart.getPro_id() == item.getPro_id()) {
				cart.setNum(cart.getNum() + 1);
				return;
			}
		}
		
		items.add(item);
	}
	
	
	
	
}
