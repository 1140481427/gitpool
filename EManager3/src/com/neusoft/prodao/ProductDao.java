package com.neusoft.prodao;

import java.util.List;
import java.util.Map;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.T_Product_List;
import com.neusoft.utils.PageModel;

/**
 * 商品信息dao
 * @author Administrator
 *
 */
public interface ProductDao {
	
	/**
	 * 添加商品信息
	 * @param pro
	 * @return
	 * @throws DaoException
	 */
	public boolean addPro(T_Product_List pro) throws DaoException;
	
	
	/**
	 * 修改商品信息
	 * @param pro
	 * @return
	 * @throws DaoException
	 */
	public boolean updatePro(T_Product_List pro) throws DaoException;
	
	
	/**
	 * 删除商品信息
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public boolean delPro(int pid) throws DaoException;	//pro_item_no
	
	/**
	 * 根据id查询商品
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public T_Product_List showProById(int pid) throws DaoException;
	
	
	/**
	 * 根据hot查询商品
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public PageModel<T_Product_List> showProByHot(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 根据折扣查询商品
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public PageModel<T_Product_List> showProByZK(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 根据上线查询商品
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public PageModel<T_Product_List> showProByOnline(int pageNo,int pageSize) throws DaoException;
	
	
	
	/**
	 * 分页查询商品信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public PageModel<T_Product_List> showPro(int pageNo,int pageSize) throws DaoException;
	
	/**
	 * 获取商品总数
	 * @return
	 * @throws DaoException
	 */
	public int getProTotalRecords() throws DaoException;
	
	/**
	 * 获取Online商品总数
	 * @return
	 * @throws DaoException
	 */
	public int getOnlineProTotalRecords() throws DaoException;
	
	/**
	 * 获取ZK商品总数
	 * @return
	 * @throws DaoException
	 */
	public int getZKProTotalRecords() throws DaoException;
	
	/**
	 * 获取Hot商品总数
	 * @return
	 * @throws DaoException
	 */
	public int getHotProTotalRecords() throws DaoException;
	
	/**
	 * 查询商品一级类别
	 * @return
	 * @throws DaoException
	 */
	public Map<Integer,String> showProOneCate() throws DaoException;
	
	/**
	 * 查询商品二级类别
	 * @param pid
	 * @return
	 * @throws DaoException
	 */
	public Map<Integer,String> showProTowCate(int pid) throws DaoException;
}
