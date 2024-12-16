package com.adv.empdetailsms.service.impl;

import static org.mockito.Mockito.when;

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
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.entity.BasicDetailsEntity;
import com.adv.empdetailsms.exception.EmployeeDetailsException;
import com.adv.empdetailsms.repository.AddressRepository;
import com.adv.empdetailsms.repository.BasicDetailsRepository;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

	@InjectMocks
	private AddressServiceImpl addressServiceImpl;

	@Mock
	private AddressRepository addressRepository;

	@Mock
	private BasicDetailsRepository basicDetailsRepository;

	private BasicDetailsEntity basicDetailsEntity = new BasicDetailsEntity();

	private AddressEntity addressEntity = new AddressEntity();

	private AddressEntity addressEntity2 = new AddressEntity();

	private List<AddressEntity> addressEntityList = new ArrayList<>();


	private List<AddressDto> addressList = new ArrayList<>();

	private final String userId = "userNameOne";

	AddressDto addressDto = new AddressDto();
	AddressDto secondAddressDto = new AddressDto();

	@Before
	public void createBasicDetailsDto() {

		addressDto.setId("100");
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

		addressEntity.setDeleted(false);

		addressEntity.setId("100");
		addressEntity.setDoorNo("no123");
		addressEntity.setLandMark("landmarkOne");
		addressEntity.setStreet("streetOne");
		addressEntity.setCity("cityOne");
		addressEntity.setDistrict("districtOne");
		addressEntity.setPincode("789067");
		addressEntity.setState("stateOne");
		addressEntity.setCountry("countryOne");
		addressEntity.setType("permanent");

		addressEntity2.setDeleted(true);
		addressEntity.setSameAsCommu(false);
		addressEntityList.add(addressEntity);
		addressEntityList.add(addressEntity2);
		basicDetailsEntity.setAddressEntities(addressEntityList);
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

	}

	@Test
	public void testDeleteEmployeeAddressDetailsCase3() {

		when(addressRepository.findByIdAndIsDeleted("100", false)).thenReturn(new AddressEntity());
		when(addressRepository.saveAndFlush(Mockito.any())).thenReturn(new AddressEntity());
		addressServiceImpl.deleteParticularAddress("100", userId);
	}


	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmployeeAddressDetailsCase1() {
		addressServiceImpl.deleteParticularAddress(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testDeleteEmployeeAddressDetailsCase2() {
		when(addressRepository.findByIdAndIsDeleted("100", false)).thenReturn(null);
		addressServiceImpl.deleteParticularAddress("100", userId);
	}

	@Test
	public void testDeleteEmployeeAddressCase4() {
		when(addressRepository.findByIdAndIsDeleted("100", false)).thenReturn(addressEntity);
		addressServiceImpl.deleteParticularAddress("100", userId);
	}

	@Test
	public void testGetEmployeeAddressDetailsCase1() {
		when(basicDetailsRepository.findAllAddressesByIdAndIsDeleted("100", false)).thenReturn(basicDetailsEntity);
		addressServiceImpl.getAllAddressesOfParticularEmp("100", userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetEmployeeAddressDetailsCase2() {
		addressServiceImpl.getAllAddressesOfParticularEmp(null, userId);
	}

	@Test(expected = EmployeeDetailsException.class)
	public void testGetEmployeeAddressDetailsCase3() {
		addressServiceImpl.getAllAddressesOfParticularEmp("100", userId);
	}

	@Test
	public void testGetEmployeeAddressDetailsCase5() {
		when(basicDetailsRepository.findAllAddressesByIdAndIsDeleted("100", false)).thenReturn(basicDetailsEntity);
		addressServiceImpl.getAllAddressesOfParticularEmp("100", userId);
	}

	@Test
	public void testGetEmployeeAddressDetailsCase6() {
		when(basicDetailsRepository.findAllAddressesByIdAndIsDeleted("100", false)).thenReturn(basicDetailsEntity);
		addressServiceImpl.getAllAddressesOfParticularEmp("100", userId);
	}
}
