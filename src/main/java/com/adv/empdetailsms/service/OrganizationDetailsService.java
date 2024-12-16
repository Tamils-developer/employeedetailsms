package com.adv.empdetailsms.service;

import java.util.List;

import com.adv.empdetailsms.dto.OrganizationDetailsDto;

public interface OrganizationDetailsService {

	public void createOrganizationDetails(List<OrganizationDetailsDto> organizationDetailsDto, String employeeId,
			String userId);

	public List<OrganizationDetailsDto> getParticularEmpOrgDetails(String empId);


	public void updateParticularEmpOrgDetails(List<OrganizationDetailsDto> organizationDetailsDto, String employeeId,
			String userId);

	public void deleteParticularEmpOrgDetails(String organizationId, String userId);
}