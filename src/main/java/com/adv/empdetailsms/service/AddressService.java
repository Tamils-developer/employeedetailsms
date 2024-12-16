package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.AddressDto;

public interface AddressService {

	public void deleteParticularAddress(String addressId, String userId);
	
	public List<AddressDto> getAllAddressesOfParticularEmp(String employeeId, String userId);
	
}
