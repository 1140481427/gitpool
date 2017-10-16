package com.neusoft.acc_productdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.acc_productdao.AccProductDao;
import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Account_Product;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

public class AccProductDaoImpl implements AccProductDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertAccPro(Account_Product ap) throws DaoException {
		String sql = "insert into account_product(id,loginname,password,registertime,lastlogintime,ip) values(seq_proacc.nextval,?,?,?,?,?);";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ap.getLoginname(),ap.getPassword(),ap.getRegistertime(),ap.getLastlogintime(),ap.getIp());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入规格信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateAccPro(Account_Product ap) throws DaoException {
		String sql = "update account_product set loginname = ?, password = ?, registertime = ?, lastlogintime = ?, ip = ? where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ap.getLoginname(), ap.getPassword(), ap.getRegistertime(), ap.getLastlogintime(), ap.getIp(), ap.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改规格信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delAccPro(int id) throws DaoException {
		String sql = "delete from account_product where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除规格信息出错",e);
		}
		return false;
	}

	@Override
	public Account_Product selectAccProById(int id) throws DaoException {
		String sql = "select id,loginname,password,registertime,lastlogintime,ip from account_product where id = ?";
		Account_Product as = null;
		try {
			conn = UtilC3P0.getConnection();
			as = qr.query(conn, sql, new BeanHandler<Account_Product>(Account_Product.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除规格信息出错",e);
		}
		return as;
	}

	@Override
	public PageModel<Account_Product> selectAccProAll(int pageNo, int pageSize) throws DaoException {
		PageModel<Account_Product> pageModel = new PageModel<Account_Product>();
		List<Account_Product> list = new ArrayList<Account_Product>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from account_product ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Account_Product>(Account_Product.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getAccProTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getAccProTotalRecords() throws DaoException {
		String sql = "select count(id) from account_product ";
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

}
