package com.adv.empdetailsms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adv.empdetailsms.dto.BankDto;
import com.adv.empdetailsms.dto.BloodGroupDto;
import com.adv.empdetailsms.dto.DegreeTypeDto;
import com.adv.empdetailsms.dto.DepartmentDto;
import com.adv.empdetailsms.dto.EmpPersonalDetailsDto;
import com.adv.empdetailsms.dto.GenderDto;
import com.adv.empdetailsms.dto.MaritalStatusDto;
import com.adv.empdetailsms.dto.RoleDto;
import com.adv.empdetailsms.entity.BankEntity;
import com.adv.empdetailsms.entity.BloodGroupEntity;
import com.adv.empdetailsms.entity.DegreeTypeEntity;
import com.adv.empdetailsms.entity.DepartmentEntity;
import com.adv.empdetailsms.entity.GenderEntity;
import com.adv.empdetailsms.entity.MaritalStatusEntity;
import com.adv.empdetailsms.entity.RoleEntity;
import com.adv.empdetailsms.entity.StateAndCountryEntity;
import com.adv.empdetailsms.repository.BankNameRepository;
import com.adv.empdetailsms.repository.BloodGroupRepository;
import com.adv.empdetailsms.repository.DegreeTypeRepository;
import com.adv.empdetailsms.repository.DepartmentRepository;
import com.adv.empdetailsms.repository.GenderRepository;
import com.adv.empdetailsms.repository.MaritalStatusRepository;
import com.adv.empdetailsms.repository.RoleRepository;
import com.adv.empdetailsms.repository.StateAndCountryRepository;
import com.adv.empdetailsms.service.DropdownService;

