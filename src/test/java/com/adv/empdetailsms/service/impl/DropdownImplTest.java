package com.adv.empdetailsms.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.adv.empdetailsms.dto.MaritalStatusDto;
import com.adv.empdetailsms.entity.BloodGroupEntity;
import com.adv.empdetailsms.entity.GenderEntity;
import com.adv.empdetailsms.entity.MaritalStatusEntity;
import com.adv.empdetailsms.entity.RoleEntity;
import com.adv.empdetailsms.entity.StateAndCountryEntity;
import com.adv.empdetailsms.repository.BloodGroupRepository;
import com.adv.empdetailsms.repository.GenderRepository;
import com.adv.empdetailsms.repository.MaritalStatusRepository;
import com.adv.empdetailsms.repository.RoleRepository;
import com.adv.empdetailsms.repository.StateAndCountryRepository;

@RunWith(MockitoJUnitRunner.class)
public class DropdownImplTest {

	@InjectMocks
	DropdownServiceImpl dropdownServiceImpl = new DropdownServiceImpl();

	@Mock
	private StateAndCountryRepository stateAndCountryRepository;

	@Mock
	private BloodGroupRepository bloodGroupRepository;

	@Mock
	private MaritalStatusRepository maritalStatusRepository;

	@Mock
	private GenderRepository genderRepository;

	@Mock
	private RoleRepository roleRepository;

	StateAndCountryEntity stateAndCountryEntity = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity2 = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity3 = new StateAndCountryEntity();
	StateAndCountryEntity stateAndCountryEntity4 = new StateAndCountryEntity();
	RoleEntity roleEntity = new RoleEntity();
	MaritalStatusEntity maritalStatusEntity = new MaritalStatusEntity();
	MaritalStatusDto dto = new MaritalStatusDto();
	GenderEntity genderEntity = new GenderEntity();
	BloodGroupEntity bloodGroupEntity = new BloodGroupEntity();

	private List<StateAndCountryEntity> stateCountryList = new ArrayList<StateAndCountryEntity>();
	private List<RoleEntity> roleEntityList = new ArrayList<RoleEntity>();
	private List<MaritalStatusEntity> maritalStatusEntityList = new ArrayList<MaritalStatusEntity>();
	private List<GenderEntity> genderEntityList = new ArrayList<GenderEntity>();
	private List<BloodGroupEntity> bloodGroupEntityList = new ArrayList<BloodGroupEntity>();

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

		roleEntity.setId(100);
		roleEntity.setRole("developer");
		roleEntityList.add(roleEntity);

		maritalStatusEntity.setId("12");
		maritalStatusEntity.setIsDeleted(false);
		maritalStatusEntity.setMartialStatus("married");
		maritalStatusEntityList.add(maritalStatusEntity);

		genderEntity.setGender("male");
		genderEntity.setId("100");
		genderEntity.setIsDeleted(false);
		genderEntityList.add(genderEntity);

		bloodGroupEntity.setId("100");
		bloodGroupEntity.setIsDeleted(false);
		bloodGroupEntity.setBloodGroup("A+");
		bloodGroupEntityList.add(bloodGroupEntity);
	}

	@Test
	public void stateCountryDropdownTestCase() {
		when(stateAndCountryRepository.findAll()).thenReturn(stateCountryList);
		dropdownServiceImpl.getStateAndCountry();
	}

	@Test
	public void dropdownDetailsTestCase() {
		dropdownServiceImpl.getDropdownDetails();
	}

	
	@Test
	public void dropdownTestCase2() {
		when(roleRepository.findAll()).thenReturn(roleEntityList);
		dropdownServiceImpl.getDropdownDetails();
	}

	@Test
	public void dropdownBloodGroupTestCase1() {
		when(maritalStatusRepository.findByIsDeleted(false)).thenReturn(maritalStatusEntityList);
		dropdownServiceImpl.getAllPersonalDetails();
	}

	@Test
	public void dropdownBloodGroupTestCase2() {
		when(genderRepository.findByIsDeleted(false)).thenReturn(genderEntityList);
		dropdownServiceImpl.getAllPersonalDetails();
	}

	@Test
	public void dropdownBloodGroupTestCase3() {
		when(bloodGroupRepository.findByIsDeleted(false)).thenReturn(bloodGroupEntityList);
		dropdownServiceImpl.getAllPersonalDetails();
	}

}