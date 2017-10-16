package com.neusoft.product_orderdao;

import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.ProductOrderInfo;

public interface ProductOrderDao {

	public boolean insertPo(ProductOrderInfo[] po) throws DaoException;
	
	public boolean updatePo(ProductOrderInfo po) throws DaoException;
	
	//public boolean delPo(int id) throws DaoException;
	
	public List<ProductOrderInfo> selectPo(int oid) throws DaoException;
}
