package com.adv.empdetailsms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.OrganizationDetailsDto;
import com.adv.empdetailsms.service.OrganizationDetailsService;
import com.adv.empdetailsms.service.impl.OrganizationDetailsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/employees/{employeeId}/organization-details")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganizationDetailsController {

	@Autowired
	@Qualifier(value = "experienceDetailsServiceImpl")
	private OrganizationDetailsService experienceDetailsService;

	private static final Logger LOGGER = LogManager.getLogger(OrganizationDetailsServiceImpl.class);

	@PostMapping("/")
	@Operation(summary = "create a employee experience details")
	public ResponseEntity<Void> createOrganizationDetails(@RequestHeader HttpHeaders headers,
			@RequestBody List<OrganizationDetailsDto> organizationDetailsDtoList,
			@PathVariable(value = "employeeId") String employeeId) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Create Employee Experience Details :: EmpId : {} and Experience details List : {} and userId : {}",
				employeeId, organizationDetailsDtoList, userId);
		experienceDetailsService.createOrganizationDetails(organizationDetailsDtoList, employeeId, userId);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PatchMapping("/")
	@Operation(summary = "updating employee experience details")
	public ResponseEntity<Void> updateParticularEmpOrgDetails(@RequestHeader HttpHeaders headers,
			@RequestBody List<OrganizationDetailsDto> organizationDetailsDtoList,
			@PathVariable(value = "employeeId") String employeeId) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Update Employee Experience Details :: EmpId : {} and organizationDetailsDtoList :{} and userId : {} ",
				employeeId, organizationDetailsDtoList, userId);
		experienceDetailsService.updateParticularEmpOrgDetails(organizationDetailsDtoList, employeeId, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/")
	@Operation(summary = "get employee experience details")
	public ResponseEntity<List<OrganizationDetailsDto>> getParticularEmpOrgDetails(
			@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get Employee Experience Details :: EmpId : {} ", employeeId);
		return new ResponseEntity<List<OrganizationDetailsDto>>(
				experienceDetailsService.getParticularEmpOrgDetails(employeeId), HttpStatus.OK);
	}

	@DeleteMapping("/{organizationId}")
	@Operation(summary = "deleting employee experience details")
	public ResponseEntity<Void> deleteParticularEmpOrgDetails(@RequestHeader HttpHeaders headers,
			@PathVariable(value = "organizationId") String organizationId) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Delete Employee organisation Details :: EmpId : {} and userId : {}", organizationId, userId);
		experienceDetailsService.deleteParticularEmpOrgDetails(organizationId, userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}