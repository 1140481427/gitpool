
package com.neusoft.acc_consumerdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.acc_consumerdao.Acc_CsmDao;
import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.utils.MD5Utils;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

import oracle.net.aso.MD5;

/**
 * 买家帐号实现类
 * @author Administrator
 *
 */
public class Acc_CsmDaoImpl implements Acc_CsmDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertAccCsm(Account_Consumer as ) throws DaoException {
		String sql = "insert into account_consumer(id,loginname,password,registertime,lastlogintime,ip) values(seq_consumer.nextval,?,?,sysdate,sysdate,?)";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, as.getLoginname(),as.getPassword(),as.getIp());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入买家帐号信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateAccCsm(Account_Consumer as ) throws DaoException {
		String sql = "update account_consumer set loginname = ?, password = ?, registertime = ?, lastlogintime = ?, ip = ? where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, as.getLoginname(), as.getPassword(), as.getRegistertime(), as.getLastlogintime(), as.getIp(), as.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改买家帐号信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delAccCsm(int id) throws DaoException {
		String sql = "delete from account_consumer where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除买家帐号信息出错",e);
		}
		return false;
	}

	@Override
	public Account_Consumer selectAccCsmById(int id) throws DaoException {
		String sql = "select id,loginname,password,registertime,lastlogintime,ip from account_consumer where id = ?";
		Account_Consumer as = null;
		try {
			conn = UtilC3P0.getConnection();
			as = qr.query(conn, sql, new BeanHandler<Account_Consumer>(Account_Consumer.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除买家帐号信息出错",e);
		}
		return as;
	}

	@Override
	public PageModel<Account_Consumer> selectAccCsmAll(int pageNo, int pageSize) throws DaoException {
		PageModel<Account_Consumer> pageModel = new PageModel<Account_Consumer>();
		List<Account_Consumer> list = new ArrayList<Account_Consumer>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from account_consumer ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Account_Consumer>(Account_Consumer.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getAccCsmTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getAccCsmTotalRecords() throws DaoException {
		String sql = "select count(id) from account_consumer ";
		int num = 0; 
		try {
			conn = UtilC3P0.getConnection();
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public Account_Consumer selectAccCsmByUser(String user) throws DaoException {
		String sql = "select id,loginname,password,registertime,lastlogintime,ip from account_consumer where loginname = ?";
		Account_Consumer as = null;
		try {
			conn = UtilC3P0.getConnection();
			as = qr.query(conn, sql, new BeanHandler<Account_Consumer>(Account_Consumer.class), user);
		} catch (SQLException e) {
			throw new DaoException("查询买家帐号信息出错",e);
		}
		return as;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.GetMD5Code("111111"));
	}

	@Override
	public List<Account_Consumer> selectAccCsmList() throws DaoException {
		List<Account_Consumer> list = new ArrayList<Account_Consumer>();
		try {
			conn = UtilC3P0.getConnection();
			String sql = "select id,loginname,password,registertime,lastlogintime,ip from account_consumer order by id desc";
			list = qr.query(conn, sql, new BeanListHandler<Account_Consumer>(Account_Consumer.class));
		} catch (SQLException e) {
			throw new DaoException("查询失败!",e);
		}
		return null;
	}

}
