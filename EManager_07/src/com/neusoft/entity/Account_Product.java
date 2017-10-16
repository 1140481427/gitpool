package com.neusoft.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 卖家帐号信息
 * @author Administrator
 *
 */
public class Account_Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2713825668095860952L;
	
	private int id;	
	private String loginname;	//卖家登陆名
	private String password; 	//卖家密码
	private Date registertime;	//注册时间
	private Date lastlogintime; //最后一次登陆时间
	private String ip;	//ip地址
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public String toString() {
		return "Account_Product [id=" + id + ", loginname=" + loginname + ", password=" + password + ", registertime="
				+ registertime + ", lastlogintime=" + lastlogintime + ", ip=" + ip + "]";
	}
	
	
	
	
}
