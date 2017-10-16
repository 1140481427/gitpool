package com.neusoft.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 买家帐号信息
 * @author Administrator
 *
 */
public class Account_Consumer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7531139558541120921L;
	
	private int id;	//买家id
	private String loginname;	//用户名
	private String password;	//密码
	private Date registertime;	//注册时间
	private Date lastlogintime;	//最后一次登陆时间
	private String ip;
	
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
		return "Account_Consumer [id=" + id + ", loginname=" + loginname + ", password=" + password + ", registertime="
				+ registertime + ", lastlogintime=" + lastlogintime + ", ip=" + ip + "]";
	}
	
	
	
	
	
}
