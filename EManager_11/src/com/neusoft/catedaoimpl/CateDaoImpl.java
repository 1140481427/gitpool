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
 * ���ʵ����
 * @author Administrator
 *
 */
public class CateDaoImpl implements CateDao{
	Connection conn =null;
	QueryRunner qr = new QueryRunner();
	public CateDaoImpl(){
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����
	 */
	@Override
	public boolean insertCate(T_Pro_Category c) throws DaoException {
		String sql = "insert into T_Pro_Category(cate_id,cate_name,cate_pid) values(seq_cate.nextval,?,?)";
		try {
			int count = qr.update(conn, sql, c.getCate_name(),c.getCate_pid());
			if(count>0){
				System.out.println("����ɹ�!");
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("���������Ϣ����!",e);
		}
		return false;
	}

	@Override
	public boolean updateCate(T_Pro_Category c) throws DaoException {
		String sql = "update T_Pro_Category set cate_name = ?, cate_pid = ? where cate_id = ?";
		try{
			int count = qr.update(conn, sql, c.getCate_name(),c.getCate_pid(),c.getCate_id());
			if(count>0){
				System.out.println("�޸ĳɹ�!");
				return true;
			}
		}catch (SQLException e) {
			throw new DaoException("�޸������Ϣ����!",e);
		}
		return false;
	}
	

	/**
	 * ����ɾ��
	 */
	@Override
	public boolean delCate(int cid) {
			if(delCateByPid(conn,cid)){
				System.out.println("ɾ���ɹ�");
				return true;
			}
		return false;
	}
	
	/**
	 * �����Ƿ����ӽڵ㲢����ɾ��
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
				//ɾ�������ӽڵ�
				delCateById(conn,c.getCate_id());
			}
			//ɾ������
			return delCateById(conn,cid);
		} catch (SQLException e) {
			throw new DaoException("����pidɾ�������Ϣ����!",e);
		}
	}

	@Override
	public boolean delCateById(Connection conn,int cid) throws DaoException {
		String sql = "delete from T_Pro_Category where cate_id = ?";
		try{
			int count = qr.update(conn, sql, cid);
			if(count>0){
				return true;
			}
		}catch (SQLException e) {
			throw new DaoException("����idɾ�������Ϣ����!",e);
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
				.append("select cate_id,cate_name,cate_pid from T_Pro_Category order by cate_id desc ")
				.append(")t where rownum <=? ")
				.append(") where rn>?");
		String sql = sbSql.toString();
		List<T_Pro_Category> list= new ArrayList<T_Pro_Category>();
		try{
			list = qr.query(conn, sql, new BeanListHandler<T_Pro_Category>(T_Pro_Category.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			if(list.size()>0){
				System.out.println("��ѯȫ�������Ϣ�ɹ�!");
			}
			pm.setList(list);
			int count = getProTotalRecords();
			pm.setTotalRecords(count);
		}catch (SQLException e) {
			throw new DaoException("��ѯȫ�������Ϣ����!",e);
		}
		return pm;
	}
	
	@Override
	public T_Pro_Category selectCateById(int cid) throws DaoException {
		String sql = "select cate_id,cate_name,cate_pid from T_Pro_Category where cate_id = ?";
		T_Pro_Category cate = null;
		try{
			cate = qr.query(conn, sql, new BeanHandler<T_Pro_Category>(T_Pro_Category.class), cid);
		}catch (SQLException e) {
			throw new DaoException("����id��ѯ�����Ϣ����!",e);
		}
		return cate;
	}
	
	@Override
	public int getProTotalRecords() throws DaoException {
		int num = 0;
		String sql = "select count(cate_id) from T_Pro_Category";
		try {
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	
	
}