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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.DropDownDto;
import com.adv.empdetailsms.dto.NomineeDto;
import com.adv.empdetailsms.service.NomineeDetailsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/employees/{employeeId}/nominee-details")
@CrossOrigin(origins = "http://localhost:3000")
public class NomineeDetailsController {

	@Autowired
	@Qualifier(value = "nomineeDetailsServiceImpl")
	private NomineeDetailsService nomineeDetailsService;

	private static final Logger LOGGER = LogManager.getLogger(NomineeDetailsController.class);

	@GetMapping("/")
	@Operation(summary = "Get the Family details")
	public ResponseEntity<NomineeDto> getNomineeDetails(@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get the Family details for employee id : {} request initialized and familyDto : {}", employeeId,
				nomineeDetailsService.getNomineeDetails(employeeId));
		return new ResponseEntity<NomineeDto>(nomineeDetailsService.getNomineeDetails(employeeId), HttpStatus.OK);
	}

	@GetMapping("/dropdown-details")
	@Operation(summary = "Get the Dropdown details")
	public ResponseEntity<List<DropDownDto>> getDropDownDetails(@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Get the Family details for employee id : {} request initialized and familyDto : {}", employeeId,
				nomineeDetailsService.getDropDownDetails(employeeId));
		return new ResponseEntity<List<DropDownDto>>(nomineeDetailsService.getDropDownDetails(employeeId),
				HttpStatus.OK);
	}

	@PatchMapping("/")
	@Operation(summary = "Update the Nominee Details")
	public ResponseEntity<Void> updateNomineeDetails(@PathVariable(value = "employeeId") String employeeId,
			@RequestBody NomineeDto dto, @RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Update the Nominee Details for employee id : {} and NomineeDto : {} and userId : {} request initialized",
				employeeId, dto, userId);
		nomineeDetailsService.updateNomineeDetails(dto, employeeId, userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/{nomineeId}")
	@Operation(summary = "Delete the Nominee Details")
	public ResponseEntity<Void> deleteNomineeDetails(@PathVariable(value = "nomineeId") String nomineeId,@RequestHeader HttpHeaders headers) {
		String userId = headers.getFirst("userId");
		LOGGER.info(
				"Delete the Nominee Details for employee id : {} and userId : {} request initialized",
				nomineeId, userId);
		nomineeDetailsService.deleteNomineeDetails(nomineeId, userId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}