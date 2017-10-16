package com.neusoft.catedaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.catedao.CateDao;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

/**
 * 类别实现类
 * @author Administrator
 *
 */
public class CateDaoImpl implements CateDao{

	QueryRunner qr = new QueryRunner();

	/**
	 * 添加
	 */
	@Override
	public boolean insertCate(T_Pro_Category c) throws DaoException {
		String sql = "insert into T_Pro_Category(cate_id,cate_name,cate_pid) values(seq_cate.nextval,?,?)";
		Connection conn = null;
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, c.getCate_name(),c.getCate_pid());
			if(count>0){
				System.out.println("插入成功!");
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入类别信息出错!",e);
		}
		return false;
	}

	@Override
	public boolean updateCate(T_Pro_Category c) throws DaoException {
		String sql = "update T_Pro_Category set cate_name = ?, cate_pid = ? where cate_id = ?";
		Connection conn = null;
		try{
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, c.getCate_name(),c.getCate_pid(),c.getCate_id());
			if(count>0){
				System.out.println("修改成功!");
				return true;
			}
		}catch (SQLException e) {
			throw new DaoException("修改类别信息出错!",e);
		}
		return false;
	}
	

	/**
	 * 初步删除
	 */
	@Override
	public boolean delCate(int cid) {
		Connection conn = null;
		try{
			conn = UtilC3P0.getConnection();
			if(delCateByPid(conn,cid)){
				System.out.println("删除成功");
				return true;
			}
			
		}catch (SQLException e) {
			throw new DaoException("删除类别信息出错!",e);
		}
		return false;
	}
	
	/**
	 * 查找是否有子节点并调用删除
	 */
	@Override
	public boolean delCateByPid(Connection conn,int cid) throws DaoException {
		String sql = "select cate_id from T_Pro_Category where cate_pid = ?";
		List<T_Pro_Category> list = new ArrayList<T_Pro_Category>();
		T_Pro_Category c = null;
		try {
			list = qr.query(conn, sql, new BeanListHandler<T_Pro_Category>(T_Pro_Category.class), cid);
			for(int i=0; i<list.size(); i++){
				c = list.get(i);
				//删除所有子节点
				delCateById(conn,c.getCate_id());
			}
			//删除自身
			return delCateById(conn,cid);
		} catch (SQLException e) {
			throw new DaoException("根据pid删除类别信息出错!",e);
		}
	}

	@Override
	public boolean delCateById(Connection conn,int cid) throws DaoException {
		String sql = "delete from T_Pro_Category where cate_id = ?";
		try{
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, cid);
			if(count>0){
				return true;
			}
		}catch (SQLException e) {
			throw new DaoException("根据id删除类别信息出错!",e);
		}
		return false;
	}

	@Override
	public PageModel<T_Pro_Category> selectCateList(int pageNo,int pageSize) throws DaoException {
		PageModel<T_Pro_Category> pm = new PageModel<T_Pro_Category>();
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* from ")
				.append("( ")
				.append("select cate_id,cate_name,cate_pid from T_Pro_Category ")
				.append(")t where rownum <=? ")
				.append(") where rn>?");
		String sql = sbSql.toString();
		Connection conn = null;
		List<T_Pro_Category> list= new ArrayList<T_Pro_Category>();
		try{
			conn = UtilC3P0.getConnection();
			list = qr.query(conn, sql, new BeanListHandler<T_Pro_Category>(T_Pro_Category.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			if(list.size()>0){
				System.out.println("查询全部类别信息成功!");
			}
			pm.setList(list);
			int count = getProTotalRecords();
			pm.setTotalRecords(count);
		}catch (SQLException e) {
			throw new DaoException("查询全部类别信息出错!",e);
		}
		return pm;
	}
	
	@Override
	public T_Pro_Category selectCateById(int cid) throws DaoException {
		String sql = "select cate_id,cate_name,cate_pid from T_Pro_Category where cate_id = ?";
		Connection conn = null;
		T_Pro_Category cate = null;
		try{
			conn = UtilC3P0.getConnection();
			cate = qr.query(conn, sql, new BeanHandler<T_Pro_Category>(T_Pro_Category.class), cid);
		}catch (SQLException e) {
			throw new DaoException("根据id查询类别信息出错!",e);
		}
		return cate;
	}
	
	@Override
	public int getProTotalRecords() throws DaoException {
		int num = 0;
		Connection conn = null;
		String sql = "select count(cate_id) from T_Pro_Category";
		try {
			conn = UtilC3P0.getConnection();
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	
	
}
