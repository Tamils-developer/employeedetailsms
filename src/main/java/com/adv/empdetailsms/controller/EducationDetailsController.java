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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.EmpEducationalDetailDto;
import com.adv.empdetailsms.service.EmpEducationDetailService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(path = "/employees/{employeeId}/education-details")
public class EducationDetailsController {

	@Autowired
	@Qualifier(value = "empEducationDetailServiceImpl")
	private EmpEducationDetailService empEducationDetailService;

	private final static Logger LOGGER = LogManager.getLogger(EducationDetailsController.class);

//	@GetMapping(value = "/")
//	@Operation(summary = "Get all employee education details")
//	public ResponseEntity<List<EmpEducationalDetailDto>> getEmpEduDetails() {
//		LOGGER.info("Get all employee education details request initialized ");
//		return new ResponseEntity<List<EmpEducationalDetailDto>>(
//					empEducationDetailService.getEmpEduDetails(), HttpStatus.OK);
//	}

	@PostMapping(value = "/")
	@Operation(summary = "Create employee education details")
	public ResponseEntity<Void> createEmpEduDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody List<EmpEducationalDetailDto> empEducationalDetailDtos, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Register Employee Educational Details :: employeeId : {} and Educational details List : {} and userId : {}",
				employeeId, empEducationalDetailDtos, userId);
		empEducationDetailService.registerEmpEduDetails(employeeId, empEducationalDetailDtos, userId);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

//	@DeleteMapping(value = "/education-detail/{employeeId}")
//	@Operation(summary = "Delete employee education details")
//	public ResponseEntity<Void> delEmpEduDetails(
//				@PathVariable(value = "employeeId") String employeeId,
//				@RequestHeader HttpHeaders headers) {
//		String userId = headers.getFirst("userId");
//		LOGGER.info("Delete Employee Educational Details Request initialiazed :: employeeId : {} and userId : {} ", employeeId, userId);
//		empEducationDetailService.delEmpEduDetails(employeeId, userId);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}

	@PutMapping(value = "/")
	@Operation(summary = "Update employee education details")
	public ResponseEntity<Void> updateEmpEduDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody List<EmpEducationalDetailDto> empEducationalDetailDtos, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Update Employee Educational Details :: employeeId : {} and Educational details List : {} and userId : {}",
				employeeId, empEducationalDetailDtos, userId);
		empEducationDetailService.updParticularEmpEduDetails(employeeId, empEducationalDetailDtos, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{educationDetailId}")
	@Operation(summary = "Delete particular education detail")
	public ResponseEntity<Void> delParticularEduDetail(
			@PathVariable(value = "educationDetailId") String educationDetailId, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Delete Employee Educational Details Request initialiazed :: educationDetailId : {} and userId : {} ",
				educationDetailId, userId);
		empEducationDetailService.delParticularEduDetails(educationDetailId, userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/")
	@Operation(summary = "Get particular employee education details")
	public ResponseEntity<List<EmpEducationalDetailDto>> getparticularEmpEduDetails(
			@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get particular employee education details request initialized for employeeId : {}", employeeId);
		return new ResponseEntity<List<EmpEducationalDetailDto>>(
				empEducationDetailService.getParticularEmpEduDetails(employeeId), HttpStatus.OK);
	}
}