package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.entity.AddressEntity;
import com.adv.empdetailsms.service.AddressService;

@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

	@InjectMocks
	private AddressController addressController;

	@Mock
	private AddressService addressService;

	private AddressDto addressDtos = new AddressDto();

	private HttpHeaders headers = new HttpHeaders();

	private List<AddressEntity> addressEntityList = new ArrayList<>();

	private List<AddressDto> addressList = new ArrayList<>();

	private final String userId = "userNameOne";

	AddressDto addressDto = new AddressDto();
	AddressDto secondAddressDto = new AddressDto();

	@Before
	public void run() {

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
		addressDtos.setId("100");
		addressDtos.setDoorNo("no123");
		addressDtos.setLandMark("landmarkOne");
		addressDtos.setStreet("streetOne");
		addressDtos.setCity("cityOne");
		addressDtos.setDistrict("districtOne");
		addressDtos.setPincode("789067");
		addressDtos.setState("stateOne");
		addressDtos.setCountry("countryOne");
		addressDtos.setType("permanent");
		addressDtos.setSameAsCommu(false);
	}

	@Test
	public void DeleteParticularContactDetailsTest() {
		addressController.deleteParticularAddress(headers, userId);
	}

	@Test
	public void GetAllAddress() {
		addressController.getAllAddressesOfParticularEmp(headers, userId);
	}

}
