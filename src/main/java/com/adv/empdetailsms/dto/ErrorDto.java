package com.adv.empdetailsms.dto;

public class ErrorDto {

	private String errorCode;

	private String errorMessage;

	public ErrorDto(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorDto [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

}