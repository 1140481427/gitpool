package com.neusoft.shop_consumerdaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * 买家个人信息实现类
 * @author Administrator
 *
 */
public class ShopConsumerDaoImpl implements ShopConsumerDao {

	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertShopCsm(Shoppinginfo_Consumer sc ) throws DaoException {
		String sql = "insert into Shoppinginfo_Consumer(id,aid,nickname,money,count,lasttime) values(seq_shop_consumer.nextval,?,?,?,?,sysdate)";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, sc.getAid(), sc.getNickname(), sc.getMoney(),sc.getCount());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入买家个人信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateShopCsm(Shoppinginfo_Consumer sc ) throws DaoException {
		String sql = "update Shoppinginfo_Consumer set aid = ?, nickname = ?, money = ?,count=?, lasttime = sysdate where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, sc.getAid(), sc.getNickname(), sc.getMoney(), sc.getCount(), sc.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改买家个人信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delShopCsm(int id) throws DaoException {
		String sql = "delete from Shoppinginfo_Consumer where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除买家个人信息出错",e);
		}
		return false;
	}

	@Override
	public Shoppinginfo_Consumer selectShopCsmById(int id) throws DaoException {
		String sql = "select id,aid,nickname,money,count,lasttime from Shoppinginfo_Consumer where aid = ?";
		Shoppinginfo_Consumer sc = null;
		try {
			conn = UtilC3P0.getConnection();
			sc = qr.query(conn, sql, new BeanHandler<Shoppinginfo_Consumer>(Shoppinginfo_Consumer.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除买家个人信息出错",e);
		}
		return sc;
	}

	@Override
	public PageModel<Shoppinginfo_Consumer> selectShopCsmAll(int pageNo, int pageSize) throws DaoException {
		PageModel<Shoppinginfo_Consumer> pageModel = new PageModel<Shoppinginfo_Consumer>();
		List<Shoppinginfo_Consumer> list = new ArrayList<Shoppinginfo_Consumer>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from Shoppinginfo_Consumer ")
				.append(") t where rownum <= ? ")
				.append(") where rn > ? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Shoppinginfo_Consumer>(Shoppinginfo_Consumer.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getShopCsmTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getShopCsmTotalRecords() throws DaoException {
		String sql = "select count(id) from Shoppinginfo_Consumer ";
		int num = 0; 
		try {
			conn = UtilC3P0.getConnection();
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

}
