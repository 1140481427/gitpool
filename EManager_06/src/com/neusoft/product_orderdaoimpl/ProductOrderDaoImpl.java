package com.neusoft.product_orderdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.ProductOrderInfo;
import com.neusoft.product_orderdao.ProductOrderDao;
import com.neusoft.utils.UtilC3P0;

/**
 * 插入订单
 * 
 * @author Administrator
 *
 */

public class ProductOrderDaoImpl implements ProductOrderDao {

	private Connection conn = null;
	
	QueryRunner qr = new QueryRunner();

	public ProductOrderDaoImpl() {
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertPo(ProductOrderInfo[] po) throws DaoException {
		String sql ="insert into product_orderinfo(orderid,productid,num) values(?,?,?)";
		boolean flag = true;
		Object params[][] = new Object[po.length][];
		for(int i=0; i<po.length; i++) {
			params[i] = new Object[] {po[i].getOrderid(),po[i].getProductid(),po[i].getNum()};
		}
		try {
			int[] count =	qr.batch(conn, sql, params);
			if(count.length == po.length){
				flag = true;
			}
		} catch (SQLException e) {
			throw new DaoException("批量添加失败!");
		}
		return flag;
	}

	

	@Override
	public boolean updatePo(ProductOrderInfo po) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductOrderInfo> selectPo(int oid) throws DaoException {
		String sql = "select * from product_orderinfo where orderid = ?";
		List<ProductOrderInfo> list = new ArrayList<ProductOrderInfo>();
		try {
			list = qr.query(conn, sql,new BeanListHandler<ProductOrderInfo>(ProductOrderInfo.class), oid);
		} catch (SQLException e) {
			throw new DaoException("查询订单信息失败!");
		}
		return list;
	}

}
