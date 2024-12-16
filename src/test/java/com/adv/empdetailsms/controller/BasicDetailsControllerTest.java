package com.adv.empdetailsms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.http.ResponseEntity;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.BasicDetailsDto;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.service.BasicDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class BasicDetailsControllerTest {

	@InjectMocks
	private BasicDetailsController basicDetailsController;

	@Mock
	private BasicDetailsService basicDetailsService;

	private BasicDetailsDto basicDetailsDto = new BasicDetailsDto();
	
	private List<BasicDetailsDto> basicDetailsDtos=new ArrayList();

	private HttpHeaders headers = new HttpHeaders();

	@Before
	public void createBasicDetailsObj() {
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
		List<AddressDto> addressList = new ArrayList<>();
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
		secondAddressDto.setId("8e46f3d5-f3cf-4734-a47a-07c7de32b4b1");
		secondAddressDto.setDoorNo("no1234");
		secondAddressDto.setLandMark("landmarkTwo");
		secondAddressDto.setStreet("streetTwo");
		secondAddressDto.setCity("cityTwo");
		secondAddressDto.setDistrict("districtTwo");
		secondAddressDto.setPincode("676767");
		secondAddressDto.setState("stateTwo");
		secondAddressDto.setCountry("countryTwo");
		secondAddressDto.setType("communication");
		secondAddressDto.setSameAsCommu(false);
		addressList.add(secondAddressDto);
		basicDetailsDto.setAddresses(addressList);
		basicDetailsDtos.add(basicDetailsDto);
		headers.add("userId", "userNameOne");
	}

	@Test
	public void createEmployeeTest() {
		String userId = headers.getFirst("userId");
		when(basicDetailsService.createEmployee(basicDetailsDto, userId))
					.thenReturn(basicDetailsDto.getId());
		ResponseEntity<String> responseEntity = basicDetailsController
					.createEmployee(headers, basicDetailsDto);
		assertEquals("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", responseEntity.getBody());
	}

	@Test
	public void getParticularEmployeeCase1Test() {
		when(basicDetailsService.getParticularEmployee(Mockito.anyString())).thenReturn(basicDetailsDto);
		ResponseEntity<BasicDetailsDto> responseEntity = basicDetailsController
					.getParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		assertEquals("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", responseEntity.getBody().getId());
	}

	@Test(expected = EmployeeDetailsException.class)
	public void getParticularEmployeeCase2Test() {
		EmployeeDetailsException error = new EmployeeDetailsException("ERR001", "No such ID exist");
		when(basicDetailsService.getParticularEmployee(null)).thenThrow(error);
		basicDetailsController.getParticularEmployee(null);
	}

	@Test
	public void updateParticularEmployeeTest() {
		String userId = headers.getFirst("userId");
		doNothing().when(basicDetailsService)
					.updateParticularEmpDetails("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto, userId);
		ResponseEntity<Void> updateParticularEmployee = basicDetailsController
					.updateParticularEmployee(headers, "9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", basicDetailsDto);
		assertEquals(200, updateParticularEmployee.getStatusCode().value());
	}

	@Test
	public void deleteParticularEmployeeTest() {
		String userId = headers.getFirst("userId");
		doNothing().when(basicDetailsService).deleteParticularEmployee("9e36f3d5-f3cf-4734-a47a-07c7de32b4b1", userId);
		ResponseEntity<Void> deleteParticularEmployee = basicDetailsController
					.deleteParticularEmployee(headers, "9e36f3d5-f3cf-4734-a47a-07c7de32b4b1");
		assertEquals(204, deleteParticularEmployee.getStatusCode().value());
	}
	
	@Test
	public void getAllEmployeesCase1Test() {
		when(basicDetailsService.getAllEmployees()).thenReturn(basicDetailsDtos);
		ResponseEntity<List<BasicDetailsDto>> responseEntity = basicDetailsController
					.getAllEmployees();
		assertEquals(200, responseEntity.getStatusCode().value());
	}
	
	@Test(expected = EmployeeDetailsException.class)
	public void getAllEmployeesCase2Test() {
		EmployeeDetailsException error = new EmployeeDetailsException("ERR003", "No Data exist");
		when(basicDetailsService.getAllEmployees()).thenThrow(error);
		basicDetailsController.getAllEmployees();
	}

}
