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
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.ContactDetailsDto;
import com.adv.empdetailsms.dto.FamilyDetailsDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.entity.FamilyEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.BasicDetailsRepository;
import com.adv.empdetailsms.repository.FamilyRepository;

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailsServiceImplTest {

	@Mock
	private FamilyRepository repository;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;

	@InjectMocks
	private ContactDetailsServiceImpl contactDetailsServiceImpl;

	private ContactDetailsDto contactDetailsDto = new ContactDetailsDto();

	private FamilyEntity familyEntityCase1 = new FamilyEntity();

	private FamilyDetailsDto familyDetailsDto = new FamilyDetailsDto();

	private List<FamilyDetailsDto> familyDetailsDtoList = new ArrayList<>();
	private FamilyEntity familyEntityCase2 = new FamilyEntity();
	FamilyDetailsDto familydetailsDtoCase2 = new FamilyDetailsDto();

	private FamilyEntity familyEntitySameCase = new FamilyEntity();
	private FamilyDetailsDto familyDtoSameCase = new FamilyDetailsDto();
	List<FamilyDetailsDto> familyDtoListSame = new ArrayList<>();
	List<FamilyEntity> familyEntityListSame = new ArrayList<>();

	List<FamilyEntity> familyEntity = new ArrayList<>();

	private HttpHeaders headers = new HttpHeaders();

	private String userId = headers.getFirst("userId");

	@Before
	public void run() {

		headers.add("userId", "raj");
		contactDetailsDto.setEmployeeId("100");
		familyEntityCase1.setName("raj");
		familyEntityCase1.setId("1");
		familyEntityCase1.setAadhar("3483114424");
		familyEntityCase1.setDob(new Date(1999 / 12 / 9));
		familyEntityCase1.setRelation("father");

		familyDtoSameCase.setName("raj");
		familyDtoSameCase.setId("1");
		familyDtoSameCase.setAadhar("3483114424");
		familyDtoSameCase.setDob(new Date(1999 / 12 / 9));
		familyEntityCase1.setRelation("father");
		familyEntity.add(familyEntityCase1);

		familyEntityCase2.setName("anbu");
		familyEntityCase2.setId("1");
		familyEntityCase2.setAadhar("3443434311");
		familyEntityCase2.setDob(new Date(1999 - 12 - 19));
		familyEntityCase2.setRelation("mother");
		familyEntity.add(familyEntityCase2);

		familyDetailsDto.setId("1");
		familyDetailsDto.setAadhar("3483114434");
		familyDetailsDto.setDob(new Date(1999 / 12 / 9));
		familyDetailsDto.setName("raj");
		familyDetailsDto.setRelation("father1");

		familydetailsDtoCase2.setId("2");
		familydetailsDtoCase2.setAadhar("3443434311");
		familydetailsDtoCase2.setDob(new Date(1999 - 12 - 1));
		familydetailsDtoCase2.setName("pushparaj");
		familydetailsDtoCase2.setRelation("mother");

		familyDetailsDtoList.add(familydetailsDtoCase2);
		familyDetailsDtoList.add(familyDetailsDto);

		contactDetailsDto.setFamilydetails(familyDetailsDtoList);
	}

	@Test
	public void createEmpContactDetails() {
		when(basicDetailsRepository.findByIdAndIsDeleted("100", false)).thenReturn(new BasicDetailsEntity());
		when(repository.save(Mockito.any())).thenReturn(new FamilyEntity());
		contactDetailsServiceImpl.createContactDetails("100", contactDetailsDto, userId);
	}

	@Test
	public void createEmpContactDetail() {
		when(basicDetailsRepository.findByIdAndIsDeleted("100", false)).thenReturn(new BasicDetailsEntity());
		when(repository.save(Mockito.any())).thenReturn(null);
		contactDetailsServiceImpl.createContactDetails("100", contactDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpContactDetailsCase() {
		contactDetailsServiceImpl.createContactDetails(null, null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpContactDetailsCase1() {
		when(basicDetailsRepository.findByIdAndIsDeleted("100", false)).thenReturn(null);
		contactDetailsServiceImpl.createContactDetails("100", contactDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpContactDetailsCase4() {
		when(basicDetailsRepository.findByIdAndIsDeleted("100", false)).thenReturn(null);
		contactDetailsServiceImpl.createContactDetails("100", null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void createEmpContactDetailsCase2() {
		contactDetailsServiceImpl.createContactDetails(null, contactDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpContactDetailsCase() {
		contactDetailsServiceImpl.updatePartiEmpContactDtls(null, contactDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpContactDetailsCase3() {
		FamilyDetailsDto familyDto2 = new FamilyDetailsDto();
		List<FamilyEntity> fe1 = new ArrayList<>();
		List<FamilyDetailsDto> fd1 = new ArrayList<>();
		familyEntityCase1.setName("raaj");
		familyEntityCase1.setId("1");
		familyEntityCase1.setAadhar("3483ww114434");
		familyEntityCase1.setDob(new Date(2 / 12 / 9));
		familyEntityCase1.setRelation("fatheer");
		fe1.add(familyEntityCase1);
		familyDto2.setId("1");
		familyDto2.setAadhar("232323");
		familyDto2.setMobile("232323");
		fd1.add(familyDto2);
	
		contactDetailsServiceImpl.updatePartiEmpContactDtls(userId, contactDetailsDto, userId);
	}

	@Test
	public void testUpdateEmpContactDetailsCase6() {
		List<FamilyDetailsDto> familyEntity2 = new ArrayList<>();
		FamilyDetailsDto familydetailsDtoCase2 = new FamilyDetailsDto();
		familydetailsDtoCase2.setId(null);
		familyEntity2.add(familydetailsDtoCase2);
		ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
		contactDetailsDto.setFamilydetails(familyEntity2);
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(familyEntity);
		contactDetailsServiceImpl.updatePartiEmpContactDtls("100", contactDetailsDto, userId);
	}

	@Test
	public void testUpdateEmpContactDetailsCase7() {
		List<FamilyEntity> famEnt = new ArrayList<>();
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(famEnt);
		contactDetailsServiceImpl.updatePartiEmpContactDtls("100", contactDetailsDto, userId);
	}

	@Test
	public void testUpdateEmpContactDetailsCase12() {

		familyEntitySameCase.setId("80");
		familyEntitySameCase.setAadhar("454545");
		familyEntitySameCase.setDob(new Date(1998 / 12 / 9));
		familyEntitySameCase.setName("timmy");

		familyEntityListSame.add(familyEntitySameCase);

		familyDtoSameCase.setId("80");
		familyDtoSameCase.setAadhar("454545");
		familyDtoSameCase.setDob(new Date(1998 / 12 / 9));
		familyDtoSameCase.setName("timmy");
		familyDtoListSame.add(familyDtoSameCase);


		contactDetailsDto.setFamilydetails(familyDetailsDtoList);

		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(familyEntityListSame);
		contactDetailsServiceImpl.updatePartiEmpContactDtls("100", contactDetailsDto, userId);
	}



	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateEmpContactDetailsCase4() {
		contactDetailsServiceImpl.updatePartiEmpContactDtls("100", null, userId);
	}

	@Test
	public void testGetEmployeeContactDetails() {
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(familyEntity);
		contactDetailsServiceImpl.getParticularEmployeeContactDetails("100");
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetEmployeeContactDetailsCase2() {
		contactDetailsServiceImpl.getParticularEmployeeContactDetails(null);
	}

	@Test (expected = EmployeeDetailsException.class)
	public void upadateEmpContactDettailsTestCase5() {
		contactDetailsServiceImpl.updatePartiEmpContactDtls(userId, contactDetailsDto, userId);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testGetEmployeeContactDetailsCase3() {
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(new ArrayList<>());
		contactDetailsServiceImpl.getParticularEmployeeContactDetails("100");
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetEmployeeContactDetailsCase12() {
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(new ArrayList<>());
		contactDetailsServiceImpl.getParticularEmployeeContactDetails("100");
	}

	@Test
	public void testDeleteEmployeeContactDetailsCase1() {
		contactDetailsServiceImpl.deleteParticularEmployeeContactDetails("100", userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmployeeContactDetailsCase2() {
		contactDetailsServiceImpl.deleteParticularEmployeeContactDetails(null, userId);
	}

	@Test
	public void testDeleteEmployeeContactDetailsCase3() {
		when(repository.findByEmployeeIdAndIsDeleted("100", false)).thenReturn(familyEntity);
		contactDetailsServiceImpl.deleteParticularEmployeeContactDetails("100", userId);
	}
}
