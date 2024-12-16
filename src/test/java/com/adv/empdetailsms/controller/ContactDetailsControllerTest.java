package com.adv.empdetailsms.controller;

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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.dto.ContactDetailsDto;
import com.adv.empdetailsms.dto.FamilyDetailsDto;
import com.adv.empdetailsms.service.ContactDetailsService;

@RunWith(MockitoJUnitRunner.class)
public class ContactDetailsControllerTest {

	@InjectMocks
	private ContactDetailsController conactDetailsController;

	@Mock
	private ContactDetailsService contactDetailsService;

	private ContactDetailsDto contactDetailsDto = new ContactDetailsDto();

	private HttpHeaders headers = new HttpHeaders();

	private String userId = headers.getFirst("userId");

	@Before
	public void run() {
		contactDetailsDto.setEmployeeId("100");
		List<AddressDto> addressDtoList = new ArrayList<>();

		AddressDto addressDto1 = new AddressDto();
		addressDto1.setId("1");
		addressDto1.setDoorNo("34");
		addressDto1.setCity("vpm");
		addressDto1.setCountry("india");
		addressDto1.setLandMark("anna nagar");
		addressDto1.setPincode("34343");
		addressDto1.setStreet("kk nagar");
		addressDto1.setDistrict("vpm");
		addressDto1.setState("tn");

		AddressDto addressDto2 = new AddressDto();
		addressDto2.setId("1");
		addressDto2.setDoorNo("34");
		addressDto2.setCity("vpm");
		addressDto2.setCountry("india");
		addressDto2.setLandMark("anna nagar");
		addressDto2.setPincode("34343");
		addressDto2.setStreet("kk nagar");
		addressDto2.setDistrict("vpm");
		addressDto2.setState("tn");
		addressDtoList.add(addressDto2);
		addressDtoList.add(addressDto1);


		List<FamilyDetailsDto> familyDetailsDtoList = new ArrayList<>();
		FamilyDetailsDto familydetailsDto1 = new FamilyDetailsDto();
		familydetailsDto1.setAadhar("34434343");
		familydetailsDto1.setDob(new Date(1999 / 12 / 9));
		familydetailsDto1.setName("raj");
		familydetailsDto1.setRelation("father");

		FamilyDetailsDto familydetailsDto2 = new FamilyDetailsDto();
		familydetailsDto2.setAadhar("34434343");
		familydetailsDto2.setDob(new Date(1999 / 12 / 9));
		familydetailsDto2.setName("raj");
		familydetailsDto2.setRelation("father");

		familyDetailsDtoList.add(familydetailsDto2);
		familyDetailsDtoList.add(familydetailsDto1);
		contactDetailsDto.setFamilydetails(familyDetailsDtoList);
	}

	@Test
	public void createEmpContactDetails() {
		doNothing().when(contactDetailsService).createContactDetails("100", contactDetailsDto, userId);
		conactDetailsController.createContactDetais(headers, "100", contactDetailsDto);
	}

	@Test
	public void getParticularContactDetailsTest() {
		when(contactDetailsService.getParticularEmployeeContactDetails("100")).thenReturn(contactDetailsDto);
		conactDetailsController.getContactDetails("100");

	}

	@Test
	public void updateParticularContactDetailsTest() {
		conactDetailsController.updateParticularEmpContactDetails(userId, contactDetailsDto, headers);
	}


}