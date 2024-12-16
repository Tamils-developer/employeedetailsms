package com.adv.empdetailsms.exception;

public class EmployeeDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMessage;

	public EmployeeDetailsException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
