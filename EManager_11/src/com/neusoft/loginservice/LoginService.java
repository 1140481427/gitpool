package com.neusoft.loginservice;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.neusoft.accountdao.AccountDao;
import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.Account;
import com.neusoft.utils.UtilC3P0;

public class LoginService {

	public LoginService() {
	}
	
	public Account login(Account mAcc){
		Connection conn = null;
		AccountDao acc = null;
		try {
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			acc = DaoFactory.getInstance("accdaoimpl", AccountDao.class);
			
			mAcc = acc.doLogin(mAcc);
			if(mAcc!=null){
				mAcc.setIp(mAcc.getIp());
				mAcc.setLogindate(System.currentTimeMillis());
				acc.updateAccount(mAcc);
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			DbUtils.closeQuietly(conn);
		}
		
		return mAcc;
	}


}
