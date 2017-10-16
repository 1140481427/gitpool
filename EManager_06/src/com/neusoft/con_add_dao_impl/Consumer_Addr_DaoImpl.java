package com.neusoft.con_add_dao_impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.con_add_dao.Consumer_Addr_Dao;
import com.neusoft.entity.Consumer_Addr;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

/**
 * 买家地址实体类
 * @author Administrator
 *
 */
public class Consumer_Addr_DaoImpl implements Consumer_Addr_Dao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertCsmAddr(Consumer_Addr ca) throws DaoException {
		String sql = "insert into Consumer_Addr(id,name,addr,phone,provice,city,Area) values(seq_consumer_addr.nextval,?,?,?,?,?,?)";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ca.getName(),ca.getAddr(),ca.getPhone(),ca.getProvice(),ca.getCity(),ca.getArea());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入买家地址信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateCsmAddr(Consumer_Addr ca) throws DaoException {
		String sql = "update Consumer_Addr set name = ?,addr = ?,phone = ?,provice = ?,city = ?,Area = ? where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ca.getName(),ca.getAddr(),ca.getPhone(),ca.getProvice(),ca.getCity(),ca.getArea(),ca.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改买家地址信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delCsmAddr(int id) throws DaoException {
		String sql = "delete from Consumer_Addr where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除买家地址信息出错",e);
		}
		return false;
	}

	@Override
	public Consumer_Addr selectCsmAddrById(int id) throws DaoException {
		String sql = "select id,name,addr,phone,provice,city,Area from Consumer_Addr where id = ?";
		Consumer_Addr as = null;
		try {
			conn = UtilC3P0.getConnection();
			as = qr.query(conn, sql, new BeanHandler<Consumer_Addr>(Consumer_Addr.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除买家地址信息出错",e);
		}
		return as;
	}

	@Override
	public PageModel<Consumer_Addr> selectCsmAddrAll(int pageNo,int pageSize) throws DaoException {
		PageModel<Consumer_Addr> pageModel = new PageModel<Consumer_Addr>();
		List<Consumer_Addr> list = new ArrayList<Consumer_Addr>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Consumer_Addr ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Consumer_Addr>(Consumer_Addr.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getCsmAddrTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getCsmAddrTotalRecords() throws DaoException { 
		String sql = "select count(id) from Consumer_Addr ";
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
	public List<Consumer_Addr> selectCsmAddrList() throws DaoException {
		List<Consumer_Addr> list = new ArrayList<Consumer_Addr>();
		try {
			conn = UtilC3P0.getConnection();
			
			String sql = "select * from Consumer_Addr order by id desc";
			list = qr.query(conn, sql, new BeanListHandler<Consumer_Addr>(Consumer_Addr.class));
		} catch (SQLException e) {
			throw new DaoException("查询失败!",e);
		}
		return list;
	}
}
