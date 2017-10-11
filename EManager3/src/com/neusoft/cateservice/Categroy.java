package com.neusoft.cateservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.neusoft.accountdao.DaoException;
import com.neusoft.accountdao.DaoFactory;
import com.neusoft.catedao.CateDao;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

/**
 * 类别业务逻辑
 * @author Administrator
 *
 */
public class Categroy {

	Connection conn = null;
	CateDao cd = DaoFactory.getInstance("catedaoimpl",CateDao.class);;
	
	
	public boolean addCate(T_Pro_Category c){
		boolean flag = false;
		try{
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			flag = cd.insertCate(c);
			if(flag){
				System.out.println("添加成功");
			}
			conn.commit();
		}catch(DaoException e){
			UtilC3P0.rollback(conn);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			UtilC3P0.rollback(conn);
			e.printStackTrace();
		}finally{
			UtilC3P0.close(conn);
		}
		return false;
	}
	
	public boolean modiflyCate(T_Pro_Category c){
		boolean flag = false;
		try{
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			flag = cd.updateCate(c);
			if(flag){
				System.out.println("修改成功");
			}
			conn.commit();
		}catch(DaoException e){
			UtilC3P0.rollback(conn);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			UtilC3P0.rollback(conn);
			e.printStackTrace();
		}finally{
			UtilC3P0.close(conn);
		}
		return false;
	}
	
	public boolean removeCate(int cid){
		boolean flag = false;
		try{
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			flag = cd.delCate(cid);
			if(flag){
				System.out.println("删除成功");
			}
			conn.commit();
		}catch(DaoException e){
			UtilC3P0.rollback(conn);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			UtilC3P0.rollback(conn);
			e.printStackTrace();
		}finally{
			UtilC3P0.close(conn);
		}
		return flag;
	}
	
	public T_Pro_Category showCate(int cid){
		T_Pro_Category c = null;
		try{
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			c = cd.selectCateById(cid);
			conn.commit();
		}catch(DaoException e){
			UtilC3P0.rollback(conn);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			UtilC3P0.rollback(conn);
			e.printStackTrace();
		}finally{
			UtilC3P0.close(conn);
		}
		return c;
	}
	
	public PageModel<T_Pro_Category> showCateAll(int pageNo,int pageSize){
		PageModel<T_Pro_Category> pm = new PageModel<T_Pro_Category>();
		List<T_Pro_Category> list =null;
		try{
			conn = UtilC3P0.getConnection();
			conn.setAutoCommit(false);
			pm = cd.selectCateList(pageNo, pageSize);
			conn.commit();
		}catch(DaoException e){
			UtilC3P0.rollback(conn);
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			UtilC3P0.rollback(conn);
			e.printStackTrace();
		}finally{
			UtilC3P0.close(conn);
		}
		return pm;
	}
	
}
