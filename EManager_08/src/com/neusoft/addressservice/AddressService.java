package com.neusoft.addressservice;

import com.neusoft.accountdao.DaoFactory;
import com.neusoft.addressdao.AddressDao;
import com.neusoft.entity.Address;
import com.neusoft.utils.PageModel;

/**
 * µØÖ·ÒµÎñ²ã
 * @author Administrator
 *
 */
public class AddressService {

	AddressDao ad = DaoFactory.getInstance("addressdaoimpl", AddressDao.class);

	public boolean addAddress(Address aress){
		return ad.insertAddress(aress);
	}
	
	public boolean modiflyAddress(Address aress){
		return ad.updateAddress(aress);
	}
	
	public boolean removeAddress(int id){
		return ad.delAddress(id);
	}
	
	public PageModel<Address> showAddressByPageModel(int pageNo,int pageSize){
		return ad.selectAddress(pageNo, pageSize);
	}
	
	public Address showAddressById(int id){
		return ad.selectAddress(id);
	}
}
