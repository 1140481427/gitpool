package com.neusoft.addressdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.addressdao.AddressDao;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Address;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

/**
 * 地址实现类
 * @author Administrator
 *
 */
public class AddressDaoImpl implements AddressDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertAddress(Address ad) throws DaoException {
		String sql = "insert into address(id,province,city,area) values(seq_address.nextval,?,?,?,);";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ad.getProvince(),ad.getCity(),ad.getArea());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入地址信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateAddress(Address ad) throws DaoException {
		String sql = "update address set province = ?,city = ?,area = ? where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, ad.getProvince(),ad.getCity(),ad.getArea(),ad.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改地址信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delAddress(int id) throws DaoException {
		String sql = "delete from address where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除地址信息出错",e);
		}
		return false;
	}

	@Override
	public Address selectAddress(int id) throws DaoException {
		String sql = "select id,province,city,area from address where id = ?";
		Address as = null;
		try {
			conn = UtilC3P0.getConnection();
			as = qr.query(conn, sql, new BeanHandler<Address>(Address.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除地址信息出错",e);
		}
		return as;
	}

	@Override
	public PageModel<Address> selectAddress(int pageNo,int pageSize) throws DaoException {
		PageModel<Address> pageModel = new PageModel<Address>();
		List<Address> list = new ArrayList<Address>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from address ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Address>(Address.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getAddressTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getAddressTotalRecords() throws DaoException {
		String sql = "select count(id) from address ";
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
