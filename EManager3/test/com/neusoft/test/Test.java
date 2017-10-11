package com.neusoft.test;

import com.neusoft.accountdao.AccountDao;
import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.Account;

/**
 * ≤‚ ‘∑Ω∑®
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		testLogin();
	}
	
	public static void testLogin(){
		AccountDao acc = DaoFactory.getInstance("accdaoimpl", AccountDao.class);
		Account mAcc = new Account();
		mAcc.setUsername("admin");
		mAcc.setPassword("admin");
		mAcc = acc.doLogin(mAcc);
		if(mAcc!=null){
			mAcc.setIp("10.25.151.173");
			mAcc.setLogindate(System.currentTimeMillis());
			acc.updateAccount(mAcc);
		}
		
		
		
	}
}
