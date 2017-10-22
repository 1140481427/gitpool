package com.neusoft.shop_consumerdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Util;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.shop_consumerdao.ShopConsumerDao;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

/**
 * ��Ҹ�����Ϣʵ����
 * @author Administrator
 *
 */
public class ShopConsumerDaoImpl implements ShopConsumerDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	public ShopConsumerDaoImpl() {
		try {
			conn = UtilC3P0.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean insertShopCsm(Shoppinginfo_Consumer sc ) throws DaoException {
		String sql = "insert into Shoppinginfo_Consumer(id,aid,nickname,money,count,lasttime) values(seq_shop_consumer.nextval,?,?,?,?,sysdate)";
		try {
			int count = qr.update(conn, sql, sc.getAid(), sc.getNickname(), sc.getMoney(),sc.getCount());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("������Ҹ�����Ϣ����",e);
		}
		return false;
	}

	@Override
	public boolean updateShopCsm(Shoppinginfo_Consumer sc ) throws DaoException {
		String sql = "update Shoppinginfo_Consumer set aid = ?, nickname = ?, money = ?,count=?, lasttime = sysdate where id = ?";
		try {
			int count = qr.update(conn, sql, sc.getAid(), sc.getNickname(), sc.getMoney(), sc.getCount(), sc.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("�޸���Ҹ�����Ϣ����",e);
		}
		return false;
	}

	@Override
	public boolean delShopCsm(int id) throws DaoException {
		String sql = "delete from Shoppinginfo_Consumer where id = ?";
		try {
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("ɾ����Ҹ�����Ϣ����",e);
		}
		return false;
	}

	@Override
	public Shoppinginfo_Consumer selectShopCsmById(int id) throws DaoException {
		String sql = "select id,aid,nickname,money,count,lasttime from Shoppinginfo_Consumer where aid = ?";
		Shoppinginfo_Consumer sc = null;
		try {
			sc = qr.query(conn, sql, new BeanHandler<Shoppinginfo_Consumer>(Shoppinginfo_Consumer.class), id);
		} catch (SQLException e) {
			throw new DaoException("��ѯ��ҹ�����Ϣ����",e);
		}
		return sc;
	}

	@Override
	public PageModel<Shoppinginfo_Consumer> selectShopCsmAll(int pageNo, int pageSize,String type) throws DaoException {
		PageModel<Shoppinginfo_Consumer> pageModel = new PageModel<Shoppinginfo_Consumer>();
		List<Shoppinginfo_Consumer> list = new ArrayList<Shoppinginfo_Consumer>();
		try {
			StringBuffer sbSql = new StringBuffer();
			if("0".equals(type)) {
				sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Shoppinginfo_Consumer order by id desc ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			} else if("1".equals(type)) {
				sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Shoppinginfo_Consumer order by lasttime desc ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			} else if("2".equals(type)) {
				sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Shoppinginfo_Consumer order by count desc ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			} else if("3".equals(type)) {
				sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Shoppinginfo_Consumer order by money desc ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			}
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Shoppinginfo_Consumer>(Shoppinginfo_Consumer.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getShopCsmTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("��ҳ��ѯʧ��!",e);
		}
		return pageModel;
	}

	@Override
	public int getShopCsmTotalRecords() throws DaoException {
		String sql = "select count(id) from Shoppinginfo_Consumer ";
		int num = 0; 
		try {
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
