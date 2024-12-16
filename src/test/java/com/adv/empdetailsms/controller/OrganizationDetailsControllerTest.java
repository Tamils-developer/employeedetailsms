package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.OrganizationDetailsDto;
import com.adv.empdetailsms.service.OrganizationDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationDetailsControllerTest {

	@InjectMocks
	private OrganizationDetailsController organizationDetailsController;

	@Mock
	private OrganizationDetailsService detailsService;

	private List<OrganizationDetailsDto> organizationDetailsDtoList = new ArrayList<>();

	private HttpHeaders headers = new HttpHeaders();

	private String userId = headers.getFirst("userId");
	private String employeeId = "8f751cd6-c9d5-4156-b2af-33a53c3a42d3";


	@Test
	public void createOrganizationDetailsTest() {
		OrganizationDetailsDto detailsDto = new OrganizationDetailsDto();
		detailsDto.setEsiNumber("123456789012");
		detailsDto.setId(employeeId);
		detailsDto.setNameOfOrganization("Adv");
		detailsDto.setRole("ui Designer");
		detailsDto.setEndDate(new Date(2022 - 12 - 12));
		detailsDto.setFromDate(new Date(2020 - 12 - 12));
		OrganizationDetailsDto detailsDto1 = new OrganizationDetailsDto();
		detailsDto1.setEsiNumber("283427362732");
		detailsDto1.setId(employeeId);
		detailsDto1.setNameOfOrganization("zoho");
		detailsDto1.setRole("ux Designer");
		detailsDto1.setEndDate(new Date(2022 - 12 - 12));
		detailsDto1.setFromDate(new Date(2012 - 12 - 12));
		OrganizationDetailsDto detailsDto2 = new OrganizationDetailsDto();
		detailsDto2.setEsiNumber("92378937434");
		detailsDto2.setId(employeeId);
		detailsDto2.setNameOfOrganization("tcs");
		detailsDto2.setRole("software developer");
		detailsDto2.setEndDate(new Date(2022 - 12 - 12));
		detailsDto2.setFromDate(new Date(2020 - 12 - 12));
		organizationDetailsDtoList.add(detailsDto);
		organizationDetailsDtoList.add(detailsDto1);
		organizationDetailsDtoList.add(detailsDto2);
		doNothing().when(detailsService).createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
		organizationDetailsController.createOrganizationDetails(headers, organizationDetailsDtoList, employeeId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTest() {
		doNothing().when(detailsService).updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
		organizationDetailsController.updateParticularEmpOrgDetails(headers, organizationDetailsDtoList, employeeId);
	}

	@Test
	public void getParticularEmpOrgDetailsTest() {
		when(detailsService.getParticularEmpOrgDetails(employeeId)).thenReturn(organizationDetailsDtoList);
		organizationDetailsController.getParticularEmpOrgDetails(employeeId);
	}

	@Test
	public void deleteParticularEmpOrgDetails() {
		doNothing().when(detailsService).deleteParticularEmpOrgDetails(employeeId, userId);
		organizationDetailsController.deleteParticularEmpOrgDetails(headers, employeeId);
	}
}