package com.neusoft.prodaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.rmi.CORBA.Util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.T_Pro_Category;
import com.neusoft.entity.T_Product_List;
import com.neusoft.prodao.ProductDao;
import com.neusoft.utils.MD5Utils;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

public class ProductDaoImpl implements ProductDao {
	QueryRunner qr = new QueryRunner();
	Connection conn = null;
	@Override
	public boolean addPro(T_Product_List pro) throws DaoException {
		String sql = "insert into T_Product_List(pid,pro_item_no,pro_name,pro_category_no,pro_spec_no,pro_img_src,pro_price,pro_hits,isrecommand,isspecial_offer,isup_line,pro_paritculars)"
				+ " values(seq_pro.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, pro.getPro_category_no(),pro.getPro_name(),pro.getPro_category_no(),pro.getPro_spec_no(),pro.getPro_img_src(),pro.getPro_price(),pro.getPro_hits(),pro.getIsRecommand(),pro.getIsSpecial_offer(),pro.getIsUp_Line(),pro.getPro_paritculars());
			if(count > 0){
				return true; 
			}
		} catch (SQLException e) {
			throw new DaoException("添加商品失败!",e);
		}
		return false;
	}
	
	@Override
	public boolean updatePro(T_Product_List pro) throws DaoException {
		String sql = "update t_product_list set pro_item_no = ?,pro_name = ?,pro_category_no = ?,pro_spec_no = ?,pro_img_src = ?,"
				+ "pro_price = ?,pro_hits = ?,isrecommand = ?,isspecial_offer = ?,isup_line = ?,pro_paritculars = ? where pid = ? ";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, pro.getPro_category_no(),pro.getPro_name(),pro.getPro_category_no(),pro.getPro_spec_no(),pro.getPro_img_src(),pro.getPro_price(),pro.getPro_hits(),pro.getIsRecommand(),pro.getIsSpecial_offer(),pro.getIsUp_Line(),pro.getPro_paritculars(),pro.getPid());
			if(count > 0){
				return true; 
			}
		} catch (SQLException e) {
			throw new DaoException("修改商品失败!",e);
		}
		return false;
	}

	@Override
	public boolean delPro(int pid) throws DaoException {
		String sql = "delete from t_product_list where pid = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, pid);
			if(count>0){
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除商品失败!",e);
		}
		
		return false;
	}

	@Override
	public PageModel<T_Product_List> showPro(int pageNo, int pageSize) throws DaoException {
		PageModel<T_Product_List> pageModel = new PageModel<T_Product_List>();
		List<T_Product_List> list = new ArrayList<T_Product_List>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from t_product_list ")
				.append(") t where rownum <= ? ")
				.append(") where rn>? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<T_Product_List>(T_Product_List.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getProTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getProTotalRecords() throws DaoException {
		int num = 0;
		String sql = "select count(pid) from t_product_list";
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
	

	@Override
	public Map<Integer, String> showProOneCate() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, String> showProTowCate(int pid) throws DaoException {
		String sql = "select cate_id, cate_name from t_pro_category where cate_pid = ?";
		Map<Integer, String> map = new HashMap();
		try{
			conn = UtilC3P0.getConnection();
			List<T_Pro_Category> list = qr.query(conn, sql, new BeanListHandler<T_Pro_Category>(T_Pro_Category.class), pid);
			if(list.size()>0){
				for(int i=0; i<list.size(); i++){
					T_Pro_Category tpc = list.get(i);
					map.put(tpc.getCate_id(), tpc.getCate_name());
				}
			}
		}catch(SQLException e) {
			throw new DaoException("查询二级类别失败!",e);
		}
		return map;
	}

	
	@Override
	public T_Product_List showProById(int pid) throws DaoException {
		String sql = "select pid,pro_item_no,pro_name,pro_category_no,pro_spec_no,pro_img_src,pro_price,pro_hits,isrecommand,isspecial_offer,isup_line,pro_paritculars from T_Product_List where pid = ?";
		T_Product_List pro = null;
		try{
			conn = UtilC3P0.getConnection();
			pro  =  qr.query(conn, sql, new BeanHandler<T_Product_List>(T_Product_List.class), pid);
		}catch(SQLException e) {
			throw new DaoException("根据id查询商品失败!",e);
		}
		return pro;
	}

	
	
	public static void main(String[] args) {
		//new ProductDaoImpl().getProTotalRecords();
		PageModel<T_Product_List> page = new ProductDaoImpl().showPro(1, 2);
		List<T_Product_List> list = page.getList();
		System.out.println(list);
		
		//System.out.println(new ProductDaoImpl().getProTotalRecords());
		/*Map<Integer, String> map = new ProductDaoImpl().showProTowCate(102);
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		  while (it.hasNext()) {
		   Map.Entry<Integer, String> entry = it.next();
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		  }*/
	}

	@Override
	public PageModel<T_Product_List> showProByHot(int pageNo,int pageSize) throws DaoException {
		PageModel<T_Product_List> pageModel = new PageModel<T_Product_List>();
		List<T_Product_List> list = new ArrayList<T_Product_List>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from t_product_list where ISRECOMMAND = 1 ")
				.append(") t where rownum <= ? ")
				.append(") where rn>? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<T_Product_List>(T_Product_List.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getHotProTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public PageModel<T_Product_List> showProByZK(int pageNo,int pageSize) throws DaoException {
		PageModel<T_Product_List> pageModel = new PageModel<T_Product_List>();
		List<T_Product_List> list = new ArrayList<T_Product_List>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from t_product_list where ISSPECIAL_OFFER = 1 ")
				.append(") t where rownum <= ? ")
				.append(") where rn>? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<T_Product_List>(T_Product_List.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getZKProTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public PageModel<T_Product_List> showProByOnline(int pageNo,int pageSize) throws DaoException {
		PageModel<T_Product_List> pageModel = new PageModel<T_Product_List>();
		List<T_Product_List> list = new ArrayList<T_Product_List>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from t_product_list where ISUP_LINE = 1 ")
				.append(") t where rownum <= ? ")
				.append(") where rn>? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<T_Product_List>(T_Product_List.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getOnlineProTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getOnlineProTotalRecords() throws DaoException {
		int num = 0;
		String sql = "select count(pid) from t_product_list where ISUP_LINE = 1";
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

	@Override
	public int getZKProTotalRecords() throws DaoException {
		int num = 0;
		String sql = "select count(pid) from t_product_list where ISSPECIAL_OFFER = 1";
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

	@Override
	public int getHotProTotalRecords() throws DaoException {
		int num = 0;
		String sql = "select count(pid) from t_product_list where ISRECOMMAND = 1";
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
