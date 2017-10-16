package com.neusoft.ruldao;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Rule;
import com.neusoft.utils.PageModel;

/**
 * 规格接口
 * @author Administrator
 *
 */
public interface RulDao {

	/**
	 * 插入规格信息
	 */
	public boolean insertRul(Rule r) throws DaoException;
	
	/**
	 * 修改规格信息
	 * @param r
	 * @return
	 * @throws DaoException
	 */
	public boolean updateRul(Rule r) throws DaoException;
	
	/**
	 * 根据id删除规格信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public boolean delRul(int id) throws DaoException;
	
	/**
	 * 根据id查询规格信息
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public Rule selectRulById(int id) throws DaoException;
	
	/**
	 * 分页查询所有规格信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<Rule> selectRuleAll(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取规格总数
	 * @return
	 * @throws DaoException
	 */
	public int getRuleTotalRecords() throws DaoException;
}
