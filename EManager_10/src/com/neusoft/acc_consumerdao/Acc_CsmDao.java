package com.neusoft.acc_consumerdao;

import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.utils.PageModel;

/**
 * 买家帐号信息dao
 * @author Administrator
 *
 */
public interface Acc_CsmDao {

	/**
	 * 插入买家帐户信息
	 */
	public boolean insertAccCsm(Account_Consumer as) throws DaoException;
	
	/**
	 * 修改买家帐户信息
	 * @param r
	 * @return
	 * @throws DaoException
	 */
	public boolean updateAccCsm(Account_Consumer as) throws DaoException;
	
	/**
	 * 根据id删除买家帐户信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delAccCsm(int id) throws DaoException;
	
	/**
	 * 根据id查询买家帐户信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Account_Consumer selectAccCsmById(int id) throws DaoException;
	
	/**
	 * 查询所有买家帐户信息
	 * @return
	 * @throws DaoException
	 */
	public List<Account_Consumer> selectAccCsmList() throws DaoException;
	
	/**
	 * 根据用户名查询密码
	 * @param user
	 * @return
	 * @throws DaoException
	 */
	public Account_Consumer selectAccCsmByUser(String user) throws DaoException;
	
	/**
	 * 分页查询所有买家帐户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Account_Consumer> selectAccCsmAll(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取买家帐户总数
	 * @return
	 * @throws DaoException
	 */
	public int getAccCsmTotalRecords() throws DaoException;
}