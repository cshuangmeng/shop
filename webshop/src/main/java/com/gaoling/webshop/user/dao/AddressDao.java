package com.gaoling.webshop.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gaoling.webshop.user.pojo.Address;

@Repository
public interface AddressDao {

	List<Address> queryAddresses(@Param("param")Map<Object,Object> param);
	void addAddress(Address address);
	void updateAddress(Address address);
	void setDefault(@Param("userId")int userId,@Param("addressId")int addressId);
	
}
