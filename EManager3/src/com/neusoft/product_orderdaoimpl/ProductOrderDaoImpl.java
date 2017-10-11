package com.neusoft.product_orderdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.ProductOrderInfo;
import com.neusoft.product_orderdao.ProductOrderDao;
import com.neusoft.utils.UtilC3P0;

public class ProductOrderDaoImpl implements ProductOrderDao{
	
	private Connection conn = null;
	
	public ProductOrderDaoImpl(){
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertPo(ProductOrderInfo po) throws DaoException {
		// 
		return false;
	}

	@Override
	public boolean updatePo(ProductOrderInfo po) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProductOrderInfo> selectPo(int pid, int oid) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
