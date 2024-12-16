package com.adv.empdetailsms.errorhandlercontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.adv.empdetailsms.dto.ErrorDto;
import com.adv.empdetailsms.exception.EmployeeDetailsException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = EmployeeDetailsException.class )
	public ResponseEntity<ErrorDto> noSuchEmployeeIdExists(EmployeeDetailsException employeeDetailsException) {
		String errorCode = employeeDetailsException.getErrorCode();
		String errorMessage = employeeDetailsException.getErrorMessage();
		ErrorDto errorDto = new ErrorDto(errorCode, errorMessage);
		return new ResponseEntity<ErrorDto>(errorDto, HttpStatus.BAD_REQUEST);
	}

}
