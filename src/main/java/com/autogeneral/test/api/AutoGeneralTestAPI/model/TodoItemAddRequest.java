package com.autogeneral.test.api.AutoGeneralTestAPI.model;

import javax.validation.constraints.Size;

/**
 * 
 * The data transfer object which holds the details as part of the add Todo API request 
 * to the application, containing the description text to be added to the Todo object.
 *
 */
public class TodoItemAddRequest {
	
	@Size(min=1, max = 50)
	private String text;
	
	public TodoItemAddRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TodoItemAddRequest(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	

}
