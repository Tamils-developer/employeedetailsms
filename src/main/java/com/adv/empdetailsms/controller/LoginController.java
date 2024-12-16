package com.adv.empdetailsms.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adv.empdetailsms.dto.LoginDto;
import com.adv.empdetailsms.service.LoginService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping(path = "/employees/login")
public class LoginController {

	@Autowired
	@Qualifier(value = "loginServiceImpl")
	private LoginService loginService;

	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@PostMapping(value = "/")
	@Operation(summary = "Get all employee bank details")
	public ResponseEntity<String> checkCredentialsOfUser(@RequestBody LoginDto loginDto) {
		LOGGER.info("Get all employee bank details Request Initialized");
	String userId=	loginDto.getCandidateId();
	String password = loginDto.getPassword();
		return new ResponseEntity<String>(
					loginService.checkCredentials(userId, password), HttpStatus.OK);
	}
}
