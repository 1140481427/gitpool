package com.neusoft.orderdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Orderinfo;
import com.neusoft.orderdao.OrderDao;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

public class OrderDaoImpl implements OrderDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	public OrderDaoImpl() {
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertOrder(Orderinfo oi) throws DaoException {
		String sql = "insert into orderinfo(id,orderno,orderstatus,paystatus,ordertime,paytime,addrinfo,mask,num,totalprice,pid) values(seq_oderinfo.nextval,?,?,?,sysdate,sysdate,?,?,?,?,?)";
		try {
			int count = qr.update(conn, sql, oi.getOrderno(),oi.getOrderstatus(),oi.getPaystatus(),oi.getAddrinfo(),oi.getMask(),oi.getNum(),oi.getTotalprice(),oi.getPid());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入订单信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateOrder(Orderinfo oi) throws DaoException {
		String sql = "update orderinfo set orderno=?, orderstatus=?,paystatus=?,ordertime=?,paytime=?,addrinfo=?,mask=?,num=?,totalprice=?,pid=? where id = ?";
		try {
			int count = qr.update(conn, sql, oi.getOrderno(),oi.getOrderstatus(),oi.getPaystatus(),oi.getOrdertime(),oi.getPaytime(),oi.getAddrinfo(),oi.getMask(),oi.getId(),oi.getNum(),oi.getTotalprice(),oi.getPid());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改订单信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delOrder(int id) throws DaoException {
		String sql = "delete from orderinfo where id = ?";
		try {
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除订单信息出错",e);
		}
		return false;
	}

	@Override
	public Orderinfo selectOrder(int id) throws DaoException {
		String sql = "select id,orderno,orderstatus,paystatus,ordertime,paytime,addrinfo,mask,num,totalprice,pid from orderinfo where id = ?";
		Orderinfo oi = null;
		try {
			oi = qr.query(conn, sql, new BeanHandler<Orderinfo>(Orderinfo.class), id);
		} catch (SQLException e) {
			throw new DaoException("查询订单信息出错",e);
		}
		return oi;
	}

	@Override
	public PageModel<Orderinfo> selectOrder(int pageNo, int pageSize) throws DaoException {
		PageModel<Orderinfo> pageModel = new PageModel<Orderinfo>();
		List<Orderinfo> list = new ArrayList<Orderinfo>();
		try {
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from orderinfo order by id desc ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Orderinfo>(Orderinfo.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getOrderTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页订单失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getOrderTotalRecords() throws DaoException {
		String sql = "select count(id) from orderinfo ";
		int num = 0; 
		try {
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public Orderinfo selectOrderByDesc() throws DaoException {
		String sql = "select * from orderinfo order by id desc";
		Orderinfo oi = null;
		try{
			oi = qr.query(conn, sql, new BeanHandler<Orderinfo>(Orderinfo.class));
		}catch(SQLException e){
			throw new DaoException("查询新添加订单id出错!");
		}
		return oi;
	}

	@Override
	public PageModel<Orderinfo> selectOrderByLike(int pageNo, int pageSize, String likeName) throws DaoException {
		PageModel<Orderinfo> pageModel = new PageModel<Orderinfo>();
		List<Orderinfo> list = new ArrayList<Orderinfo>();
		String sql = "select count(o.rowid) from orderinfo o join consumer_addr ca on o.addrinfo = ca.id where name like '%"+ likeName +"%' or phone like '%"+ likeName +"%' or orderno like '" + likeName +"%'";
		int num = 0; 
		
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select * from ( ")
				.append("select rownum  as rn, t.* from (")
				.append("select * from orderinfo o ")
				.append("join consumer_addr ca ")
				.append("on o.addrinfo = ca.id ")
				.append("where name like '%"+ likeName +"%' or phone like '%"+ likeName +"%' or orderno like '" + likeName +"%'" )
				.append(")t where rownum  <=? ")
				.append(") where rn >= ?");

		try {
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
			String sbstr = sbSql.toString();
			list = qr.query(conn, sbstr, new BeanListHandler<Orderinfo>(Orderinfo.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(num);
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("根据搜索框模糊查询订单失败!",e);
		}
		return pageModel;
	}
	
	

}
