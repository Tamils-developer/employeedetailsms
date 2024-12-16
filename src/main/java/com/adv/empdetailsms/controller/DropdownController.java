
package com.adv.empdetailsms.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.BankDto;
import com.adv.empdetailsms.dto.DegreeTypeDto;
import com.adv.empdetailsms.dto.DepartmentDto;
import com.adv.empdetailsms.dto.EmpPersonalDetailsDto;
import com.adv.empdetailsms.dto.RoleDto;
import com.adv.empdetailsms.service.DropdownService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(path = "/employees/")
public class DropdownController {

	@Autowired
	@Qualifier(value = "dropdownServiceImpl")
	private DropdownService dropdownService;

	private static final Logger LOGGER = LogManager.getLogger(DropdownController.class);

	@GetMapping(value = "/degree-types")
	@Operation(summary = "Get all degree Types")
	public ResponseEntity<List<DegreeTypeDto>> getAllDegreeTypes() {
		LOGGER.info("Get all DegreeTypes Request Initialized");
		return new ResponseEntity<List<DegreeTypeDto>>(dropdownService.getAllDegreeTypes(), HttpStatus.OK);
	}

	@GetMapping(value = "/department-types")
	@Operation(summary = "Get all Department Types")
	public ResponseEntity<List<DepartmentDto>> getAllDepartmentTypes() {
		LOGGER.info("Get all DepartmentTypes Request Initialized");
		return new ResponseEntity<List<DepartmentDto>>(dropdownService.getAllDepartment(), HttpStatus.OK);
	}

	@GetMapping(value = "/bank-names")
	@Operation(summary = "Get all bank names Request Initialized")
	public ResponseEntity<List<BankDto>> getAllBankNames() {
		LOGGER.info("Get all Bank names Request Initialized");
		return new ResponseEntity<List<BankDto>>(dropdownService.getAllBankNames(), HttpStatus.OK);
	}

	@GetMapping("/role")
	@Operation(summary = "Get all Employee role Request Initialized")
	public ResponseEntity<List<RoleDto>> getDropdownDetails() {
		LOGGER.info("Get Employee role Details ");
		return new ResponseEntity<List<RoleDto>>(dropdownService.getDropdownDetails(), HttpStatus.OK);
	}

	@GetMapping("/personal-details")
	@Operation(summary = "Get all personal details Request Initialized")
	public ResponseEntity<EmpPersonalDetailsDto> getAllPersonalDetail() {
		LOGGER.info("Get Employee personal Details ");
		return new ResponseEntity<EmpPersonalDetailsDto>(dropdownService.getAllPersonalDetails(), HttpStatus.OK);
	}

	@GetMapping("/state-and-district")
	@Operation(summary = "Get all state and district Request Initialized")
	public ResponseEntity<Map<String, Map<String, List<String>>>> getStateAndCountry() {
		LOGGER.info("Get Employee state and district Details ");
		return new ResponseEntity<Map<String, Map<String, List<String>>>>(dropdownService.getStateAndCountry(),
				HttpStatus.OK);
	}
}