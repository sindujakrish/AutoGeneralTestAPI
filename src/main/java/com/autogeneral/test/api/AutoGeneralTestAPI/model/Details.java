package com.autogeneral.test.api.AutoGeneralTestAPI.model;

/**
 * 
 * A sub class within the data transfer object which contains details about an exception scenario within 
 * the application
 *
 */
public class Details {

	private String location;
	private String param;
	private String msg;
	private String value;
	
	public Details(String location, String param, String msg, String value) {
		super();
		this.location = location;
		this.param = param;
		this.msg = msg;
		this.value = value;
	}

	public String getLocation() {
		return location;
	}

	public String getParam() {
		return param;
	}

	public String getMsg() {
		return msg;
	}

	public String getValue() {
		return value;
	}
	
	
}
