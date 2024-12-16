package com.adv.empdetailsms.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import com.adv.empdetailsms.dto.BloodGroupDto;
import com.adv.empdetailsms.dto.EmpPersonalDetailsDto;
import com.adv.empdetailsms.dto.GenderDto;
import com.adv.empdetailsms.dto.MaritalStatusDto;
import com.adv.empdetailsms.dto.RoleDto;
import com.adv.empdetailsms.entity.StateAndCountryEntity;
import com.adv.empdetailsms.service.DropdownService;

@RunWith(MockitoJUnitRunner.class)
public class DropDownControllerTest {

	@InjectMocks
	private DropdownController dropdownController;
	
	@Mock
	private DropdownService dropdownService;
	
	@Mock
	private HttpHeaders headers;
	
	StateAndCountryEntity stateAndCountryEntity = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity2 = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity3 = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity4 = new StateAndCountryEntity();
	RoleDto roleDto = new RoleDto();
	MaritalStatusDto maritalStatusDto = new MaritalStatusDto();
	MaritalStatusDto dto = new MaritalStatusDto();
	GenderDto genderDto = new GenderDto();
	BloodGroupDto bloodGroupDto = new BloodGroupDto();

	private List<StateAndCountryEntity> stateCountryList = new ArrayList<StateAndCountryEntity>();
	private List<RoleDto> roleDtoList = new ArrayList<RoleDto>();
	private List<MaritalStatusDto> maritalStatusDtoList = new ArrayList<MaritalStatusDto>();
	private List<GenderDto> genderDtoList = new ArrayList<GenderDto>();
	private List<BloodGroupDto> bloodGroupDtoList = new ArrayList<BloodGroupDto>();
	private EmpPersonalDetailsDto detailsDto = new EmpPersonalDetailsDto();

	@Before
	public void run() {

		stateAndCountryEntity.setId(100);
		stateAndCountryEntity.setState("tamil nadu");
		stateAndCountryEntity.setCountry("india");
		stateAndCountryEntity.setDistrict("vpm");
		stateCountryList.add(stateAndCountryEntity);

		stateAndCountryEntity2.setId(100);
		stateAndCountryEntity2.setState("op");
		stateAndCountryEntity2.setCountry("india");
		stateAndCountryEntity2.setDistrict("villupuram");
		stateCountryList.add(stateAndCountryEntity2);

		stateAndCountryEntity2.setId(100);
		stateAndCountryEntity.setState("tamil nadu");
		stateAndCountryEntity2.setCountry("india");
		stateAndCountryEntity2.setDistrict("villupuram");
		stateCountryList.add(stateAndCountryEntity2);

		stateAndCountryEntity3.setId(100);
		stateAndCountryEntity3.setState("agra");
		stateAndCountryEntity3.setCountry("pakistan");
		stateAndCountryEntity3.setDistrict("chennai");
		stateCountryList.add(stateAndCountryEntity3);

		stateAndCountryEntity4.setId(100);
		stateAndCountryEntity4.setState("ap");
		stateAndCountryEntity4.setCountry("pakistan");
		stateAndCountryEntity4.setDistrict("theni");
		stateCountryList.add(stateAndCountryEntity4);

		roleDto.setId(100);
		roleDto.setRole("developer");
		roleDtoList.add(roleDto);
		
		maritalStatusDto.setId("12");
		maritalStatusDto.setMaritalStatus("married");
		maritalStatusDtoList.add(maritalStatusDto);
	
		genderDto.setGender("male");
		genderDto.setId("100");
		genderDtoList.add(genderDto);

		bloodGroupDto.setId("100");
		bloodGroupDto.setBloodGroup("A+");
		bloodGroupDtoList.add(bloodGroupDto);
				
		detailsDto.setBloodGroupList(bloodGroupDtoList);
		detailsDto.setGenderList(genderDtoList);
		detailsDto.setMaritalStatusList(maritalStatusDtoList);

	}
	
	@Test
	public void testcase1() {
		when(dropdownService.getDropdownDetails()).thenReturn(roleDtoList);
		dropdownController.getDropdownDetails();
	}
	
	@Test
	public void testcase2() {
		when(dropdownService.getAllPersonalDetails()).thenReturn(detailsDto);
		dropdownController.getAllPersonalDetail();
	}
	
	@Test
	public void testcase3() {
		Map<String, Map<String, List<String>>> map = new HashMap<>();
		when(dropdownService.getStateAndCountry()).thenReturn(map);
		dropdownController.getStateAndCountry();
	}
	
}
