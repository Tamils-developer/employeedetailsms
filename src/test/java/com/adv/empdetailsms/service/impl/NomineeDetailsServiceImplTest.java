
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

import com.adv.empdetailsms.dto.FamilyDetailsDto;
import com.adv.empdetailsms.dto.NomineeDetailsDto;
import com.adv.empdetailsms.dto.NomineeDto;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.FamilyEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.FamilyRepository;

@RunWith(MockitoJUnitRunner.class)
public class NomineeDetailsServiceImplTest {

	@InjectMocks
	private NomineeDetailsServiceImpl nomineeDetailsServiceImpl;

	@Mock
	private FamilyRepository familyRepository;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;

	private List<FamilyEntity> familyEntityList = new ArrayList<>();

	private List<FamilyDetailsDto> familyDetailsDtoList = new ArrayList<>();

	private FamilyEntity familyEntity = new FamilyEntity();

	private FamilyDetailsDto familyDetailDto = new FamilyDetailsDto();

	private BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();

	private List<NomineeDetailsDto> nomineeDetailsDtoList = new ArrayList<>();

	private NomineeDetailsDto nomineeDetailsDto = new NomineeDetailsDto();

	private NomineeDto nomineeDto = new NomineeDto();
	
	private String employeeId = "8f751cd6-c9d5-4156-b2af-33a53c3a42d3";

	private String userId;

	@Before
	public void run() {
		userId = "thulasi";
		familyDetailDto.setId(employeeId);
		familyDetailDto.setName("Lion");
		familyDetailDto.setDob(new Date(12 - 02 - 2008));
		familyDetailDto.setAadhar("879054327812");
		familyDetailDto.setNominee(false);
		familyDetailDto.setRelation("Father");
		familyDetailDto.setPercentage(50);
		familyDetailsDtoList.add(familyDetailDto);

		familyEntity.setId(employeeId);
		familyEntity.setName("Lion");
		familyEntity.setDob(new Date(12 - 02 - 2008));
		familyEntity.setAadhar("879054327812");
		familyEntity.setRelation("Father");
		familyEntity.setPercentage(50);
		familyEntityList.add(familyEntity);

		nomineeDetailsDto.setId(employeeId);
		nomineeDetailsDto.setPercentage(50);
		nomineeDetailsDtoList.add(nomineeDetailsDto);
		nomineeDto.setNomineeDetailsDtoList(nomineeDetailsDtoList);
	}


	@Test
	public void testGetFamilyDetailsCase1() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(familyEntityList);
		nomineeDetailsServiceImpl.getNomineeDetails(employeeId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetFamilyDetailsCase2() {
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(null);
		nomineeDetailsServiceImpl.getNomineeDetails(employeeId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetFamilyDetailsCase3() {
		nomineeDetailsServiceImpl.getNomineeDetails(null);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testGetFamilyDetailsCase4() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(new ArrayList<>());
		nomineeDetailsServiceImpl.getNomineeDetails(employeeId);
	}
	
	@Test
	public void testGetFamilyDetailsCase5() {
		familyEntityList.get(0).setNominee(true);
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(basicDetailsEntity);
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false)).thenReturn(familyEntityList);
		nomineeDetailsServiceImpl.getNomineeDetails(employeeId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateFamilyDetailsCase1() {
		nomineeDetailsServiceImpl.updateNomineeDetails(nomineeDto, null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateFamilyDetailsCase2() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false)).thenReturn(null);
		nomineeDetailsServiceImpl.updateNomineeDetails(nomineeDto, employeeId, userId);
	}

	@Test
	public void testUpdateFamilyDetailsCase3() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false))
				.thenReturn(new BasicDetailsEntity());
		when(basicDetailsRepository.saveAndFlush(Mockito.any())).thenReturn(new BasicDetailsEntity());
		when(familyRepository.findByIdAndIsDeleted(employeeId,false))
				.thenReturn(new FamilyEntity());
		nomineeDetailsServiceImpl.updateNomineeDetails(nomineeDto, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateFamilyDetailsCase4() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false))
				.thenReturn(new BasicDetailsEntity());
		when(basicDetailsRepository.saveAndFlush(Mockito.any())).thenReturn(new BasicDetailsEntity());
		nomineeDto.setNomineeDetailsDtoList(new ArrayList<>());
		nomineeDetailsServiceImpl.updateNomineeDetails(nomineeDto, employeeId, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateFamilyDetailsCase5() {
		nomineeDetailsServiceImpl.updateNomineeDetails(null, employeeId, userId);
	}
	
	@Test
	public void testUpdateFamilyDetailsCase6() {
		when(basicDetailsRepository.findByIdAndIsDeleted(employeeId, false))
				.thenReturn(new BasicDetailsEntity());
		when(basicDetailsRepository.saveAndFlush(Mockito.any())).thenReturn(new BasicDetailsEntity());
		when(familyRepository.findByIdAndIsDeleted(employeeId,false))
		.thenReturn(new FamilyEntity());
		when(familyRepository.saveAndFlush(Mockito.any())).thenReturn(new FamilyEntity());
		nomineeDetailsServiceImpl.updateNomineeDetails(nomineeDto, employeeId, userId);
	}
	
	@Test
	public void testdeleteNomineeDetailsCase() {
		when(familyRepository.findByIdAndIsDeleted(employeeId, false))
		.thenReturn(new FamilyEntity());
		when(familyRepository.saveAndFlush(Mockito.any())).thenReturn(new FamilyEntity());
		nomineeDetailsServiceImpl.deleteNomineeDetails( employeeId, userId);
	}
	
	@Test
	public void testdeleteNomineeDetailsCase2() {
		when(familyRepository.findByIdAndIsDeleted(employeeId, false))
		.thenReturn(new FamilyEntity());
		FamilyEntity entity= new FamilyEntity();
		entity.setDeleted(true);
		when(familyRepository.saveAndFlush(Mockito.any())).thenReturn(entity);
		nomineeDetailsServiceImpl.deleteNomineeDetails( employeeId, userId);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testdeleteNomineeDetailsCase1() {
		nomineeDetailsServiceImpl.deleteNomineeDetails( null, userId);
	}
	
	@Test
	public void testdeleteDropDownDetailsCase() {
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false))
		.thenReturn(familyEntityList);
		nomineeDetailsServiceImpl.getDropDownDetails( employeeId);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testdeleteDropDownDetailsCase1() {
		nomineeDetailsServiceImpl.getDropDownDetails( null);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testdeleteDropDownDetailsCase2() {
		when(familyRepository.findByEmployeeIdAndIsDeleted(employeeId, false))
		.thenReturn(new ArrayList<>());
		nomineeDetailsServiceImpl.getDropDownDetails( employeeId);
	}
}
