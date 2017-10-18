package com.neusoft.shop_consumerservice;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.entity.Shoppinginfo_Consumer;
import com.neusoft.shop_consumerdao.ShopConsumerDao;
import com.neusoft.utils.PageModel;

/**
 * 买家购物信息业务层
 * @author Administrator
 *
 */
public class ShopCsmService {
	
	private static ShopCsmService instance = new ShopCsmService();
	
	private ShopCsmService(){
		
	}
	
	public static ShopCsmService getInstance(){
		return instance;
	}
	
	ShopConsumerDao scd = DaoFactory.getInstance("ShopConsumerDaoImpl", ShopConsumerDao.class); 
	
	public boolean addShopCsm(Shoppinginfo_Consumer sic) {
		return scd.insertShopCsm(sic);
	}
	
	public boolean modiflyShopCsm(Shoppinginfo_Consumer sic) {
		return scd.updateShopCsm(sic);
	}
	
	public boolean removeShopCsm(int id){
		return scd.delShopCsm(id);
	}
	
	
	public PageModel<Shoppinginfo_Consumer> showShopCsm(int pageNo,int pageSize,String type){
		return scd.selectShopCsmAll(pageNo, pageSize,type);
	}
	
	public Shoppinginfo_Consumer showShopCsmByAid(int aid) {
		return scd.selectShopCsmById(aid);
	}
}
