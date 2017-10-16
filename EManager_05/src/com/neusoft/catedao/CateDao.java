package com.neusoft.catedao;

import java.sql.Connection;
import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.utils.PageModel;

/**
 * 类别dao
 * @author Administrator
 *
 */
public interface CateDao {

	/**
	 * 插入类别
	 * @param c
	 * @return
	 * @throws DaoException
	 */
	public boolean insertCate(T_Pro_Category c) throws DaoException;
	
	/**
	 * 修改类别信息   根据类别Id 
	 * @param c
	 * @return
	 * @throws DaoException
	 */
	public boolean updateCate(T_Pro_Category c) throws DaoException;
	
	/**
	 * 根据id删除类别信息
	 * @param cid
	 * @return
	 * @throws DaoException
	 */
	public boolean delCateById(Connection conn,int cid) throws DaoException;
	
	/**
	 * 查找是否有子节点并删除
	 * @param cid
	 * @return
	 * @throws DaoException
	 */
	public boolean delCateByPid(Connection conn,int cid) throws DaoException;
	
	/**
	 * 根据Id删除
	 */
	public boolean delCate(int cid);
	
	/**
	 * 查询所有类别信息
	 * @return
	 * @throws DaoException
	 */
	public PageModel<T_Pro_Category> selectCateList(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 根据id查询单个类别信息
	 * @return
	 * @throws DaoException
	 */
	public T_Pro_Category selectCateById(int cid) throws DaoException;
	
	/**
	 * 查询记录总数
	 * @return
	 * @throws DaoException
	 */
	public int getProTotalRecords() throws DaoException;

}
