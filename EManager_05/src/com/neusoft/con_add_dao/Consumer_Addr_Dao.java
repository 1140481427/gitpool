package com.neusoft.con_add_dao;

import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.entity.Consumer_Addr;
import com.neusoft.utils.PageModel;

/**
 * 买家地址Dao
 * @author Administrator
 *
 */
public interface Consumer_Addr_Dao {
	
	/**
	 * 插入买家地址信息
	 */
	public boolean insertCsmAddr(Consumer_Addr ca) throws DaoException;
	
	/**
	 * 修改买家地址信息
	 * @param r
	 * @return
	 * @throws DaoException
	 */
	public boolean updateCsmAddr(Consumer_Addr ca) throws DaoException;
	
	/**
	 * 根据id删除买家地址信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delCsmAddr(int id) throws DaoException;
	
	/**
	 * 根据id查询买家地址信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Consumer_Addr selectCsmAddrById(int id) throws DaoException;
	
	/**
	 * 查询所有
	 * @return
	 * @throws DaoException
	 */
	public List<Consumer_Addr> selectCsmAddrList() throws DaoException;
	
	/**
	 * 分页查询所有买家地址信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Consumer_Addr> selectCsmAddrAll(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取买家地址总数
	 * @return
	 * @throws DaoException
	 */
	public int getCsmAddrTotalRecords() throws DaoException;
	
	
}
