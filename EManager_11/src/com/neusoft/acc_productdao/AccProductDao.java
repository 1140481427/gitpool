package com.neusoft.acc_productdao;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account_Product;
import com.neusoft.utils.PageModel;

/**
 * 卖家帐号信息
 * @author Administrator
 *
 */
public interface AccProductDao {
	/**
	 * 插入卖家帐户信息
	 */
	public boolean insertAccPro(Account_Product ap) throws DaoException;
	
	/**
	 * 修改卖家帐户信息
	 * @param r
	 * @return
	 * @throws DaoException
	 */
	public boolean updateAccPro(Account_Product ap) throws DaoException;
	
	/**
	 * 根据id删除卖家帐户信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delAccPro(int id) throws DaoException;
	
	/**
	 * 根据id查询卖家帐户信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Account_Product selectAccProById(int id) throws DaoException;
	
	/**
	 * 分页查询所有卖家帐户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Account_Product> selectAccProAll(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取卖家帐户总数
	 * @return
	 * @throws DaoException
	 */
	public int getAccProTotalRecords() throws DaoException;
}
