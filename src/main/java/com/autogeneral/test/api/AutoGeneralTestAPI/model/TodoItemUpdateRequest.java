package com.autogeneral.test.api.AutoGeneralTestAPI.model;

/**
 * 
 * The data transfer object which holds the details as part of the update Todo API request 
 * to the application, containing the description text and the isCompleted boolean value to 
 * be updated onto the existing Todo object.
 *
 */
public class TodoItemUpdateRequest {
	
	private String text;
	private  boolean isCompleted;
	
	public TodoItemUpdateRequest() {
		super();
	}
	
	public TodoItemUpdateRequest(String text, boolean isCompleted) {
		super();
		this.text = text;
		this.isCompleted = isCompleted;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isCompleted() {
		return isCompleted;
	}
	
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	
}
