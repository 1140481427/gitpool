package com.neusoft.ruldaoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.neusoft.accountdao.DaoException;
import com.neusoft.entity.Rule;
import com.neusoft.entity.T_Product_List;
import com.neusoft.ruldao.RulDao;
import com.neusoft.utils.PageModel;
import com.neusoft.utils.UtilC3P0;

public class RulDaoImpl implements RulDao {
	
	Connection conn = null;
	QueryRunner qr = new QueryRunner();
	
	@Override
	public boolean insertRul(Rule r) throws DaoException {
		String sql = "insert into rule(id,pid,pm_size) values(seq_rul.nextval,?,?)";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, r.getPid(),r.getPm_size());
			if ( count > 0 ) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("插入规格信息出错",e);
		}
		return false;
	}

	@Override
	public boolean updateRul(Rule r) throws DaoException {
		String sql = "update rule set pid = ?, pm_size = ? where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, r.getPid(),r.getPm_size(),r.getId());
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("修改规格信息出错",e);
		}
		return false;
	}

	@Override
	public boolean delRul(int id) throws DaoException {
		String sql = "delete from rule where id = ?";
		try {
			conn = UtilC3P0.getConnection();
			int count = qr.update(conn, sql, id);
			if ( count > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new DaoException("删除规格信息出错",e);
		}
		return false;
	}

	@Override
	public Rule selectRulById(int id) throws DaoException {
		String sql = "select id, pid, pm_size from rule where id = ?";
		Rule r = null;
		try {
			conn = UtilC3P0.getConnection();
			r = qr.query(conn, sql, new BeanHandler<Rule>(Rule.class), id);
		} catch (SQLException e) {
			throw new DaoException("删除规格信息出错",e);
		}
		return r;
	}

	@Override
	public PageModel<Rule> selectRuleAll(int pageNo, int pageSize) throws DaoException {
		PageModel<Rule> pageModel = new PageModel<Rule>();
		List<Rule> list = new ArrayList<Rule>();
		try {
			conn = UtilC3P0.getConnection();
			StringBuffer sbSql = new StringBuffer();
			sbSql.append("select * from ")
				.append("( ")
				.append("select rownum as rn, t.* ")
				.append("from ( ")
				.append("select * from rule ")
				.append(") t where rownum <= ? ")
				.append(") where rn>? ");
			String sql = sbSql.toString();
			list = qr.query(conn, sql, new BeanListHandler<Rule>(Rule.class),pageNo * pageSize,(pageNo - 1) * pageSize);
			pageModel.setTotalRecords(getRuleTotalRecords());
			pageModel.setList(list);
		} catch (SQLException e) {
			throw new DaoException("分页查询失败!",e);
		}
		return pageModel;
	}

	@Override
	public int getRuleTotalRecords() throws DaoException {
		String sql = "select count(id) from rule";
		int num = 0; 
		try {
			conn = UtilC3P0.getConnection();
			Object obj = qr.query(conn, sql, new ScalarHandler<Object>());
			num = Integer.parseInt(obj.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
