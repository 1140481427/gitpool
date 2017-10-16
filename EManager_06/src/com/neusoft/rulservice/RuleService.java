package com.neusoft.rulservice;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.Rule;
import com.neusoft.ruldao.RulDao;
import com.neusoft.utils.PageModel;

/**
 * ¹æ¸ñ¿ØÖÆÆ÷
 * @author Administrator
 *
 */
public class RuleService {

	private static RuleService instance = new RuleService();
	
	private RuleService(){}
	
	public static RuleService getInstacne() {
		return instance;
	}
	
	RulDao rd = DaoFactory.getInstance("ruldaoimpl", RulDao.class);
	
	public boolean addRule(Rule rule) {
		return rd.insertRul(rule);
	}
	
	public boolean modiflyRule(Rule r) {
		return rd.updateRul(r);			
	} 
	
	public boolean removeRule(int id) {
		return rd.delRul(id);
	}
	
	public Rule selectRulById(int id) {
		return rd.selectRulById(id);
	}
	
	public PageModel<Rule> selectRulAll(int pageNo, int pageSize) {
		return rd.selectRuleAll(pageNo, pageSize);
	}
}
