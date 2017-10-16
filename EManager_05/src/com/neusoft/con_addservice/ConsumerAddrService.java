package com.neusoft.con_addservice;

import java.util.List;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.addressdao.AddressDao;
import com.neusoft.con_add_dao.Consumer_Addr_Dao;
import com.neusoft.entity.Address;
import com.neusoft.entity.Consumer_Addr;
import com.neusoft.utils.PageModel;

/**
 * 买家收货地址业务层
 * @author Administrator
 *
 */
public class ConsumerAddrService {
	
	private static ConsumerAddrService instance = new ConsumerAddrService();
	
	private ConsumerAddrService(){}
	
	public static ConsumerAddrService getInstance(){
		return instance;
	}

	Consumer_Addr_Dao ad = DaoFactory.getInstance("consumeraddrdaoimpl", Consumer_Addr_Dao.class);

	public boolean addConsumerAddr(Consumer_Addr aress){
		return ad.insertCsmAddr(aress);
	}
	
	public boolean modiflyConsumerAddr(Consumer_Addr aress){
		return ad.updateCsmAddr(aress);
	}
	
	public boolean removeConsumerAddr(int id){
		return ad.delCsmAddr(id);
	}
	
	public PageModel<Consumer_Addr> showConsumerAddrByPageModel(int pageNo,int pageSize){
		return ad.selectCsmAddrAll(pageNo, pageSize);
	}
	
	public Consumer_Addr showConsumerAddrById(int id){
		return ad.selectCsmAddrById(id);
	}
	
	public List<Consumer_Addr> showConsumerAddrList(){
		return ad.selectCsmAddrList();
	}
}

