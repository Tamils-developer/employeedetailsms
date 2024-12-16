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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.AddressDto;
import com.adv.empdetailsms.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/employees")
@CrossOrigin(value = "http://localhost:3000")
public class AddressController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BasicDetailsController.class);

	@Autowired
	@Qualifier(value = "addressServiceImpl")
	private AddressService addressService;

	@GetMapping(value = "/{employeeId}/addresses/")
	@Operation(summary = "Get a particular employee addresses")
	public ResponseEntity<List<AddressDto>> getAllAddressesOfParticularEmp(
				@RequestHeader HttpHeaders headers,
				@PathVariable(value = "employeeId") String employeeId) {
		LOGGER.info("Request to get the particular employee All addresses :{}", employeeId);
		String userId = headers.getFirst("userId");
		List<AddressDto> addressDtos = addressService
					.getAllAddressesOfParticularEmp(employeeId, userId);
		LOGGER.info("Requested employee Addresses :{}", addressDtos);
		return new ResponseEntity<List<AddressDto>>(addressDtos, HttpStatus.OK);
	}

	@DeleteMapping(value = "/addresses/{addressid}")
	@Operation(summary = "Delete a particular address details")
	public ResponseEntity<Void> deleteParticularAddress(@RequestHeader HttpHeaders headers,
				@PathVariable(value = "addressid") String addressId) {
		String userId = headers.getFirst("userId");
		LOGGER.info("Delete Request :: userId :{} and address id  :{}", userId, addressId);
		addressService.deleteParticularAddress(addressId, userId);
		LOGGER.info("Deleted address id :{}", addressId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}