package com.autogeneral.test.api.AutoGeneralTestAPI.exception;

/**
 * 
 * Custom Exception class to handle error cases when the input description/ text string in the 
 * request is invalid(null, empty or greater than 50 chars)
 *
 */
public class ValidationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private String message;
	private String value;

	public ValidationException(String message, String value) {
		this.message = message;
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public String getValue() {
		return value;
	}
	
	

}