@Service(value = "dropdownServiceImpl")
public class DropdownServiceImpl implements DropdownService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MaritalStatusRepository maritalStatusRepository;

	@Autowired
	private GenderRepository genderRepository;

	@Autowired
	private DegreeTypeRepository degreeTypeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private BankNameRepository bankNameRepository;

	@Autowired
	private BloodGroupRepository bloodGroupRepository;
	@Autowired
	private StateAndCountryRepository stateAndCountryRepository;

	private static final Logger LOGGER = LogManager.getLogger(DropdownServiceImpl.class);

	@Override
	public EmpPersonalDetailsDto getAllPersonalDetails() {
		LOGGER.info("Inside DropdownServiceImpl : getAllPersonalDetails()");

		EmpPersonalDetailsDto dropdownDto = new EmpPersonalDetailsDto();
		List<MaritalStatusEntity> maritalStatusEntitiyList = maritalStatusRepository.findByIsDeleted(false);
		LOGGER.info("maritalStatusEntitiyList response" + maritalStatusEntitiyList);
		List<MaritalStatusDto> maritalStatusDtoList = new ArrayList<MaritalStatusDto>();
		for (MaritalStatusEntity maritalStatusEntity : maritalStatusEntitiyList) {
			MaritalStatusDto maritalStatusDto = new MaritalStatusDto();
			maritalStatusDto.setId(maritalStatusEntity.getId());
			maritalStatusDto.setMaritalStatus(maritalStatusEntity.getMartialStatus());
			maritalStatusDtoList.add(maritalStatusDto);
		}
		List<GenderEntity> genderEntitiyList = genderRepository.findByIsDeleted(false);
		LOGGER.info("genderEntityList response" + genderEntitiyList);
		List<GenderDto> genderDtoList = new ArrayList<GenderDto>();
		for (GenderEntity genderEntity : genderEntitiyList) {
			GenderDto genderDto = new GenderDto();
			genderDto.setId(genderEntity.getId());
			genderDto.setGender(genderEntity.getGender());
			genderDtoList.add(genderDto);
		}
		List<BloodGroupEntity> bloodGroupEntityList = bloodGroupRepository.findByIsDeleted(false);
		LOGGER.info("BloodGroupEntityList response" + bloodGroupEntityList);
		List<BloodGroupDto> bloodGroupDtoList = new ArrayList<BloodGroupDto>();
		for (BloodGroupEntity bloodGroupEntity : bloodGroupEntityList) {
			BloodGroupDto bloodGroupDto = new BloodGroupDto();
			bloodGroupDto.setId(bloodGroupEntity.getId());
			bloodGroupDto.setBloodGroup(bloodGroupEntity.getBloodGroup());
			bloodGroupDtoList.add(bloodGroupDto);
		}
		dropdownDto.setBloodGroupList(bloodGroupDtoList);
		dropdownDto.setGenderList(genderDtoList);
		dropdownDto.setMaritalStatusList(maritalStatusDtoList);
		LOGGER.info("bankDtoList response" + dropdownDto);
		return dropdownDto;
	}

	@Override
	public List<RoleDto> getDropdownDetails() {
		List<RoleEntity> roleEntityList = roleRepository.findAll();
		LOGGER.info("roleEntityList response" + roleEntityList);
		Set<RoleDto> roleDtosSet = new TreeSet<>();
		if (!roleEntityList.isEmpty()) {
			for (RoleEntity roleEntity2 : roleEntityList) {
				RoleDto dto = new RoleDto();
				dto.setId(roleEntity2.getId());
				dto.setRole(roleEntity2.getRole());
				roleDtosSet.add(dto);
			}
		}
		List<RoleDto> roleDtos = new ArrayList<>();
		roleDtos.addAll(roleDtosSet);
		LOGGER.info("roleDtos response" + roleDtos);
		return roleDtos;
	}

	@Override
	public List<DegreeTypeDto> getAllDegreeTypes() {

		LOGGER.info("Inside DropdownServiceImpl : getAllDegreeTypes()");

		List<DegreeTypeEntity> degreeTypeEntitiyList = degreeTypeRepository.findByIsDeleted(false);
		LOGGER.info("degreeTypeEntityList response" + degreeTypeEntitiyList);
		List<DegreeTypeDto> degreeTypeDtoList = new ArrayList<DegreeTypeDto>();
		for (DegreeTypeEntity degreeTypeEntity : degreeTypeEntitiyList) {
			DegreeTypeDto degreeTypeDto = new DegreeTypeDto();
			degreeTypeDto.setId(degreeTypeEntity.getId());
			degreeTypeDto.setDegreeType(degreeTypeEntity.getDegreeType());
			degreeTypeDtoList.add(degreeTypeDto);
		}
		LOGGER.info("degreeTypeDtoList response" + degreeTypeDtoList.toString());
		return degreeTypeDtoList;
	}

	@Override
	public List<DepartmentDto> getAllDepartment() {

		LOGGER.info("Inside DropdownServiceImpl : getAllDepartment()");

		List<DepartmentEntity> departmentEntityList = departmentRepository.findByIsDeleted(false);
		LOGGER.info("departmentEntityList response" + departmentEntityList);
		List<DepartmentDto> departmentDtolist = new ArrayList<DepartmentDto>();
		for (DepartmentEntity departmentEntity : departmentEntityList) {
			DepartmentDto departmentDto = new DepartmentDto();
			departmentDto.setId(departmentEntity.getId());
			departmentDto.setDepartment(departmentEntity.getDepartment());
			departmentDtolist.add(departmentDto);
		}
		LOGGER.info("departmentDtolist response" + departmentDtolist.toString());
		return departmentDtolist;
	}

	@Override
	public List<BankDto> getAllBankNames() {
		LOGGER.info("Inside DropdownServiceImpl : getAllBankNames()");

		List<BankEntity> bankEntityList = bankNameRepository.findByIsDeleted(false);
		LOGGER.info("bankEntityList response" + bankEntityList);
		List<BankDto> bankDtoList = new ArrayList<BankDto>();
		for (BankEntity bankEntity : bankEntityList) {
			BankDto bankDto = new BankDto();
			bankDto.setId(bankEntity.getId());
			bankDto.setBankName(bankEntity.getBankName());
			bankDtoList.add(bankDto);
		}
		LOGGER.info("bankDtoList response" + bankDtoList);
		return bankDtoList;
	}

	@Override
	public Map<String, Map<String, List<String>>> getStateAndCountry() {
		LOGGER.info("Inside DropdownServiceImpl : getStateAndCountry()");
		List<StateAndCountryEntity> stateAndCountry = stateAndCountryRepository.findAll();
		Map<String, Map<String, List<String>>> countryMap = new HashMap<>();
		for (StateAndCountryEntity stateAndCountryEntity : stateAndCountry) {
			String country = stateAndCountryEntity.getCountry();
			String state = stateAndCountryEntity.getState();
			String district = stateAndCountryEntity.getDistrict();
			if (countryMap.containsKey(country)) {
				Map<String, List<String>> states =countryMap.get(country);
				if (states.containsKey(state)) {
					List<String> districtList = states.get(state);
					districtList.add(district);
					states.put(state, districtList);
				} else {
					List<String> districtList = new ArrayList<>();
					districtList.add(district);
					states.put(state, districtList);
				}
			} else {
				Map<String, List<String>> states = new HashMap<>();
				List<String> districtList = new ArrayList<>();
				districtList.add(district);
				states.put(state, districtList);
				countryMap.put(country, states);
			}
		}
		return countryMap;
	}
}