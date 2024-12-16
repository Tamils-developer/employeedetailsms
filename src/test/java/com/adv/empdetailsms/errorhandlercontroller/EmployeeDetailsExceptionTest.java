package com.adv.empdetailsms.errorhandlercontroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.adv.empdetailsms.dto.ErrorDto;
import com.adv.empdetailsms.exception.EmployeeDetailsException;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDetailsExceptionTest {
	
	@InjectMocks
	private GlobalExceptionHandler exceptionHandler;

	@Test
	public void testNoSuchEmployeeIdException() {
		EmployeeDetailsException employeeDetailsException=new EmployeeDetailsException("EC04", "No Such Employee Id Exsist");
		String errorCode=employeeDetailsException.getErrorCode();
		String errorMessgae=employeeDetailsException.getErrorMessage();
		ErrorDto errorDto=new ErrorDto(errorCode, errorMessgae);
		exceptionHandler.noSuchEmployeeIdExists(employeeDetailsException);
	}
}
