package com.adv.empdetailsms.service;

import com.adv.empdetailsms.dto.ContactDetailsDto;

public interface ContactDetailsService {

	public void createContactDetails(String employeeId, ContactDetailsDto contactDetailsDto, String userId);

	public ContactDetailsDto getParticularEmployeeContactDetails(String employeeId);

//	public void updateParticularEmployeeContactDetails(String employeeId, ContactDetailsDto contactDetailsDto,
//			String userId);

	public void deleteParticularEmployeeContactDetails(String EmployeeId, String userId);

	public void updatePartiEmpContactDtls (String employeeId, ContactDetailsDto contactDetailsDto, String userId);
}
