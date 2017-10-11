package com.neusoft.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 买家信息
 * @author Administrator
 *
 */
public class Shoppinginfo_Consumer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7915353298507263182L;
	
	
	private int id; 
	private int aid;	//买家帐号信息id
	private String nickname; //昵称
	private Double money;	//订单金额
	private int count;		//订单总数
	private Date lasttime;	//最后一次登陆时间
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getLasttime() {
		return lasttime;
	}
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	
	@Override
	public String toString() {
		return "Shoppinginfo_Consumer [id=" + id + ", aid=" + aid + ", nickname=" + nickname + ", money=" + money
				+ ", count=" + count + ", lasttime=" + lasttime + "]";
	}
	

	

}
