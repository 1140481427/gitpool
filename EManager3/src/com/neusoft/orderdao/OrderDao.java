package com.neusoft.orderdao;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Orderinfo;
import com.neusoft.utils.PageModel;

/**
 * 订单dao
 * @author Administrator
 *
 */
public interface OrderDao {

	/**
	 * 插入订单信息
	 */
	public boolean insertOrder(Orderinfo oi) throws DaoException;
	
	/**
	 * 修改订单信息
	 * @param r
	 * @return
	 * @throws DaoException
	 */
	public boolean updateOrder(Orderinfo oi) throws DaoException;
	
	/**
	 * 根据id删除订单信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delOrder(int id) throws DaoException;
	
	/**
	 * 根据id查询订单信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Orderinfo selectOrder(int id) throws DaoException;
	
	/**
	 * 分页查询所有订单信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Orderinfo> selectOrder(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取订单总数
	 * @return
	 * @throws DaoException
	 */
	public int getOrderTotalRecords() throws DaoException;
}
