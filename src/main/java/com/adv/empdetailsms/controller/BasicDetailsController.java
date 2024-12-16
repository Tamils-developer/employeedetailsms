package com.adv.empdetailsms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.BasicDetailsDto;
import com.adv.empdetailsms.service.BasicDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/employees")
@CrossOrigin(value = "http://localhost:3000")
public class BasicDetailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicDetailsController.class);

	@Autowired
	@Qualifier(value = "basicDetaisServiceImpl")
	private BasicDetailsService basicDetailsService;

	@PostMapping(value = "/")
	@Operation(summary = "Create a particular employee basic details")
	public ResponseEntity<String> createEmployee(@RequestHeader HttpHeaders headers,
			@RequestBody BasicDetailsDto basicDetailsDto) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Create Employee basic details :: userId :{} and  Employee basic details:{}", userId,
				basicDetailsDto);
		String createdEmpBasicDetailsId = basicDetailsService.createEmployee(basicDetailsDto, userId);
		LOGGER.info("Created Employee basic details Id :{}", createdEmpBasicDetailsId);
		return new ResponseEntity<String>(createdEmpBasicDetailsId, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{employeeid}")
	@Operation(summary = "Get a particular employee basic details")
	public ResponseEntity<BasicDetailsDto> getParticularEmployee(
			@PathVariable(value = "employeeid") String employeeId) {
		LOGGER.info("Request to get the particular employee id :{}", employeeId);
		BasicDetailsDto particularEmployee = basicDetailsService.getParticularEmployee(employeeId);
		LOGGER.info("Requested employee basic details :{}", particularEmployee);
		return new ResponseEntity<BasicDetailsDto>(particularEmployee, HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	@Operation(summary = "Get All employees basic details")
	public ResponseEntity<List<BasicDetailsDto> > getAllEmployees() {
		LOGGER.info("Request to get All the employees");
		List<BasicDetailsDto> AllEmployees = basicDetailsService.getAllEmployees();
		LOGGER.info("Requested employee basic details :{}", AllEmployees);
		return new ResponseEntity<List<BasicDetailsDto> >(AllEmployees, HttpStatus.OK);
	}

	@PutMapping(value = "/{employeeid}")
	@Operation(summary = "Update particular employee basic details")
	public ResponseEntity<Void> updateParticularEmployee(@RequestHeader HttpHeaders headers,
			@PathVariable(value = "employeeid") String employeeId, @RequestBody BasicDetailsDto basicDetailsDto) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Update request employee basic details ::userId:{} and employee id :{} and employee basic details :{}",
				userId, employeeId, basicDetailsDto);
		basicDetailsService.updateParticularEmpDetails(employeeId, basicDetailsDto, userId);
		LOGGER.info("Updated employee id :{}", employeeId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{employeeid}")
	@Operation(summary = "Delete a particular employee basic details")
	public ResponseEntity<Void> deleteParticularEmployee(@RequestHeader HttpHeaders headers,
			@PathVariable(value = "employeeid") String employeeId) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Delete Request :: userId :{} and employee id  :{}", userId, employeeId);
		basicDetailsService.deleteParticularEmployee(employeeId, userId);
		LOGGER.info("Deleted employee id :{}", employeeId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
