package com.adv.empdetailsms.controller;

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

import com.adv.empdetailsms.dto.EmpBankDetailsDto;
import com.adv.empdetailsms.service.EmpBankDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(path = "/employees/{employeeId}/bank-details")
public class BankDetailsController {
	@Autowired
	@Qualifier(value = "empBankDetailsServiceImpl")
	private EmpBankDetailsService empBankDetailsService;

	private static final Logger LOGGER = LogManager.getLogger(BankDetailsController.class);
	
//	@GetMapping(value = "/")
//	@Operation(summary = "Get all employee bank details")
//	public ResponseEntity<List<EmpBankDetailsDto>> getAllBankDetails() {
//		LOGGER.info("Get all employee bank details Request Initialized");
//		return new ResponseEntity<List<EmpBankDetailsDto>>(
//					empBankDetailsService.getAllBankDetails(), HttpStatus.OK);
//	}
	
	@PostMapping(value = "/")
	@Operation(summary = "Create employee bank details")
	public ResponseEntity<Void> registerEmpBankDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody EmpBankDetailsDto empBankDetailsDto, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Register Employee Bank Details :: employeeId : {} , bank detail: {} and userId : {} ", employeeId,
				empBankDetailsDto, userId);
		empBankDetailsService.registerEmpBankDetails(employeeId, empBankDetailsDto, userId);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping(value = "/")
	@Operation(summary = "Update employee bank detail")
	public ResponseEntity<Void> updateEmpBankDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody EmpBankDetailsDto empBankDetailsDto, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Update Employee bank Detail :: employeeId : {} and  bank detail : {} and userId : {}", employeeId,
				empBankDetailsDto, userId);
		empBankDetailsService.updateEmpBankDetails(employeeId, empBankDetailsDto, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{bankDetailId}")
	@Operation(summary = "Delete employee bank detail with bankDetailId")
	public ResponseEntity<Void> deleteEmpBankDetailsWithBankDetailId(
			@PathVariable(value = "bankDetailId") String bankDetailId, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Delete Employee bank Detail with bank detail id Request initialiazed :: bankDetailId : {} and userId : {} ",
				bankDetailId, userId);
		empBankDetailsService.deleteEmpBankDetailsWithBankDetailId(bankDetailId, userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/")
	@Operation(summary = "Get particular employee bank detail")
	public ResponseEntity<EmpBankDetailsDto> getParticularEmpBankDetail(
			@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get particular employee bank detail request initialized for employeeId : {}", employeeId);
		return new ResponseEntity<EmpBankDetailsDto>(empBankDetailsService.getParticularEmpBankDetail(employeeId),
				HttpStatus.OK);
	}
//	@DeleteMapping(value = "/{employeeId}")
//	@Operation(summary = "Delete employee bank details with employeeId")
//	public ResponseEntity<Void> deleteEmpBankDetailsWithEmpId(
//				@PathVariable(value = "employeeId") String employeeId,
//				@RequestHeader HttpHeaders headers) {
//		String userId = headers.getFirst("userId");
//		LOGGER.info("Delete Employee bank Detail with employee id Request initialiazed :: employeeId : {} and userId : {} ", employeeId ,userId);
//		empBankDetailsService.deleteEmpBankDetailsWithEmpId(employeeId, userId);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}
}
