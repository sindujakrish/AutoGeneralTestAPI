package com.autogeneral.test.api.AutoGeneralTestAPI.model;

import java.util.Map;

/**
 * 
 * The data transfer object which holds the details to represent a specific exception scenario within the 
 * application when the todo object looked up using an ID is not found.
 *
 */
public class TodoItemNotFoundError {

	private Map<String, String> details;
	private String name;
	
	public TodoItemNotFoundError() {
		super();
	}

	public TodoItemNotFoundError(Map<String, String> details, String name) {
		super();
		this.details = details;
		this.name = name;
	}

	
	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
