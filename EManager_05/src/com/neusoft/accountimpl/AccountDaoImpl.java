package com.neusoft.accountimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.neusoft.accountdao.AccountDao;
import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account;
import com.neusoft.utils.UtilC3P0;

/**
 * oracle AccountDao实现
 * @author Administrator
 *
 */
public class AccountDaoImpl implements AccountDao {
	
	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	public AccountDaoImpl() {
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Account doLogin(Account mAcc) throws DaoException {
		Account acc = null;
		String sql = "select id,username,password,ip,logindate from accounts where username = ? and password = ?";
		try {
			acc = qr.query(conn, sql, new BeanHandler<Account>(Account.class), mAcc.getUsername(),mAcc.getPassword());
		} catch (SQLException e) {
			throw new DaoException("查询用户信息出错",e);
		}
		return acc;
	}

	@Override
	public boolean updateAccount(Account acc) throws DaoException {
		String sql = "update accounts set ip = ?,logindate = ? where id = ?";
		try{
			int count = qr.update(conn, sql, acc.getIp(),acc.getLogindate(),acc.getId());
			if(count>0){
				return true;
			}
		}catch (SQLException e) {
			throw new DaoException("修改用户信息出错",e);
		}
		return false;
	}
	
}
