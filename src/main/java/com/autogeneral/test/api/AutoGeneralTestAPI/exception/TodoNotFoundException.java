package com.autogeneral.test.api.AutoGeneralTestAPI.exception;

/**
 * 
 * Custom Exception class to handle error cases when a specified/ requested todo object
 * is not found in the application.
 *
 */
public class TodoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1773719013613496347L;

	public TodoNotFoundException(String exception) {
		super(exception);
	}

}