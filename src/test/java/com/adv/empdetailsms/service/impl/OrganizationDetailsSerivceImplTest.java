package com.adv.empdetailsms.service.impl;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.adv.empdetailsms.dto.OrganizationDetailsDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.OrganizationDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.OrganizationDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationDetailsSerivceImplTest {

	@InjectMocks
	private OrganizationDetailsServiceImpl detailsServiceImpl;

	@Mock
	private OrganizationDetailsRepository repository;
	@Mock
	private BasicDetailsRepository detailsRepository;
	private String employeeId = "8f751cd6-c9d5-4156-b2af-33a53c3a42d3";
	private String employeeId1 = "9f751cd6-c9d5-4156-b2af-33a53c3a42d3";

	private List<OrganizationDetailsDto> organizationDetailsDtoList = new ArrayList<>();

	private List<OrganizationDetailsEntity> organizationDetailsEntityList = new ArrayList<>();

	@Before
	public void run() {
		OrganizationDetailsDto detailsDto = new OrganizationDetailsDto();
		detailsDto.setEsiNumber("123456789013");
		detailsDto.setId(employeeId);
		detailsDto.setNameOfOrganization("Zoho");
		detailsDto.setRole("ui developer");
		detailsDto.setEndDate(new Date(2023 - 12 - 12));
		detailsDto.setFromDate(new Date(2024 - 12 - 12));
		OrganizationDetailsDto detailsDto2 = new OrganizationDetailsDto();
		detailsDto2.setEsiNumber("123456789012");
		detailsDto2.setId(employeeId1);
		detailsDto2.setNameOfOrganization("Adv1");
		detailsDto2.setRole("ui Designer");
		detailsDto2.setEndDate(new Date(2023 - 12 - 12));
		detailsDto2.setFromDate(new Date(2024 - 12 - 12));
		detailsDto2.setTotalYearsOfExperience(9);
		organizationDetailsDtoList.add(detailsDto);
		organizationDetailsDtoList.add(detailsDto2);

		OrganizationDetailsEntity detailsEntity = new OrganizationDetailsEntity();
		detailsEntity.setEsiNumber("123456789012");
		detailsEntity.setId(employeeId);
		detailsEntity.setNameOfOrganization("Adv");
		detailsEntity.setRole("ui Designer");
		detailsEntity.setEndDate(new Date(2022 - 12 - 12));
		detailsEntity.setFromDate(new Date(2020 - 12 - 12));
		detailsEntity.setDeleted(true);
		OrganizationDetailsEntity detailsEntity2 = new OrganizationDetailsEntity();
		detailsEntity2.setEsiNumber("123456789012");
		detailsEntity2.setId(employeeId1);
		detailsEntity2.setNameOfOrganization("Adv1");
		detailsEntity2.setRole("ui Designer");
		detailsEntity2.setEndDate(new Date(2023 - 12 - 12));
		detailsEntity2.setFromDate(new Date(2024 - 12 - 12));
		detailsEntity2.setDeleted(true);
		detailsEntity2.setTotalYearsOfExperience(9);

		organizationDetailsEntityList.add(detailsEntity);
		organizationDetailsEntityList.add(detailsEntity2);
	}

	@Test
	public void createEmpOrgDetailsTestCase1() {
		String userId = "userName";
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setId(employeeId);
		when(detailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		when(repository.save(Mockito.any())).thenReturn(new OrganizationDetailsEntity());
		detailsServiceImpl.createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test
	public void createEmpOrgDetailsTestCase2() {
		String userId = "userName";
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setId(employeeId);
		when(detailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		when(repository.save(Mockito.any())).thenReturn(null);
		detailsServiceImpl.createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpOrgDetailsTestCase3() {
		String userId = "userName";
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setId(employeeId1);
		when(detailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		detailsServiceImpl.createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpOrgDetailsTestCase4() {
		String userId = "userName";
		when(detailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(null);
		detailsServiceImpl.createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpOrgDetailsTestCase5() {
		String userId = "userName";
		detailsServiceImpl.createOrganizationDetails(new ArrayList<>(), null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpOrgDetailsTestCase6() {
		String userId = "userName";
		detailsServiceImpl.createOrganizationDetails(new ArrayList<>(), employeeId, userId);
	}

	@Test
	public void createEmpOrgDetailsTestCase7() {
		String userId = "userName";
		List<OrganizationDetailsDto> detailsDtos = new ArrayList<>();
		OrganizationDetailsDto dto1 = new OrganizationDetailsDto();
		OrganizationDetailsDto dto2 = null;
		detailsDtos.add(dto1);
		detailsDtos.add(dto2);
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setId(employeeId);
		when(detailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		detailsServiceImpl.createOrganizationDetails(detailsDtos, employeeId, userId);
	}

	@Test
	public void getParticularEmpOrgDetailsTestCase1() {
		when(repository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntityList);
		detailsServiceImpl.getParticularEmpOrgDetails(employeeId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void getParticularEmpOrgDetailsTestCase2() {
		detailsServiceImpl.getParticularEmpOrgDetails(null);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void getParticularEmpOrgDetailsTestCase3() {
		organizationDetailsEntityList.add(null);
		when(repository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(new ArrayList<>());
		detailsServiceImpl.getParticularEmpOrgDetails(employeeId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTestCase1() {
		String userId = "userName";
		when(repository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntityList);
		when(repository.saveAndFlush(Mockito.any())).thenReturn(new OrganizationDetailsEntity());
		detailsServiceImpl.updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void updateParticularEmpOrgDetailsTestCase2() {
		String userId = "userName";
		detailsServiceImpl.updateParticularEmpOrgDetails(new ArrayList<>(), employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void updateParticularEmpOrgDetailsTestCase3() {
		String userId = "userName";
		detailsServiceImpl.updateParticularEmpOrgDetails(organizationDetailsDtoList, null, userId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTestCase5() {
		String userId = "userName";
		organizationDetailsDtoList.get(0).setId(null);
		detailsServiceImpl.updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTestCase6() {
		String userId = "userName";
		List<OrganizationDetailsDto> detailsDtos = new ArrayList<>();
		OrganizationDetailsDto dto1 = new OrganizationDetailsDto();
		OrganizationDetailsDto dto2 = null;
		detailsDtos.add(dto1);
		detailsDtos.add(dto2);
		BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
		basicDetailsEntity.setId(employeeId);
		detailsServiceImpl.updateParticularEmpOrgDetails(detailsDtos, employeeId, userId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTestCase7() {
		String userId = "userName";
		organizationDetailsDtoList.get(0).setId(null);
		when(repository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntityList);
		when(repository.saveAndFlush(Mockito.any())).thenReturn(null);
		detailsServiceImpl.updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test
	public void updateParticularEmpOrgDetailsTestCase8() {
		String userId = "userName";
		organizationDetailsDtoList.get(0).setId(null);
		when(repository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntityList);
		when(repository.save(Mockito.any())).thenReturn(new OrganizationDetailsEntity());
		detailsServiceImpl.updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
	}

	@Test
	public void deleteParticularEmpOrgDetailsTestCase() {
		String userId = "userName";
		OrganizationDetailsEntity organizationDetailsEntity = new OrganizationDetailsEntity();
		organizationDetailsEntity.setId(employeeId);
		when(repository.findByIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntity);
		when(repository.saveAndFlush(Mockito.any())).thenReturn(new OrganizationDetailsEntity());
		detailsServiceImpl.deleteParticularEmpOrgDetails(employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void deleteParticularEmpOrgDetailsTestCase2() {
		String userId = "userName";
		detailsServiceImpl.deleteParticularEmpOrgDetails(null, userId);
	}

	@Test
	public void deleteParticularEmpOrgDetailsTestCase3() {
		String userId = "userName";
		when(repository.findByIdAndIsDeleted(employeeId, false)).thenReturn(null);
		detailsServiceImpl.deleteParticularEmpOrgDetails(employeeId, userId);
	}

	@Test
	public void deleteParticularEmpOrgDetailsTestCase4() {
		String userId = "userName";
		OrganizationDetailsEntity organizationDetailsEntity = new OrganizationDetailsEntity();
		organizationDetailsEntity.setId(employeeId);
		organizationDetailsEntity.setDeleted(true);
		when(repository.findByIdAndIsDeleted(employeeId, false)).thenReturn(organizationDetailsEntity);
		when(repository.saveAndFlush(Mockito.any())).thenReturn(organizationDetailsEntity);
		detailsServiceImpl.deleteParticularEmpOrgDetails(employeeId, userId);
	}
}