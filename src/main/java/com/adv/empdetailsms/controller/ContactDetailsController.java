package com.adv.empdetailsms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.ContactDetailsDto;
import com.adv.empdetailsms.service.ContactDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/employees/{employeeId}/contact-details")
@CrossOrigin(value = "http://localhost:3000/")
public class ContactDetailsController {

	private final static Logger LOGGER = LogManager.getLogger(ContactDetailsController.class);

	@Autowired
	@Qualifier(value = "contactDetaisServiceImpl")
	private ContactDetailsService contactDetailsService;

	@PostMapping(value = "/")
	@Operation(summary = "Create the employee contact  details")
	public ResponseEntity<Void> createContactDetais(@RequestHeader HttpHeaders headers,
			@PathVariable(value = "employeeId") String employeeId, @RequestBody ContactDetailsDto contactDetailsDto) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Create the Employee Contact Details request initialized with userId : {}, employeeId : {} and contactDetailsDTO : {}",
				userId, employeeId, contactDetailsDto);
		contactDetailsService.createContactDetails(employeeId, contactDetailsDto, userId);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping(value = "/")
	@Operation(summary = "Get the contact details of the particular employee ")
	public ResponseEntity<ContactDetailsDto> getContactDetails(@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get Employee Contact Details request initialized :: employeeId:{}", employeeId);
		return new ResponseEntity<ContactDetailsDto>(
				contactDetailsService.getParticularEmployeeContactDetails(employeeId), HttpStatus.OK);
	}

	@PatchMapping(value = "/")
	@Operation(summary = "Update the contact details of the specific employee")
	public ResponseEntity<Void> updateParticularEmpContactDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody ContactDetailsDto contactDetailsDto, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Update the Employee Contact Details ::userId : {}, employeeId : {}  and contactDetailsDTO : {} ",
				userId, employeeId, contactDetailsDto);
		contactDetailsService.updatePartiEmpContactDtls(employeeId, contactDetailsDto, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}