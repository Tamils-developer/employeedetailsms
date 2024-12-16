package com.adv.empdetailsms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.BasicDetailsDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.AddressRepository;
import com.adv.empdetailsms.repository.BasicDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class BasicDetailsServiceImplTest {

	@InjectMocks
	private BasicDetailsServiceImpl basicDetailsServiceImpl;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;
	
	@Mock
	private AddressRepository addressRepository;

	private BasicDetailsDto basicDetailsDto = new BasicDetailsDto();

	private BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();
	
	private List<BasicDetailsEntity> basicDetailsEntitys = new ArrayList<>();

	private BasicDetailsEntity basicDetailsEntity2 = new BasicDetailsEntity();
	
	private AddressEntity addressEntity = new AddressEntity();

	private List<AddressEntity> addressEntityList = new ArrayList<>();

	private List<AddressDto> addressList = new ArrayList<>();

	private final String userId = "userNameOne";

	@Before
	public void createBasicDetailsDto() {
		basicDetailsDto.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsDto.setFirstName("firstnameOne");
		basicDetailsDto.setLastName("lastnameOne");
		basicDetailsDto.setEmail("some@gmail.com");
		basicDetailsDto.setDob(new Date(2003 - 11 - 18));
		basicDetailsDto.setAadharNum("64404440444");
		basicDetailsDto.setBloodGroup("AB +ve");
		basicDetailsDto.setPanNum("AAAA4567R");
		basicDetailsDto.setPassportNum("AAAA4567R856");
		basicDetailsDto.setGender("male");
		basicDetailsDto.setMaritalStatus("Unmarried");
		basicDetailsDto.setMobileNum("7867878987");
		basicDetailsDto.setAlterMobileNum("8750466550");
		basicDetailsDto.setUanNumber("8765432345678876");
		AddressDto addressDto = new AddressDto();
		addressDto.setId("8e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		addressDto.setDoorNo("no123");
		addressDto.setLandMark("landmarkOne");
		addressDto.setStreet("streetOne");
		addressDto.setCity("cityOne");
		addressDto.setDistrict("districtOne");
		addressDto.setPincode("789067");
		addressDto.setState("stateOne");
		addressDto.setCountry("countryOne");
		addressDto.setType("permanent");
		addressDto.setSameAsCommu(false);
		addressList.add(addressDto);
		AddressDto secondAddressDto = new AddressDto();
		secondAddressDto.setId("7e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		secondAddressDto.setDoorNo("no1234");
		secondAddressDto.setLandMark("landmarkTwo");
		secondAddressDto.setStreet("streetTwo");
		secondAddressDto.setCity("cityTwo");
		secondAddressDto.setDistrict("districtTwo");
		secondAddressDto.setPincode("676767");
		secondAddressDto.setState("stateTwo");
		secondAddressDto.setCountry("countryTwo");
		secondAddressDto.setCountry("countryTwo");
		secondAddressDto.setType("communication");
		secondAddressDto.setSameAsCommu(true);
		addressList.add(secondAddressDto);
		basicDetailsDto.setAddresses(addressList);
		createBasicDetailsEntity();
	}

	private void createBasicDetailsEntity() {
		basicDetailsEntity.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsEntity.setFirstName("firstnameOne");
		basicDetailsEntity.setLastName("lastnameOne");
		basicDetailsEntity.setEmail("some@gmail.com");
		basicDetailsEntity.setDob(new Date(2003 - 11 - 18));
		basicDetailsEntity.setAadharNum("64404440444");
		basicDetailsEntity.setBloodGroup("AB +ve");
		basicDetailsEntity.setPanNum("AAAA4567R");
		basicDetailsEntity.setPassportNum("AAAA4567R856");
		basicDetailsEntity.setGender("male");
		basicDetailsEntity.setMaritalStatus("Unmarried");
		basicDetailsEntity.setMobile("7867878987");
		basicDetailsEntity.setAlterMobileNum("8750466550");
		basicDetailsEntity.setDeleted(false);		
		addressEntity.setId("8e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		addressEntity.setDoorNo("no123");
		addressEntity.setLandMark("landmarkOne");
		addressEntity.setStreet("streetOne");
		addressEntity.setCity("cityOne");
		addressEntity.setDistrict("districtOne");
		addressEntity.setPincode("789067");
		addressEntity.setState("stateOne");
		addressEntity.setCountry("countryOne");
		addressEntity.setType("permanent");
		addressEntity.setSameAsCommu(false);
		addressEntityList.add(addressEntity);
		AddressEntity secondAddressEntity = new AddressEntity();
		secondAddressEntity.setId("7e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		secondAddressEntity.setDoorNo("no1234");
		secondAddressEntity.setLandMark("landmarkTwo");
		secondAddressEntity.setStreet("streetTwo");
		secondAddressEntity.setCity("cityTwo");
		secondAddressEntity.setDistrict("districtTwo");
		secondAddressEntity.setPincode("676767");
		secondAddressEntity.setState("sateTwo");
		secondAddressEntity.setCountry("countryTwo");
		secondAddressEntity.setDeleted(true);
		secondAddressEntity.setType("communication");
		secondAddressEntity.setSameAsCommu(false);
		addressEntityList.add(secondAddressEntity);
		basicDetailsEntity.setAddressEntities(addressEntityList);
		basicDetailsEntitys.add(basicDetailsEntity);
		createBasicDetailsEntity2();
	}

	public void createBasicDetailsEntity2() {
		basicDetailsEntity2.setId("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsEntity2.setFirstName("tiger");
		basicDetailsEntity2.setLastName("tiger");
		basicDetailsEntity2.setEmail("tiger@gmail.com");
		basicDetailsEntity2.setDob(new Date(2002 - 11 - 18));
		basicDetailsEntity2.setAadharNum("64404440555");
		basicDetailsEntity2.setBloodGroup("A +ve");
		basicDetailsEntity2.setPanNum("AAAA7896R");
		basicDetailsEntity2.setPassportNum("AAAA456785626");
		basicDetailsEntity2.setGender("Female");
		basicDetailsEntity2.setMaritalStatus("married");
		basicDetailsEntity2.setMobile("7010444678");
		basicDetailsEntity2.setAlterMobileNum("9750567850");
		basicDetailsEntity2.setDeleted(false);
		List<AddressEntity> addressEntityList2 = new ArrayList<>();
		AddressEntity firstAddressEntity = new AddressEntity();
		firstAddressEntity.setId("8e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		firstAddressEntity.setDoorNo("no321");
		firstAddressEntity.setLandMark("landmarkThree");
		firstAddressEntity.setStreet("streetThree");
		firstAddressEntity.setCity("cityThree");
		firstAddressEntity.setDistrict("districtThree");
		firstAddressEntity.setPincode("666666");
		firstAddressEntity.setState("stateThree");
		firstAddressEntity.setCountry("countryThree");
		firstAddressEntity.setType("permanent");
		firstAddressEntity.setSameAsCommu(false);
		firstAddressEntity.setDeleted(false);
		addressEntityList2.add(firstAddressEntity);
		AddressEntity secondAddressEntity = new AddressEntity();
		firstAddressEntity.setId("7e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		secondAddressEntity.setDoorNo("no4321");
		secondAddressEntity.setLandMark("landmarkFive");
		secondAddressEntity.setStreet("streetFive");
		secondAddressEntity.setCity("cityFive");
		secondAddressEntity.setDistrict("districtFive");
		secondAddressEntity.setPincode("777777");
		secondAddressEntity.setState("stateFive");
		secondAddressEntity.setCountry("countryFive");
		secondAddressEntity.setType("communication");
		secondAddressEntity.setSameAsCommu(false);
		secondAddressEntity.setDeleted(false);
		addressEntityList2.add(secondAddressEntity);
		basicDetailsEntity2.setAddressEntities(addressEntityList2);
	}

	@Test
	public void testCreateEmployeeCase1() {
		when(basicDetailsRepository.saveAndFlush(Mockito.any())).thenReturn(basicDetailsEntity);
		String empId = basicDetailsServiceImpl.createEmployee(basicDetailsDto, userId);
		assertEquals("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", empId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testCreateEmployeeCase2() {
		basicDetailsServiceImpl.createEmployee(null, null);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testCreateEmployeeCase3() {
		basicDetailsDto.setAddresses(null);
		basicDetailsServiceImpl.createEmployee(basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testCreateEmployeeCase4() {
		when(basicDetailsRepository.saveAndFlush(Mockito.any())).thenReturn(null);
		basicDetailsServiceImpl.createEmployee(basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testCreateEmployeeCase5() {
		basicDetailsDto.setAddresses(new ArrayList<>());
		basicDetailsServiceImpl.createEmployee(basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmployeeCase1() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl.getParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");

		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(null);
		basicDetailsServiceImpl.getParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetParticularEmployeeCase2() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl.getParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		basicDetailsServiceImpl.getParticularEmployee(null);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase1() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		basicDetailsEntity.setDob(new Date(2003 - 11 - 18));
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		basicDetailsEntity.setAddressEntities(new ArrayList<AddressEntity>());
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		basicDetailsDto.setAddresses(new ArrayList<AddressDto>());
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(null);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase2() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("8e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("8e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase3() {
		basicDetailsServiceImpl.updateParticularEmpDetails(null, basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase4() {
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase5() {
		basicDetailsDto.setAddresses(null);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
	}

	@Test
	public void testUpdateParticularEmpDetailsCase6() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity2);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		when(basicDetailsRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new BasicDetailsEntity());
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);

		when(basicDetailsRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new BasicDetailsEntity());
		AddressDto addressDto = new AddressDto();
		addressDto.setId(null);
		addressList.add(addressDto);
		basicDetailsDto.setAddresses(addressList);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testUpdateParticularEmpDetailsCase7() {
		List<AddressDto> addressList = new ArrayList<>();
		AddressDto dto = null;
		addressList.add(dto);
		basicDetailsDto.setAddresses(addressList);
		basicDetailsServiceImpl
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void DeleteParticularEmployeeTestCase1() {
		basicDetailsServiceImpl.deleteParticularEmployee(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void DeleteParticularEmployeeTestCase2() {
		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(basicDetailsEntity);
		basicDetailsServiceImpl
					.deleteParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", userId);

		when(basicDetailsRepository.saveAndFlush(Mockito.any()))
					.thenReturn(new BasicDetailsEntity());
		basicDetailsServiceImpl
					.deleteParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", userId);

		when(basicDetailsRepository
					.findByIdAndIsDeleted("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", false))
					.thenReturn(null);
		basicDetailsServiceImpl
					.deleteParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", userId);
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void testGetAllEmployeesCase1() {
		when(basicDetailsRepository
					.findAllByIsDeleted(false))
					.thenReturn(basicDetailsEntitys);
		basicDetailsServiceImpl.getAllEmployees();

		when(basicDetailsRepository
					.findAllByIsDeleted(false))
					.thenReturn(new ArrayList<BasicDetailsEntity>());
		basicDetailsServiceImpl.getAllEmployees();
	}

}
