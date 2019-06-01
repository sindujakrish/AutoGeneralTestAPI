package com.autogeneral.test.api.AutoGeneralTestAPI.model;

/**
 * 
 * The data transfer object which holds the details to represent a specific exception scenario within the 
 * application when the details entered in the request message is null or empty or exceeds 50 characters.
 *
 */
public class TodoItemValidationError {
	  private Details details;
	  private String name;
	  
	  
	  public TodoItemValidationError(Details details, String name) {
	    super();
	    this.details = details;
	    this.name = name;
	  }
	  
	  public Details getDetails() {
	    return details;
	  }
	  
	  public String getName() {
	    return name;
	  }
	}
