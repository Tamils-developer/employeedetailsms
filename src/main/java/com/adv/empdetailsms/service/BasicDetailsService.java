package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.BasicDetailsDto;

public interface BasicDetailsService {

	public String createEmployee(BasicDetailsDto basicDetailDto, String userId);

	public BasicDetailsDto getParticularEmployee(String employeeId);

	public void updateParticularEmpDetails(String employeeId, BasicDetailsDto basicDetailDto,
				String userId);

	public void deleteParticularEmployee(String employeeId, String userId);
	
	public List<BasicDetailsDto> getAllEmployees();
	
}
