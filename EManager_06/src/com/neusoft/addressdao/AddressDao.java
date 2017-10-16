package com.neusoft.addressdao;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Address;
import com.neusoft.utils.PageModel;

/**
 * 地址dao
 * @author Administrator
 *
 */
public interface AddressDao {

	/**
	 * 插入买家地址信息
	 */
	public boolean insertAddress(Address ad) throws DaoException;
	
	/**
	 * 修改买家地址信息
	 * @param 
	 * @return
	 * @throws DaoException
	 */
	public boolean updateAddress(Address ad) throws DaoException;
	
	/**
	 * 根据id删除买家地址信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delAddress(int id) throws DaoException;
	
	/**
	 * 根据id查询买家地址信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Address selectAddress(int id) throws DaoException;
	
	/**
	 * 分页查询所有买家地址信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Address> selectAddress(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取买家地址总数
	 * @return
	 * @throws DaoException
	 */
	public int getAddressTotalRecords() throws DaoException;
}
