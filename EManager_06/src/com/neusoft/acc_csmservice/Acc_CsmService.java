package com.neusoft.acc_csmservice;

import java.sql.Connection;
import java.util.List;

import com.neusoft.acc_consumerdao.Acc_CsmDao;
import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.Account_Consumer;
import com.neusoft.utils.PageModel;

public class Acc_CsmService {

	private static Acc_CsmService  instance = new Acc_CsmService();
	
	private Acc_CsmService(){}
	
	public static Acc_CsmService getInstance(){
		return instance;
	}
	
	Acc_CsmDao as = DaoFactory.getInstance("acc_csmdaoimpl", Acc_CsmDao.class);
	Connection conn = null;
	
	public boolean addAcc_Csm(Account_Consumer ac) {
		return as.insertAccCsm(ac);
	}
	
	public boolean modiflyAcc_Csm(Account_Consumer ac) {
		return as.updateAccCsm(ac);
	}
	
	public boolean removeAcc_Csm(int id) {
		return as.delAccCsm(id);
	}
	
	public Account_Consumer showAcc_CsmById(int id) {
		return as.selectAccCsmById(id);
	}
	
	public Account_Consumer showAcc_CsmByUser(String user) {
		return as.selectAccCsmByUser(user);
	}
	
	public PageModel<Account_Consumer> showAcc_CsmAll(int pageNo, int pageSize){
		return as.selectAccCsmAll(pageNo, pageSize);
	}
	
	public List<Account_Consumer> showAcc_CsmList(){
		return as.selectAccCsmList();
	}
	
	
}
